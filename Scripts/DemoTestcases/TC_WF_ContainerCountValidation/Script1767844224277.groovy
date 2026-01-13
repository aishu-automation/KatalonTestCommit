import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
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

WebUI.setText(findTestObject('Object Repository/WorkFlow_ContainerPages/Page_ProHance/input_Username_tlogin'), 'kaadmin')

WebUI.setText(findTestObject('Object Repository/WorkFlow_ContainerPages/Page_ProHance/input_Password_tpwdsaved'), '1')

WebUI.click(findTestObject('Object Repository/WorkFlow_ContainerPages/Page_ProHance/div_Captcha Text_loginSubmitFrm'))

WebUI.click(findTestObject('Object Repository/WorkFlow_ContainerPages/Page_ProHance/ol_OrganizationOrganization Settings'))

WebUI.click(findTestObject('Object Repository/WorkFlow_ContainerPages/Page_ProHance/a_WORKFLOW'))

WebUI.switchToWindowTitle('ProHance Workflow')

WebUI.delay(5)

WebDriver driver = DriverFactory.getWebDriver()

driver.switchTo().frame('contentFrame')

// Find elements 
List<WebElement> elements = driver.findElements(By.xpath('//li[@id="tab_1"]/div'))

// count
int count = elements.size()

// Print result in console
println('Number of elements found: ' + count)

WebUI.closeBrowser()

