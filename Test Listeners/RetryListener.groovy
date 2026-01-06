

import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

class RetryListener {

	private static int retryCount = 0
	private static final int MAX_RETRY = 2  // same as your retryLimit

	@AfterTestCase
	def retryFailedTest(TestCaseContext testCaseContext) {

		if ('FAILED'.equals(testCaseContext.getTestCaseStatus()) && retryCount < MAX_RETRY) {

			retryCount++
			WebUI.comment("Retrying failed test case: ${testCaseContext.getTestCaseId()} | Attempt: ${retryCount}")

			// Re-run the same test case
			WebUI.callTestCase(
				findTestCase(testCaseContext.getTestCaseId()),
				[:]  // empty params
			)

		} else {
			retryCount = 0  // reset after success or max retries
		}
	}
}