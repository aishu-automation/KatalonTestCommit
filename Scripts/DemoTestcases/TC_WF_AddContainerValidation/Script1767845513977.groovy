import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import internal.GlobalVariable as GlobalVariable


WebUI.callTestCase(findTestCase('Common/LoginPageTestCase'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/WorkFlow_ContainerAddingPage/Page_ProHance/a_WORKFLOW'))

WebUI.switchToWindowTitle('ProHance Workflow')

WebUI.delay(5)

WebDriver driver = DriverFactory.getWebDriver()

driver.switchTo().frame('contentFrame')

int iteration = 1

while (true) {
    int presentContainerCount = driver.findElements(By.xpath('//li[@id=\'tab_1\']/div')).size()

    println("Iteration $iteration - Present container count: $presentContainerCount")

    List<WebElement> addButtons = driver.findElements(By.xpath('//li[@id=\'add\']/div/span/i'))

    // 3️⃣ Check if Add button exists AND is displayed
    boolean isAddPresent = (addButtons.size() > 0) && addButtons.get(0).isDisplayed()

    if (!(isAddPresent)) {
        println('Add button not found. Breaking loop.')

        break
    }
    
    // -----------------------------
    // Click Add button
    // -----------------------------
    driver.findElements(By.xpath('//li[@id=\'add\']/div/span/i')).get(0).click()

    println('Add button clicked')

    WebUI.delay(1)

    // -----------------------------
    // Call container class
    // -----------------------------
    //WebUI.acceptAlert()
    WebUI.callTestCase(findTestCase('DemoTestcases/TC_WF_ContainerPopup'), [:], FailureHandling.STOP_ON_FAILURE)

    WebUI.delay(1)

    iteration++
}

WebUI.closeBrowser()

