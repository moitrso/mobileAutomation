package com.coinswitch.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import com.coinswitch.helper.AppiumUtilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;

public class BTCPage {
	public AppiumDriver<?> driver;
	private static By depositIcon = MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]");
	private static By recieve = MobileBy.xpath("//*[contains(@text,'Receive Bitcoin')]");
	private static By btcAddress = MobileBy.xpath("//android.widget.ScrollView//android.view.ViewGroup[2]/android.widget.TextView");
	
	public static Boolean assertion = false;

	private static final Logger log = Logger.getLogger(BTCPage.class);

	public BTCPage(AppiumDriver<?> driver) {
		this.driver = driver;
	}

	public Boolean clickDepositIcon() {
		assertion = false;
		try {
			AppiumUtilities.waitUntilMobileElementIsClickable(driver, depositIcon).click();	
			AppiumUtilities.waitUntilMobileElementIsVisible(driver, recieve);
			assertion = true;
			log.info("Deposit Icon clicked successfully");
		} catch (Exception e) {
			assertion = false;
			log.error("Unable to click Deposit Icon " + e);
		}
		return assertion;
	}
	
	public String getBTCAddress() {
		String btcAddr;
		try {

			btcAddr = AppiumUtilities.waitUntilMobileElementIsVisible(driver, btcAddress).getText();
			log.info("BTC Address is: " + btcAddr);
		} catch (Exception e) {
			btcAddr = "No Address Found";
			log.error("Unable to BTC address " + e);
		}
		return btcAddr;
	}


}
