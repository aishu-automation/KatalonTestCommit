import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

println("org name"+ GlobalVariable.org_Name)
String orgNameText = GlobalVariable.org_Name
//WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/RetentionRiskIndex/RRI_ModuleVerificationPage/Page_ProHance AI - Retention Risk/a_General'))

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

WebUI.waitForElementClickable(disengagementCheckbox, 10)
WebUI.waitForElementClickable(retentionRiskCheckbox, 10)


// ----------------- Read Current States -----------------
boolean disengagementChecked = false
boolean retentionChecked = false

try {
	disengagementChecked = WebUI.verifyElementChecked(disengagementCheckbox, 1, FailureHandling.OPTIONAL)
} catch (Exception e) {
	disengagementChecked = false
}

try {
	retentionChecked = WebUI.verifyElementChecked(retentionRiskCheckbox, 1, FailureHandling.OPTIONAL)
} catch (Exception e) {
	retentionChecked = false
}

println("disengagementChecked: " + disengagementChecked)
println("retentionChecked: " + retentionChecked)


// ----------------- Enforce Both Modules Enabled -----------------
boolean isUpdated = false

if (!disengagementChecked) {
	WebUI.click(disengagementCheckbox)
	WebUI.comment("✅ Enabled Disengagement Risk")
	isUpdated = true
}

if (!retentionChecked) {
	WebUI.click(retentionRiskCheckbox)
	WebUI.comment("✅ Enabled Retention Risk")
	isUpdated = true
}

// ----------------- Update Only If Needed -----------------

if (isUpdated) {
    TestObject updateButton = findTestObject(
        'Object Repository/RRI_Inside_Organization/DisEngagement/RRI_DI_ModuleVerificationPage/Page_ProHance AI - Retention Risk/button_Update Organization'
    )
    WebUI.click(updateButton)

    TestObject successMsg = findTestObject(
        'Object Repository/RRI_Inside_Organization/DisEngagement/RRI_DI_ModuleVerificationPage/Page_ProHance AI - Retention Risk/div_Organization updated successfully'
    )

    WebUI.waitForElementVisible(successMsg, 10)
    WebUI.comment("✅ Organization updated successfully")

    // Optional: wait for toast to disappear
    WebUI.waitForElementNotVisible(successMsg, 5)
    WebUI.delay(1) // give DOM a moment to stabilize
} else {
    WebUI.comment("ℹ️ No changes needed. Both modules already enabled.")
}

TestObject orgName = new TestObject('orgName')
orgName.addProperty(
	'xpath',
	ConditionType.EQUALS,
	"//div[contains(text(),'${orgNameText}')]/./../../div[2]/div"
)




