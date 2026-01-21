package validation

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.testobject.impl.HttpTextBodyContent
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import groovy.json.JsonSlurper
import groovy.json.JsonOutput
import com.kms.katalon.core.testobject.ConditionType

class JsonHelper {

	// --------------------- Login and get session ID ---------------------
	@Keyword
	def String loginAndGetSession() {
		def loginPayload = [
			username: "fkjQCkVkQ0Jtk1W/l3AgFQ==",
			password: "JvAkHliEQlcgi+Sfb0uYLw==",
			viewType: "large"
		]
		String body = JsonOutput.toJson(loginPayload)

		RequestObject loginRequest = new RequestObject()
		loginRequest.setRestRequestMethod("POST")
		loginRequest.setRestUrl("https://devlab.prohance.ai:8000/phaiservices/api/auth/login")
		loginRequest.setHttpHeaderProperties([
			new TestObjectProperty("Content-Type", ConditionType.EQUALS, "application/json"),
			new TestObjectProperty("Accept", ConditionType.EQUALS, "application/json")
		])
		loginRequest.setBodyContent(new HttpTextBodyContent(body, "UTF-8", "application/json"))

		def loginResponse = WS.sendRequest(loginRequest)
		WS.verifyResponseStatusCode(loginResponse, 200)

		// Extract JSESSIONID from cookie
		def cookies = loginResponse.getHeaderFields()['Set-Cookie']
		def jsessionId = null
		if (cookies) {
			cookies.each { cookie ->
				if (cookie.startsWith("JSESSIONID=")) {
					jsessionId = cookie.split(";")[0]  // get only JSESSIONID=xxxx
				}
			}
		}

		if (!jsessionId) {
			throw new Exception("JSESSIONID not found in login response")
		}

		return jsessionId
	}

	// --------------------- Get Widget1 Data ---------------------
	@Keyword
	def Map getWidget1Data() {
		// Get session first
		String sessionCookie = loginAndGetSession()

		// Prepare payload
		def payload = [
			refName: "DASHBOARD",
			userGroupId: -1,
			superGroupId: 81,
			disReportWeekPeriod: "01 Dec 2025 - 07 Dec 2025",
			filterJson: [
				[
					widgetRefName: "widget1",
					isEngagement: true,
					userAttributes: "superGroup",
					showBy: "Top"
				]
			]
		]
		String requestBody = JsonOutput.toJson(payload)

		// Create request
		RequestObject request = new RequestObject()
		request.setRestRequestMethod("POST")
		request.setRestUrl("https://devlab.prohance.ai:8000/phaiservices/api/dashboard/getCharData")
		request.setHttpHeaderProperties([
			new TestObjectProperty("Content-Type", ConditionType.EQUALS, "application/json"),
			new TestObjectProperty("Accept", ConditionType.EQUALS, "application/json"),
			new TestObjectProperty("Cookie", ConditionType.EQUALS, sessionCookie)
		])
		request.setBodyContent(new HttpTextBodyContent(requestBody, "UTF-8", "application/json"))

		// Send request
		def response = WS.sendRequest(request)
		WS.verifyResponseStatusCode(response, 200)

		// Parse JSON
		def json = new JsonSlurper().parseText(response.getResponseBodyContent())

		return [
			High  : json.High,
			Medium: json.Medium,
			Low   : json.Low
		]
	}
}
