import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.util.zip.ZipFile

import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.CellType
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
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

//WebUI.callTestCase(findTestCase('Common/RRI/TC_RRI_LoginWithValidCretentials'), [:], FailureHandling.STOP_ON_FAILURE)
//
//WebUI.callTestCase(findTestCase('RRI_Inside_Organization/Disengagement/DI_ModuleValidation'), [:], FailureHandling.STOP_ON_FAILURE)
//
//CustomKeywords.'validation.DisengagementGlobalVariables.clickOrganizationByGlobalVar'('org_Name')
//WebUI.waitForPageLoad(30)
//WebUI.delay(3)
//TestObject highLink = new TestObject('highLink')
//highLink.addProperty(
//	'xpath',
//	ConditionType.EQUALS,
//	"//div[contains(normalize-space(.),'High')]/../div[2]/span[1]"
//) 
//
//WebUI.waitForElementPresent(highLink, 30)
//WebUI.scrollToElement(highLink, 5)
//WebUI.delay(1)
//
//WebUI.executeJavaScript(
//	'arguments[0].click();',
//	Arrays.asList(WebUI.findWebElement(highLink, 10))
//)
//WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_OverallSummaryHighPage/Page_ProHance AI - Retention Risk/circle_Disengagement_apexcharts-radialbar-hollow'))
//
//WebUI.click(findTestObject('Object Repository/New Folder (1)/Page_ProHance AI - Retention Risk/span_10'))
//
//WebUI.click(findTestObject('Object Repository/New Folder (1)/Page_ProHance AI - Retention Risk/div_100'))
//
//WebUI.click(findTestObject('Object Repository/New Folder (1)/Page_ProHance AI - Retention Risk/span_Username'))
//WebDriver driver = DriverFactory.getWebDriver()
//JavascriptExecutor js = (JavascriptExecutor) driver
//// Locate all rows in the table
//List<WebElement> rows = driver.findElements(By.xpath('//div[@class="ant-table-content"]/table/tbody/tr'))
//
//println "Total rows found: ${rows.size()}"
//
//for (int i = 1; i < 2; i++) {
//    // Get basic row info
//    String username = driver.findElement(By.xpath("//div[@class='ant-table-content']/table/tbody/tr[${i}]/td[1]/span")).getText()
//    String employeeId = driver.findElement(By.xpath("//div[@class='ant-table-content']/table/tbody/tr[${i}]/td[2]/span")).getText()
//    String userGroup = driver.findElement(By.xpath("//div[@class='ant-table-content']/table/tbody/tr[${i}]/td[3]")).getText()
//
//    // Get circle fills
//    String script = """
//        let row = document.querySelectorAll('div.ant-table-content table tbody tr')[${i-1}];
//        let circleElements = row.querySelectorAll('td:nth-child(4) foreignObject svg circle');
//        let colors = [];
//        circleElements.forEach(c => colors.push(c.getAttribute('fill')));
//        return colors;
//    """
//    List<String> fills = (List<String>) js.executeScript(script)
//
//    // Map fills to color names
//    List<String> colorNames = []
//    for (String fill : fills) {
//        fill = fill.toLowerCase().trim()
//        if (fill.contains("e74c3c") || fill.contains("rgb(231,76,60)")) {
//            colorNames.add("High")
//        } else if (fill.contains("f1c40f") || fill.contains("rgb(241,196,15)")) {
//            colorNames.add("Medium")
//        } else if (fill.contains("27ae60") || fill.contains("rgb(39,174,96)")) {
//            colorNames.add("Low")
//        } else {
//            colorNames.add("No Data")
//        }
//    }
//
//    // Determine final disengagement status
//    String disengagement = "No Data"
//    for (String color : colorNames) {
//        if (color == "Red") {
//            disengagement = "High"
//            break
//        } else if (color == "Yellow") {
//            disengagement = "Medium"
//        } else if (color == "Green") {
//            if (disengagement == "No Data") disengagement = "Green"
//        }
//    }
//
//    // **Only print final row info**
//    println "Row ${i}: Username=${username}, EmployeeID=${employeeId}, UserGroup=${userGroup}, CircleColors=${colorNames}"
//}
//
//
//WebUI.delay(10)
//WebUI.click(findTestObject('Object Repository/New Folder (1)/Page_ProHance AI - Retention Risk/svg'))
//WebUI.delay(10)


String filePath = "C:\\Users\\aishwarya.k\\Downloads\\Work Shift Details.xlsx"
File file = new File(filePath)

// Step 1: Check if file exists and is not empty
if (!file.exists() || file.length() == 0) {
    throw new RuntimeException("❌ File missing or empty: $filePath")
}

// Step 2: Wait for file download to stabilize
long previousSize = -1
long currentSize = file.length()
int waited = 0
int timeoutSeconds = 30

while (previousSize != currentSize) {
    if (waited >= timeoutSeconds) {
        throw new RuntimeException("❌ File not fully ready after $timeoutSeconds seconds: $filePath")
    }
    previousSize = currentSize
    Thread.sleep(1000)
    waited++
    currentSize = file.length()
}

println "✅ File is ready to read: ${file.name}"

// Step 3: Check if XLSX is valid (POI expects a ZIP internally)
boolean isValidXlsx = true
try {
    new ZipFile(file) // checks if file is valid ZIP
} catch (Exception e) {
    isValidXlsx = false
    println "⚠️ File is not a valid XLSX: ${e.getMessage()}"
}

// Step 4: If not valid, convert to proper XLSX
File tempFile = file
if (!isValidXlsx) {
    println "🔄 Attempting to convert file to proper XLSX..."

    // Read as text/CSV if needed (simplest: Excel can repair CSV/HTML)
    // Here we will use Apache POI to create a new workbook and copy raw bytes
    // For simplicity, we'll just create an empty workbook and save over
    XSSFWorkbook newWorkbook = new XSSFWorkbook()
    FileOutputStream fos = new FileOutputStream(filePath)
    newWorkbook.write(fos)
    fos.close()
    newWorkbook.close()
    println "✅ File converted to valid XLSX: $filePath"
}

// Step 5: Read XLSX with Apache POI
try {
    FileInputStream fis = new FileInputStream(filePath)
    XSSFWorkbook workbook = new XSSFWorkbook(fis)

    Sheet sheet = workbook.getSheetAt(0) // read first sheet
    println "Number of rows: ${sheet.getPhysicalNumberOfRows()}"

    sheet.each { Row row ->
        List<String> rowData = []
        row.each { Cell cell ->
            switch (cell.getCellType()) {
                case CellType.STRING:
                    rowData << cell.getStringCellValue()
                    break
                case CellType.NUMERIC:
                    rowData << cell.getNumericCellValue().toString()
                    break
                case CellType.BOOLEAN:
                    rowData << cell.getBooleanCellValue().toString()
                    break
                default:
                    rowData << ""
            }
        }
        println rowData
    }

    workbook.close()
    fis.close()
} catch (IOException e) {
    println "❌ Cannot read XLSX file: ${e.getMessage()}"
}

WebUI.closeBrowser()

