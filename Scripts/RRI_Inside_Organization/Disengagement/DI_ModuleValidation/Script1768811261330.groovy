import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

println(GlobalVariable.org_Name)
//call 'getModulename' method
String statusText = CustomKeywords.'validation.DisengagementGlobalVariables.getStatusText'(GlobalVariable.org_Name)

if (statusText == 'DI') {
    WebUI.comment('✅ Only DI present, no action needed' // Call your existing steps here
        )
} else if ((statusText == 'RI') || (statusText == 'DI and RI')) {
    WebUI.callTestCase(findTestCase('RRI_Inside_Organization/Disengagement/DI_EnableDIModule'), [:], FailureHandling.STOP_ON_FAILURE)
} else {
    WebUI.comment("⚠️ Unexpected status text: $statusText")
}

