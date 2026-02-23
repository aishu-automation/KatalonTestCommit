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

WebUI.callTestCase(findTestCase('RRI_Inside_Organization/RetentionRisk/RI_ModuleValidation'), [:], FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'validation.DisengagementGlobalVariables.clickOrganizationByGlobalVar'('org_Name')

WebUI.rightClick(findTestObject('Object Repository/RRI_Inside_Organization/RetebtionRiskIndex/MonthlySummaryEventCRUDPage/Page_ProHance AI - Retention Risk/tspan_Nov - 24'))

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetebtionRiskIndex/MonthlySummaryEventCRUDPage/Page_ProHance AI - Retention Risk/div_Events'))

WebUI.delay(2)
WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetebtionRiskIndex/MonthlySummaryEventCRUDPage/Page_ProHance AI - Retention Risk/path'))

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetebtionRiskIndex/MonthlySummaryEventCRUDPage/Page_ProHance AI - Retention Risk/input_Event Name_ant-input css-mncuj7 ant-i_b32ef2'))

WebUI.setText(findTestObject('Object Repository/RRI_Inside_Organization/RetebtionRiskIndex/MonthlySummaryEventCRUDPage/Page_ProHance AI - Retention Risk/input_Event Name_ant-input css-mncuj7 ant-i_b32ef2_3'), 
    'add')

WebUI.setText(findTestObject('Object Repository/RRI_Inside_Organization/RetebtionRiskIndex/MonthlySummaryEventCRUDPage/Page_ProHance AI - Retention Risk/textarea_add'), 'add')

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetebtionRiskIndex/MonthlySummaryEventCRUDPage/Page_ProHance AI - Retention Risk/button_Save'))

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetebtionRiskIndex/MonthlySummaryEventCRUDPage/Page_ProHance AI - Retention Risk/div_Event added successfully'))

WebUI.rightClick(findTestObject('Object Repository/RRI_Inside_Organization/RetebtionRiskIndex/MonthlySummaryEventCRUDPage/Page_ProHance AI - Retention Risk/tspan_Nov - 24'))
WebUI.delay(2)
WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetebtionRiskIndex/MonthlySummaryEventCRUDPage/Page_ProHance AI - Retention Risk/div_Events'))
WebUI.delay(2)
WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetebtionRiskIndex/MonthlySummaryEventCRUDPage/Page_ProHance AI - Retention Risk/svg'))
WebUI.delay(2)
WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetebtionRiskIndex/MonthlySummaryEventCRUDPage/Page_ProHance AI - Retention Risk/input_Event Name_ant-input css-mncuj7 ant-i_b32ef2_3'))

WebUI.setText(findTestObject('Object Repository/RRI_Inside_Organization/RetebtionRiskIndex/MonthlySummaryEventCRUDPage/Page_ProHance AI - Retention Risk/input_Event Name_ant-input css-mncuj7 ant-i_b32ef2_9'), 
    'addupdate')

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetebtionRiskIndex/MonthlySummaryEventCRUDPage/Page_ProHance AI - Retention Risk/button_Update'))
WebUI.delay(2)
WebUI.rightClick(findTestObject('Object Repository/RRI_Inside_Organization/RetebtionRiskIndex/MonthlySummaryEventCRUDPage/Page_ProHance AI - Retention Risk/tspan_Nov - 24'))
WebUI.delay(2)
WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetebtionRiskIndex/MonthlySummaryEventCRUDPage/Page_ProHance AI - Retention Risk/div_Events_1'))
WebUI.delay(2)
WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetebtionRiskIndex/MonthlySummaryEventCRUDPage/Page_ProHance AI - Retention Risk/svg_1'))

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetebtionRiskIndex/MonthlySummaryEventCRUDPage/Page_ProHance AI - Retention Risk/span_Yes'))

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetebtionRiskIndex/MonthlySummaryEventCRUDPage/Page_ProHance AI - Retention Risk/div_Event deleted successfully'))

WebUI.closeBrowser()

