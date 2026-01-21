import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.callTestCase(findTestCase('Common/RRI/TC_RRI_LoginWithValidCretentials'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('RRI_Inside_Organization/Disengagement/DI_ModuleValidation'), [:], FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'validation.DisengagementGlobalVariables.clickOrganizationByGlobalVar'('org_Name')

//get usergroup name
String allGroups = CustomKeywords.'validation.DisengagementGlobalVariables.getUserGroup'(
	findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_UserGroupPage/Page_ProHance AI - Retention Risk/div_User GroupsAll Groups'),
	'User Groups'
)
println("Extracted Text: " + allGroups)

//get count of users in medium risk
WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_OverallSummaryMediumPage/Page_ProHance AI - Retention Risk/div_Medium'))
TestObject mediumText = new TestObject('mediumText')
mediumText.addProperty('xpath', ConditionType.EQUALS, '//div[contains(text(),"Medium")]/../div[2]/span[1]')
String medium = WebUI.getText(mediumText)
println("Text of Medium element: " + medium)

WebUI.waitForPageLoad(30)
WebUI.delay(3)


TestObject mediumLink = new TestObject('mediumLink')
mediumLink.addProperty(
	'xpath',
	ConditionType.EQUALS,
	"//div[contains(normalize-space(.),'Medium')]/../div[2]/span[1]"
)

WebUI.waitForElementPresent(mediumLink, 30)
WebUI.scrollToElement(mediumLink, 5)
WebUI.delay(1)

WebUI.executeJavaScript(
	'arguments[0].click();',
	Arrays.asList(WebUI.findWebElement(mediumLink, 10))
)

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_OverallSummaryMediumPage/Page_ProHance AI - Retention Risk/div_Medium_1'))
String na = WebUI.getText(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_UserGroupPage/Page_ProHance AI - Retention Risk/div_All Groups'))

println(na)

WebUI.verifyEqual(allGroups, na)
def expectedCheckboxState = [
	'High'   : false,
	'Medium' : true,
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

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_OverallSummaryMediumPage/Page_ProHance AI - Retention Risk/div_Total Records 16Displaying 1 to 10'))
TestObject totalRecordsObj = new TestObject("totalRecords")
totalRecordsObj.addProperty('xpath', ConditionType.EQUALS, "//div[b[text()='Total Records:']]")

WebUI.waitForElementPresent(totalRecordsObj, 10)

String fullText = WebUI.getText(totalRecordsObj)
WebUI.comment("Full text: " + fullText)

def matcher = (fullText =~ /Total Records:\s*(\d+)/)
String totalRecordsStr = matcher ? matcher[0][1] : "0"

WebUI.comment("Total Records value: " + totalRecordsStr)

WebUI.verifyMatch(totalRecordsStr, medium, false)
WebUI.closeBrowser()

