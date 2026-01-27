import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.callTestCase(findTestCase('Common/RRI/TC_RRI_LoginWithValidCretentials'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('RRI_Inside_Organization/Disengagement/DI_ModuleValidation'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_barChart_BreakupByUserAttributeReportFilterChangePage/Page_ProHance AI - Retention Risk/div_PH Demo (199 Server)'))

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_barChart_BreakupByUserAttributeReportFilterChangePage/Page_ProHance AI - Retention Risk/svg_By Super Groups (Top 5)_MuiSvgIcon-root_1f6b44'))

TestObject dropdownBtn = new TestObject('dropdownBtn')
dropdownBtn.addProperty('xpath', ConditionType.EQUALS, "(//div[@class=\'MuiFormControl-root MuiFormControl-fullWidth css-x38ezb\']/div/div)[1]")
WebUI.click(dropdownBtn)

// Step 2: Click the desired option
TestObject option = new TestObject('optionUSA')
option.addProperty('xpath', ConditionType.EQUALS, "//ul[@id=\'«r1r»\']/li[contains(text(),'Super Groups')]")
WebUI.click(option)

TestObject show = new TestObject('show')
show.addProperty('xpath', ConditionType.EQUALS, "(//div[@class=\'MuiFormControl-root MuiFormControl-fullWidth css-x38ezb\']/div/div)[2]")
WebUI.click(show)

// Step 2: Click the desired option
TestObject showOpt = new TestObject('showOpt')
showOpt.addProperty('xpath', ConditionType.EQUALS, "//ul[@id=\'«r1s»\']/li[contains(text(),'Top')]")
WebUI.click(showOpt)
WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_barChart_BreakupByUserAttributeReportFilterChangePage/Page_ProHance AI - Retention Risk/button_Apply'))

String test = WebUI.getText(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_barChart_BreakupByUserAttributeReportFilterChangePage/Page_ProHance AI - Retention Risk/foreignobject_apexcharts-flip-y         tra_c76794'))
println(test)
//WebUI.closeBrowser()

