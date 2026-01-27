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


int totalBars = 3

for (int i = 1; i <= totalBars; i++) {
	//WebUI.comment(('---- Validating bar index: ' + i) + ' ----')

	//  Get value from label
	TestObject labelObj = new TestObject('label_' + i)

	labelObj.addProperty('xpath', ConditionType.EQUALS, ('(//*[@id=\'apexchartshorizontal-bar-widget7\']//*[name()=\'text\' and contains(@class,\'apexcharts-datalabel\')])[' +
		i) + ']')

	WebUI.waitForElementVisible(labelObj, 10)

	String expectedCount = WebUI.getText(labelObj).trim()

	WebUI.comment('Expected count = ' + expectedCount)

	//  Click corresponding bar
	TestObject barObj = new TestObject('bar_' + i)

	barObj.addProperty('xpath', ConditionType.EQUALS, ('(//*[@id=\'apexchartshorizontal-bar-widget7\']//*[name()=\'g\' and @seriesName=\'Disengaged\']//*[name()=\'path\'])[' +
		i) + ']')

	WebUI.waitForElementClickable(barObj, 10)

	WebUI.click(barObj)

	// 3c️⃣ Read Total Records
	TestObject totalRecordsObj = new TestObject('totalRecords')

	totalRecordsObj.addProperty('xpath', ConditionType.EQUALS, '//div[b[text()=\'Total Records:\']]')

	WebUI.waitForElementPresent(totalRecordsObj, 10)

	String fullText = WebUI.getText(totalRecordsObj)

	def matcher = fullText =~ 'Total Records:\\s*(\\d+)'

	String actualCount = matcher ? (matcher[0])[1] : '0'

	WebUI.comment('Actual Total Records = ' + actualCount)

	//  Validate count
	WebUI.verifyMatch(actualCount, expectedCount, false)

	//  Click Back button to return to chart
	WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_Disengaged_Emp_UserCountPage/Page_ProHance AI - Retention Risk/button_Select Value_MuiButtonBase-root MuiI_029fad'))


	WebUI.waitForElementVisible(labelObj, 10)
}

// 4️⃣ Close browser
WebUI.closeBrowser()

