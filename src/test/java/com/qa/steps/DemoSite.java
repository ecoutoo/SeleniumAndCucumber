package com.qa.steps;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import com.qa.pages.DemoHomePage;
import com.qa.pages.DemoUserPage;
import com.qa.pages.DemoLoginPage;

public class DemoSite {
	
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
	
	@Given("^I can access demo site$")
	public void i_can_access_demo_site() throws Throwable {
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		try {
			driver.get(DemoHomePage.URL);
		} catch (TimeoutException e) {
			System.out.println("Page: " + DemoHomePage.URL + " did not load within 30 seconds!");
		}
	}

	@Given("^I am on the user page$")
	public void i_am_on_the_user_page() throws Throwable {
		DemoHomePage demoHomePage = PageFactory.initElements(driver, DemoHomePage.class);
		demoHomePage.navUserPage();
		new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.name("saveform")));
	}

	@When("^I insert user information$")
	public void i_insert_user_information() throws Throwable {
		String uName = "Test";
		String pWord = "Test1";

		DemoUserPage demoUserPage = PageFactory.initElements(driver, DemoUserPage.class);
		demoUserPage.createUser(uName, pWord);
	}

	@Then("^user is created$")
	public void user_is_created() throws Throwable {
		driver.getPageSource().contains("Test");
	}

	@Given("^I am on the login page$")
	public void i_am_on_the_login_page() throws Throwable {
		DemoHomePage demoHomePage = PageFactory.initElements(driver, DemoHomePage.class);
		demoHomePage.navloginPage();
		new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.name("saveform")));
	}

	@Then("^user is logged in$")
	public void user_is_logged_in() throws Throwable {
		WebElement loginResult = driver.findElement(By.cssSelector("body > table > tbody > tr > td.auto-style1 > big > blockquote > blockquote > font > center > b"));
		Assert.assertEquals(loginResult.getText(),"**Successful Login**");
	}

}
