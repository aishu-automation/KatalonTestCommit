import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.By
import org.openqa.selenium.interactions.Actions


WebUI.callTestCase(findTestCase('Common/RRI/TC_RRI_LoginWithValidCretentials'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/New Folder (1)/Page_ProHance AI - Retention Risk/div_PH Demo (199 Server)'))

TestObject barSeries2 = new TestObject("barSeries2")
barSeries2.addProperty("xpath", ConditionType.EQUALS,
	"(//*[name()='g' and contains(@class,'apexcharts-bar-series')])[2]//*[name()='g' and contains(@class,'apexcharts-series')]//*[name()='path' and contains(@class,'apexcharts-bar-area')]"
)

// Get driver and actions
WebDriver driver = DriverFactory.getWebDriver()
Actions actions = new Actions(driver)

// Find all bar elements
List<WebElement> bars = driver.findElements(By.xpath("(//*[name()='g' and contains(@class,'apexcharts-bar-series')])[2]//*[name()='g' and contains(@class,'apexcharts-series')]//*[name()='path' and contains(@class,'apexcharts-bar-area')]"))

println("Number of bars = " + bars.size())

// ---------- STEP 4: Hover each bar and read tooltip ----------
for (int i = 0; i < bars.size(); i++) {
    WebElement bar = bars[i]
    
    // Hover over the bar
    actions.moveToElement(bar).perform()
    Thread.sleep(1500) // wait for tooltip to appear
    
    WebElement tooltip = null
    int attempts = 0
    while (tooltip == null && attempts < 5) {
        try {
            // Find tooltip (visible and not display:none)
            tooltip = driver.findElement(By.xpath("//*[contains(@class,'apexcharts-tooltip') and not(contains(@style,'display:none'))]"))
        } catch (Exception e) {
            Thread.sleep(500)
            attempts++
        }
    }
    
    if (tooltip != null) {
        // Handle both HTML div or SVG text/tspan
        List<WebElement> tspans = tooltip.findElements(By.xpath(".//*[name()='tspan' or name()='text']"))
        String tooltipText = tspans.size() > 0 ? tspans.collect { it.getText() }.join(" | ") : tooltip.getText()
        println("Tooltip for bar ${i+1}: " + tooltipText)
    } else {
        println("Tooltip not found for bar ${i+1}")
    }
}


WebUI.closeBrowser()

