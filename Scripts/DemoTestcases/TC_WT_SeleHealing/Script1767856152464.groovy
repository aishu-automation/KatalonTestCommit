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

WebUI.callTestCase(findTestCase('Common/LoginPageTestCase'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/WT_SeleHealing/Page_ProHance/a_WORK TIME'))

WebUI.click(findTestObject('Object Repository/WT_SeleHealing/Page_ProHance/td_ACTIVITY DASHBOARD'))

WebUI.click(findTestObject('Object Repository/WT_SeleHealing/Page_ProHance/a_ACTIVITY DASHBOARD'))

WebUI.click(findTestObject('Object Repository/WT_SeleHealing/Page_ProHance/span_Top Categories Activity Type For  CS L_e574cf'))

//WebUI.click(findTestObject('Object Repository/WT_SeleHealing/Page_ProHance/input_Are you sure you want to remove this _ad8099'))
WebUI.closeBrowser()

WebUI.acceptAlert()

