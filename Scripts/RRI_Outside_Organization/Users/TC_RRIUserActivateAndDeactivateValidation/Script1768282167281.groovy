import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
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

comboBox.addProperty('xpath', ConditionType.EQUALS, '//div[@role=\'combobox\']')

// First dropdown option
TestObject firstOption = new TestObject()

firstOption.addProperty('xpath', ConditionType.EQUALS, '//ul[@role=\'listbox\']/li[1]')

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

WebUI.setText(findTestObject('Object Repository/RRI_Outside_Organization/Users/UserActivateAndDeactivatePage/Page_ProHance AI - Retention Risk/input_Search_userSearch'), 
    'Add')

WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/UserActivateAndDeactivatePage/Page_ProHance AI - Retention Risk/span_-_status-circle active'))

WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/UserActivateAndDeactivatePage/Page_ProHance AI - Retention Risk/p_Are you sure you want to deactivate this user'))

WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/UserActivateAndDeactivatePage/Page_ProHance AI - Retention Risk/button_Deactivate'))

WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/UserActivateAndDeactivatePage/Page_ProHance AI - Retention Risk/div_User deactivated successfully'))

WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/UserActivateAndDeactivatePage/Page_ProHance AI - Retention Risk/svg_User deactivated successfully_MuiSvgIco_824362'))

WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/UserActivateAndDeactivatePage/Page_ProHance AI - Retention Risk/span_-_status-circle active'))

WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/UserActivateAndDeactivatePage/Page_ProHance AI - Retention Risk/p_Are you sure you want to activate this user'))

WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/UserActivateAndDeactivatePage/Page_ProHance AI - Retention Risk/button_Activate'))

WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/UserActivateAndDeactivatePage/Page_ProHance AI - Retention Risk/div_User activated successfully'))

WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/UserActivateAndDeactivatePage/Page_ProHance AI - Retention Risk/svg_User activated successfully_MuiSvgIcon-_c2599d'))

WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/UserActivateAndDeactivatePage/Page_ProHance AI - Retention Risk/svg__MuiSvgIcon-root MuiSvgIcon-fontSizeMed_2c1a76'))

WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/UserActivateAndDeactivatePage/Page_ProHance AI - Retention Risk/p_Are you sure you want to delete this user'))

WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/UserActivateAndDeactivatePage/Page_ProHance AI - Retention Risk/button_Delete'))

WebUI.click(findTestObject('Object Repository/RRI_Outside_Organization/Users/UserActivateAndDeactivatePage/Page_ProHance AI - Retention Risk/div_User deleted successfully'))

WebUI.closeBrowser()

