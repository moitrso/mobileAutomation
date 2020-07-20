package com.coinswitch.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import com.coinswitch.helper.AppiumUtilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;

public class HomePage {
	public AppiumDriver<?> driver;
	private static By homeSearch = MobileBy.xpath("//*[contains(@text,'Search Currencies')]");
	private static By searchCurr = MobileBy.className("android.widget.EditText");
	private static By searchResult = MobileBy.xpath("//android.widget.ScrollView//android.widget.TextView[1]");
	private static By imageResult = MobileBy.xpath("//android.widget.ScrollView//android.widget.ImageView");
	
	public static Boolean assertion = false;

	private static final Logger log = Logger.getLogger(HomePage.class);

	public HomePage(AppiumDriver<?> driver) {
		this.driver = driver;
	}
	
	public Boolean clickSearchAndEnterCurrencyBar(String currency) {
		assertion = false;
		try {
			AppiumUtilities.waitUntilMobileElementIsClickable(driver, homeSearch).click();
			AppiumUtilities.waitUntilMobileElementIsClickable(driver, searchCurr).sendKeys(currency);		
			assertion = true;
			log.info("Clicked and entered Search term Successfully");
		} catch (Exception e) {
			assertion = false;
			log.error("Unable to click and enter Search term " + e);
		}
		return assertion;
	}
	
	public String getSearchResult() {
		String searchRes;
		try {
			
			searchRes = AppiumUtilities.waitUntilMobileElementIsVisible(driver, searchResult).getText();
			log.info("Search Result is: " + searchRes);
		} catch (Exception e) {
			searchRes = "No Search Result Found";
			log.error("Unable to find any search result " + e);
		}
		return searchRes;
	}
	
	public Boolean clickSearchResult() {
		assertion = false;
		try {	
			MobileElement searchImage = AppiumUtilities.waitUntilMobileElementIsClickable(driver, imageResult);
			searchImage.click();
			searchImage.click();
			assertion = true;
			log.info("Clicked on Search Result Successfully");
		} catch (Exception e) {
			assertion = false;
			log.error("Unable to click on enter Search Result " + e);
		}
		return assertion;
	}


}
