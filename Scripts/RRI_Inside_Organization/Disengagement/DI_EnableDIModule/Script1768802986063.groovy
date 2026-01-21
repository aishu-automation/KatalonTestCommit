import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling


WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/RRI_ModuleVerificationPage/Page_ProHance AI - Retention Risk/a_General'))

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/RRI_ModuleVerificationPage/Page_ProHance AI - Retention Risk/svg__MuiSvgIcon-root MuiSvgIcon-fontSizeMed_571381'))



// ----------------- Test Objects -----------------
TestObject disengagementCheckbox = new TestObject("DisengagementCheckbox")
disengagementCheckbox.addProperty(
    "xpath",
    ConditionType.EQUALS,
    "//span[text()='Disengagement']/ancestor::label//input"
)

TestObject retentionRiskCheckbox = new TestObject("RetentionRiskCheckbox")
retentionRiskCheckbox.addProperty(
    "xpath",
    ConditionType.EQUALS,
    "//span[text()='Retention Risk']/ancestor::label//input"
)

// ----------------- Read Current States -----------------
boolean disengagementChecked =
        WebUI.verifyElementChecked(disengagementCheckbox, 1, FailureHandling.OPTIONAL)

boolean retentionChecked =
        WebUI.verifyElementChecked(retentionRiskCheckbox, 1, FailureHandling.OPTIONAL)

// ----------------- Enforce Desired State -----------------

// Disengagement must be ENABLED
if (!disengagementChecked) {
    WebUI.click(disengagementCheckbox)
    WebUI.comment("✅ Disengagement enabled")
} else {
    WebUI.comment("ℹ️ Disengagement already enabled")
}

// Retention Risk must be DISABLED
if (retentionChecked) {
    WebUI.click(retentionRiskCheckbox)
    WebUI.comment("❌ Retention Risk disabled")
} else {
    WebUI.comment("ℹ️ Retention Risk already disabled")
}
//WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/RRI_ModuleVerificationPage/Page_ProHance AI - Retention Risk/button_Login'))
WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/RRI_ModuleVerificationPage/Page_ProHance AI - Retention Risk/button_Update Organization'))

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/RRI_ModuleVerificationPage/Page_ProHance AI - Retention Risk/div_Organization updated successfully'))

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/RRI_ModuleVerificationPage/Page_ProHance AI - Retention Risk/path'))

