package com.it.pages;
import com.microsoft.playwright.*;


public class HomePage {
	
	private Page page;
	
	public HomePage(Page page) {
        this.page = page;
    }
	
	public void navigateToHomePage() {
        page.navigate("https://www.automationexercise.com/");
    }
	
	public void middlePageProductSelector() {
		
		page.evaluate("window.scrollTo(0, document.body.scrollHeight / 2)");
		
		page.click("//a[@href='/product_details/30' and contains(.,'View Product')]");
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
	
	public static void clickLogoutLink(Page page) {
        
        
        page.waitForSelector("//a[@href='/logout' and @style='color:brown;']//i[@class='fa fa-lock']/..")
            .click();
        waitForPage(page);
    }
	
public static void clickLoginLink(Page page) {
        

        page.waitForSelector("//a[@href='/login']")
            .click();
        waitForPage(page);
    }
	
public static void clickContactUsLink(Page page) {
        
      
        page.waitForSelector("//a[@href='/contact_us']//i[@class='fa fa-envelope']/..")
            .click();
        waitForPage(page);
    }
	
	

}
