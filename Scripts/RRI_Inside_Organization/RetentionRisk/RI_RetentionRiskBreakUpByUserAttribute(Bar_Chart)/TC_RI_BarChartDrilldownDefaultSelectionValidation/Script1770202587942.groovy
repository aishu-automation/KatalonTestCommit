import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.callTestCase(findTestCase('Common/RRI/TC_RRI_LoginWithValidCretentials'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('RRI_Inside_Organization/RetentionRisk/RI_ModuleValidation'), [:], FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'validation.DisengagementGlobalVariables.clickOrganizationByGlobalVar'('org_Name')

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetentionRisk/RI_BarChart_DrilldownDefaultSelectionPage/Page_ProHance AI - Retention Risk/div_Retention BreakupBy User Role (Top 5)  _3bdf33'))

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetentionRisk/RI_BarChart_DrilldownDefaultSelectionPage/Page_ProHance AI - Retention Risk/path'))

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetentionRisk/RI_BarChart_DrilldownDefaultSelectionPage/Page_ProHance AI - Retention Risk/div_Retention RiskAt RiskNo Risk'))
def expectedCheckboxState = [
	'At Risk'   : true,
	'No Risk'    : false
]


expectedCheckboxState.each { label, expectedState ->

	TestObject checkbox = new TestObject("chk_${label}")
	checkbox.addProperty(
		'xpath',
		ConditionType.EQUALS,
		"//label[.//text()[contains(normalize-space(.),'${label}')]]//input"
	)

	WebUI.waitForElementPresent(checkbox, 10)

	if (expectedState) {
		WebUI.verifyElementChecked(checkbox, 5)
		WebUI.comment("${label} checkbox is CHECKED as expected")
	} else {
		WebUI.verifyElementNotChecked(checkbox, 5)
		WebUI.comment(" ${label} checkbox is UNCHECKED as expected")
	}
}
WebUI.closeBrowser()

