package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShoppingHome {
	
	public static final String URL = "http://automationpractice.com/index.php";
	
	@FindBy(id = "search_query_top")
	WebElement searchBar;
	
	@FindBy(xpath = "/html/body/div/div[1]/header/div[3]/div/div/div[2]/form/button")
	WebElement searchButton;
	
	
	public void useSearchBar(String text) {
		searchBar.sendKeys(text);
		searchButton.click();
	}

}
