import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.openBrowser('')

WebUI.navigateToUrl('https://10.10.10.181:8443/prohance')

WebUI.setText(findTestObject('Object Repository/TC_CustomeDelete/Page_ProHance/input_Username_tlogin'), 'kaadmin')

WebUI.setText(findTestObject('Object Repository/TC_CustomeDelete/Page_ProHance/input_Password_tpwdsaved'), '1')

WebUI.click(findTestObject('Object Repository/TC_CustomeDelete/Page_ProHance/input_Captcha Text_btn-login loginbtn'))

WebUI.click(findTestObject('Object Repository/TC_CustomeDelete/Page_ProHance/li_Organization'))

WebUI.click(findTestObject('Object Repository/TC_CustomeDelete/Page_ProHance/i_ADMIN_fa fa-chevron-right icon-white'))

WebUI.click(findTestObject('Object Repository/TC_CustomeDelete/Page_ProHance/li_Work Template'))

WebUI.click(findTestObject('Object Repository/TC_CustomeDelete/Page_ProHance/i_Teams_fa fa-pencil-square-o fa-lg pointer (1)'))

WebUI.closeBrowser()

