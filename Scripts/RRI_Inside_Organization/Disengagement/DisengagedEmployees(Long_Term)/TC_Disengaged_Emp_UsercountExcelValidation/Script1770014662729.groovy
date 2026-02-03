import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.nio.file.Files
import java.nio.file.StandardCopyOption

import org.apache.poi.openxml4j.opc.OPCPackage
import org.apache.poi.openxml4j.opc.PackageAccess
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.DataFormatter
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.callTestCase(findTestCase('Common/RRI/TC_RRI_LoginWithValidCretentials'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('RRI_Inside_Organization/Disengagement/DI_ModuleValidation'), [:], FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'validation.DisengagementGlobalVariables.clickOrganizationByGlobalVar'('org_Name')

int totalBars = 3

for (int i = 1; i <= totalBars; i++) {
	//WebUI.comment(('---- Validating bar index: ' + i) + ' ----')

	//  Get value from label
	TestObject labelObj = new TestObject('label_' + i)

	labelObj.addProperty('xpath', ConditionType.EQUALS, ('(//*[@id=\'apexchartshorizontal-bar-widget7\']//*[name()=\'text\' and contains(@class,\'apexcharts-datalabel\')])[' +
		i) + ']')

	WebUI.waitForElementVisible(labelObj, 10)

	String expectedCount = WebUI.getText(labelObj).trim()

	WebUI.comment('Expected count = ' + expectedCount)

	//  Click corresponding bar
	TestObject barObj = new TestObject('bar_' + i)

	barObj.addProperty('xpath', ConditionType.EQUALS, ('(//*[@id=\'apexchartshorizontal-bar-widget7\']//*[name()=\'g\' and @seriesName=\'Disengaged\']//*[name()=\'path\'])[' +
		i) + ']')

	WebUI.waitForElementClickable(barObj, 10)

	WebUI.click(barObj)
	
	WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_Disengaged_Emp_UserCountExcelPage/Page_ProHance AI - Retention Risk/span_10'))
	
	WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_Disengaged_Emp_UserCountExcelPage/Page_ProHance AI - Retention Risk/div_100'))
	
	WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_Disengaged_Emp_UserCountExcelPage/Page_ProHance AI - Retention Risk/span_Username'))
	WebDriver driver = DriverFactory.getWebDriver()
	
	// Cast it to JavascriptExecutor
	JavascriptExecutor js = (JavascriptExecutor) driver
	List<Map<String, Object>> uiDataList = []
	List<WebElement> rows = driver.findElements(By.xpath('//div[@class="ant-table-content"]/table/tbody/tr'))
	println "Total UI rows: ${rows.size()}"
    
	rows.eachWithIndex { WebElement row, int rowIndex ->
		String username = driver.findElement(By.xpath("//div[@class='ant-table-content']/table/tbody/tr[${rowIndex+1}]/td[1]/span")).getText()
		String employeeId = driver.findElement(By.xpath("//div[@class='ant-table-content']/table/tbody/tr[${rowIndex+1}]/td[2]/span")).getText()
		String userGroup = driver.findElement(By.xpath("//div[@class='ant-table-content']/table/tbody/tr[${rowIndex+1}]/td[3]")).getText()
	
		// Get circle fills dynamically
		String script = """
let row = document.querySelectorAll('div.ant-table-content table tbody tr')[${rowIndex}];
let foreignObjects = row.querySelectorAll('td:nth-child(4) foreignObject');

let colors = [];

foreignObjects.forEach(fo => {
    let circles = fo.querySelectorAll('circle');

    let realCircle = Array.from(circles).find(c =>
        c.getAttribute('fill') &&
        c.getAttribute('fill') !== 'none'
    );

    if (realCircle) {
        colors.push(realCircle.getAttribute('fill'));
    } else {
        colors.push('NO_DATA');
    }
});

return colors;
"""
		List<String> fills = ((js.executeScript(script)) as List<String>)
	
		// Map colors
		List<String> colorNames = fills.collect { fill ->
			if (!fill || fill == 'NO_DATA') return 'No Data'
	
			fill = fill.toLowerCase()
	
			if (fill.contains('e74c3c') || fill.contains('rgb(231,76,60)')) return 'High'
			if (fill.contains('f1c40f') || fill.contains('rgb(241,196,15)')) return 'Medium'
			if (fill.contains('27ae60') || fill.contains('rgb(39,174,96)')) return 'Low'
	
			return 'No Data'
		}
	
		uiDataList << [
			"Username": username,
			"EmployeeID": employeeId,
			"UserGroup": userGroup,
			"Disengagement": colorNames
		]
	}
	
	
	println "✅ UI data collected:"
	uiDataList.each { println it }
	
	WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_Disengaged_Emp_UserCountExcelPage/Page_ProHance AI - Retention Risk/button_Records to display_ant-btn css-os2fv_830e3b'))
	String downloadPath = 'C:\\Users\\aishwarya.k\\Downloads'
	File sourceFile = CustomKeywords.'utility.ExcelUtil.getMostRecentFile'(downloadPath, ".xlsx")
	File workingFile = new File(downloadPath + "\\working_" + sourceFile.name)
	File cleanFile   = new File(downloadPath + "\\clean_" + sourceFile.name)
	
	// Wait for stable size
	//File sourceFile = null
	int timeout = 30 // max seconds to wait
	int waited = 0
	long previousSize = -1
	long currentSize = 0
	
	while (waited < timeout) {
		// Get all .xlsx files in the folder
		File[] files = new File(downloadPath).listFiles({ dir, name -> name.toLowerCase().endsWith(".xlsx") } as FilenameFilter)
	
		if (files?.length > 0) {
			// pick the most recent file
			sourceFile = files.sort { -it.lastModified() }[0] // descending order
			currentSize = sourceFile.length()
	
			// Wait until size stabilizes
			if (currentSize == previousSize) {
				break // file fully downloaded
			}
			previousSize = currentSize
		}
	
		Thread.sleep(1000)
		waited++
	}
	
	if (sourceFile == null) {
		throw new RuntimeException("❌ No .xlsx file found in folder: ${downloadPath}")
	}
	
	println "✅ Most recent Excel file ready: ${sourceFile.name}"
	
	//// Copy to working file
	Files.copy(sourceFile.toPath(), workingFile.toPath(), StandardCopyOption.REPLACE_EXISTING)
	
	// Clean Excel
	OPCPackage pkg = OPCPackage.open(workingFile, PackageAccess.READ_WRITE)
	XSSFWorkbook workbook = new XSSFWorkbook(pkg)
	FileOutputStream fos = new FileOutputStream(cleanFile)
	workbook.write(fos)
	fos.close()
	workbook.close()
	pkg.close()
	
	println "✅ Clean XLSX created successfully"
	
	//// ================= STEP 4: READ EXCEL =================
	FileInputStream fis = new FileInputStream(cleanFile)
	XSSFWorkbook wb = new XSSFWorkbook(fis)
	Sheet sheet = wb.getSheetAt(0)
	DataFormatter formatter = new DataFormatter()
	
	List<Map<String, Object>> excelDataList = []
	
	// Build excelDataList safely
	sheet.eachWithIndex { Row row, int rIndex ->
		if (rIndex < 3) return // skip first 3 header rows
	
		List<String> rowValues = []
		for (int c = 1; c < row.getLastCellNum(); c++) {
			Cell cell = row.getCell(c, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
			rowValues << formatter.formatCellValue(cell).trim()
		}
	
		String username   = rowValues[0] ?: "No Data"
		String employeeID = rowValues[1] ?: "No Data"
		String userGroup  = rowValues[2] ?: "No Data"
	
		// Start from column index 3 for disengagement values
	   List<String> disengagementValues = []
		for (int col = 3; col < rowValues.size(); col++) {
			disengagementValues << (rowValues[col] ? rowValues[col] : "No Data")
		}
	
		// Pad to 12 columns if needed
		while (disengagementValues.size() < 12) {
			disengagementValues << "No Data"
		}
	
		excelDataList << [
			"Username": username,
			"EmployeeID": employeeID,
			"UserGroup": userGroup,
			"Disengagement": disengagementValues
		]
	}
	
	
	wb.close()
	fis.close()
	
	println "✅ Excel data collected:"
	excelDataList.each { println it }
	
	// ================= STEP 5: COMPARE UI VS EXCEL =================
	int UI_USERNAME_MAX = 25
	excelDataList.each { excelRow ->
	
		def uiRow = uiDataList.find { it.EmployeeID == excelRow.EmployeeID }
	
		if (!uiRow) {
			println "❌ No matching UI row for EmployeeID: ${excelRow.EmployeeID}"
			return
		}
	
		// Basic fields
		String uiUsername = uiRow.Username
		String excelUsername = excelRow.Username
		  String uiUsernameTrimmed = uiUsername.length() > UI_USERNAME_MAX ? uiUsername[0..(UI_USERNAME_MAX-1)] : uiUsername
		String excelUsernameTrimmed = excelUsername.length() > UI_USERNAME_MAX ? excelUsername[0..(UI_USERNAME_MAX-1)] : excelUsername
	
		boolean usernameMatch = uiUsernameTrimmed == excelUsernameTrimmed
	
		if (!usernameMatch) {
			println "⚠️ Username mismatch due to truncation: UI='${uiUsernameTrimmed}' Excel='${excelUsernameTrimmed}'"
		}
	
		boolean userGroupMatch = uiRow.UserGroup == excelRow.UserGroup
	
		// Compare disengagement safely
		List<String> uiDisengagement = uiRow.Disengagement
		List<String> excelDisengagement = excelRow.Disengagement
		int maxLength = Math.max(uiDisengagement.size(), excelDisengagement.size())
		while (uiDisengagement.size() < maxLength) uiDisengagement << "No Data"
		while (excelDisengagement.size() < maxLength) excelDisengagement << "No Data"
	
		boolean disengagementMatch = true
		List<String> mismatchedIndexes = []
		for (int datCom = 0; datCom < maxLength; datCom++) {
			if (uiDisengagement[datCom] != excelDisengagement[datCom]) {
				disengagementMatch = false
				mismatchedIndexes << "${datCom+1}"
			}
		}
	
		println "🔹 Comparing EmployeeID ${excelRow.EmployeeID}: Username=$usernameMatch, UserGroup=$userGroupMatch, Disengagement=$disengagementMatch"
		if (!disengagementMatch) {
			println "   ❌ Disengagement mismatch at columns: ${mismatchedIndexes.join(', ')}"
			println "   UI:    ${uiDisengagement}"
			println "   Excel: ${excelDisengagement}"
		}
	
		if (!usernameMatch || !userGroupMatch || !disengagementMatch) {
			KeywordUtil.markFailed("❌ Data mismatch for EmployeeID ${excelRow.EmployeeID}")
		}
	}
	
	println "✅ UI vs Excel comparison completed!"
	
	
	//back button
	WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_Disengaged_Emp_UserCountPage/Page_ProHance AI - Retention Risk/button_Select Value_MuiButtonBase-root MuiI_029fad'))
	WebUI.waitForElementVisible(labelObj, 10)
}

WebUI.closeBrowser()

