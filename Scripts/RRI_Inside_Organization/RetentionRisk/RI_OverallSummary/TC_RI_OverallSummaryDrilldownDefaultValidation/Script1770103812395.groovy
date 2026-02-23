import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.WebElement

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.callTestCase(findTestCase('Common/RRI/TC_RRI_LoginWithValidCretentials'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('RRI_Inside_Organization/RetentionRisk/RI_ModuleValidation'), [:], FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'validation.DisengagementGlobalVariables.clickOrganizationByGlobalVar'('org_Name')

String allGroups = CustomKeywords.'validation.DisengagementGlobalVariables.getUserGroup'(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_UserGroupPage/Page_ProHance AI - Retention Risk/div_User GroupsAll Groups'),
	'User Groups')

println('Extracted Text: ' + allGroups)

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetentionRisk/RI_OverallSummaryDefaultPage/Page_ProHance AI - Retention Risk/svg_Retention Risk_MuiSvgIcon-root MuiSvgIc_47fccd'))
String na = WebUI.getText(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_UserGroupPage/Page_ProHance AI - Retention Risk/div_All Groups'))

println(na)

WebUI.verifyEqual(allGroups, na)

WebUI.waitForPageLoad(30)

TestObject allCheckboxes = new TestObject()

allCheckboxes.addProperty('xpath', ConditionType.EQUALS, '//input[@class=\'ant-checkbox-input\']')

List<WebElement> checkboxes = WebUI.findWebElements(allCheckboxes, 10)

assert checkboxes.size() > 0 : 'No checkboxes found on the page'

boolean allChecked = true

for (WebElement checkbox : checkboxes) {
	if (!(checkbox.isSelected())) {
		allChecked = false

		WebUI.comment('One of the checkboxes is NOT checked by default')
	}
}

assert allChecked : 'Not all checkboxes are checked by default'

WebUI.comment('All checkboxes are checked by default: ' + allChecked)

WebUI.closeBrowser()

