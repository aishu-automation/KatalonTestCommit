import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

println("org name"+ GlobalVariable.org_Name)
String orgNameText = GlobalVariable.org_Name
WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetentionRisk/RRI_RI_ModuleVerificationPage/Page_ProHance AI - Retention Risk/a_General'))

TestObject modifyButton = new TestObject("ModifyButton")
modifyButton.addProperty(
	"xpath",
	ConditionType.EQUALS,
	"//div[contains(text(),'" + orgNameText + "')]/../../div[1]/div/span[1]"
)

// Wait until clickable and click
WebUI.waitForElementClickable(modifyButton, 20)
WebUI.click(modifyButton)
WebUI.comment("✅ Clicked Modify for org: " + orgNameText)



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
println("disengagementChecked"+disengagementChecked)
boolean retentionChecked =
		WebUI.verifyElementChecked(retentionRiskCheckbox, 1, FailureHandling.OPTIONAL)
		println("retentionChecked"+retentionChecked)
// ----------------- Enforce Desired State -----------------

// Retention Risk must be ENABLED
if (!retentionChecked) {
	WebUI.click(retentionRiskCheckbox)
	WebUI.comment("✅ Retention enabled")
} else {
	WebUI.comment("ℹ️ Retention already enabled")
}

// Disengagement must be DISABLED
if (disengagementChecked) {
	WebUI.click(disengagementCheckbox)
	WebUI.comment("❌ Disengagement Risk disabled")
} else {
	WebUI.comment("ℹ️ Disengagement Risk already disabled")
}
//WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/RRI_DI_ModuleVerificationPage/Page_ProHance AI - Retention Risk/button_Login'))
WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/RRI_DI_ModuleVerificationPage/Page_ProHance AI - Retention Risk/button_Update Organization'))

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/RRI_DI_ModuleVerificationPage/Page_ProHance AI - Retention Risk/div_Organization updated successfully'))

//WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/RRI_DI_ModuleVerificationPage/Page_ProHance AI - Retention Risk/path'))

TestObject orgName = new TestObject('orgName')

orgName.addProperty(
	'xpath',
	ConditionType.EQUALS,
	"//div[contains(text(),'${orgNameText}')]/./../../div[2]/div"
)

WebUI.waitForElementClickable(orgName, 20)



