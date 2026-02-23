package validation

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI




public class RetentionRiskIndexGlobalVaribles {
	
	@Keyword
	def boolean verifyRRIHasTenSegments() {

		TestObject segments = new TestObject("DisengagementSegments")
		segments.addProperty(
			"xpath",
			ConditionType.EQUALS,
			"//div[@class=\'dashboard-grid\']/div/div"
		)

		WebUI.waitForElementPresent(segments, 10)

		int count = WebUI.findWebElements(segments, 10).size()
		WebUI.comment("Segment count: " + count)

		return count == 10
	}

}
