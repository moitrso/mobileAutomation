package com.coinswitch.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import com.coinswitch.helper.AppiumUtilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.android.nativekey.AndroidKey;

public class LoginPage {

	public AppiumDriver<?> driver;
	private static By phone = MobileBy.className("android.widget.EditText");
	private static By proceed = MobileBy.xpath("(//android.widget.Button)[2]");
	private static By otp = MobileBy.xpath("//*[contains(@text,'is your CoinSwitch Kuber OTP')]");
	private static By confirm = MobileBy.xpath("(//android.widget.Button)[3]");
	private static By copyOtp = MobileBy.xpath("//*[contains(@text,'Copy OTP')]");
	private static By homeScreenMessage = MobileBy.xpath("//*[contains(@text,'Your Investment Worth')]");
	private static By errorMessage = MobileBy.xpath("//android.widget.FrameLayout/android.view.ViewGroup[2]/android.widget.TextView");
	private static By invalidPinErrorMessage = MobileBy.xpath("//android.view.ViewGroup[3]/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.TextView");
	
	

	public static Boolean assertion = false;

	private static final Logger log = Logger.getLogger(LoginPage.class);

	public LoginPage(AppiumDriver<?> driver) {
		this.driver = driver;
	}

	public Boolean enterPhoneNumber(String phoneNumber) {
		assertion = false;
		try {
			MobileElement phoneInput = AppiumUtilities.waitUntilMobileElementIsClickable(driver, phone);
			phoneInput.clear();
			phoneInput.sendKeys(phoneNumber);
			assertion = true;
			log.info("Phone Number Entered successfully");
		} catch (Exception e) {
			assertion = false;
			log.error("Unable to enter phone number, " + e);
		}
		return assertion;
	}

	public Boolean clickProceed() {
		assertion = false;
		try {
			AppiumUtilities.waitUntilMobileElementIsClickable(driver, proceed).click();
			assertion = true;
			log.info("Clicked Proceed Successfully");
		} catch (Exception e) {
			assertion = false;
			log.error("Unable to click Proceed, " + e);
		}
		return assertion;
	}
	public String getErrorMessage() {
		String errorMsg;
		try {

			errorMsg = AppiumUtilities.waitUntilMobileElementIsVisible(driver, errorMessage).getText();
			log.info("Error message displayed is: " + errorMsg);
		} catch (Exception e) {
			errorMsg = "No Error Message displayed";
			log.error("Unable to locate any error message " + e);
		}
		return errorMsg;
	}
	
	public String getInvalidPinErrorMessage() {
		String errorMsg;
		try {

			errorMsg = AppiumUtilities.waitUntilMobileElementIsVisible(driver, invalidPinErrorMessage).getText();
			log.info("Error message displayed for Incorrect PIN is: " + errorMsg);
		} catch (Exception e) {
			errorMsg = "No Error Message displayed for Incorrect PIN";
			log.error("Unable to locate any error message for Incorrect PIN " + e);
		}
		return errorMsg;
	}
	public Boolean enterOTP() {
		assertion = false;
		try {
			AppiumUtilities.android_openNotifications(driver);
			MobileElement otpCode = AppiumUtilities.waitUntilMobileElementIsVisible(driver, otp);
			log.info("OTP is " + otpCode.getText());
			AppiumUtilities.waitUntilMobileElementIsClickable(driver, copyOtp).click();
			AppiumUtilities.waitUntilMobileElementIsClickable(driver, confirm).click();

			log.info("Entered OTP Successfully");
			//AppiumUtilities.androidClearNotifications(driver);
			assertion = true;
		} catch (Exception e) {
			assertion = false;
			log.error("Unable to enter OTP, " + e);
		}
		return assertion;
	}

	public Boolean clickConfirm() {
		assertion = false;
		try {
			AppiumUtilities.waitUntilMobileElementIsClickable(driver, confirm).click();
			assertion = true;
			log.info("Clicked Confirm Successfully");
		} catch (Exception e) {
			assertion = false;
			log.error("Unable to click Proceed, " + e);
		}
		return assertion;
	}

	public Boolean enterPin(String pin) {
		assertion = false;
		try {
		
			String[] arr = pin.split("");
			for(String character : arr){
				//AppiumUtilities.androidReturnMobileElementPresentUsingText(driver, character).click();
				AppiumUtilities.waitUntilMobileElementIsVisible(driver, By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[" + character + "]/android.view.ViewGroup")).click();
			}
			log.info("Entered PIN Successfully");
			//AppiumUtilities.androidClearNotifications(driver);
			assertion = true;
		} catch (Exception e) {
			assertion = false;
			log.error("Unable to enter PIN, " + e);
		}
		return assertion;
	}

	public Boolean checkHomeScreenMessage() {
		assertion = false;
		try {
			AppiumUtilities.waitUntilMobileElementIsVisible(driver, homeScreenMessage);
			log.info("Getting home screen message..");
			assertion = true;
		} catch (Exception e) {
			assertion = false;
			log.error("Unable to get home screen message, " + e);
		}
		return assertion;
	}

}
