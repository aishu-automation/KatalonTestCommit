import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.callTestCase(findTestCase('Common/RRI/TC_RRI_LoginWithValidCretentials'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('RRI_Inside_Organization/RetentionRiskIndex/RRI_ModuleValidation'), [:], FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'validation.DisengagementGlobalVariables.clickOrganizationByGlobalVar'('org_Name')

TestObject highText = new TestObject('highText')

highText.addProperty('xpath', ConditionType.EQUALS, '//div[contains(text(),"Low")]/../div[2]/span[1]')

String Low = WebUI.getText(highText)

println('Text of Low element: ' + Low)

WebUI.waitForPageLoad(20)

// Wait for chart container
TestObject chartContainer = new TestObject()

chartContainer.addProperty('xpath', com.kms.katalon.core.testobject.ConditionType.EQUALS, '(//div[contains(@class,\'apexcharts-canvas\')])[3]')

WebUI.waitForElementVisible(chartContainer, 15)

WebUI.waitForElementClickable(chartContainer, 15)

// Click chart
WebUI.click(chartContainer)

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
def retentionexpectedCheckboxState = [('At Risk') : true, ('No Risk') : true]

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

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetebtionRiskIndex/DisengagementOverallSummaryLowDrilldownPage/Page_ProHance AI - Retention Risk/span_Username'))

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetebtionRiskIndex/DisengagementOverallSummaryLowDrilldownPage/Page_ProHance AI - Retention Risk/span_Employee ID'))

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetebtionRiskIndex/DisengagementOverallSummaryLowDrilldownPage/Page_ProHance AI - Retention Risk/span_User Group'))

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetebtionRiskIndex/DisengagementOverallSummaryLowDrilldownPage/Page_ProHance AI - Retention Risk/th_Disengagement (12 W)'))

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetebtionRiskIndex/DisengagementOverallSummaryLowDrilldownPage/Page_ProHance AI - Retention Risk/span_Retention Risk'))

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetebtionRiskIndex/DisengagementOverallSummaryLowDrilldownPage/Page_ProHance AI - Retention Risk/div_Total Records 8Displaying 1 to 8'))

TestObject totalRecordsObj = new TestObject('totalRecords')

totalRecordsObj.addProperty('xpath', ConditionType.EQUALS, '//div[b[text()=\'Total Records:\']]')

WebUI.waitForElementPresent(totalRecordsObj, 10)

String fullText = WebUI.getText(totalRecordsObj)

WebUI.comment('Full text: ' + fullText)

def matcher = fullText =~ 'Total Records:\\s*(\\d+)'

String totalRecordsStr = matcher ? (matcher[0])[1] : '0'

WebUI.comment('Total Records value: ' + totalRecordsStr)

WebUI.verifyMatch(totalRecordsStr, Low, false)

WebUI.closeBrowser()

