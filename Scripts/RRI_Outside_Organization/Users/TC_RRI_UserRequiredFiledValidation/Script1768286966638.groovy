import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.callTestCase(findTestCase('Common/RRI/TC_RRI_LoginWithValidCretentials'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/UserRequiredFiledPage/Page_ProHance AI - Retention Risk/a_General'))

WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/UserRequiredFiledPage/Page_ProHance AI - Retention Risk/svg_AI_MuiSvgIcon-root MuiSvgIcon-fontSizeS_1693d1'))

WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/UserRequiredFiledPage/Page_ProHance AI - Retention Risk/div_Administration'))

WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/UserRequiredFiledPage/Page_ProHance AI - Retention Risk/div_Users'))

WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/UserRequiredFiledPage/Page_ProHance AI - Retention Risk/a_Administration'))

WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/UserRequiredFiledPage/Page_ProHance AI - Retention Risk/p_Users'))

WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/UserRequiredFiledPage/Page_ProHance AI - Retention Risk/button_ADD NEW'))

WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/UserRequiredFiledPage/Page_ProHance AI - Retention Risk/span_Add User'))

WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/UserRequiredFiledPage/Page_ProHance AI - Retention Risk/button_Create'))

// Define required fields with robust XPaths
def requiredFields = [('Login Id') : '//span[contains(text(),\'Login Id\')]/ancestor::div[1]//input', ('First Name') : '//span[contains(text(),\'First Name\')]/ancestor::div[1]//input'
    , ('Role') : '//span[contains(text(),\'Role\')]/ancestor::div[1]//select', ('Password') : '//span[contains(text(),\'Password\')]/ancestor::div[1]//input[@type=\'password\'][1]'
    , ('Confirm Password') : '//span[contains(text(),\'Confirm Password\')]/ancestor::div[1]//input[@type=\'password\'][1]']

// 3️⃣ Call keyword for soft assert validation
CustomKeywords.'validation.UserRequiredFieldSoftAssert.verifyRequiredByXpath'(requiredFields)

WebUI.closeBrowser()

