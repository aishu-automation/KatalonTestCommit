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
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.testobject.ConditionType


WebUI.callTestCase(findTestCase('Common/LoginPageTestCase'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('null'))

WebUI.click(findTestObject('null'))

WebUI.click(findTestObject('null'))

WebUI.setText(findTestObject('null'), 'add')

WebUI.setText(findTestObject('null'), 'dddd')

WebUI.click(findTestObject('null'))

WebUI.click(findTestObject('null'))

WebUI.click(findTestObject('null'))

WebUI.click(findTestObject('null'))

WebUI.setText(findTestObject('null'), 
    'add')

WebUI.click(findTestObject('null'))

WebUI.setText(findTestObject('null'), 'add_Mod')

WebUI.click(findTestObject('null'))

WebUI.click(findTestObject('null'))

WebUI.click(findTestObject('null'))

WebUI.click(findTestObject('null'))

WebUI.click(findTestObject('null'))

WebUI.setText(findTestObject('null'), 
    'add_Mod')

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType

// The row name you want to delete
String templateName = "add_Mod"

// 1️⃣ Wait for the row to exist
TestObject row = new TestObject()
row.addProperty(
    "xpath",
    ConditionType.EQUALS,
    "//tr[.//div[normalize-space()='" + templateName + "']]"
)
WebUI.waitForElementPresent(row, 15)

// 2️⃣ Create TestObject for the delete icon inside that row
TestObject deleteIcon = new TestObject()
deleteIcon.addProperty(
    "xpath",
    ConditionType.EQUALS,
    "//tr[.//div[normalize-space()='" + templateName + "']]//i[contains(@class,'fa-trash-o')]"
)

// Scroll into view & click
WebUI.scrollToElement(deleteIcon, 5)
WebUI.waitForElementClickable(deleteIcon, 15)
WebUI.click(deleteIcon)

// 3️⃣ Handle the custom delete modal
// Update the XPath according to your modal's “Yes” button
TestObject confirmBtn = new TestObject()
confirmBtn.addProperty(
    "xpath",
    ConditionType.EQUALS,
    "//button[text()='Yes']"
)

// Wait & click confirm
WebUI.waitForElementClickable(confirmBtn, 10)
WebUI.click(confirmBtn)

// 4️⃣ Verify the row is deleted
WebUI.verifyElementNotPresent(row, 10)



WebUI.closeBrowser()

