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

WebUI.navigateToUrl('https://devlab.prohance.ai:8000/prohanceai')

WebUI.click(findTestObject('Object Repository/New Folder (2)/Page_ProHance AI - Retention Risk/label_Username'))

WebUI.setText(findTestObject('Object Repository/New Folder (2)/Page_ProHance AI - Retention Risk/input_Username_r0'), 'superadmin')

WebUI.setEncryptedText(findTestObject('Object Repository/New Folder (2)/Page_ProHance AI - Retention Risk/input_Password_r1'), 
    '7326p5kcqg3YJazKjhRTWA==')

WebUI.click(findTestObject('Object Repository/New Folder (2)/Page_ProHance AI - Retention Risk/button_Login'))

WebUI.click(findTestObject('Object Repository/New Folder (2)/Page_ProHance AI - Retention Risk/div_PH Demo (199 Server)'))

WebUI.click(findTestObject('Object Repository/New Folder (2)/Page_ProHance AI - Retention Risk/path'))

WebUI.click(findTestObject('Object Repository/New Folder (2)/Page_ProHance AI - Retention Risk/span_10'))

WebUI.click(findTestObject('Object Repository/New Folder (2)/Page_ProHance AI - Retention Risk/div_100'))

WebUI.click(findTestObject('Object Repository/New Folder (1)/Page_ProHance AI - Retention Risk/span_Username'))

WebUI.closeBrowser()

