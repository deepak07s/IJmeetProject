package com.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Utility {
	/**
	 * This method will open Object Repository Properties and return value associate with key
	 * 
	 * @param key
	 * @return value in form of{@code String}
	 *  @author Sujit Kolhe
	 */
	public static String getProperty(String key, String propertiesFilepath) {
		String value = null;
		FileInputStream fis;

		try {
			fis = new FileInputStream(propertiesFilepath);
			Properties prop = new Properties();
			prop.load(fis);
			value = prop.getProperty(key);
		} catch (IOException e) {
			System.out.println("Unable to load Properties File");
			e.printStackTrace();
		}
		return value;

	}

	public static String captureScreenshot(WebDriver driver, String fileName) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd_hh_mm_ss").format(new java.util.Date());
		String screenshotPath = System.getProperty("user.dir") + "/Screenshots/" + fileName + " " + dateTimeFormat
				+ ".png";
		try {
			FileUtils.copyFile(src, new File(screenshotPath));
		} catch (IOException e) {
			System.out.println("Screenshot result failed" + e.getMessage());
			e.printStackTrace();
		}
		return screenshotPath;
	}
}
