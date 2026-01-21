import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import internal.GlobalVariable as GlobalVariable

WebUI.callTestCase(findTestCase('Common/RRI/TC_RRI_LoginWithValidCretentials'), [('url') : 'http://10.10.10.188:5173/prohanceai/v3'
        , ('username') : 'superadmin', ('password') : 'HeCM15nHKBI='], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('RRI_Inside_Organization/Disengagement/DI_ModuleValidation'), [:], FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'validation.DisengagementGlobalVariables.clickOrganizationByGlobalVar'('org_Name')

TestObject low = new TestObject('low')

low.addProperty('xpath', ConditionType.EQUALS, '(//*[@class=\'apexcharts-series-markers\']/../*[1]//*[name()=\'path\'])[1]')

WebUI.waitForElementClickable(low, 20)

WebUI.click(low)

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_WeeklyDisengagementSummaryLowPage/Page_ProHance AI - Retention Risk/div_Low'))

//checkbox filter validation
def expectedCheckboxState = [('High') : false, ('Medium') : false, ('Low') : true]

expectedCheckboxState.each({ def label, def expectedState ->
		TestObject checkbox = new TestObject("chk_$label")

		checkbox.addProperty('xpath', ConditionType.EQUALS, "//label[.//text()[contains(normalize-space(.),'$label')]]//input")

		WebUI.waitForElementPresent(checkbox, 10)

		if (expectedState) {
			WebUI.verifyElementChecked(checkbox, 5)

			WebUI.comment("$label checkbox is CHECKED as expected")
		} else {
			WebUI.verifyElementNotChecked(checkbox, 5)

			WebUI.comment(" $label checkbox is UNCHECKED as expected")
		}
	})

WebUI.closeBrowser()

