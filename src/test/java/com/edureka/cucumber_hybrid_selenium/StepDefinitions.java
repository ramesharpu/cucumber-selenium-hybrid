package com.edureka.cucumber_hybrid_selenium;

import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pom.Login;
import pom.Navigation;
import utils.Common;
import utils.ReadFiles;

public class StepDefinitions{
	Common common;
	Login login;
	WebDriver driver;
	
	Map<String, String> result;
	Navigation nav;
	String browser;
	String configFile = "config.properties";
	String url;
	String expectedEmailErrorHeader = "There was a problem";
	String expectedEmailErrorMessage = "We cannot find an account with that email address";
	String expectedPhoneErrorHeader = "Incorrect phone number";
	String expectedPhoneErrorMessage = "We cannot find an account with that mobile number";

	@Before
	public void setup(){
		readPropertiesFile();
		common = new Common();
		common.setupBrowser(browser, url);
		driver = common.getDriver();
		nav = new Navigation(driver);
	}
	
	@After
	public void tearDown() {
		common.quitBrowser();
	}
	

	public void readPropertiesFile() {
		ReadFiles readConfigFile = new ReadFiles();
		Properties prop = readConfigFile.readPropertyFile(configFile);
		browser = prop.getProperty("browser");
		url = prop.getProperty("url");
	}
	
	public String getCurrentPageUrl() {
		return driver.getCurrentUrl();
	}
	
	public String getCurrentPageTitle() {
		return driver.getTitle();
	}
	
	public void navigateToBaseUrl() {
		driver.navigate().to(url);
	}
	
	@Given("Browser is open")
	public void browser_is_open() {
		navigateToBaseUrl();	    
	}

	@When("enter the short url {string}")
	public void enter_the_short_url(String shortUrl) {
	    driver.navigate().to(shortUrl);
	}

	@Then("validate the amazon url")
	public void validate_the_amazon_url() {
		String currentUrl = getCurrentPageUrl();
	   assert(currentUrl.equals(url));
	}
	
	@Then("Validate the page title")
	public void validate_the_page_title() {
	    String expectedTitle = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
	    String actualTitle = getCurrentPageTitle();
	    assert(actualTitle.equals(expectedTitle));
	}
	
	@When("I want to go to Wish List page")
	public void i_want_to_go_to_wish_list_page() {
		nav.createWishList();
	}

	@Then("I verify that I am in Wish List page")
	public void i_verify_that_i_am_in_wish_list_page() {
		String text = driver.findElement(By.xpath("//div[@role='heading']")).getText();
		assert(text.equals("Your Lists"));
	}
	
	@When("I want to go to Amazon Pay page")
	public void i_want_to_go_to_amazon_pay_page() {
		nav.amazonPay();
	}

	@Then("I verify that I am in Amazon Page page")
	public void i_verify_that_i_am_in_amazon_pay_page() {
		boolean elem = driver.findElement(By.xpath("//span[contains(text(),'Amazon Pay balance')]")).isDisplayed();
		assert(elem);
	}
	
	@When("I want to go to New Releases page")
	public void i_want_to_go_to_new_releases_page() {
		nav.newReleases();	 
	}

	@Then("I verify that I am in New Releases page")
	public void i_verify_that_i_am_in_new_releases_page() {
		boolean elem = driver.findElement(By.xpath("//div[contains(text(),'Amazon Hot New Releases')]")).isDisplayed();
		assert(elem);
	}
	
	@Given("I am in login page")
	public void i_am_in_login_page() {
		nav.login();
	}

	@When("I enter the invalid credentials {string}")
	public void i_enter_the_invalid_email_id(String username) {
		login = new Login(driver);
		result = login.invalidLoginMessage(username);
	}

	@Then("I verify the login status as Fail")
	public void i_verify_the_login_status_as_fail() {
			assert(result.get("errorHeader").equals(expectedEmailErrorHeader) || result.get("errorHeader").equals(expectedPhoneErrorHeader));
			assert(result.get("errorMessage").equals(expectedEmailErrorMessage) || result.get("errorMessage").equals(expectedPhoneErrorMessage));
	}

	@When("I enter the valid credentials {string} and {string}")
	public void i_enter_the_valid_email_id(String username, String password) {
		login = new Login(driver);
		login.successfulLogin(username, password);
	}
	
	@Then("I verify the login status as Pass")
	public void i_verify_the_login_status_as_pass() {
		String defaultwelcomeString = "Hello, Sign in";
		By welcomeMessage = By.xpath("//span[@class='nav-line-3']");
		String actualWelcomeString = driver.findElement(welcomeMessage).getText();
		assert(!actualWelcomeString.equals(defaultwelcomeString));
		login.logout();
	}
	
}
