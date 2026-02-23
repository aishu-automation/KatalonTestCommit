import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.callTestCase(findTestCase('Common/RRI/TC_RRI_LoginWithValidCretentials'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('RRI_Inside_Organization/RetentionRisk/RI_ModuleValidation'), [:], FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'validation.DisengagementGlobalVariables.clickOrganizationByGlobalVar'('org_Name')
//get usergroup name
String allGroups = CustomKeywords.'validation.DisengagementGlobalVariables.getUserGroup'(
	findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_UserGroupPage/Page_ProHance AI - Retention Risk/div_User GroupsAll Groups'),
	'User Groups'
)
println("Extracted Text: " + allGroups)

TestObject noriskText = new TestObject('noriskText')
noriskText.addProperty('xpath', ConditionType.EQUALS, '//div[contains(text(),"No Risk")]/../div[2]/span[1]')
String noRisk = WebUI.getText(noriskText)

println("Text of noRisk: " + noRisk)

TestObject chartContainer = new TestObject()
chartContainer.addProperty(
	"xpath",
	com.kms.katalon.core.testobject.ConditionType.EQUALS,
	"(//div[contains(@class,'apexcharts-canvas')])[2]"
)
WebUI.waitForElementVisible(chartContainer, 15)
WebUI.waitForElementClickable(chartContainer, 15)
// Click chart
WebUI.click(chartContainer)
String na = WebUI.getText(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_UserGroupPage/Page_ProHance AI - Retention Risk/div_All Groups'))

println(na)

WebUI.verifyEqual(allGroups, na)



def expectedCheckboxState = [
	'At Risk'   : false,
	'No Risk' : true,
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
WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetentionRisk/RI-OverallSummaryNoRiskPage/Page_ProHance AI - Retention Risk/div_Total Records 101Displaying 1 to 10'))

TestObject totalRecordsObj = new TestObject("totalRecords")
totalRecordsObj.addProperty('xpath', ConditionType.EQUALS, "//div[b[text()='Total Records:']]")

WebUI.waitForElementPresent(totalRecordsObj, 10)

String fullText = WebUI.getText(totalRecordsObj)
WebUI.comment("Full text: " + fullText)

def matcher = (fullText =~ /Total Records:\s*(\d+)/)
String totalRecordsStr = matcher ? matcher[0][1] : "0"

WebUI.comment("Total Records value: " + totalRecordsStr)

WebUI.verifyMatch(totalRecordsStr, noRisk, false)
WebUI.closeBrowser()

