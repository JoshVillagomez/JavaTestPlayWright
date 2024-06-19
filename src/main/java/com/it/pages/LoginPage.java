package com.it.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LoginPage {
	
private Page page;
	
	public LoginPage(Page page) {
        this.page = page;

    }
	
public  void fillSignupForm(Page page, String signUpName, String signUpEmail) {
        
        Locator sigupNameField = page.locator("//input[@data-qa='signup-name']");
        Locator sigupEmailField = page.locator("//input[@data-qa='signup-email']");
        sigupNameField.fill(signUpName);     
        sigupEmailField.fill(signUpEmail);

  
        page.click("//button[@data-qa='signup-button']");
    }

public  void fillLoginpForm(Page page, String loginEmail, String loginPassword) {
    
    Locator loginEmailField = page.locator("//input[@data-qa='login-email']");
    Locator passwordField = page.locator("//input[@data-qa='login-password']");
    loginEmailField.fill(loginEmail);     
    passwordField.fill(loginPassword);


    page.click("//button[@data-qa='login-button']");
}

}
