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

WebUI.callTestCase(findTestCase('RRI_Inside_Organization/RetentionRisk/RI_ModuleValidation'), [:], FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'validation.DisengagementGlobalVariables.clickOrganizationByGlobalVar'('org_Name')
WebUI.delay(3)
WebDriver driver = DriverFactory.getWebDriver()
Actions actions = new Actions(driver)

TestObject high = new TestObject('high')

high.addProperty('xpath', ConditionType.EQUALS, '(//*[@class=\'apexcharts-series-markers\']/../*[1]//*[name()=\'path\'])[1]')

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
def expectedCheckboxState = [('No Risk') : true, ('At Risk') : false]

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
WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetentionRisk/RI_MonthlySummaryNoRiskPage/Page_ProHance AI - Retention Risk/div_Total Records 100Displaying 1 to 10'))
TestObject totalRecordsObj = new TestObject("totalRecords")
totalRecordsObj.addProperty('xpath', ConditionType.EQUALS, "//div[b[text()='Total Records:']]")

WebUI.waitForElementPresent(totalRecordsObj, 10)

String fullText = WebUI.getText(totalRecordsObj)
WebUI.comment("Full text: " + fullText)

def matcher1 = (fullText =~ /Total Records:\s*(\d+)/)
String totalRecordsStr = matcher1 ? matcher1[0][1] : "0"

WebUI.comment("Total Records value: " + totalRecordsStr)
WebUI.verifyMatch(totalRecordsStr, value, false)
WebUI.closeBrowser()

