package com.coinswitch.helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class AppiumUtilities {


	private static final Logger log = Logger.getLogger(AppiumUtilities.class);

	public static MobileElement waitUntilMobileElementIsClickable(AppiumDriver<?> driver, By locator) {
		log.info("15 secs - Waiting for element using -" + locator);
		WebDriverWait wait = new WebDriverWait(driver, 15);
		return (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public static MobileElement waitUntilMobileElementIsVisible(AppiumDriver<?> driver, By locator) {
		log.info("15 secs - Waiting for element using -" + locator);
		WebDriverWait wait = new WebDriverWait(driver, 20);
		return (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	 public static MobileElement androidReturnMobileElementPresentUsingText(AppiumDriver<?> driver, String text) {
		    log.info("Trying to find element with text - " + text);
		    return waitUntilMobileElementIsVisible(
		        driver, MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + text + "\")"));
		  }

	public static void android_openNotifications(AppiumDriver<?> driver) {
		log.info("Opening notifications page");
		((AndroidDriver<?>) driver).openNotifications();
	}
	
	public static MobileElement androidReturnMobileElementPresentUsingID(AppiumDriver<?> driver, String id) {
	    log.info("Trying to find element with ID - " + id);
	    return waitUntilMobileElementIsVisible(
	        driver, MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"" + id + "\")"));
	  }
	public static void androidClearNotifications(AppiumDriver<?> driver) {
		log.info("Clearing Notifications...");
		androidReturnMobileElementPresentUsingID(driver, "com.android.systemui:id/dismiss_view").click();
		
	}


}
