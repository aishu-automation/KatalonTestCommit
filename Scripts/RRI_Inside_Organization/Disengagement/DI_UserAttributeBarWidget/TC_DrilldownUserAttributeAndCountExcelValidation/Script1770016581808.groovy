import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.nio.file.Files
import java.nio.file.StandardCopyOption

import org.apache.poi.openxml4j.opc.OPCPackage
import org.apache.poi.openxml4j.opc.PackageAccess
import org.apache.poi.ss.usermodel.DataFormatter
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.apache.poi.ss.usermodel.DataFormatter
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.nio.file.Files
import java.nio.file.StandardCopyOption

WebUI.callTestCase(findTestCase('Common/RRI/TC_RRI_LoginWithValidCretentials'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('RRI_Inside_Organization/Disengagement/DI_ModuleValidation'), [:], FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'validation.DisengagementGlobalVariables.clickOrganizationByGlobalVar'('org_Name')

WebUI.delay(3)

WebDriver driver = DriverFactory.getWebDriver()
Actions actions = new Actions(driver)

// ================= BAR & LABELS =================
List<WebElement> bars = driver.findElements(By.xpath(
	"(//*[name()='g' and contains(@class,'apexcharts-bar-series')])[2]//*[name()='path' and contains(@class,'apexcharts-bar-area')]"
))

List<WebElement> labels = driver.findElements(By.xpath(
	"(//*[name()='g' and @class='apexcharts-yaxis-texts-g apexcharts-xaxis-inversed-texts-g'])[2]//*[name()='text']"
))

println "Number of bars   = ${bars.size()}"
println "Number of labels = ${labels.size()}"

// ================= LOOP THROUGH BARS =================
for (int barIndex = 0; barIndex < bars.size(); barIndex++) {

	// Refetch after navigation
	bars = driver.findElements(By.xpath(
		"(//*[name()='g' and contains(@class,'apexcharts-bar-series')])[2]//*[name()='path' and contains(@class,'apexcharts-bar-area')]"
	))

	WebElement bar = bars.get(barIndex)

	// Hover to get tooltip
	actions.moveToElement(bar, 5, 5).pause(1200).perform()
	WebUI.delay(1)

	TestObject tooltip = new TestObject('tooltip')
	tooltip.addProperty('xpath', ConditionType.EQUALS,
		"//div[contains(@class,'apexcharts-tooltip')]//div[normalize-space()]"
	)

	String tooltipText = WebUI.getText(tooltip)
	def matcher = tooltipText =~ /Disengagement\s*:\s*(\d+)/
	String disengagedCount = matcher ? matcher[0][1] : "0"
	println "Disengaged count = $disengagedCount"

	// Click bar
	TestObject currentBar = new TestObject("bar_${barIndex}")
	currentBar.addProperty('xpath', ConditionType.EQUALS,
		"(//*[name()='g' and contains(@class,'apexcharts-bar-series')])[2]//*[name()='path' and contains(@class,'apexcharts-bar-area')][${barIndex + 1}]"
	)
	WebUI.waitForElementClickable(currentBar, 10)
	WebUI.click(currentBar)
	WebUI.delay(2)

	// ================= TABLE FILTER =================
	WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_barChart_BreakupDrilldownByAttributeAndUserCountExcelPage/Page_ProHance AI - Retention Risk/span_10'))
	WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_barChart_BreakupDrilldownByAttributeAndUserCountExcelPage/Page_ProHance AI - Retention Risk/div_100'))
	WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_barChart_BreakupDrilldownByAttributeAndUserCountExcelPage/Page_ProHance AI - Retention Risk/div_Username'))

	// ================= READ UI TABLE =================
	JavascriptExecutor js = (JavascriptExecutor) driver
	List<Map<String, Object>> uiDataList = []

	List<WebElement> rows = driver.findElements(By.xpath("//div[@class='ant-table-content']/table/tbody/tr"))
	println "Total UI rows: ${rows.size()}"

	rows.eachWithIndex { WebElement row, int rowIndex ->

		String username = row.findElement(By.xpath("./td[1]/span")).getText()
		String employeeId = row.findElement(By.xpath("./td[2]/span")).getText()
		String userGroup = row.findElement(By.xpath("./td[3]")).getText()

		// SVG disengagement extraction (handles notes case)
		String script = """
		let row = document.querySelectorAll('div.ant-table-content table tbody tr')[${rowIndex}];
		let circles = row.querySelectorAll('td:nth-child(4) svg circle');

		for (let c of circles) {
			let fill = c.getAttribute('fill');
			if (fill && fill !== 'none') {
				return fill;
			}
		}
		return 'NO_DATA';
		"""

		String fill = js.executeScript(script) as String
		String disengagement = "No Data"

		if (fill) {
			fill = fill.toLowerCase()
			if (fill.contains('e74c3c')) disengagement = 'High'
			else if (fill.contains('f1c40f')) disengagement = 'Medium'
			else if (fill.contains('27ae60')) disengagement = 'Low'
		}

		uiDataList << [
			Username     : username,
			EmployeeID   : employeeId,
			UserGroup    : userGroup,
			Disengagement: disengagement
		]
	}

	println "✅ UI Data:"
	uiDataList.each { println it }

	// ================= DOWNLOAD EXCEL =================
	WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_barChart_BreakupDrilldownByAttributeAndUserCountExcelPage/Page_ProHance AI - Retention Risk/svg'))

	  String downloadPath = 'C:\\Users\\aishwarya.k\\Downloads'
    File sourceFile = null

    // Wait for Excel to download completely
    int timeout = 30
    int waited = 0
    long previousSize = -1
    while (waited < timeout) {
        File[] files = new File(downloadPath).listFiles({ dir, name -> name.toLowerCase().endsWith(".xlsx") } as FilenameFilter)
        if (files?.length > 0) {
            sourceFile = files.sort { -it.lastModified() }[0]
            long size = sourceFile.length()
            if (size == previousSize) break
            previousSize = size
        }
        Thread.sleep(1000)
        waited++
    }

    if (sourceFile == null) throw new RuntimeException("❌ No .xlsx file found in ${downloadPath}")
    println "✅ Excel downloaded: ${sourceFile.name}"

    // Sanitize filename
    String safeName = sourceFile.name.replaceAll("^(working_|clean_)+", "")
    File workingFile = new File(downloadPath, "working_" + safeName)
    File cleanFile = new File(downloadPath, "clean_" + safeName)

    Files.copy(sourceFile.toPath(), workingFile.toPath(), StandardCopyOption.REPLACE_EXISTING)
    Files.copy(sourceFile.toPath(), cleanFile.toPath(), StandardCopyOption.REPLACE_EXISTING)

    // Open Excel for cleaning
    OPCPackage pkg = OPCPackage.open(workingFile, PackageAccess.READ_WRITE)
    XSSFWorkbook workbook = new XSSFWorkbook(pkg)
    FileOutputStream fos = new FileOutputStream(cleanFile)
    workbook.write(fos)
    fos.close()
    workbook.close()
    pkg.close()
    println "✅ Clean XLSX created successfully"

    // ================= STEP 6: READ EXCEL =================
 // --- Read Excel from row 4 and correct columns ---
FileInputStream fis = new FileInputStream(cleanFile)
XSSFWorkbook wb = new XSSFWorkbook(fis)
Sheet sheet = wb.getSheetAt(0)
DataFormatter formatter = new DataFormatter()

List<Map> excelDataList = []

sheet.eachWithIndex { Row row, int rIndex ->
    if (rIndex < 3) return  // skip first 3 rows (row index 0,1,2)
    
    String username   = formatter.formatCellValue(row.getCell(1)).trim()  // Column B
    String employeeId = formatter.formatCellValue(row.getCell(2)).trim()  // Column C
    String userGroup  = formatter.formatCellValue(row.getCell(3)).trim()  // Column D
    String disengagement = formatter.formatCellValue(row.getCell(4)).trim() // Column E

    // Skip rows without EmployeeID
    if (!employeeId) return

    excelDataList << [
        Username     : username,
        EmployeeID   : employeeId,
        UserGroup    : userGroup,
        Disengagement: disengagement
    ]
}

wb.close()
fis.close()

println "✅ Excel data collected: ${excelDataList.size()} rows"


    // ================= STEP 7: COMPARE UI VS EXCEL =================
int UI_USERNAME_MAX = 25
    excelDataList.each { excelRow ->
        def matched = uiDataList.find { it.EmployeeID == excelRow.EmployeeID }
        if (matched) {
         String uiUsername = matched.Username
		String excelUsername = excelRow.Username
		  String uiUsernameTrimmed = uiUsername.length() > UI_USERNAME_MAX ? uiUsername[0..(UI_USERNAME_MAX-1)] : uiUsername
		String excelUsernameTrimmed = excelUsername.length() > UI_USERNAME_MAX ? excelUsername[0..(UI_USERNAME_MAX-1)] : excelUsername
	
		boolean usernameMatch = uiUsernameTrimmed == excelUsernameTrimmed
	
		if (!usernameMatch) {
			println "⚠️ Username mismatch due to truncation: UI='${uiUsernameTrimmed}' Excel='${excelUsernameTrimmed}'"
		}
            boolean groupMatch = matched.UserGroup == excelRow.UserGroup
            boolean disengMatch = excelRow.Disengagement.contains(matched.Disengagement)

            println "EmployeeID=${excelRow.EmployeeID} | UsernameMatch=${usernameMatch} | GroupMatch=${groupMatch} | DisengagementMatch=${disengMatch}"
            if (!usernameMatch || !groupMatch || !disengMatch) {
                KeywordUtil.markFailed("❌ Data mismatch for EmployeeID ${excelRow.EmployeeID}")
            }
        } else {
            println "❌ No UI row for EmployeeID ${excelRow.EmployeeID}"
        }
    }
	println "✅ UI vs Excel comparison completed"
	
	
	// Back
	WebUI.click(findTestObject(
		'Object Repository/RRI_Inside_Organization/DisEngagement/DI_Disengaged_Emp_UserCountPage/Page_ProHance AI - Retention Risk/button_Select Value_MuiButtonBase-root MuiI_029fad'
	))

	WebUI.delay(2)
}

WebUI.closeBrowser()

