package com.config;

import java.io.File;
import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class TestListener implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("On Test Start : " + result.getName());
		Constants.test.log(Status.INFO, "Start Test Case: " + result.getName());

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("On Test Successed : " + result.getName());
		Constants.test.log(Status.PASS, "Passed Test Case: " + result.getName().getClass());
		try {
			Constants.test.pass("Test Passed ",
					MediaEntityBuilder
							.createScreenCaptureFromPath(Utility.captureScreenshot(Constants.driver, result.getName()))
							.build());
		} catch (IOException e) {
			System.out.println("File not found" + e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("On Test Failed : " + result.getName());
		Constants.test.log(Status.FAIL, "Failed Test Case: " + result.getName());
		try {
			Constants.test.fail("Test Failed ",
					MediaEntityBuilder
							.createScreenCaptureFromPath(Utility.captureScreenshot(Constants.driver, result.getName()))
							.build());
		} catch (IOException e) {
			System.out.println("File not found" + e.getMessage());
			e.printStackTrace();
		}

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("On test Skipped " +result.getName());
		Constants.test.log(Status.SKIP, "Skipped Test Case: " + result.getName());

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// Start Reporters
		ExtentHtmlReporter reporter = new ExtentHtmlReporter(
				new File(System.getProperty("user.dir") + "/Report/ExtentsReport.html"));
		// create ExtentReports and attach reporter
		Constants.extent = new ExtentReports();
		Constants.extent.attachReporter(reporter);
		// creates a toggle for the given test, adds all log events under it
		Constants.test = Constants.extent.createTest("IJmeet Report");
		// log(Status, details)
		Constants.test.log(Status.INFO, "IJmeet");
	}

	@Override
	public void onFinish(ITestContext context) {
		// calling flush writes everything to the log file
		Constants.extent.flush();

	}

}
