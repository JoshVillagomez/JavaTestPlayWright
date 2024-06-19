package com.it.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;

public class CartPage {
private Page page;
	
	

	public CartPage(Page page) {
		this.page = page;
	}
	
	public  void clickCheckOutCart(Page page) {
		waitForPageCart(page); 
		page.click("//a[@class='btn btn-default check_out']");
	           
	       }
	public  void clickRegisterLink(Page page) {
		waitForPageCart(page); 
		page.click("//div[@class='modal-content']//p[@class='text-center']//a[contains(., 'Register')]");
	           
	       }
	
	public static void waitForPageCart(Page page) {
	    try {
	        page.waitForTimeout(4000
	            
	        );
	    } catch (PlaywrightException e) {
	        System.out.println("Error waiting for page load: " + e.getMessage());
	    }
	}

	
	
}
