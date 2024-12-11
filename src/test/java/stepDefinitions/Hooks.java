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
    public void setup(Scenario scenario) {
        // Set up the report with the scenario name
        ExtentReportManager.setupReport(scenario.getName());
        ExtentReportManager.logInfo("Starting scenario: " + scenario.getName());
    }

    @After
    public void teardown(Scenario scenario) {
        // Log scenario status
        if (scenario.isFailed()) {
            ExtentReportManager.logFail("Scenario failed: " + scenario.getName());
        } else {
            ExtentReportManager.logPass("Scenario passed: " + scenario.getName());
        }
        ExtentReportManager.tearDown();
    }
}
