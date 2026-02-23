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

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_OverallSummaryBackButtonPage/Page_ProHance AI - Retention Risk/svg_Disengagement_MuiSvgIcon-root MuiSvgIco_98517e'))

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_OverallSummaryBackButtonPage/Page_ProHance AI - Retention Risk/ol_DashboardRecord'))

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_OverallSummaryBackButtonPage/Page_ProHance AI - Retention Risk/button_Select Value_MuiButtonBase-root MuiI_77586b'))

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_OverallSummaryBackButtonPage/Page_ProHance AI - Retention Risk/div_High'))

TestObject chartContainer = new TestObject()
chartContainer.addProperty(
	"xpath",
	com.kms.katalon.core.testobject.ConditionType.EQUALS,
	"(//div[contains(@class,'apexcharts-canvas')])[1]"
)
WebUI.waitForElementVisible(chartContainer, 15)
WebUI.waitForElementClickable(chartContainer, 15)
// Click chart
WebUI.click(chartContainer)

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_OverallSummaryBackButtonPage/Page_ProHance AI - Retention Risk/ol_DashboardRecord'))

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_OverallSummaryBackButtonPage/Page_ProHance AI - Retention Risk/button_Select Value_MuiButtonBase-root MuiI_77586b'))

TestObject chartContainer1 = new TestObject()
chartContainer1.addProperty(
	"xpath",
	com.kms.katalon.core.testobject.ConditionType.EQUALS,
	"(//div[contains(@class,'apexcharts-canvas')])[2]"
)
WebUI.waitForElementVisible(chartContainer1, 15)
WebUI.waitForElementClickable(chartContainer1, 15)
// Click chart
WebUI.click(chartContainer1)

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_OverallSummaryBackButtonPage/Page_ProHance AI - Retention Risk/ol_DashboardRecord'))

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_OverallSummaryBackButtonPage/Page_ProHance AI - Retention Risk/button_Select Value_MuiButtonBase-root MuiI_77586b'))

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_OverallSummaryBackButtonPage/Page_ProHance AI - Retention Risk/div_Low'))

TestObject chartContainer2 = new TestObject()
chartContainer2.addProperty(
	"xpath",
	com.kms.katalon.core.testobject.ConditionType.EQUALS,
	"(//div[contains(@class,'apexcharts-canvas')])[2]"
)
WebUI.waitForElementVisible(chartContainer2, 15)
WebUI.waitForElementClickable(chartContainer2, 15)
// Click chart
WebUI.click(chartContainer2)

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_OverallSummaryBackButtonPage/Page_ProHance AI - Retention Risk/svg_Select Value_MuiSvgIcon-root MuiSvgIcon_6582a7'))

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_OverallSummaryBackButtonPage/Page_ProHance AI - Retention Risk/div_Disengagement0112 - 0712'))

WebUI.closeBrowser()

