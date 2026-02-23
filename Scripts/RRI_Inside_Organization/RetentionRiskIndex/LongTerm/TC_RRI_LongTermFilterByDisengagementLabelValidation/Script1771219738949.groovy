import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.callTestCase(findTestCase('Common/RRI/TC_RRI_LoginWithValidCretentials'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('RRI_Inside_Organization/RetentionRiskIndex/RRI_ModuleValidation'), [:], FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'validation.DisengagementGlobalVariables.clickOrganizationByGlobalVar'('org_Name')

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetebtionRiskIndex/LongTermFilterByDisengagementLabelValidationPage/Page_ProHance AI - Retention Risk/svg_(Long term)_MuiSvgIcon-root MuiSvgIcon-_bca184'))

TestObject dropdownBtn = new TestObject('dropdownBtn')

dropdownBtn.addProperty('xpath', ConditionType.EQUALS, '//div[@class="MuiBox-root css-1hskriy"]/div[1]/div/div')

WebUI.click(dropdownBtn)

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetebtionRiskIndex/LongTermFilterByDisengagementLabelValidationPage/Page_ProHance AI - Retention Risk/li_Disengagement'))

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetebtionRiskIndex/LongTermFilterByDisengagementLabelValidationPage/Page_ProHance AI - Retention Risk/button_Apply'))

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetebtionRiskIndex/LongTermFilterByDisengagementLabelValidationPage/Page_ProHance AI - Retention Risk/tspan_Last 4W'))

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetebtionRiskIndex/LongTermFilterByDisengagementLabelValidationPage/Page_ProHance AI - Retention Risk/tspan_Last 8W'))

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetebtionRiskIndex/LongTermFilterByDisengagementLabelValidationPage/Page_ProHance AI - Retention Risk/foreignobject_apexcharts-flip-y         tra_c76794'))

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetebtionRiskIndex/LongTermFilterByDisengagementLabelValidationPage/Page_ProHance AI - Retention Risk/tspan_Last 12W'))

WebUI.closeBrowser()

