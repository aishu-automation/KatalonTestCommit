package utility


import org.apache.pdfbox.pdfparser.PDFParser
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
		println(PDDocument.class.getProtectionDomain().getCodeSource().getLocation())
		
//	RandomAccessFile raf = new RandomAccessFile(latestPdf, "r")
//        PDFParser parser = new PDFParser(raf)
//        parser.parse()
//        PDDocument document = parser.
//		PDFTextStripper stripper = new PDFTextStripper()
//		String pdfText = stripper.getText(document)
//
//		document.close()
//		
//
//		return pdfText.trim()
	}

}
