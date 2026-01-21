import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.callTestCase(findTestCase('Common/RRI/TC_RRI_LoginWithValidCretentials'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('RRI_Inside_Organization/Disengagement/DI_ModuleValidation'), [:], FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'validation.DisengagementGlobalVariables.clickOrganizationByGlobalVar'('org_Name')

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_OverallSummaryHighPage/Page_ProHance AI - Retention Risk/div_High'))

TestObject highText = new TestObject('highText')
highText.addProperty('xpath', ConditionType.EQUALS, '//div[contains(text(),"High")]/../div[2]/span[1]')
String high = WebUI.getText(highText)

println("Text of high element: " + high)

WebUI.waitForPageLoad(30)
WebUI.delay(3)


TestObject highLink = new TestObject('highLink')
highLink.addProperty(
	'xpath',
	ConditionType.EQUALS,
	"//div[contains(normalize-space(.),'High')]/../div[2]/span[1]"
) 

WebUI.waitForElementPresent(highLink, 30)
WebUI.scrollToElement(highLink, 5)
WebUI.delay(1)

WebUI.executeJavaScript(
	'arguments[0].click();',
	Arrays.asList(WebUI.findWebElement(highLink, 10))
)
WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_OverallSummaryHighPage/Page_ProHance AI - Retention Risk/circle_Disengagement_apexcharts-radialbar-hollow'))
def expectedCheckboxState = [
	'High'   : true,
	'Medium' : false,
	'Low'    : false
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

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_OverallSummaryHighPage/Page_ProHance AI - Retention Risk/div_Total Records 1Displaying 1 to 1'))

WebUI.closeBrowser()

