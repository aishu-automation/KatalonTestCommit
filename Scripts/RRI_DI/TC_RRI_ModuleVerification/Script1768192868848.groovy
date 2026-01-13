import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.openBrowser('')

WebUI.navigateToUrl('https://devlab.prohance.ai:8000/prohanceai/v3/dashboard')

WebUI.setText(findTestObject('Object Repository/RRI_ModuleVerificationPage/Page_ProHance AI - Retention Risk/input_Username_r0'), 
    'superadmin')

WebUI.setEncryptedText(findTestObject('Object Repository/RRI_ModuleVerificationPage/Page_ProHance AI - Retention Risk/input_Password_r1'), 
    '7326p5kcqg3YJazKjhRTWA==')

WebUI.click(findTestObject('Object Repository/RRI_ModuleVerificationPage/Page_ProHance AI - Retention Risk/button_Login'))

WebUI.click(findTestObject('Object Repository/RRI_ModuleVerificationPage/Page_ProHance AI - Retention Risk/a_General'))

WebUI.click(findTestObject('Object Repository/RRI_ModuleVerificationPage/Page_ProHance AI - Retention Risk/svg__MuiSvgIcon-root MuiSvgIcon-fontSizeMed_571381'))

//WebUI.closeBrowser()
TestObject disengagementCheckbox = new TestObject()
disengagementCheckbox.addProperty(
    "xpath",
    ConditionType.EQUALS,
    "//span[contains(text(),'Disengagement')]/ancestor::label//input"
)

TestObject retentionRiskCheckbox = new TestObject()
retentionRiskCheckbox.addProperty(
    "xpath",
    ConditionType.EQUALS,
    "//span[contains(text(),'Retention Risk')]/ancestor::label//input"
)

/* -------- Enable Disengagement -------- */
String disengagementState = WebUI.getAttribute(disengagementCheckbox, "aria-checked")

if (disengagementState == "false") {
    WebUI.click(disengagementCheckbox)
    WebUI.comment("Disengagement module enabled")
} else {
    WebUI.comment("Disengagement module already enabled")
}

/* -------- Disable Retention Risk -------- */
String retentionRiskState = WebUI.getAttribute(retentionRiskCheckbox, "aria-checked")

if (retentionRiskState == "true") {
    WebUI.click(retentionRiskCheckbox)
    WebUI.comment("Retention Risk module disabled")
} else {
    WebUI.comment("Retention Risk module already disabled")
}


