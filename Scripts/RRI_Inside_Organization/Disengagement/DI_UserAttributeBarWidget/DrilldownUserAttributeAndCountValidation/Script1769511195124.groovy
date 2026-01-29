import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.testobject.ConditionType

import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.By as By
import org.openqa.selenium.JavascriptExecutor as JavascriptExecutor
import org.openqa.selenium.interactions.Actions as Actions
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

WebUI.delay(3)

WebDriver driver = DriverFactory.getWebDriver()

Actions actions = new Actions(driver)

// Find bars and labels
List<WebElement> bars = driver.findElements(By.xpath('(//*[name()=\'g\' and contains(@class,\'apexcharts-bar-series\')])[2]//*[name()=\'path\' and contains(@class,\'apexcharts-bar-area\')]'))

List<WebElement> labels = driver.findElements(By.xpath('(//*[name()=\'g\' and @class=\'apexcharts-yaxis-texts-g apexcharts-xaxis-inversed-texts-g\'])[2]//*[name()=\'text\']'))

println('Number of bars = ' + bars.size())

println('Number of labels = ' + labels.size())

// Loop through each bar
for (int i = 0; i < bars.size(); i++) {
    // Refetch bars and labels after each back navigation
    bars = driver.findElements(By.xpath('(//*[name()=\'g\' and contains(@class,\'apexcharts-bar-series\')])[2]//*[name()=\'path\' and contains(@class,\'apexcharts-bar-area\')]'))

    labels = driver.findElements(By.xpath('(//*[name()=\'g\' and @class=\'apexcharts-yaxis-texts-g apexcharts-xaxis-inversed-texts-g\'])[2]//*[name()=\'text\']'))

    WebElement bar = bars.get(i)

    String labelText = (labels[i]).getText().trim()

    // Hover bar to show tooltip
    actions.moveToElement(bar, 5, 5).pause(1200).perform()

    WebUI.delay(1)

    // Get tooltip text
    TestObject tooltipObj = new TestObject('tooltip')

    tooltipObj.addProperty('xpath', ConditionType.EQUALS, '//div[contains(@class,\'apexcharts-tooltip\')]//div[normalize-space()]')

    String tooltipText = WebUI.getText(tooltipObj).trim()

	// Assuming "Disengagement : 123" is part of tooltipText
	def countMatcher = tooltipText =~ /Disengagement\s*:\s*(\d+)/
	
	String disengagedCount = countMatcher ? (countMatcher[0])[1] : '0'
	
	println("Disengaged count = $disengagedCount")

    // Click bar
    TestObject currentBar = new TestObject("currentBar$i")

    currentBar.addProperty('xpath', ConditionType.EQUALS, "(//*[name()='g' and contains(@class,'apexcharts-bar-series')])[2]//*[name()='path' and contains(@class,'apexcharts-bar-area')][${i + 1}]")


    WebUI.waitForElementClickable(currentBar, 10)

    WebUI.click(currentBar)

    WebUI.delay(2)

    // Verify Disengaged checkbox selection
    def expectedCheckboxState = [('High') : true, ('Medium') : true, ('Low') : false]

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

    TestObject totalRecordsObj = new TestObject('totalRecords')

    totalRecordsObj.addProperty('xpath', ConditionType.EQUALS, '//div[b[text()=\'Total Records:\']]')

    WebUI.waitForElementPresent(totalRecordsObj, 10)

    String fullText = WebUI.getText(totalRecordsObj)

    WebUI.comment('Full text: ' + fullText)

    def matcher = fullText =~ 'Total Records:\\s*(\\d+)'

    String totalRecordsStr = matcher ? (matcher[0])[1] : '0'

    WebUI.comment('Total Records value: ' + totalRecordsStr)

    WebUI.verifyEqual(disengagedCount, totalRecordsStr)

    String na = WebUI.getText(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_barChart_BreakupOverallDrilldownByAttributeAndUserCountPage/Page_ProHance AI - Retention Risk/div_Product Support'))

    println(na)

    WebUI.verifyEqual(labelText, na)

    // Perform drill-down actions
    WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DI_Disengaged_Emp_UserCountPage/Page_ProHance AI - Retention Risk/button_Select Value_MuiButtonBase-root MuiI_029fad'))

    WebUI.delay(2)
}

WebUI.closeBrowser()

