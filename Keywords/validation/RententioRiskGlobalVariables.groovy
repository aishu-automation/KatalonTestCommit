package validation

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI


public class RententioRiskGlobalVariables {
	
	@Keyword
	def boolean verifyRetentionRiskHasSevenSegments() {

		TestObject segments = new TestObject("RetentionRiskSegments")
		segments.addProperty(
			"xpath",
			ConditionType.EQUALS,
			"//div[@class='split-dashboard-grid']/div/div"
		)

		WebUI.waitForElementPresent(segments, 10)

		int count = WebUI.findWebElements(segments, 10).size()
		WebUI.comment("Segment count: " + count)

		return count == 7
	}

}
