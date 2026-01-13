import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.callTestCase(findTestCase('Common/RRI/TC_RRI_LoginWithValidCretentials'), [:], FailureHandling.STOP_ON_FAILURE)

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

WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/UserResetPasswordPage/Page_ProHance AI - Retention Risk/div_User created successfully'))

WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/UserResetPasswordPage/Page_ProHance AI - Retention Risk/path'))

WebUI.setText(findTestObject('Object Repository/RRI_Outside_Organization/Users/UserResetPasswordPage/Page_ProHance AI - Retention Risk/input_Search_userSearch'), 
    'add')

WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/UserResetPasswordPage/Page_ProHance AI - Retention Risk/button__MuiButtonBase-root MuiIconButton-ro_d26695'))

WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/UserResetPasswordPage/Page_ProHance AI - Retention Risk/input_Role_PrivateSwitchBase-input css-j8yymo'))

WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/UserResetPasswordPage/Page_ProHance AI - Retention Risk/label_Reset Password to Login Id'))

WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/UserResetPasswordPage/Page_ProHance AI - Retention Risk/span_Reset Password to Login Id'))

WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/UserResetPasswordPage/Page_ProHance AI - Retention Risk/button_Update'))

WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/UserResetPasswordPage/Page_ProHance AI - Retention Risk/div_User updated successfully'))

WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/UserResetPasswordPage/Page_ProHance AI - Retention Risk/svg_User updated successfully_MuiSvgIcon-ro_e73cd6'))

WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/UserResetPasswordPage/Page_ProHance AI - Retention Risk/i_AI_fa fa-user fa-lg icon-white'))

WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/UserResetPasswordPage/Page_ProHance AI - Retention Risk/li_Logout'))

WebUI.setText(findTestObject('Object Repository/RRI_Outside_Organization/Users/UserResetPasswordPage/Page_ProHance AI - Retention Risk/input_Username_r3b'), 'AddUser')

WebUI.setEncryptedText(findTestObject('Object Repository/RRI_Outside_Organization/Users/UserResetPasswordPage/Page_ProHance AI - Retention Risk/input_Password_r3c'), 
    'lO1UznV+lTg=')

WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/UserResetPasswordPage/Page_ProHance AI - Retention Risk/button_Login'))

WebUI.setEncryptedText(findTestObject('Object Repository/RRI_Outside_Organization/Users/UserResetPasswordPage/Page_ProHance AI - Retention Risk/input_New Password_r3f'), 
    'HeCM15nHKBI=')

WebUI.setEncryptedText(findTestObject('Object Repository/RRI_Outside_Organization/Users/UserResetPasswordPage/Page_ProHance AI - Retention Risk/input_Confirm Password_r3g'), 
    'HeCM15nHKBI=')

WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/UserResetPasswordPage/Page_ProHance AI - Retention Risk/button_Change Password'))

WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/UserResetPasswordPage/Page_ProHance AI - Retention Risk/div_Password changed successfully, please l_6c8fe7'))

WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/UserResetPasswordPage/Page_ProHance AI - Retention Risk/svg_Password changed successfully, please l_baef72'))

WebUI.setText(findTestObject('Object Repository/RRI_Outside_Organization/Users/UserResetPasswordPage/Page_ProHance AI - Retention Risk/input_Username_r3h'), 'superadmin')

WebUI.setEncryptedText(findTestObject('Object Repository/RRI_Outside_Organization/Users/UserResetPasswordPage/Page_ProHance AI - Retention Risk/input_Password_r3c'), 
    '7326p5kcqg3YJazKjhRTWA==')

WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/UserResetPasswordPage/Page_ProHance AI - Retention Risk/button_Login'))

WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/UserResetPasswordPage/Page_ProHance AI - Retention Risk/svg_AI_MuiSvgIcon-root MuiSvgIcon-fontSizeS_1693d1'))

WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/UserResetPasswordPage/Page_ProHance AI - Retention Risk/p_Administration'))

WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/UserResetPasswordPage/Page_ProHance AI - Retention Risk/div_Users'))

WebUI.setText(findTestObject('Object Repository/RRI_Outside_Organization/Users/UserResetPasswordPage/Page_ProHance AI - Retention Risk/input_Search_userSearch'), 
    'add')

WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/UserResetPasswordPage/Page_ProHance AI - Retention Risk/svg__MuiSvgIcon-root MuiSvgIcon-fontSizeMed_2c1a76'))

WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/UserResetPasswordPage/Page_ProHance AI - Retention Risk/button_Delete'))

WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/UserResetPasswordPage/Page_ProHance AI - Retention Risk/div_User deleted successfully'))

WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/UserResetPasswordPage/Page_ProHance AI - Retention Risk/svg_User deleted successfully_MuiSvgIcon-ro_39fc0a'))

WebUI.closeBrowser()

