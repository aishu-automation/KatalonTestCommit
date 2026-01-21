import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.callTestCase(findTestCase('RRI_Inside_Organization/Disengagement/DI_EnableDIModule'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/RRI_Inside_Organization/DisEngagement/DisengagementWidgetCountValidation/Page_ProHance AI - Retention Risk/div_PH Demo (199 Server)'))

boolean result = CustomKeywords.'validation.DisengagementGlobalVariables.verifyDisengagementHasFiveSegments'()
assert result



