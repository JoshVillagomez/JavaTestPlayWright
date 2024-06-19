package com.it.pages;

import java.util.Random;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;

public class AddressPage {
private Page page;
	
	public AddressPage(Page page) {
        this.page = page;
        

    }
	public void fillAddressPageInfo(Page page) {
		String randomComment = generateRandomComment();
        writeRandomComment(page, randomComment);
        waitForPageAddress(page);
        clickPlaceOrderButton(page);
	}

    public static void writeRandomComment(Page page, String comment) {
       
        page.waitForSelector("//textarea[@name='message']")
            .focus();

        page.fill("//textarea[@name='message']", comment);
    }
public static String generateRandomComment() {
    String[] comments = {
        "Great post!",
        "I really enjoyed this.",
        "Interesting perspective.",
        "Thanks for sharing.",
        "Well written."
    };

    Random random = new Random();
    return comments[random.nextInt(comments.length)];
}

public static void clickPlaceOrderButton(Page page) {
    

        page.click("//a[@href='/payment' and @class='btn btn-default check_out']");
        waitForPageAddress(page); 
    }

public static void waitForPageAddress(Page page) {
    try {
        page.waitForTimeout(4000
            
        );
    } catch (PlaywrightException e) {
        System.out.println("Error waiting for page load: " + e.getMessage());
    }
}

}
