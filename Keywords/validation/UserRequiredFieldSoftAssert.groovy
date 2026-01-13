package validation

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.model.FailureHandling

class UserRequiredFieldSoftAssert {

	@Keyword
	static def verifyRequiredByXpath(Map fields) {
    fields.each { fieldName, xpath ->
        try {
            // Create a dynamic TestObject
            TestObject to = new TestObject(fieldName)
            to.addProperty("xpath", ConditionType.EQUALS, xpath)
            
            // Wait for element to be visible
            if(WebUI.waitForElementVisible(to, 5)) {
                // Wait for validation (aria-invalid changes to true if empty)
                WebUI.waitForElementAttributeValue(to, 'aria-invalid', 'true', 5)
                
                // Get attribute value
                def ariaInvalid = WebUI.getAttribute(to, 'aria-invalid')
                if(ariaInvalid == 'true') {
                    KeywordUtil.logInfo("✅ Required validation PASSED for field: ${fieldName}")
                } else {
                    KeywordUtil.markFailed("❌ Required validation FAILED for field: ${fieldName}")
                }
            } else {
                KeywordUtil.markWarning("⚠️ Field not visible: ${fieldName}")
            }
        } catch(Exception e) {
            KeywordUtil.markFailed("❌ Error verifying field ${fieldName}: ${e.getMessage()}")
        }
    }
}
}
