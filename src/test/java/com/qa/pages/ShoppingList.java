package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShoppingList {

	@FindBy(xpath = "/html/body/div/div[2]/div/div[3]/div[2]/ul/li[1]/div/div[1]/div/a[1]")
	private WebElement resultOne;
	
	public void clickItem1() {
		resultOne.click();
	}

}
