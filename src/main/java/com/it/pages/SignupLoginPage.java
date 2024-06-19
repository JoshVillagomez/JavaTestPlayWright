package com.it.pages;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;

public class SignupLoginPage {
private Page page;
	
	public SignupLoginPage(Page page) {
        this.page = page;
    }

	public  void fillSignUpFields(Page page) {
		fillTitleField(page);
		fillPasswordField(page);
		fillDayDropdown(page);
		fillMonthDropdown(page);
		fillYearDropdown(page);
		fillNewsletterCheckbox(page);
		fillOptinCheckbox(page);
		fillPersonalFields(page, "John", "Doe", "ApplyDigital", "7th Street Ave.");
		fillCountryDropdown(page);
		fillLocationlFields(page, "Washington", "Bothell", "98011", "5551111333222");
		clickCreateAccountButton(page);
		clickContinueButton(page);
		clickCartButton(page);
		clickCheckoutButton(page);
	       }
	public static void clickCartButton(Page page) {
        
        page.click("//a[contains(@href, '/view_cart')]/i[@class='fa fa-shopping-cart']");
           
       }
	
	private static void waitForPage(Page page) {
	    try {
	        page.waitForTimeout(3000
	            
	        );
	    } catch (PlaywrightException e) {
	        System.out.println("Error waiting for page load: " + e.getMessage());
	    }
	}
   
   public static void clickCheckoutButton(Page page) {
    
   	waitForPage(page);     
   	page.click("//a[@class='btn btn-default check_out']");
   	waitForPage(page);         
       }

	private static void clickContinueButton(Page page) {
        
        page.click("//a[@href='/' and @data-qa='continue-button' and @class='btn btn-primary']");

    }
	
	private static void fillTitleField(Page page) {
       
        Locator titleRadios = page.locator("//input[@name='title']");

        
        int randomIndex = (int) (Math.random() * titleRadios.count());
        titleRadios.nth(randomIndex).click();
    }
	
	 private static void fillPasswordField(Page page) {
	        
	        String randomPassword = generateRandomPassword();

	   
	        Locator passwordInput = page.locator("//input[@type='password' and @data-qa='password']");

	      
	        passwordInput.fill(randomPassword);
	    }
	    private static String generateRandomPassword() {
	           return "MyStrongPassword123!";
	    }

	    private static String generateRandomDay() {
	    	List<String> DAYS = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29");
	    	Random random = new Random();
	        int index = random.nextInt(DAYS.size());
	        return DAYS.get(index);
	    }

	    private static void fillDayDropdown(Page page) {
	        
	        Locator dayDropdown = page.locator("select#days");

	        
	        String randomDay = generateRandomDay();

	        
	        dayDropdown.selectOption(randomDay);
	    }
	    
	    private static String generateRandomMonth() {
	    	List<String> MONTHS = Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
	    	Random random = new Random();
	        int index = random.nextInt(MONTHS.size());
	        return MONTHS.get(index);
	    }

	    private static void fillMonthDropdown(Page page) {
	       
	        Locator monthDropdown = page.locator("select#months");

	       
	        String randomMonth = generateRandomMonth();

	        
	        monthDropdown.selectOption(randomMonth);
	    }
	    
	    private static String generateRandomYear() {
	    	List<String> YEARS = Arrays.asList("1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000");
	    	Random random = new Random();
	        int index = random.nextInt(YEARS.size());
	        return YEARS.get(index);
	    }

	    private static void fillYearDropdown(Page page) {
	   
	        Locator yearDropdown = page.locator("select#years");

	       
	        String randomYear = generateRandomYear();

	       
	        yearDropdown.selectOption(randomYear);
	    }
	    
	    private static void fillNewsletterCheckbox(Page page) {
	     
	        Locator checkboxLocator = page.locator("//div[@class='checker']//input[@name='newsletter']");

	      
	        checkboxLocator.check();
	    }
	    
	    private static void fillOptinCheckbox(Page page) {
	        
	        Locator checkboxLocator = page.locator("//div[@class='checker']//input[@name='optin']");

	       
	        checkboxLocator.check();
	    }
	    
	    private static void fillPersonalFields(Page page, String firstName, String lastName, String companyName, String adress) {
	       
	        Locator firstNameField = page.locator("//input[@data-qa='first_name']");
	        Locator lastNameField = page.locator("//input[@data-qa='last_name']");
	        Locator companyField = page.locator("//input[@data-qa='company']");
	        Locator addressField = page.locator("//input[@data-qa='address']");
	       
	        firstNameField.fill(firstName);     
	        lastNameField.fill(lastName);
	        companyField.fill(companyName);
	        addressField.fill(adress);
	    }
	    
	    private static String generateRandomCountry() {
	    	List<String> COUNTRIES = Arrays.asList("India", "United States", "Canada", "Australia", "Israel", "New Zealand", "Singapore");
	    	Random random = new Random();
	        int index = random.nextInt(COUNTRIES.size());
	        return COUNTRIES.get(index);
	    }

	    private static void fillCountryDropdown(Page page) {
	       
	        Locator countryDropdown = page.locator("select#country");

	      
	        String randomCountry = generateRandomCountry();

	   
	        countryDropdown.selectOption(randomCountry);
	    }
	    
	    private static void fillLocationlFields(Page page, String state, String city, String zip, String mobile) {
	
	        Locator stateField = page.locator("//input[@data-qa='state']");
	        Locator cityField = page.locator("//input[@data-qa='city']");
	        Locator zipField = page.locator("//input[@data-qa='zipcode']");
	        Locator mobileField = page.locator("//input[@data-qa='mobile_number']");
	       
	        stateField.fill(state);     
	        cityField.fill(city);
	        zipField.fill(zip);
	        mobileField.fill(mobile);
	    }
	    private static void clickCreateAccountButton(Page page) {
	    
	        Locator createAccountButton = page.locator("//button[@data-qa='create-account']");
	        
	     
	        createAccountButton.click();
	    }
}
