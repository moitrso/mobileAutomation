package com.coinswitch.base;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;

/***
 * 
 * @author Soumyabrata
 * @date July 19, 2020
 * 
 */

/**
 * 
 * @Usage: This class is used to initiate the Appium Driver and all other
 *         reporting stuffs. All static variables are declared here and
 *         inherited by other classes.
 *
 */

public class BaseTest {

	public static FileInputStream fInputStream;
	protected static Properties properties;

	private static final Logger log = Logger.getLogger(BaseTest.class);
	// Initiating Appium Driver
	public static AppiumDriver<?> driver;

	/*
	 * This method is used for initializing the Log4j and Config.properties
	 */
	@BeforeSuite
	public static void startSuite() {
		log.info("Test Started successfully");

		log.info("Initializing the Config.properties file");
		// Path where Config.properties file is placed
		String propertyFilePath = "src//main//resources//config.properties";

		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
				log.info("Properties file loaded successfully:");
				// Config file is loaded successfully, now with the help of
				// properties.getProperty(key) we can retrieve the value.
			} catch (IOException ioE) {
				ioE.printStackTrace();
			}
		} catch (FileNotFoundException fnfE) {
			fnfE.printStackTrace();
			log.fatal("Unable to find/Load the Properties file ");
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}
	}



	/*
	 * This method is used for init the Appium Driver and Extent report
	 */
	@BeforeTest
	public static void startTest() {

		// Getting the driver declared using our Capabilities
		try {
			driver = startAppium();
		} catch (Exception e) {
			log.fatal("Driver is not Initiated as Expected" + e.getMessage());
		}

	}


	/*
	 * This method is used for initiate the AppiumDriver with caps and connection protocol
	 */
	public static AppiumDriver<?> startAppium() {
		// Initializing the Appium driver
		try {
			DesiredCapabilities cap = new DesiredCapabilities();
			// All Capability values are retrieved from Config.properties file.
			cap.setCapability(MobileCapabilityType.PLATFORM_NAME, properties.getProperty("PLATFORM_NAME"));
			cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, properties.getProperty("PLATFORM_VERS"));
			cap.setCapability(MobileCapabilityType.DEVICE_NAME, properties.getProperty("DEVICE_NAME"));
			cap.setCapability("appActivity", properties.getProperty("appActivity"));
			cap.setCapability("appPackage", properties.getProperty("appPackage"));
			cap.setCapability("autoLaunch", true);
			cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 500);
			cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);

			// Declaring the driver as "Android driver" with the Host and Port number to communicate with Appium desktop
			driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
			//Printing the driver details in Log file
			log.info("Driver declared successfully : " +driver);
		} catch (Exception e) {
			driver = null;
			log.fatal("Driver declaration failed : " +driver);
			log.fatal(e.getStackTrace());
		}
		//Returning the instance of the driver to the parent method
		return driver;
	}

	@BeforeClass
	public static void openApp() {
		log.info("Test case started successfully");
		log.info("Trying to launch the Application under Test");
		try{
			driver.launchApp();
			log.info("Kuber App launched successfully");
		}catch(Exception e) {
			log.info("Unable to launch the Kuber App :" +e.getMessage());
		}

	}

	@AfterClass
	public static void closeApp() {
		log.info("Going to close the app");
		try{
			driver.terminateApp(properties.getProperty("appPackage"));
			log.info("Kuber App closed successfully");
		}catch(Exception e) {
			log.info("Unable to close the Kuber App :" +e.getMessage());
		}
	}

	@AfterTest
	public static void closeSession() {
		log.info("Going to terminate Appium session");
		try{
			driver.quit();
			log.info("Appium Session terminated Successfully");
		}catch(Exception e) {
			log.info("Unable to terminate Appium Session :" +e.getMessage());
		}
	}


}
