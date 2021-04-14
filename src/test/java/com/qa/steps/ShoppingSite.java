package com.qa.steps;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.pages.DemoHomePage;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import com.qa.pages.ShoppingHome;
import com.qa.pages.ShoppingList;


public class ShoppingSite {
	
	private static Logger LOGGER = Logger.getGlobal();
    private static RemoteWebDriver driver;
    
    
    
    @Before
    public static void init() {
    	
		System.setProperty("webdriver.chrome.driver",
				"src/test/resources/chromedriver.exe");
		ChromeOptions cOptions = new ChromeOptions();
		cOptions.setHeadless(false);
		driver = new ChromeDriver(cOptions);
    	cOptions.setCapability("profile.default_content_setting_values.cookies", 2);
    	cOptions.setCapability("network.cookie.cookieBehavior", 2);
    	cOptions.setCapability("profile.block_third_party_cookies", true);
		driver.manage().window().setSize(new Dimension(1366, 768));
    	
    }
    
    @After
    public static void finalTearDown() {
		LOGGER.warning("Closing webdriver instance!");
		driver.quit();
    }
	
	@Given("^I can access shopping site$")
	public void i_can_access_shopping_site() throws Throwable {
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		try {
			driver.get(ShoppingHome.URL);
		} catch (TimeoutException e) {
			System.out.println("Page: " + DemoHomePage.URL + " did not load within 30 seconds!");
		}
	}

	@When("^I search for dress$")
	public void i_search_for_dress() throws Throwable {
		ShoppingHome shoppingHome = PageFactory.initElements(driver, ShoppingHome.class);
		shoppingHome.useSearchBar("dress");
	}

	@When("^I check a dress is there$")
	public void i_check_a_dress_is_there() throws Throwable {
		new WebDriverWait(driver, 5);
		driver.getPageSource().contains("dress");
		ShoppingList shoppingList = PageFactory.initElements(driver, ShoppingList.class);
		shoppingList.clickItem1();
	}

	@Then("^add the dress to checkout$")
	public void add_the_dress_to_checkout() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^purchase the dress$")
	public void purchase_the_dress() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

}
