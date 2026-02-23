import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.callTestCase(findTestCase('Common/RRI/TC_RRI_LoginWithValidCretentials'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('RRI_Inside_Organization/RetentionRiskIndex/RRI_ModuleValidation'), [:], FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'validation.DisengagementGlobalVariables.clickOrganizationByGlobalVar'('org_Name')

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetebtionRiskIndex/LongTermDropdownDefaultValidationPage/Page_ProHance AI - Retention Risk/path'))

TestObject dropdownBtn = new TestObject('dropdownBtn')

dropdownBtn.addProperty('xpath', ConditionType.EQUALS, '//div[@class="MuiBox-root css-1hskriy"]/div[1]/div/div')

WebUI.click(dropdownBtn)

TestObject disengagementOption = findTestObject('Object Repository/RRI_Inside_Organization/RetebtionRiskIndex/LongTermDropdownDefaultValidationPage/Page_ProHance AI - Retention Risk/li_Disengagement')
TestObject retentionRiskOption = findTestObject('Object Repository/RRI_Inside_Organization/RetebtionRiskIndex/LongTermDropdownDefaultValidationPage/Page_ProHance AI - Retention Risk/li_RetentionRisk')

WebUI.waitForElementVisible(disengagementOption, 10)
WebUI.waitForElementVisible(retentionRiskOption, 10)

WebUI.verifyElementText(disengagementOption, "Disengagement")
WebUI.verifyElementText(retentionRiskOption, "Retention Risk")

WebUI.closeBrowser()

