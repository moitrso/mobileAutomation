package com.coinswitch.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.coinswitch.pages.LoginPage;

import coinswitch.base.BaseTest;

public class NegativeTests extends BaseTest {

	private LoginPage loginPage;

	@BeforeClass
	public void initialized() {
		loginPage = new LoginPage(driver);

	}
	
	@Test(priority=0)
	public void validateLoginWithInvalidPhoneNumber() {

		Assert.assertTrue(loginPage.enterPhoneNumber(properties.getProperty("invalidaPhone")));
		Assert.assertTrue(loginPage.clickProceed());
		Assert.assertEquals(loginPage.getErrorMessage().trim(), "Invalid Phone Number");

	}
	
	
	
	@Test(priority=1)
	public void validateLoginWithInvalidPIN() {

		Assert.assertTrue(loginPage.enterPhoneNumber(properties.getProperty("phone")));
		Assert.assertTrue(loginPage.clickProceed());
		Assert.assertTrue(loginPage.enterOTP());
		Assert.assertTrue(loginPage.clickConfirm());
		Assert.assertTrue(loginPage.enterPin(properties.getProperty("invalidPin")));
		Assert.assertEquals(loginPage.getInvalidPinErrorMessage().trim(), "Incorrect PIN, please try again.");
		Assert.assertFalse(loginPage.checkHomeScreenMessage());

	}

}
