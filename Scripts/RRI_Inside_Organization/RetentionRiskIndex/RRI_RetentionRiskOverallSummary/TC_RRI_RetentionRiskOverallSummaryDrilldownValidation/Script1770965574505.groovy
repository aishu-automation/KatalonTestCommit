import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.WebElement

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.callTestCase(findTestCase('Common/RRI/TC_RRI_LoginWithValidCretentials'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('RRI_Inside_Organization/RetentionRiskIndex/RRI_ModuleValidation'), [:], FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'validation.DisengagementGlobalVariables.clickOrganizationByGlobalVar'('org_Name')

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetebtionRiskIndex/RetentionRiskOverallSummaryDrilldownPage/Page_ProHance AI - Retention Risk/path'))
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

// RI
TestObject allCheckboxes1 = new TestObject()

allCheckboxes1.addProperty('xpath', ConditionType.EQUALS, '//input[@class=\'ant-checkbox-input\']')

List<WebElement> checkboxes1 = WebUI.findWebElements(allCheckboxes1, 10)

assert checkboxes1.size() > 0 : 'No checkboxes found on the page'

boolean allChecked1 = true

for (WebElement checkbox1 : checkboxes1) {
	if (!(checkbox1.isSelected())) {
		allChecked1 = false

		WebUI.comment('One of the checkboxes is NOT checked by default')
	}
}

assert allChecked1 : 'Not all checkboxes are checked by default'

WebUI.comment('All checkboxes are checked by default: ' + allChecked1)

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetebtionRiskIndex/RetentionRiskOverallSummaryDrilldownPage/Page_ProHance AI - Retention Risk/span_Username'))

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetebtionRiskIndex/RetentionRiskOverallSummaryDrilldownPage/Page_ProHance AI - Retention Risk/span_Employee ID'))

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetebtionRiskIndex/RetentionRiskOverallSummaryDrilldownPage/Page_ProHance AI - Retention Risk/span_User Group'))

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetebtionRiskIndex/RetentionRiskOverallSummaryDrilldownPage/Page_ProHance AI - Retention Risk/th_Disengagement (12 W)'))

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetebtionRiskIndex/RetentionRiskOverallSummaryDrilldownPage/Page_ProHance AI - Retention Risk/th_Retention Risk (12 M)'))

WebUI.closeBrowser()

