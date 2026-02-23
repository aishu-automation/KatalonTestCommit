import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import internal.GlobalVariable as GlobalVariable

WebUI.callTestCase(findTestCase('Common/RRI/TC_RRI_LoginWithValidCretentials'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('RRI_Inside_Organization/Disengagement/DI_ModuleValidation'), [:], FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'validation.DisengagementGlobalVariables.clickOrganizationByGlobalVar'('org_Name')

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_barChart_BreakupByUserAttributeReportFilterChangePage/Page_ProHance AI - Retention Risk/svg_By Super Groups (Top 5)_MuiSvgIcon-root_1f6b44'))

TestObject dropdownBtn = new TestObject('dropdownBtn')

dropdownBtn.addProperty('xpath', ConditionType.EQUALS, '(//div[@class=\'MuiFormControl-root MuiFormControl-fullWidth css-x38ezb\']/div/div)[1]')

WebUI.click(dropdownBtn)
String attribute = GlobalVariable.superGroup;
println(attribute);
TestObject option = new TestObject('optionUSA')

option.addProperty(
    'xpath',
    ConditionType.EQUALS,
    "//li[contains(text(),'${attribute}')]"
)

WebUI.click(option)

TestObject show = new TestObject('show')

show.addProperty('xpath', ConditionType.EQUALS, '(//div[@class=\'MuiFormControl-root MuiFormControl-fullWidth css-x38ezb\']/div/div)[2]')

WebUI.click(show)

// Step 2: Click the desired option
TestObject showOpt = new TestObject('showOpt')

showOpt.addProperty('xpath', ConditionType.EQUALS, '//li[contains(text(),\'Top\')]')

WebUI.click(showOpt)

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_barChart_BreakupByUserAttributeReportFilterChangePage/Page_ProHance AI - Retention Risk/button_Apply'))

String test = WebUI.getText(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_barChart_BreakupByUserAttributeReportFilterChangePage/Page_ProHance AI - Retention Risk/foreignobject_apexcharts-flip-y         tra_c76794'))

println(test)
WebUI.closeBrowser()

