import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import graphql.Assert
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.callTestCase(findTestCase('Common/LoginPageTestCase'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/WT_WidgetTextValidation/Page_ProHance/span_WORK TIME_caret margin-Left-Neg1px'))

WebUI.click(findTestObject('Object Repository/WT_WidgetTextValidation/Page_ProHance/a_ACTIVITY DASHBOARD'))

WebUI.click(findTestObject('Object Repository/WT_WidgetTextValidation/Page_ProHance/span_Top Categories Activity Type For  CS L_def09f'))

WebUI.switchToWindowIndex(1)

WebUI.click(findTestObject('Object Repository/WT_WidgetTextValidation/Page_ProHance/td_Communication Tools'))

String totalDuration = WebUI.getText(findTestObject("Object Repository/WT_WidgetTextValidation/Page_ProHance/td_646.13h"))
println("Value from TD element: " + totalDuration)
totalDuration = totalDuration.replaceAll("h", "").trim()
println("TD element after removing 'h': " + totalDuration)

// Click another element if needed
WebUI.click(findTestObject("Object Repository/WT_WidgetTextValidation/Page_ProHance/span_Communication Tools"))

// Get the text of the DIV element
String durationInDrilldown = WebUI.getText(findTestObject("Object Repository/WT_WidgetTextValidation/Page_ProHance/div_Total Time     Hrs 646.13"))
println("Value from DIV element: " + durationInDrilldown)
durationInDrilldown = durationInDrilldown.replaceAll("[^0-9.]", "")
println("DIV element numeric value: " + durationInDrilldown)

if (totalDuration.equals(durationInDrilldown)) {
	println("✅ The values are equal!")
	//assert true
} else {
	println("❌ The values are NOT equal!")
	// assert false
}

WebUI.closeBrowser()

