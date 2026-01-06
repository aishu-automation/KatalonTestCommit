import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebElement

import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.openBrowser('')

WebUI.navigateToUrl('https://10.10.10.181:8443/prohance')

WebUI.setText(findTestObject('null'), 'kaadmin')

WebUI.setText(findTestObject('null'), '1')

WebUI.click(findTestObject('null'))

WebUI.click(findTestObject('null'))

WebUI.click(findTestObject('null'))

def driver = DriverFactory.getWebDriver()

List<WebElement> elements = driver.findElements(
    By.xpath("//table[@id='CommonDataTableId']/tbody/tr/td[2]/div")
)

List<String> uiDataList = []

for (WebElement el : elements) {
    uiDataList.add(el.getText().trim())
}

println "UI Data: " + uiDataList

WebUI.click(findTestObject('null'))

WebUI.closeBrowser()

