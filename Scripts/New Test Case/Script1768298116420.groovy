import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.callTestCase(findTestCase('Common/RRI/TC_RRI_LoginWithValidCretentials'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/New Folder/Page_ProHance AI - Retention Risk/a_General'))

WebUI.click(findTestObject('Object Repository/New Folder/Page_ProHance AI - Retention Risk/path'))

WebUI.click(findTestObject('Object Repository/New Folder/Page_ProHance AI - Retention Risk/p_Administration'))

WebUI.click(findTestObject('Object Repository/New Folder/Page_ProHance AI - Retention Risk/div_Users'))

WebUI.click(findTestObject('Object Repository/New Folder/Page_ProHance AI - Retention Risk/a_Administration'))

WebUI.click(findTestObject('Object Repository/New Folder/Page_ProHance AI - Retention Risk/button_ADD NEW'))

WebUI.setText(findTestObject('Object Repository/New Folder/Page_ProHance AI - Retention Risk/input_Login Id_r1q'), 'AddUser')

WebUI.setText(findTestObject('Object Repository/New Folder/Page_ProHance AI - Retention Risk/input_First Name_r1r'), '')

WebUI.click(findTestObject('Object Repository/New Folder/Page_ProHance AI - Retention Risk/span_'))

WebUI.setText(findTestObject('Object Repository/New Folder/Page_ProHance AI - Retention Risk/input_First Name_r1r'), 'Add')
WebUI.delay(5)
WebUI.click(findTestObject('Object Repository/New Folder/Page_ProHance AI - Retention Risk/div_Create_MuiBackdrop-root MuiBackdrop-inv_5c3d39'))
WebUI.delay(5)
WebUI.click(findTestObject('Object Repository/New Folder/Page_ProHance AI - Retention Risk/li_Administrator'))

WebUI.setEncryptedText(findTestObject('Object Repository/New Folder/Page_ProHance AI - Retention Risk/input_Password_r1v'), 
    'HeCM15nHKBI=')

WebUI.setEncryptedText(findTestObject('Object Repository/New Folder/Page_ProHance AI - Retention Risk/input_Confirm Password_r20'), 
    'HeCM15nHKBI=')

WebUI.click(findTestObject('Object Repository/New Folder/Page_ProHance AI - Retention Risk/button_Create'))

WebUI.click(findTestObject('Object Repository/New Folder/Page_ProHance AI - Retention Risk/div_User created successfully'))

WebUI.setText(findTestObject('Object Repository/New Folder/Page_ProHance AI - Retention Risk/input_Search_userSearch'), 
    'Add')

WebUI.click(findTestObject('Object Repository/New Folder/Page_ProHance AI - Retention Risk/svg__MuiSvgIcon-root MuiSvgIcon-fontSizeMed_571381'))

WebUI.click(findTestObject('Object Repository/New Folder/Page_ProHance AI - Retention Risk/div_Create_MuiBackdrop-root MuiBackdrop-inv_5c3d39'))

WebUI.click(findTestObject('Object Repository/New Folder/Page_ProHance AI - Retention Risk/li_Manager'))

WebUI.click(findTestObject('Object Repository/New Folder/Page_ProHance AI - Retention Risk/div_Organization MappingOrganizationOrganization'))

WebUI.click(findTestObject('Object Repository/New Folder/Page_ProHance AI - Retention Risk/li_SHEWTR'))

WebUI.click(findTestObject('Object Repository/New Folder/Page_ProHance AI - Retention Risk/div_Create_MuiBackdrop-root MuiBackdrop-inv_5c3d39'))

WebUI.click(findTestObject('Object Repository/New Folder/Page_ProHance AI - Retention Risk/button_Update'))

WebUI.click(findTestObject('Object Repository/New Folder/Page_ProHance AI - Retention Risk/button__MuiButtonBase-root MuiIconButton-ro_d26695'))

WebUI.click(findTestObject('Object Repository/New Folder/Page_ProHance AI - Retention Risk/div_Create_MuiBackdrop-root MuiBackdrop-inv_5c3d39'))

WebUI.click(findTestObject('Object Repository/New Folder/Page_ProHance AI - Retention Risk/li_Member'))

WebUI.click(findTestObject('Object Repository/New Folder/Page_ProHance AI - Retention Risk/div_Organization MappingOrganizationOrganization'))

WebUI.click(findTestObject('Object Repository/New Folder/Page_ProHance AI - Retention Risk/li_SHEWTR_1'))

WebUI.click(findTestObject('Object Repository/New Folder/Page_ProHance AI - Retention Risk/div_Create_MuiBackdrop-root MuiBackdrop-inv_5c3d39'))

WebUI.click(findTestObject('Object Repository/New Folder/Page_ProHance AI - Retention Risk/button_Update'))

WebUI.click(findTestObject('Object Repository/New Folder/Page_ProHance AI - Retention Risk/path'))

WebUI.click(findTestObject('Object Repository/New Folder/Page_ProHance AI - Retention Risk/button_Delete'))

WebUI.closeBrowser()

