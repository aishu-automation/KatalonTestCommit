import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.callTestCase(findTestCase('Common/RRI/TC_RRI_LoginWithValidCretentials'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('RRI_Inside_Organization/RetentionRisk/RI_ModuleValidation'), [:], FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'validation.DisengagementGlobalVariables.clickOrganizationByGlobalVar'('org_Name')

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetentionRisk/RI_RetentionRiskBarChartFilterPage/Page_ProHance AI - Retention Risk/div_-_cell-1-undefined'))

TestObject dropdownBtn = new TestObject('dropdownBtn')

dropdownBtn.addProperty('xpath', ConditionType.EQUALS, '(//div[@class=\'MuiFormControl-root MuiFormControl-fullWidth css-x38ezb\']/div/div)[1]')

WebUI.click(dropdownBtn)

// Step 2: Click the desired option
TestObject option = new TestObject('optionUSA')

option.addProperty('xpath', ConditionType.EQUALS, '//li[contains(text(),\'Super Groups\')]')

WebUI.click(option)

TestObject show = new TestObject('show')

show.addProperty('xpath', ConditionType.EQUALS, '(//div[@class=\'MuiFormControl-root MuiFormControl-fullWidth css-x38ezb\']/div/div)[2]')

WebUI.click(show)

// Step 2: Click the desired option
TestObject showOpt = new TestObject('showOpt')

showOpt.addProperty('xpath', ConditionType.EQUALS, '//li[contains(text(),\'Top\')]')

WebUI.click(showOpt)

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetentionRisk/RI_RetentionRiskBarChartFilterPage/Page_ProHance AI - Retention Risk/button_Apply'))


WebUI.closeBrowser()

