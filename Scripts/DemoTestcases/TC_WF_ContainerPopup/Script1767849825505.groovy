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

//WebUI.openBrowser('')
//
//WebUI.navigateToUrl('https://10.10.10.181:8443/prohance')
//
//WebUI.setText(findTestObject('Object Repository/con_popuppage/Page_ProHance/input_Username_tlogin'), 'kaadmin')
//
//WebUI.setText(findTestObject('Object Repository/con_popuppage/Page_ProHance/input_Password_tpwdsaved'), '1')
//
//WebUI.click(findTestObject('Object Repository/con_popuppage/Page_ProHance/div_Captcha Text_col-lg-12'))
//
//WebUI.click(findTestObject('Object Repository/con_popuppage/Page_ProHance/a_WORKFLOW'))
//
//WebUI.switchToWindowTitle('ProHance Workflow')

//WebUI.click(findTestObject('Object Repository/con_popuppage/Page_ProHance Workflow/i_Are you sure you want to remove this cont_555f54'))

WebUI.setText(findTestObject('Object Repository/con_popuppage/Page_ProHance Workflow/input_Container Name_new-container-name'), 
    'test')

WebUI.click(findTestObject('Object Repository/con_popuppage/Page_ProHance Workflow/input_Retain container specific period_btn _26f434'))

println("Modal saved")