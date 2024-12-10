package stepDefinitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.ExtentReportManager;

public class Hooks {
    private ExtentReports extent;
    private ExtentTest test;

    @Before
    public void setUp(Scenario scenario) {
        // Initialize ExtentReports and create a test entry
        extent = ExtentReportManager.getInstance();
        test = extent.createTest(scenario.getName());

        // Add a log entry for test start
        test.log(Status.INFO, "Starting scenario: " + scenario.getName());
    }

    @After
    public void tearDown(Scenario scenario) {
        // Capture screenshot if scenario fails
        if (scenario.isFailed()) {
            test.log(Status.FAIL, "Scenario failed: " + scenario.getName());
        } else {
            test.log(Status.PASS, "Scenario passed: " + scenario.getName());
        }
        extent.flush();
    }
}
