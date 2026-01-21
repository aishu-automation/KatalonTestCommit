import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import internal.GlobalVariable as GlobalVariable

WebUI.callTestCase(findTestCase('Common/RRI/TC_RRI_LoginWithValidCretentials'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('RRI_Inside_Organization/Disengagement/DI_ModuleValidation'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_OverallSummaryDefaultPage/Page_ProHance AI - Retention Risk/div_PH Demo (199 Server)'))
//get user group name
String allGroups = CustomKeywords.'validation.DisengagementGlobalVariables.getUserGroup'(
	findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_UserGroupPage/Page_ProHance AI - Retention Risk/div_User GroupsAll Groups'),
	'User Groups'
)

println("Extracted Text: " + allGroups)

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_OverallSummaryDefaultPage/Page_ProHance AI - Retention Risk/svg_Disengagement_MuiSvgIcon-root MuiSvgIco_98517e'))

//get usergroup name in filter and cmpare
WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_UserGroupPage/Page_ProHance AI - Retention Risk/div_All Groups'))
String na = WebUI.getText(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_UserGroupPage/Page_ProHance AI - Retention Risk/div_All Groups'))
println(na)
WebUI.verifyEqual(allGroups, na)

WebUI.waitForPageLoad(30)
// Create TestObject for all checkboxes
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

