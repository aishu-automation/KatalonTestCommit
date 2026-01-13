import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
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

WebUI.setText(findTestObject('Object Repository/WT_WorkTemplateSortingPage/Page_ProHance/input_Username_tlogin'), 'kaadmin')

WebUI.setText(findTestObject('Object Repository/WT_WorkTemplateSortingPage/Page_ProHance/input_Password_tpwdsaved'), '1')

WebUI.click(findTestObject('Object Repository/WT_WorkTemplateSortingPage/Page_ProHance/div_Captcha Text_loginSubmitFrm'))

WebUI.click(findTestObject('Object Repository/WT_WorkTemplateSortingPage/Page_ProHance/i_ADMIN_fa fa-chevron-right icon-white'))

WebUI.click(findTestObject('Object Repository/WT_WorkTemplateSortingPage/Page_ProHance/li_Work Template'))

WebUI.click(findTestObject('Object Repository/WT_WorkTemplateSortingPage/Page_ProHance/div_Infra Team'))

WebUI.delay(5)

WebDriver driver = DriverFactory.getWebDriver()

driver.switchTo().frame('contentFrame')

// Find elements
List<WebElement> elements = driver.findElements(By.xpath('//table[@id="CommonDataTableId"]/tbody/tr/td[2]/div'))

List<String> textList = elements.collect({ 
        it.getText()
    })

// Print as List
println('Values List: ' + textList)

WebUI.click(findTestObject('Object Repository/WT_WorkTemplateSortingPage/Page_ProHance/th_Name'))

WebUI.click(findTestObject('Object Repository/WT_WorkTemplateSortingPage/Page_ProHance/th_Name'))

List<WebElement> elements1 = driver.findElements(By.xpath('//table[@id="CommonDataTableId"]/tbody/tr/td[2]/div'))

List<String> textList1 = elements1.collect({ 
        it.getText()
    })

// Print as List
println('Values List: ' + textList1)

if (textList == textList1) {
    KeywordUtil.markPassed('Lists are equal')
} else {
    KeywordUtil.markFailed('Lists are NOT equal')
}

WebUI.closeBrowser()

