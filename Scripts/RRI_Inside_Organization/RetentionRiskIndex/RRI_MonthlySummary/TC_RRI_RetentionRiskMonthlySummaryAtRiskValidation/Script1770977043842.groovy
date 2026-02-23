import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.WebDriver
import org.openqa.selenium.interactions.Actions

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.callTestCase(findTestCase('Common/RRI/TC_RRI_LoginWithValidCretentials'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('RRI_Inside_Organization/RetentionRiskIndex/RRI_ModuleValidation'), [:], FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'validation.DisengagementGlobalVariables.clickOrganizationByGlobalVar'('org_Name')
WebUI.delay(3)
WebDriver driver = DriverFactory.getWebDriver()
Actions actions = new Actions(driver)

TestObject high = new TestObject('high')
high.addProperty('xpath', ConditionType.EQUALS, '(//*[name()=\'g\' and @seriesName="AtxRisk"]//*[name()=\'path\'])[3]')
WebUI.waitForElementClickable(high, 20)
actions.moveToElement(WebUiCommonHelper.findWebElement(high, 20)).perform()
actions.moveToElement(WebUiCommonHelper.findWebElement(high, 20))
	   .pause(java.time.Duration.ofMillis(500)) // small pause to let tooltip appear
	   .perform()

TestObject tooltip = new TestObject('tooltip')
tooltip.addProperty('xpath', ConditionType.EQUALS, "//div[contains(@class,'apexcharts-tooltip') and contains(@class,'active')]")

WebUI.waitForElementVisible(tooltip, 10)

String tooltipText = WebUI.getText(tooltip)
String[] lines = tooltipText.split("\\r?\\n")
String mainTooltip = lines[lines.length - 1]  // last line
println("Full Tooltip: " + mainTooltip)

// ------------------
// Extract numeric value and percentage using regex
// ------------------

// Example tooltip: "Nov - 24, No Risk: 100 ( 94.34% )"
def matcher = mainTooltip =~ /No Risk:\s*(\d+)\s*\(\s*([\d.]+%)\s*\)/
String value = "0"
if (matcher.find()) {
	 value = matcher.group(1)        // 100
	String percentage = matcher.group(2)   // 94.34%
	
	println("Value: " + value)
	println("Percentage: " + percentage)
	
	// Store in variables
	def numericValue = value.toInteger()
	def percentValue = percentage
}
WebUI.click(high)
def expectedCheckboxState = [
	'High'   : true,
	'Medium' : true,
	'Low'    : true
]


expectedCheckboxState.each { label, expectedState ->

	TestObject checkbox = new TestObject("chk_${label}")
	checkbox.addProperty(
		'xpath',
		ConditionType.EQUALS,
		"//label[.//text()[contains(normalize-space(.),'${label}')]]//input"
	)

	WebUI.waitForElementPresent(checkbox, 10)

	if (expectedState) {
		WebUI.verifyElementChecked(checkbox, 5)
		WebUI.comment("${label} checkbox is CHECKED as expected")
	} else {
		WebUI.verifyElementNotChecked(checkbox, 5)
		WebUI.comment(" ${label} checkbox is UNCHECKED as expected")
	}
}
// RI
def retentionexpectedCheckboxState = [
	'At Risk'   : true,
	'No Risk' : false,
]

retentionexpectedCheckboxState.each { label, retentionexpectedState ->

	TestObject retentioncheckbox = new TestObject("chk_${label}")
	retentioncheckbox.addProperty(
		'xpath',
		ConditionType.EQUALS,
		"//label[.//text()[contains(normalize-space(.),'${label}')]]//input"
	)

	WebUI.waitForElementPresent(retentioncheckbox, 10)

	if (retentionexpectedState) {
		WebUI.verifyElementChecked(retentioncheckbox, 5)
		WebUI.comment("${label} checkbox is CHECKED as expected")
	} else {
		WebUI.verifyElementNotChecked(retentioncheckbox, 5)
		WebUI.comment(" ${label} checkbox is UNCHECKED as expected")
	}
}


WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetebtionRiskIndex/RetentionRiskMonthlySummaryAtRiskPage/Page_ProHance AI - Retention Risk/span_Username'))

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetebtionRiskIndex/RetentionRiskMonthlySummaryAtRiskPage/Page_ProHance AI - Retention Risk/span_Employee ID'))

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetebtionRiskIndex/RetentionRiskMonthlySummaryAtRiskPage/Page_ProHance AI - Retention Risk/span_User Group'))

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetebtionRiskIndex/RetentionRiskMonthlySummaryAtRiskPage/Page_ProHance AI - Retention Risk/th_Disengagement (12 W)'))

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetebtionRiskIndex/RetentionRiskMonthlySummaryAtRiskPage/Page_ProHance AI - Retention Risk/th_Retention Risk (12 M)'))

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetebtionRiskIndex/RetentionRiskMonthlySummaryAtRiskPage/Page_ProHance AI - Retention Risk/div_Total Records 6Displaying 1 to 6'))

WebUI.closeBrowser()

