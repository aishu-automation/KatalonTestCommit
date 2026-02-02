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

WebUI.callTestCase(findTestCase('RRI_Inside_Organization/Disengagement/DI_ModuleValidation'), [:], FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'validation.DisengagementGlobalVariables.clickOrganizationByGlobalVar'('org_Name')

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_Disengaged_Emp_LongTermLabelPage/Page_ProHance AI - Retention Risk/div_ProHance 199'))

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_Disengaged_Emp_LongTermLabelPage/Page_ProHance AI - Retention Risk/span_Disengaged Employees'))

String dis1 = WebUI.getText(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_Disengaged_Emp_LongTermLabelPage/Page_ProHance AI - Retention Risk/tspan_Last 4W'))

println(dis1)

WebUI.verifyEqual(dis1, 'Last 4W')

String dis2 = WebUI.getText(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_Disengaged_Emp_LongTermLabelPage/Page_ProHance AI - Retention Risk/tspan_Last 8W'))

println(dis2)

WebUI.verifyEqual(dis2, 'Last 8W')

String dis3 = WebUI.getText(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_Disengaged_Emp_LongTermLabelPage/Page_ProHance AI - Retention Risk/tspan_Last 12W'))

println(dis3)

WebUI.verifyEqual(dis3, 'Last 12W')

WebUI.closeBrowser()

