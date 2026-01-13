import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.openBrowser('')

WebUI.maximizeWindow()

WebUI.navigateToUrl('https://10.10.10.181:8443/prohance')

WebUI.setText(findTestObject('Object Repository/LoginValidation/Page_ProHance/input_Username_tlogin'), 'kaadmin')

WebUI.setText(findTestObject('Object Repository/LoginValidation/Page_ProHance/input_Password_tpwdsaved'), '1')

WebUI.click(findTestObject('Object Repository/LoginValidation/Page_ProHance/div_Captcha Text_col-lg-12'))

WebUI.click(findTestObject('Object Repository/LoginValidation/Page_ProHance/li_Organization'))

WebUI.acceptAlert()

