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

WebUI.openBrowser('')

WebUI.navigateToUrl('https://10.10.10.181:8443/prohance')

WebUI.setText(findTestObject('Object Repository/ShiftPage/Page_ProHance/input_Username_tlogin'), 'kaadmin')

WebUI.setText(findTestObject('Object Repository/ShiftPage/Page_ProHance/input_Password_tpwdsaved'), '1')

WebUI.click(findTestObject('Object Repository/ShiftPage/Page_ProHance/div_Captcha Text_loginSubmitFrm'))

WebUI.click(findTestObject('Object Repository/ShiftPage/Page_ProHance/li_Organization'))

WebUI.click(findTestObject('Object Repository/ShiftPage/Page_ProHance/div_ADMIN_arrow-top'))

WebUI.click(findTestObject('Object Repository/ShiftPage/Page_ProHance/li_Work Shifts'))

WebUI.waitForElementClickable(
	findTestObject('Object Repository/ShiftPage/Page_ProHance/i_Status_fa fa-trash-o fa-lg'),
	30
)

WebUI.click(findTestObject('Object Repository/ShiftPage/Page_ProHance/i_Status_fa fa-trash-o fa-lg'))

WebUI.closeBrowser()

