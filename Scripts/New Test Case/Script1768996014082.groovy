import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.callTestCase(findTestCase('Common/RRI/TC_RRI_LoginWithValidCretentials'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('RRI_Inside_Organization/Disengagement/DI_ModuleValidation'), [:], FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'validation.DisengagementGlobalVariables.clickOrganizationByGlobalVar'('org_Name')

String allGroups = CustomKeywords.'validation.DisengagementGlobalVariables.getUserGroup'(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_UserGroupPage/Page_ProHance AI - Retention Risk/div_User GroupsAll Groups'), 
    'User Groups')

println('Extracted Text: ' + allGroups)

TestObject low = new TestObject('low')

low.addProperty('xpath', ConditionType.EQUALS, '(//*[@class=\'apexcharts-series-markers\']/../*[1]//*[name()=\'path\'])[1]')

WebUI.waitForElementClickable(low, 20)

WebUI.click(low)

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_WeeklyDisengagementSummaryLowPage/Page_ProHance AI - Retention Risk/div_Low'))

String na = WebUI.getText(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_UserGroupPage/Page_ProHance AI - Retention Risk/div_All Groups'))

println(na)

WebUI.verifyEqual(allGroups, na)

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_OverallSummaryBackButtonPage/Page_ProHance AI - Retention Risk/button_Select Value_MuiButtonBase-root MuiI_77586b'))

//medium
TestObject medium = new TestObject('medium')

medium.addProperty('xpath', ConditionType.EQUALS, '(//*[@class=\'apexcharts-series-markers\']/../*[1]//*[name()=\'path\'])[3]')

WebUI.waitForElementClickable(medium, 20)

WebUI.click(medium)

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_WeeklyDisengagementSummaryMediumPage/Page_ProHance AI - Retention Risk/div_Medium'))

String med = WebUI.getText(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_UserGroupPage/Page_ProHance AI - Retention Risk/div_All Groups'))

println(med)

WebUI.verifyEqual(allGroups, med)

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_OverallSummaryBackButtonPage/Page_ProHance AI - Retention Risk/button_Select Value_MuiButtonBase-root MuiI_77586b'))

//high
TestObject high = new TestObject('high')

high.addProperty('xpath', ConditionType.EQUALS, '(//*[@class=\'apexcharts-series-markers\']/../*[1]//*[name()=\'path\'])[5]')

WebUI.waitForElementClickable(high, 20)

WebUI.click(high)

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_WeeklyDisengagementSummaryHighPage/Page_ProHance AI - Retention Risk/div_High'))

String hig = WebUI.getText(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_UserGroupPage/Page_ProHance AI - Retention Risk/div_All Groups'))

println(hig)

WebUI.verifyEqual(allGroups, hig)

TestObject records = new TestObject('records')

records.addProperty('xpath', ConditionType.EQUALS, '//span[contains(text(),"Records to display:")]/../div//span[@class="ant-select-selection-wrap"]/span[2]')
int tot_record = WebUI.getText(records).
println(tot_record)
WebUI.verifyEqual(allGroups, 10)
