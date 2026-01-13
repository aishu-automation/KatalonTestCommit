import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.openBrowser('')

WebUI.navigateToUrl('https://devlab.prohance.ai:8000/prohanceai/v3/dashboard')

WebUI.setText(findTestObject('Object Repository/UserAdd/Page_ProHance AI - Retention Risk/input_Username_r0'), 'superadmin')

WebUI.setEncryptedText(findTestObject('Object Repository/UserAdd/Page_ProHance AI - Retention Risk/input_Password_r1'), 
    '7326p5kcqg3YJazKjhRTWA==')

WebUI.click(findTestObject('Object Repository/UserAdd/Page_ProHance AI - Retention Risk/button_Login'))

WebUI.click(findTestObject('Object Repository/UserAdd/Page_ProHance AI - Retention Risk/a_General'))

WebUI.click(findTestObject('Object Repository/UserAdd/Page_ProHance AI - Retention Risk/svg_AI_MuiSvgIcon-root MuiSvgIcon-fontSizeS_1693d1'))

WebUI.click(findTestObject('Object Repository/UserAdd/Page_ProHance AI - Retention Risk/div_Administration'))

WebUI.click(findTestObject('Object Repository/UserAdd/Page_ProHance AI - Retention Risk/div_Users'))

WebUI.click(findTestObject('Object Repository/UserAdd/Page_ProHance AI - Retention Risk/button_ADD NEW'))

WebUI.click(findTestObject('Object Repository/UserAdd/Page_ProHance AI - Retention Risk/span_Add User'))

WebUI.setText(findTestObject('Object Repository/UserAdd/Page_ProHance AI - Retention Risk/input_Login Id_r1q'), 'AddUser')

WebUI.setText(findTestObject('Object Repository/UserAdd/Page_ProHance AI - Retention Risk/input_First Name_r1r'), 'add')

WebUI.setText(findTestObject('Object Repository/UserAdd/Page_ProHance AI - Retention Risk/input_Last Name_r1s'), 'user')

TestObject comboBox = new TestObject()
comboBox.addProperty(
	"xpath",
	ConditionType.EQUALS,
	"//div[@role='combobox']"
)

// First dropdown option
TestObject firstOption = new TestObject()
firstOption.addProperty(
	"xpath",
	ConditionType.EQUALS,
	"//ul[@role='listbox']/li[1]"
)

// Click combobox
WebUI.waitForElementClickable(comboBox, 10)
WebUI.click(comboBox)

// Wait for dropdown
WebUI.waitForElementVisible(firstOption, 10)

// Select first value
WebUI.click(firstOption)



WebUI.setEncryptedText(findTestObject('Object Repository/UserAdd/Page_ProHance AI - Retention Risk/input_Password_r1v'), 
    'HeCM15nHKBI=')

WebUI.setEncryptedText(findTestObject('Object Repository/UserAdd/Page_ProHance AI - Retention Risk/input_Confirm Password_r20'), 
    '9NLz+4tGZcQ=')

WebUI.click(findTestObject('Object Repository/UserAdd/Page_ProHance AI - Retention Risk/label_Confirm Password'))

WebUI.setEncryptedText(findTestObject('Object Repository/UserAdd/Page_ProHance AI - Retention Risk/input_Confirm Password_r20'), 
    'HeCM15nHKBI=')

WebUI.click(findTestObject('Object Repository/UserAdd/Page_ProHance AI - Retention Risk/button_Create'))

WebUI.click(findTestObject('Object Repository/UserAdd/Page_ProHance AI - Retention Risk/div_User created successfully'))

WebUI.click(findTestObject('Object Repository/UserAdd/Page_ProHance AI - Retention Risk/svg_User created successfully_MuiSvgIcon-ro_d660c0'))

WebUI.closeBrowser()



