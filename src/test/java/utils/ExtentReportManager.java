package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.restassured.response.Response;
import org.junit.Assert;

public class ExtentReportManager {
    private static ExtentReports extent;
    private static ExtentTest test;

    public static void setupReport(String testName) {
        if (extent == null) {
            String reportPath = System.getProperty("user.dir") + "/reports/ExtentReport.html";
            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
            reporter.config().setDocumentTitle("API Testing Report");
            reporter.config().setReportName("API Test Results");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
        }
        test = extent.createTest(testName);
    }

    public static void logInfo(String message) {
        if (test != null) {
            test.info(message);
        }
    }

    public static void logPass(String message) {
        if (test != null) {
            test.pass(message);
        }
    }

    public static void logFail(String message) {
        if (test != null) {
            test.fail(message);
        }
    }

    public static void logResponseDetails(Response response, int expectedStatusCode) {
        if (test != null) {
            // Log response status and body
            test.info("Response Status Code: " + response.getStatusCode());
            test.info("Response Body: " + response.getBody().asPrettyString());

            try {
                // Validate status code using assertion
                Assert.assertEquals(expectedStatusCode, response.getStatusCode());
                test.pass("Request was successful with expected status code: " + expectedStatusCode);
            } catch (AssertionError e) {
                // Log assertion failure and rethrow exception for test framework
                test.fail("Request failed. Expected status: " + expectedStatusCode + ", Actual status: " + response.getStatusCode());
                throw e;
            }
        }
    }

    public static void tearDown() {
        if (extent != null) {
            extent.flush();
        }
    }

    public static Response logAndValidate(Response response, int expectedStatusCode) {
        // Log response details in console
        System.out.println("=== Response Details ===");
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asPrettyString());

        // Log response details in the report and validate
        ExtentReportManager.logResponseDetails(response, expectedStatusCode);

        return response; // Return response for further use
    }
}
