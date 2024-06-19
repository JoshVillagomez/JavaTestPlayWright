package com.it.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.Request;

public class ProductsPage {
private Page page;
	
	public ProductsPage(Page page) {
        this.page = page;
    }
public  void fillProductAmount(Page page) {
	blockAds(page);
	waitForPage(page);
		page.fill("#quantity", "30");
           
       }
public  void clickOnCartButton(Page page) {
	waitForPage(page);  
	page.click("//button[@class='btn btn-default cart']");
           
       }
public  void clickOnViewCartLink(Page page) {
	waitForPage(page);  
	page.click("//div[@class='modal-content']//a[contains(., 'View Cart')]");
           
}   


private static void blockAds(Page page) {
    page.route("**/*", route -> {
        if (isAdRequest(route.request())) {
            route.abort();
        } else {
            route.resume();
        }
    });
}

private static boolean isAdRequest(Request request) {
    return request.url().contains("doubleclick.net") || request.resourceType().equals("image");
}

public static void waitForPage(Page page) {
    try {
        page.waitForTimeout(3000
            
        );
    } catch (PlaywrightException e) {
        System.out.println("Error waiting for page load: " + e.getMessage());
    }
}

	

}
