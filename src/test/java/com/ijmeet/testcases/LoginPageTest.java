package com.ijmeet.testcases;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.config.Constants;
import com.config.TestListener;
import com.ijmeet.pageobject.LoginPage;
@Listeners(TestListener.class)
public class LoginPageTest extends BaseClass {
	@Test
	public void tc_001() {
		loginpage.clickOnSign();
		Constants.logger.info("Verify Sign In link");
		Constants.extent.createTest("tc_001 Sign In link");
	}
}
