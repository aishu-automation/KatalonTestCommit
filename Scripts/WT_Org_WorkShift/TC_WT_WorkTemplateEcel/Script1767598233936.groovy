import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.time.Duration

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import utility.ExcelUtil
import utility.ExcelUtil as FailureHandling

// ----------------- Step 1: Open Browser and Login -----------------
WebUI.callTestCase(findTestCase('Common/LoginPageTestCase'), [:], FailureHandling.STOP_ON_FAILURE)

// ---------------- Step 2: Navigate to Work Template ----------------
WebUI.click(findTestObject('Object Repository/WorkTemplateExcel_Page/Page_ProHance/div_ADMINISTRATION MENU'))

WebUI.click(findTestObject('Object Repository/WorkTemplateExcel_Page/Page_ProHance/li_Work Template'))

WebUI.click(findTestObject('Object Repository/WorkTemplateExcel_Page/Page_ProHance/div_Infra Team'))

WebUI.click(findTestObject('Object Repository/WorkTemplateExcel_Page/Page_ProHance/td_Infra Team'))

// ---------------- Step 3: Switch to iframe ----------------
def driver = DriverFactory.getWebDriver()

WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20))

wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt('contentFrame'))

wait.until(ExpectedConditions.presenceOfElementLocated(By.id('CommonDataTableId')))

// ---------------- Step 4: Extract UI Table Data ----------------
List<WebElement> rows = driver.findElements(By.xpath('//table[@id="CommonDataTableId"]/tbody/tr'))

List<Map> uiDataList = []

for (WebElement row : rows) {
    Map<String, String> rowData = [:]

    (rowData['Name']) = row.findElement(By.xpath('td[2]/div')).getText().trim()

    (rowData['Description']) = row.findElement(By.xpath('td[3]/div')).getText().trim()

    (rowData['WorkDays']) = row.findElement(By.xpath('td[4]')).getText().trim()

    (rowData['Teams']) = row.findElement(By.xpath('td[5]')).getText().trim()

    uiDataList.add(rowData)
}

// ---------------- Step 5: Export Excel ----------------
WebUI.delay(5)

WebUI.click(findTestObject('Object Repository/WorkTemplateExcel_Page/Page_ProHance/img_Export_vertical-Align-Middle cursor-pointer'))

WebUI.delay(10 // wait for download
    )

// ---------------- Step 6: Read Latest Excel ----------------
List<List> fullData = ExcelUtil.readSheetFromLatestExcel('C:\\Users\\aishwarya.k\\Downloads')

// Convert Excel data to List<Map> for comparison
List<Map> excelDataList = []

for (int i = 1; i < fullData.size(); i++) {
    // skip header row
    List<String> row = fullData[i]

    Map<String, String> rowData = [:]

    (rowData['Name']) = row.size() > 0 ? (row[0]).toString().trim() : ''

    (rowData['Description']) = row.size() > 1 ? (row[1]).toString().trim() : ''

    (rowData['WorkDays']) = row.size() > 2 ? (row[2]).toString().trim() : ''

    (rowData['Teams']) = row.size() > 3 ? (row[3]).toString().trim() : ''

    excelDataList.add(rowData)
}

// ---------------- Step 7: Helper to Normalize Numbers ----------------
// 1.0 -> "1"
// ---------------- Step 8: Compare UI vs Excel ----------------
println('\n----- Comparison Results -----')

uiDataList.each({ def uiRow ->
        Map<String, String> excelRow = excelDataList.find({ 
                (it['Name']).equalsIgnoreCase(uiRow['Name'])
            })

        if (excelRow == null) {
            println("❌ No matching Excel row found for UI Name: $uiRow[Name]")

            return null
        }
        
        boolean isMatch = ((normalizeValue(uiRow['Description']) == normalizeValue(excelRow['Description'])) && (normalizeValue(
            uiRow['WorkDays']) == normalizeValue(excelRow['WorkDays']))) && (normalizeValue(uiRow['Teams']) == normalizeValue(
            excelRow['Teams']))

        if (isMatch) {
            println("✅ '$uiRow[Name]' matches between UI and Excel.")
        } else {
            println("❌ '$uiRow[Name]' mismatch:")

            println("    UI    -> $uiRow[Description], $uiRow[WorkDays], $uiRow[Teams]")

            println("    Excel -> $excelRow[Description], $excelRow[WorkDays], $excelRow[Teams]")
        }
    })

// ---------------- Step 9: Close Browser ----------------
WebUI.closeBrowser()

String normalizeValue(String value) {
    if (value == null) {
        return ''
    }
    
    value = value.trim()
    
    try {
        double d = Double.parseDouble(value)
        
        // if the number is whole, return as integer string
        if (d == (int)d) {
            return ((int)d).toString()
        } else {
            return value
        }
    } catch (Exception e) {
        return value
    }

}

