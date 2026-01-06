import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import java.time.Duration as Duration
import org.openqa.selenium.By as By
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.support.ui.ExpectedConditions as ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait as WebDriverWait
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import internal.GlobalVariable as GlobalVariable

WebUI.openBrowser('')

WebUI.navigateToUrl('https://10.10.10.181:8443/prohance')

WebUI.setText(findTestObject('Object Repository/WorktemplatePdf_Page/Page_ProHance/input_Username_tlogin'), 'kaadmin')

WebUI.setText(findTestObject('Object Repository/WorktemplatePdf_Page/Page_ProHance/input_Password_tpwdsaved'), '1')

WebUI.click(findTestObject('Object Repository/WorktemplatePdf_Page/Page_ProHance/div_Captcha Text_loginSubmitFrm'))

WebUI.click(findTestObject('Object Repository/WorktemplatePdf_Page/Page_ProHance/li_Organization'))

WebUI.click(findTestObject('Object Repository/WorktemplatePdf_Page/Page_ProHance/div_ADMINISTRATION MENU'))

WebUI.click(findTestObject('Object Repository/WorktemplatePdf_Page/Page_ProHance/li_Work Template'))

WebUI.click(findTestObject('Object Repository/WorktemplatePdf_Page/Page_ProHance/div_Infra Team'))

WebUI.click(findTestObject('Object Repository/WorktemplatePdf_Page/Page_ProHance/div_Infra Team'))

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

WebUI.click(findTestObject('Object Repository/WorktemplatePdf_Page/Page_ProHance/img_Export_vertical-Align-Middle cursor-pointer'))

WebUI.delay(10)

String downloadDir = 'C:/Users/aishwarya.k/Downloads'

String pdfText = CustomKeywords.'utility.PDFUtil.readLatestDownloadedPdf'(downloadDir)

println('========= PDF DATA =========')

println(pdfText)

WebUI.closeBrowser()

