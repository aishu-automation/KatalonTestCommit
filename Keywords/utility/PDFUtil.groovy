package utility

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper
import com.kms.katalon.core.annotation.Keyword

public class PDFUtil {
	
	@Keyword
	String readLatestDownloadedPdf(String downloadDir) {
		File dir = new File(downloadDir)
		assert dir.exists() : "Download directory not found: " + downloadDir

		// Get latest PDF file
		File latestPdf = dir.listFiles()
				.findAll { it.name.toLowerCase().endsWith(".pdf") }
				.sort { a, b -> b.lastModified() <=> a.lastModified() }
				.first()

		assert latestPdf != null : "No PDF found in directory: " + downloadDir

		// Wait for the download to complete (size > 0)
		int wait = 0
		while (latestPdf.length() == 0 && wait < 30) {
			Thread.sleep(1000)
			wait++
			latestPdf = dir.listFiles()
					.findAll { it.name.toLowerCase().endsWith(".pdf") }
					.sort { a, b -> b.lastModified() <=> a.lastModified() }
					.first()
		}

		assert latestPdf.length() > 0 : "PDF file is empty or download failed!"
		println("PDF found: " + latestPdf.getAbsolutePath())

		// Extract text from PDF using PDFBox
		PDDocument document = null
		println("PDF found: " +document.getVersion())
//		try {
//			document = PDDocument.load(latestPdf)
//			PDFTextStripper textStripper = new PDFTextStripper()
//			String pdfText = textStripper.getText(document)
//			println("PDF text extracted successfully")
//			return pdfText.trim()
//		} catch (Exception e) {
//			println("Error extracting PDF text: " + e.getMessage())
//			e.printStackTrace()
//			throw e
//		} finally {
//			if (document != null) {
//				document.close()
//			}
//		}
	}
}