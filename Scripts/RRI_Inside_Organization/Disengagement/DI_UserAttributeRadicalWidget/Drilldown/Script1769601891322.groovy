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

TestObject redSegments = new TestObject('redSegments')

redSegments.addProperty('xpath', ConditionType.EQUALS, ('(//div[@class=\'split-card-content-div\'])[3]' + '//*[name()=\'g\' and contains(@class,\'apexcharts-radial-series\')]') + 
    '//*[name()=\'path\']')

/* -------------------------------------------------
 STEP 5: Wait for Chart
------------------------------------------------- */
WebUI.waitForElementPresent(redSegments, 15)

WebUI.delay(2)

/* -------------------------------------------------
 STEP 6: Get number of segments
------------------------------------------------- */
List<WebElement> initialElements = WebUI.findWebElements(redSegments, 10)

int segmentCount = initialElements.size()

println('Total SVG segments found: ' + segmentCount)

/* -------------------------------------------------
 STEP 7: Click each segment safely
------------------------------------------------- */
for (int i = 0; i < segmentCount; i++) {
    // Re-fetch elements AFTER every click (important)
    List<WebElement> freshElements = WebUI.findWebElements(redSegments, 10)

    if (i >= freshElements.size()) {
        println('Segment index out of range after re-render')

        break
    }
    
    WebElement segment = freshElements.get(i)

    // Scroll + JS click (best for SVG)
    WebUI.executeJavaScript('arguments[0].scrollIntoView({block:\'center\'});' + 'arguments[0].dispatchEvent(new MouseEvent(\'click\', { bubbles: true }));', 
        Arrays.asList(segment))

    println('Clicked segment index: ' + i)

    WebUI.delay(2)

    // Click Select Value button
    WebUI.click(findTestObject((('Object Repository/RRI_Inside_Organization/DisEngagement/' + 'DI_Disengaged_Emp_UserCountPage/') + 
            'Page_ProHance AI - Retention Risk/') + 'button_Select Value_MuiButtonBase-root MuiI_029fad'))

    WebUI.delay(3)
}