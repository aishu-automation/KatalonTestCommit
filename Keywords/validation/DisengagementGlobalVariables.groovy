package validation

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable


class DisengagementGlobalVariables {

	@Keyword
	def boolean verifyDisengagementHasFiveSegments() {

		TestObject segments = new TestObject("DisengagementSegments")
		segments.addProperty(
			"xpath",
			ConditionType.EQUALS,
			"//div[@class='split-dashboard-grid']/div/div"
		)

		WebUI.waitForElementPresent(segments, 10)

		int count = WebUI.findWebElements(segments, 10).size()
		WebUI.comment("Segment count: " + count)

		return count == 5
	}
	
@Keyword
def String getStatusText(String serverName) {
    String xpath = "//div[contains(text(),'" + serverName + "')]/../../div[6]//span"
    println("Constructed XPath: " + xpath)

    TestObject dynamicElement = new TestObject("DynamicStatusElement")
    dynamicElement.addProperty("xpath", ConditionType.EQUALS, xpath)

    boolean isPresent = WebUI.waitForElementPresent(dynamicElement, 30)
    if (!isPresent) {
        WebUI.comment("⚠️ Element not found for XPath: " + xpath)
        return ""
    }

    def elements = WebUI.findWebElements(dynamicElement, 10)
    def statusList = []

    elements.each { el ->
        String text = el.getText().trim()
        if (text) {
            statusList.add(text)
        }
    }

    String statusText = statusList.join(" and ")
    WebUI.comment("🔹 Status Text for '${serverName}': ${statusText}")

    return statusText
}

@Keyword
def clickOrganizationByGlobalVar(String globalVarName) {
	// Get the value from GlobalVariable dynamically
	  String textToClick = GlobalVariable[globalVarName].toString().trim()

	// Build dynamic XPath
	String xpath = "//div[contains(text(),'" + textToClick + "')]"

	// Create dynamic TestObject
	TestObject dynamicObj = new TestObject("DynamicObject_" + textToClick)
	dynamicObj.addProperty("xpath", ConditionType.EQUALS, xpath)

	// Wait for element presence and click
	WebUI.waitForElementPresent(dynamicObj, 20)
	WebUI.click(dynamicObj)

	WebUI.comment("✅ Clicked element with text: " + textToClick)
}

 @Keyword
    def getUserGroup(TestObject testObject, String labelText) {

        String fullText = WebUI.getText(testObject)
        println("Full Text: " + fullText)

        if (!fullText.contains(labelText)) {
            throw new Exception("Label text not found: " + labelText)
        }

        return fullText.split(labelText)[1].trim()
    }
	
	
}
