import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.interactions.Actions

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.callTestCase(findTestCase('Common/RRI/TC_RRI_LoginWithValidCretentials'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('RRI_Inside_Organization/RetentionRisk/RI_ModuleValidation'), [:], FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'validation.DisengagementGlobalVariables.clickOrganizationByGlobalVar'('org_Name')

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetentionRisk/RI_RetentionRiskBarChartOverallSummaryDtilldownPage/Page_ProHance AI - Retention Risk/svg_By Super Groups (Top 5)_MuiSvgIcon-root_1f6b44'))
TestObject bar = new TestObject('mediumBar')

bar.addProperty('xpath', ConditionType.EQUALS, '(//*[name()=\'g\' and @seriesName="AtxRisk"]//*[name()=\'path\'])[1]')

def driver = DriverFactory.getWebDriver()

Actions actions = new Actions(driver)

actions.moveToElement(WebUI.findWebElement(bar, 10)).perform()

TestObject tooltip = new TestObject('tooltip')

tooltip.addProperty('xpath', ConditionType.EQUALS, '//div[contains(@class,\'apexcharts-tooltip\') and not(contains(@style,\'display: none\'))]')

WebUI.waitForElementVisible(tooltip, 5)

String tooltipText = WebUI.getText(tooltip)

//println("Tooltip content = " + tooltipText)
String[] lines = tooltipText.split('\\r?\\n')

String finalTooltipLine = (lines[(lines.length - 1)]).trim()

//println("FINAL tooltip = " + finalTooltipLine)
String group = (finalTooltipLine.split(',')[0]).trim( // "Product Support"
	)

// Step 2: Extract records number using regex
def recordsMatcher = finalTooltipLine =~ '-\\s*(\\d+)\\s*\\('

String records = recordsMatcher ? (recordsMatcher[0])[1] : '0'

println('Group = ' + group)

println('Records = ' + records)

WebUI.click(bar)
def expectedCheckboxState = [('At Risk') : true, ('No Risk') : false]

expectedCheckboxState.each({ def label, def expectedState ->
		TestObject checkbox = new TestObject("chk_$label")

		checkbox.addProperty('xpath', ConditionType.EQUALS, "//label[.//text()[contains(normalize-space(.),'$label')]]//input")

		WebUI.waitForElementPresent(checkbox, 10)

		if (expectedState) {
			WebUI.verifyElementChecked(checkbox, 5)

			WebUI.comment("$label checkbox is CHECKED as expected")
		} else {
			WebUI.verifyElementNotChecked(checkbox, 5)

			WebUI.comment(" $label checkbox is UNCHECKED as expected")
		}
	})
//WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetentionRisk/RI_RetentionRiskBarChartOverallSummaryDtilldownPage/Page_ProHance AI - Retention Risk/div_Retention RiskAt RiskNo Risk'))

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetentionRisk/RI_RetentionRiskBarChartOverallSummaryDtilldownPage/Page_ProHance AI - Retention Risk/div_Total Records 1Displaying 1 to 1'))
TestObject totalRecordsObj = new TestObject('totalRecords')

totalRecordsObj.addProperty('xpath', ConditionType.EQUALS, '//div[b[text()=\'Total Records:\']]')

WebUI.waitForElementPresent(totalRecordsObj, 10)

String fullText = WebUI.getText(totalRecordsObj)

WebUI.comment('Full text: ' + fullText)

def matcher = fullText =~ 'Total Records:\\s*(\\d+)'

String totalRecordsStr = matcher ? (matcher[0])[1] : '0'

WebUI.comment('Total Records value: ' + totalRecordsStr)
WebUI.verifyEqual(records, totalRecordsStr)

String na = WebUI.getText(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_barChart_BreakupOverallDrilldownByAttributeAndUserCountPage/Page_ProHance AI - Retention Risk/div_Product Support'))

println(na)

WebUI.verifyEqual(group, na)

WebUI.closeBrowser()

