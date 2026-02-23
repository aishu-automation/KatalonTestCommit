import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.callTestCase(findTestCase('Common/RRI/TC_RRI_LoginWithValidCretentials'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('RRI_Inside_Organization/RetentionRisk/RI_ModuleValidation'), [:], FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'validation.DisengagementGlobalVariables.clickOrganizationByGlobalVar'('org_Name')

String allGroups = CustomKeywords.'validation.DisengagementGlobalVariables.getUserGroup'(
	findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_UserGroupPage/Page_ProHance AI - Retention Risk/div_User GroupsAll Groups'),
	'User Groups'
)
println("Extracted Text: " + allGroups)

TestObject atriskText = new TestObject('atriskText')
atriskText.addProperty('xpath', ConditionType.EQUALS, '//div[contains(text(),"At Risk")]/../div[2]/span[1]')
String atRisk = WebUI.getText(atriskText)

println("Text of noRisk: " + atRisk)

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
String na = WebUI.getText(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_UserGroupPage/Page_ProHance AI - Retention Risk/div_All Groups'))

println(na)

WebUI.verifyEqual(allGroups, na)
WebUI.verifyEqual(allGroups, na)



def expectedCheckboxState = [
	'At Risk'   : true,
	'No Risk' : false,
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

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetentionRisk/RI-OverallSummaryAtRiskPage/Page_ProHance AI - Retention Risk/div_Total Records 5Displaying 1 to 5Notes'))

TestObject totalRecordsObj = new TestObject("totalRecords")
totalRecordsObj.addProperty('xpath', ConditionType.EQUALS, "//div[b[text()='Total Records:']]")

WebUI.waitForElementPresent(totalRecordsObj, 10)

String fullText = WebUI.getText(totalRecordsObj)
WebUI.comment("Full text: " + fullText)

def matcher = (fullText =~ /Total Records:\s*(\d+)/)
String totalRecordsStr = matcher ? matcher[0][1] : "0"

WebUI.comment("Total Records value: " + totalRecordsStr)

WebUI.verifyMatch(totalRecordsStr, atRisk, false)
WebUI.closeBrowser()

