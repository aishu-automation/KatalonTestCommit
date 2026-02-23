import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.callTestCase(findTestCase('Common/RRI/TC_RRI_LoginWithValidCretentials'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('RRI_Inside_Organization/RetentionRiskIndex/RRI_ModuleValidation'), [:], FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'validation.DisengagementGlobalVariables.clickOrganizationByGlobalVar'('org_Name')

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetebtionRiskIndex/LongTermFiletrByRetentionRiskUserCountPage/Page_ProHance AI - Retention Risk/path'))

TestObject dropdownBtn = new TestObject('dropdownBtn')

dropdownBtn.addProperty('xpath', ConditionType.EQUALS, '//div[@class="MuiBox-root css-1hskriy"]/div[1]/div/div')

WebUI.click(dropdownBtn)

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetebtionRiskIndex/LongTermFiletrByRetentionRiskUserCountPage/Page_ProHance AI - Retention Risk/li_Retention Risk'))

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetebtionRiskIndex/LongTermFiletrByRetentionRiskUserCountPage/Page_ProHance AI - Retention Risk/button_Apply'))

int totalBars = 3
for (int i = 1; i <= totalBars; i++) {
	
	TestObject labelObj = new TestObject('label_' + i)
	
		labelObj.addProperty('xpath', ConditionType.EQUALS, ('(//*[@id=\'apexchartshorizontal-bar-widget7\']//*[name()=\'text\' and contains(@class,\'apexcharts-datalabel\')])[' +
			i) + ']')
	
		WebUI.waitForElementVisible(labelObj, 10)
	
		String expectedCount = WebUI.getText(labelObj).trim()
	
		WebUI.comment('Expected count = ' + expectedCount)
		TestObject barObj = new TestObject('bar_' + i)

	barObj.addProperty('xpath', ConditionType.EQUALS, ('(//*[@id=\'apexchartshorizontal-bar-widget7\']//*[name()=\'g\' and @seriesName=\'Disengaged\']//*[name()=\'path\'])[' +
		i) + ']')

	WebUI.waitForElementClickable(barObj, 10)

	WebUI.click(barObj)
	TestObject totalRecordsObj = new TestObject('totalRecords')
	
		totalRecordsObj.addProperty('xpath', ConditionType.EQUALS, '//div[b[text()=\'Total Records:\']]')
	
		WebUI.waitForElementPresent(totalRecordsObj, 10)
	
		String fullText = WebUI.getText(totalRecordsObj)
	
		def matcher = fullText =~ 'Total Records:\\s*(\\d+)'
	
		String actualCount = matcher ? (matcher[0])[1] : '0'
	
		WebUI.comment('Actual Total Records = ' + actualCount)
	
		//  Validate count
		WebUI.verifyMatch(actualCount, expectedCount, false)

    WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetebtionRiskIndex/LongTermFiletrByRetentionRiskUserCountPage/Page_ProHance AI - Retention Risk/button_All Groups_MuiButtonBase-root MuiIco_474954'))
}
WebUI.closeBrowser()