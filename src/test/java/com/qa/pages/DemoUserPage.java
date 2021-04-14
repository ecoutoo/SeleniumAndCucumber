package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DemoUserPage {
	
	@FindBy(name = "username")
	WebElement uName;

	@FindBy(name = "password")
	WebElement pWord;

	public void createUser(String username, String password) {
		uName.sendKeys(username);
		pWord.sendKeys(password);
		pWord.submit();
	}

}
