import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.callTestCase(findTestCase('Common/RRI/TC_RRI_LoginWithValidCretentials'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/RoleChangingPage/Page_ProHance AI - Retention Risk/a_General'))

WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/RoleChangingPage/Page_ProHance AI - Retention Risk/path'))

WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/RoleChangingPage/Page_ProHance AI - Retention Risk/p_Administration'))

WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/RoleChangingPage/Page_ProHance AI - Retention Risk/div_Users'))

WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/RoleChangingPage/Page_ProHance AI - Retention Risk/a_Administration'))

WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/RoleChangingPage/Page_ProHance AI - Retention Risk/button_ADD NEW'))

WebUI.setText(findTestObject('Object Repository/RRI_Outside_Organization/Users/RoleChangingPage/Page_ProHance AI - Retention Risk/input_Login Id_r1q'), 'AddUser')

WebUI.setText(findTestObject('Object Repository/RRI_Outside_Organization/Users/RoleChangingPage/Page_ProHance AI - Retention Risk/input_First Name_r1r'), '')

WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/RoleChangingPage/Page_ProHance AI - Retention Risk/span_'))

WebUI.setText(findTestObject('Object Repository/RRI_Outside_Organization/Users/RoleChangingPage/Page_ProHance AI - Retention Risk/input_First Name_r1r'), 'Add')
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

WebUI.setEncryptedText(findTestObject('Object Repository/RRI_Outside_Organization/Users/RoleChangingPage/Page_ProHance AI - Retention Risk/input_Password_r1v'), 
    'HeCM15nHKBI=')

WebUI.setEncryptedText(findTestObject('Object Repository/RRI_Outside_Organization/Users/RoleChangingPage/Page_ProHance AI - Retention Risk/input_Confirm Password_r20'), 
    'HeCM15nHKBI=')

WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/RoleChangingPage/Page_ProHance AI - Retention Risk/button_Create'))

WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/RoleChangingPage/Page_ProHance AI - Retention Risk/div_User created successfully'))

WebUI.setText(findTestObject('Object Repository/RRI_Outside_Organization/Users/RoleChangingPage/Page_ProHance AI - Retention Risk/input_Search_userSearch'), 
    'Add')

WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/RoleChangingPage/Page_ProHance AI - Retention Risk/svg__MuiSvgIcon-root MuiSvgIcon-fontSizeMed_571381'))

TestObject comboBox1 = new TestObject()
comboBox1.addProperty(
	"xpath",
	ConditionType.EQUALS,
	"//div[@role='combobox']"
)

// First dropdown option
TestObject managerOpt = new TestObject()
managerOpt.addProperty(
	"xpath",
	ConditionType.EQUALS,
	"//ul[@role='listbox']/li[2]"
)

// Click combobox
WebUI.waitForElementClickable(comboBox1, 10)
WebUI.click(comboBox1)

// Wait for dropdown
WebUI.waitForElementVisible(managerOpt, 10)

// Select first value
WebUI.click(managerOpt)

WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/RoleChangingPage/Page_ProHance AI - Retention Risk/div_Organization MappingOrganizationOrganization'))


WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/RoleChangingPage/Page_ProHance AI - Retention Risk/button_Update'))
WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/RoleChangingPage/Page_ProHance AI - Retention Risk/svg__MuiSvgIcon-root MuiSvgIcon-fontSizeMed_571381'))
TestObject comboBox2 = new TestObject()
comboBox2.addProperty(
	"xpath",
	ConditionType.EQUALS,
	"//div[@role='combobox']"
)

// First dropdown option
TestObject memberOpt = new TestObject()
memberOpt.addProperty(
	"xpath",
	ConditionType.EQUALS,
	"//ul[@role='listbox']/li[3]"
)

// Click combobox
WebUI.waitForElementClickable(comboBox2, 10)
WebUI.click(comboBox2)

// Wait for dropdown
WebUI.waitForElementVisible(memberOpt, 10)

// Select first value
WebUI.click(memberOpt)

WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/RoleChangingPage/Page_ProHance AI - Retention Risk/div_Organization MappingOrganizationOrganization'))


WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/RoleChangingPage/Page_ProHance AI - Retention Risk/button_Update'))
WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/RoleChangingPage/Page_ProHance AI - Retention Risk/path'))
WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/RoleChangingPage/Page_ProHance AI - Retention Risk/svg__MuiSvgIcon-root MuiSvgIcon-fontSizeMed_2c1a76'))
WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/RoleChangingPage/Page_ProHance AI - Retention Risk/div_Are you sure you want to delete this user'))

WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/RoleChangingPage/Page_ProHance AI - Retention Risk/button_Delete'))

WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/RoleChangingPage/Page_ProHance AI - Retention Risk/div_User deleted successfully'))

WebUI.closeBrowser()

