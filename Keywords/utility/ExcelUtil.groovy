package utility

import java.io.File
import java.io.FileInputStream

import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook

class ExcelUtil {

	/**
	 * Get the most recently modified file from a folder with optional extension filter
	 * @param folderPath Path to the folder
	 * @param extension File extension filter, e.g. "xlsx" (optional)
	 * @return File object of the most recent file
	 */
	static File getMostRecentFile(String folderPath, String extension = null) {
		File dir = new File(folderPath)
		if (!dir.exists() || !dir.isDirectory()) {
			throw new RuntimeException("Folder does not exist: " + folderPath)
		}

		// Groovy-friendly way to filter files
		File[] files = dir.listFiles()?.findAll { f ->
			!extension || f.name.toLowerCase().endsWith(extension.toLowerCase())
		} as File[]

		if (!files || files.length == 0) {
			throw new RuntimeException(
				"No files" + (extension ? " with extension '${extension}'" : "") + " found in folder: " + folderPath
			)
		}

		// Sort files by last modified date (newest first)
		files.sort { -it.lastModified() }

		return files[0] // most recently modified file
	}

	/**
	 * Reads a column from Excel and returns it as a List<String>
	 * @param excelPath Full path of the Excel file
	 * @param sheetIndex Index of the sheet (0 = first sheet)
	 * @param columnIndex Index of column to read (0 = first column)
	 * @return List of cell values as String
	 */
	static List<String> readColumn(String excelPath, int sheetIndex = 0, int columnIndex = 0) {
		List<String> dataList = []

		File file = new File(excelPath)
		if (!file.exists()) {
			throw new RuntimeException("Excel file not found at: " + excelPath)
		}

		FileInputStream fis = new FileInputStream(file)
		XSSFWorkbook workbook = new XSSFWorkbook(fis)
		Sheet sheet = workbook.getSheetAt(sheetIndex)

		for (Row row : sheet) {
			Cell cell = row.getCell(columnIndex)
			if (cell != null) {
				dataList.add(cell.toString().trim())
			}
		}

		workbook.close()
		fis.close()
		return dataList
	}

	/**
	 * Reads a single column from the most recent Excel file in a folder
	 * @param folderPath Folder path
	 * @param columnIndex Column index to read (0 = first column)
	 * @param sheetIndex Sheet index (default 0)
	 * @return List<String> of cell values
	 */
	static List<String> readColumnFromLatestExcel(String folderPath, int columnIndex = 0, int sheetIndex = 0) {
		File latestFile = getMostRecentFile(folderPath, "xlsx")
		return readColumn(latestFile.absolutePath, sheetIndex, columnIndex)
	}

	/**
	 * Reads the entire Excel sheet and returns it as List<List<String>>
	 * Each inner list represents a row, each element is a cell value
	 * @param excelPath Full path of the Excel file
	 * @param sheetIndex Index of the sheet (0 = first sheet)
	 * @return List of rows, each row is a List<String>
	 */
	static List<List<String>> readSheet(String excelPath, int sheetIndex = 0) {
		List<List<String>> sheetData = []

		File file = new File(excelPath)
		if (!file.exists()) {
			throw new RuntimeException("Excel file not found at: " + excelPath)
		}

		FileInputStream fis = new FileInputStream(file)
		XSSFWorkbook workbook = new XSSFWorkbook(fis)
		Sheet sheet = workbook.getSheetAt(sheetIndex)

		for (Row row : sheet) {
			List<String> rowData = []
			row.cellIterator().each { Cell cell ->
				rowData.add(cell?.toString()?.trim() ?: '')
			}
			sheetData.add(rowData)
		}

		workbook.close()
		fis.close()
		return sheetData
	}

	/**
	 * Reads the full sheet from the most recent Excel file in a folder
	 * @param folderPath Folder path
	 * @param sheetIndex Sheet index (default 0)
	 * @return List of rows, each row is a List<String>
	 */
	static List<List<String>> readSheetFromLatestExcel(String folderPath, int sheetIndex = 0) {
		File latestFile = getMostRecentFile(folderPath, "xlsx")
		return readSheet(latestFile.absolutePath, sheetIndex)
	}
}
