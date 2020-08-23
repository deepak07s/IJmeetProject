package com.ijmeet.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.config.Constants;

public class LoginPage {
	// This is to Initializing the page Objects
	public LoginPage() {
		PageFactory.initElements(Constants.driver, this);
	}

	@FindBy(xpath = "//a[text()=' Sign In ']")
	private WebElement signIn;
	
  //This method to click on Sign In link
	public void clickOnSign() {
		signIn.click();
	}
}