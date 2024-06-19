package com.it.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;

public class PaymentPage {
private Page page;
	
	public PaymentPage(Page page) {
        this.page = page;
	}
	
public  void fillPaymentFields(Page page) {
        
	fillPaymentForm(
            page,
            "John Doe",
            "4111111111111111",
            "123",
            "05",
            "2025"
        );
    clickContinueButton(page);
    }
public static void fillPaymentForm(Page page, String nameOnCard, String cardNumber, String cvc, String expiryMonth, String expiryYear) {

    page.waitForSelector("//input[@name='name_on_card' and @data-qa='name-on-card']")
        .fill(nameOnCard);

    page.waitForSelector("//input[@name='card_number' and @data-qa='card-number']")
        .fill(cardNumber);

    page.waitForSelector("//input[@name='cvc' and @data-qa='cvc']")
        .fill(cvc);

    page.waitForSelector("//input[@name='expiry_month' and @data-qa='expiry-month']")
        .fill(expiryMonth);

    page.waitForSelector("//input[@name='expiry_year' and @data-qa='expiry-year']")
        .fill(expiryYear);

    page.waitForSelector("//button[@data-qa='pay-button' and @type='submit']")
        .click();
    
}
public static void clickContinueButton(Page page) {

    
    page.waitForSelector("//a[@href='/' and @data-qa='continue-button' and @class='btn btn-primary']")
        .click();
    waitForPagePayment(page);
}

public static void waitForPagePayment(Page page) {
    try {
        page.waitForTimeout(4000
            
        );
    } catch (PlaywrightException e) {
        System.out.println("Error waiting for page load: " + e.getMessage());
    }
}


}
