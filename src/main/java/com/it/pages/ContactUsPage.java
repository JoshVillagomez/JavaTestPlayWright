package com.it.pages;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;


public class ContactUsPage {
private Page page;
	
	public ContactUsPage(Page page) {
        this.page = page;
	}
	public static void fillOutContactUsForm(Page page, String name, String email, String subject, String message) {
        try {
        	
            Locator nameInput = page.locator("//input[@name='name' and @data-qa='name']");
            Locator emailInput = page.locator("//input[@name='email' and @data-qa='email']");
            Locator subjectInput = page.locator("//input[@name='subject' and @data-qa='subject']");
            Locator messageInput = page.locator("//textarea[@name='message' and @data-qa='message']");
           
            waitForPagecontactUs(page);
            nameInput.fill(name);
            emailInput.fill(email);
            subjectInput.fill(subject);
            messageInput.fill(message);
          
            page.press("input[data-qa='submit-button']", "Enter");
           
            
        } catch (Exception e) {
            System.out.println("Error filling out the contact us form: " + e.getMessage());
        }
    }
	
	public static void clickSubmitButton(Page page) {
		Locator submitButton = page.locator("div.form-group.col-md-12 input[data-qa='submit-button']");
        submitButton.click();
    }
	
	public static void handleChromePopup(Page page) {
        try {
        
            page.waitForSelector("//div[@class='chrome-popup']//button[contains(text(), 'OK')]").click();
        } catch (Exception e) {
            System.out.println("Error handling the Chrome popup: " + e.getMessage());
        }
    }
	

	public static void waitForPagecontactUs(Page page) {
	    try {
	        page.waitForTimeout(4000
	            
	        );
	    } catch (PlaywrightException e) {
	        System.out.println("Error waiting for page load: " + e.getMessage());
	    }
	}
	
	
	public static void clickLogoutLink(Page page) {
        
        
        page.waitForSelector("//a[@href='/logout' and @style='color:brown;']//i[@class='fa fa-lock']/..")
            .click();
    }
	
}
