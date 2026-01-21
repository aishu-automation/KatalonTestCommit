
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject

import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile

import internal.GlobalVariable as GlobalVariable

import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.annotation.BeforeTestSuite
import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.annotation.AfterTestSuite
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.context.TestSuiteContext

import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.chrome.ChromeDriver

class GlobalChromeSetup {

	/**
	 * Executes before every test case starts.
	 * @param testCaseContext related information of the executed test case.
	 */
	@BeforeTestCase
	def setupChrome(TestCaseContext testCaseContext) {
		println "Launching Chrome for Test Case: " + testCaseContext.getTestCaseId()

		// Chrome preferences (optional for downloads)
		Map<String, Object> prefs = new HashMap<>()
		prefs.put("download.default_directory", "C:\\Users\\aishwarya.k\\Downloads")
		prefs.put("download.prompt_for_download", false)

		// ChromeOptions setup
		ChromeOptions options = new ChromeOptions()
		options.setExperimentalOption("prefs", prefs)

		// SSL bypass
		options.addArguments("--ignore-certificate-errors")
		options.addArguments("--allow-insecure-localhost")

		// Use a clean Chrome profile
		options.addArguments("--user-data-dir=C:\\Users\\aishwarya.k\\AppData\\Local\\Google\\Chrome\\User Data\\KatalonClean")

		// Disable all extensions
		options.addArguments("--disable-extensions")

		// Specify Chrome executable path
		options.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe")

		// Launch Chrome driver
		//DriverFactory.changeWebDriver(new ChromeDriver(options))
	}
}