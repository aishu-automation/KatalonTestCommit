import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.WebElement as WebElement
import java.util.Arrays as Arrays

WebUI.callTestCase(findTestCase('Common/RRI/TC_RRI_LoginWithValidCretentials'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('RRI_Inside_Organization/Disengagement/DI_ModuleValidation'), [:], FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'validation.DisengagementGlobalVariables.clickOrganizationByGlobalVar'('org_Name')

WebUI.delay(6)
TestObject radialSegments = new TestObject('radialSegments')

radialSegments.addProperty(
	'xpath',
	ConditionType.EQUALS,
	"(//div[@class='split-card-content-div'])[3]" +
	"//*[name()='svg']//*[name()='g' and contains(@class,'apexcharts-radial-series')]" +
	"//*[name()='path']"
)
WebUI.waitForElementPresent(radialSegments, 20)
WebUI.delay(3)
// ✅ GET SEGMENT COUNT
List<WebElement> segments = WebUI.findWebElements(radialSegments, 10)
int segmentCount = segments.size()

println("Total SVG segments found: " + segmentCount)


/* -------------------------------------------------
 STEP 7: Click each segment safely
------------------------------------------------- */
for (int i = 0; i < segmentCount; i++) {
	
		List<WebElement> freshSegments = WebUI.findWebElements(radialSegments, 10)
	
		if (i >= freshSegments.size()) {
			println("Segment index out of range after re-render")
			break
		}
	
		WebElement segment = freshSegments.get(i)
	
		WebUI.executeJavaScript("""
        arguments[0].scrollIntoView({block:'center'});
        arguments[0].dispatchEvent(new MouseEvent('click', {bubbles:true}));
    """, Arrays.asList(segment))
	
		println("Clicked segment index: " + i)
	
		WebUI.delay(2)
	}