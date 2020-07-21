package com.coinswitch.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.coinswitch.base.BaseTest;
import com.coinswitch.pages.BTCPage;
import com.coinswitch.pages.HomePage;
import com.coinswitch.pages.LoginPage;

public class SmokeTest extends BaseTest {

	private LoginPage loginPage;
	private HomePage homePage;
	private BTCPage btcPage;

	@BeforeClass
	public void initialized() {
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		btcPage = new BTCPage(driver);

	}
	
	@Test(priority=0)
	public void validateSuccessfulLogin() {

		Assert.assertTrue(loginPage.enterPhoneNumber(properties.getProperty("phone")));
		Assert.assertTrue(loginPage.clickProceed());
		Assert.assertTrue(loginPage.enterOTP());
		Assert.assertTrue(loginPage.clickConfirm());
		Assert.assertTrue(loginPage.enterPin(properties.getProperty("pin")));
		Assert.assertTrue(loginPage.checkHomeScreenMessage());

	}

	@Test(priority=1)
	public void validateBTCSearch() {

		Assert.assertTrue(homePage.clickSearchAndEnterCurrencyBar(properties.getProperty("currency")));
		Assert.assertEquals(homePage.getSearchResult().trim(), properties.getProperty("currencyName"));
		Assert.assertTrue(homePage.clickSearchResult());

	}

	@Test(priority=2)
	public void validateBTCAddress() {

		Assert.assertTrue(btcPage.clickDepositIcon());
		Assert.assertEquals(btcPage.getBTCAddress().trim(), properties.getProperty("btcaddress"));

	}
}
