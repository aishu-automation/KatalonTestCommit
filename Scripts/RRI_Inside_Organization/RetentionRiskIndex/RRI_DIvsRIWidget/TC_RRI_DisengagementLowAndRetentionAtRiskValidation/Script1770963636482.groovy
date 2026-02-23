import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.callTestCase(findTestCase('Common/RRI/TC_RRI_LoginWithValidCretentials'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('RRI_Inside_Organization/RetentionRiskIndex/RRI_ModuleValidation'), [:], FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'validation.DisengagementGlobalVariables.clickOrganizationByGlobalVar'('org_Name')
TestObject highText = new TestObject('highText')

highText.addProperty('xpath', ConditionType.EQUALS, '(//*[name()=\'g\' and @class="apexcharts-heatmap"]//*[name()=\'g\' and @class="apexcharts-data-labels"])[1]')

String Low = WebUI.getText(highText)

println('Text of Low element: ' + Low)
TestObject fourthRect = new TestObject()
fourthRect.addProperty(
	"xpath",
	ConditionType.EQUALS,
	"(//*[name()='rect' and contains(@class,'apexcharts-heatmap-rect')])[1]"
)
// Wait for it
WebUI.waitForElementVisible(fourthRect, 15)

// Get real WebElement
WebElement element = WebUI.findWebElement(fourthRect, 20)

// Perform REAL mouse click
Actions actions = new Actions(DriverFactory.getWebDriver())
actions.moveToElement(element).click().perform()

WebUI.comment("✅ Heatmap rectangle clicked using Actions class")
def expectedCheckboxState = [('High') : false, ('Medium') : false, ('Low') : true]

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

// RI
def retentionexpectedCheckboxState = [('At Risk') : true, ('No Risk') : false]

retentionexpectedCheckboxState.each({ def label, def retentionexpectedState ->
		TestObject retentioncheckbox = new TestObject("chk_$label")

		retentioncheckbox.addProperty('xpath', ConditionType.EQUALS, "//label[.//text()[contains(normalize-space(.),'$label')]]//input")

		WebUI.waitForElementPresent(retentioncheckbox, 10)

		if (retentionexpectedState) {
			WebUI.verifyElementChecked(retentioncheckbox, 5)

			WebUI.comment("$label checkbox is CHECKED as expected")
		} else {
			WebUI.verifyElementNotChecked(retentioncheckbox, 5)

			WebUI.comment(" $label checkbox is UNCHECKED as expected")
		}
	})
WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetebtionRiskIndex/DIvsRI_DisengagementLowAndRetentionAtRiskPage/Page_ProHance AI - Retention Risk/span_Username'))

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetebtionRiskIndex/DIvsRI_DisengagementLowAndRetentionAtRiskPage/Page_ProHance AI - Retention Risk/span_Employee ID'))

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetebtionRiskIndex/DIvsRI_DisengagementLowAndRetentionAtRiskPage/Page_ProHance AI - Retention Risk/span_User Group'))

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetebtionRiskIndex/DIvsRI_DisengagementLowAndRetentionAtRiskPage/Page_ProHance AI - Retention Risk/span_Disengagement'))

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetebtionRiskIndex/DIvsRI_DisengagementLowAndRetentionAtRiskPage/Page_ProHance AI - Retention Risk/span_Retention Risk'))

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetebtionRiskIndex/DIvsRI_DisengagementLowAndRetentionAtRiskPage/Page_ProHance AI - Retention Risk/div_Total Records 2Displaying 1 to 2'))
TestObject totalRecordsObj = new TestObject("totalRecords")
totalRecordsObj.addProperty('xpath', ConditionType.EQUALS, "//div[b[text()='Total Records:']]")

WebUI.waitForElementPresent(totalRecordsObj, 10)

String fullText = WebUI.getText(totalRecordsObj)
WebUI.comment("Full text: " + fullText)

def matcher = (fullText =~ /Total Records:\s*(\d+)/)
String totalRecordsStr = matcher ? matcher[0][1] : "0"

WebUI.comment("Total Records value: " + totalRecordsStr)

WebUI.verifyMatch(totalRecordsStr, Low, false)

WebUI.closeBrowser()

