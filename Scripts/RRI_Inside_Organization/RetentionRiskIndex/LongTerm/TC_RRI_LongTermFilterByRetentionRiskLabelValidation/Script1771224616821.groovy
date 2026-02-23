import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.callTestCase(findTestCase('Common/RRI/TC_RRI_LoginWithValidCretentials'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('RRI_Inside_Organization/RetentionRiskIndex/RRI_ModuleValidation'), [:], FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'validation.DisengagementGlobalVariables.clickOrganizationByGlobalVar'('org_Name')

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetebtionRiskIndex/LongTermFilterByRetentionRiskLabelValidationPage/Page_ProHance AI - Retention Risk/path'))

TestObject dropdownBtn = new TestObject('dropdownBtn')

dropdownBtn.addProperty('xpath', ConditionType.EQUALS, '//div[@class="MuiBox-root css-1hskriy"]/div[1]/div/div')

WebUI.click(dropdownBtn)


WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetebtionRiskIndex/LongTermFilterByRetentionRiskLabelValidationPage/Page_ProHance AI - Retention Risk/li_Retention Risk'))

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetebtionRiskIndex/LongTermFilterByRetentionRiskLabelValidationPage/Page_ProHance AI - Retention Risk/button_Apply'))

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetebtionRiskIndex/LongTermFilterByRetentionRiskLabelValidationPage/Page_ProHance AI - Retention Risk/tspan_Last 1M'))

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetebtionRiskIndex/LongTermFilterByRetentionRiskLabelValidationPage/Page_ProHance AI - Retention Risk/tspan_Last 2M'))

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetebtionRiskIndex/LongTermFilterByRetentionRiskLabelValidationPage/Page_ProHance AI - Retention Risk/tspan_Last 3M'))

WebUI.closeBrowser()

