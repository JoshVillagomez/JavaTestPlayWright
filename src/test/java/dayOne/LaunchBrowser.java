package dayOne;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.*;
import java.util.Random; //toberemoded
import java.util.List;
import java.util.Arrays;

public class LaunchBrowser {
	
	public static void main(String[] args) {
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new LaunchOptions().setHeadless(false));
		Page page = browser.newPage();
		
		// Block ad-related network requests
		blockAds(page);
		page.navigate("https://automationexercise.com/");
		//Locator myAccount = page.locator("//ul[@class=\"nav navbar-nav\"]/li/a[contains(., \"Signup / Login\")]");
		//page.click("//ul[@class=\"nav navbar-nav\"]/li/a[contains(., \"Signup / Login\")]");
		page.evaluate("window.scrollTo(0, document.body.scrollHeight / 2)");
		page.click("//a[@href='/product_details/30' and contains(.,'View Product')]");
		waitForPageLoad(page);
		page.fill("#quantity", "30");
		page.click("//button[@class='btn btn-default cart']");
		waitForPageLoad2(page);
		page.click("//div[@class='modal-content']//a[contains(., 'View Cart')]");
		waitForPageLoad2(page);
		page.click("//a[@class='btn btn-default check_out']");
		waitForPageLoad2(page);
		page.click("//div[@class='modal-content']//p[@class='text-center']//a[contains(., 'Register')]");
		waitForPageLoad2(page);
		fillSignupForm(page, "Joe Maggio", "emailJoe@joe.com");
		waitForPageLoad2(page);
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
        
		
	}
	private static void waitForPageLoad(Page page) {
        // Wait for 5 seconds
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
	private static void waitForPageLoad2(Page page) {
        // Wait for 5 seconds
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
	private static void fillSignupForm(Page page, String signUpName, String signUpEmail) {
        // Generate random name and email
       // String randomName = generateRandomName();
        //String randomEmail = generateRandomEmail();

        // Fill the name input field
        //page.fill("//input[@data-qa='signup-name']", randomName);
        Locator sigupNameField = page.locator("//input[@data-qa='signup-name']");
        Locator sigupEmailField = page.locator("//input[@data-qa='signup-email']");
        sigupNameField.fill(signUpName);     
        sigupEmailField.fill(signUpEmail);

        // Fill the email input field
        //page.fill("//input[@data-qa='signup-email']", randomEmail);

        // Submit the form
        page.click("//button[@data-qa='signup-button']");
    }
	/*private static String generateRandomName() {
        // Generate a random name
		String[] names = {
			    "Johny", "Janet", "Bobby", "Alicent", "Tomm", "Emilys",
			    "Emma", "Liam", "Olivia", "Noah", "Sophia", "Jacob",
			    "Isabella", "William", "Ava", "Michael", "Mia", "Daniel"
			};
        Random random = new Random();
        return names[random.nextInt(names.length)];
    }*/

   /* private static String generateRandomEmail() {
        // Generate a random email address
        String[] domains = {"example.com", "gmail.com", "yahoo.com", "hotmail.com"};
        Random random = new Random();
        return generateRandomName().toLowerCase() + "@" + domains[random.nextInt(domains.length)];
    }*/
    
    private static void fillTitleField(Page page) {
        // Find the title radio buttons using XPath
        Locator titleRadios = page.locator("//input[@name='title']");

        // Select a random title option
        int randomIndex = (int) (Math.random() * titleRadios.count());
        titleRadios.nth(randomIndex).click();
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
    private static void fillPasswordField(Page page) {
        // Generate a random strong password
        String randomPassword = generateRandomPassword();

        // Find the password input field using XPath
        Locator passwordInput = page.locator("//input[@type='password' and @data-qa='password']");

        // Fill out the password field
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
        // Find the day dropdown field using a CSS selector
        Locator dayDropdown = page.locator("select#days");

        // Generate a random day
        String randomDay = generateRandomDay();

        // Select the random day from the dropdown
        dayDropdown.selectOption(randomDay);
    }
    
    private static String generateRandomMonth() {
    	List<String> MONTHS = Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
    	Random random = new Random();
        int index = random.nextInt(MONTHS.size());
        return MONTHS.get(index);
    }

    private static void fillMonthDropdown(Page page) {
        // Find the month dropdown field using a CSS selector
        Locator monthDropdown = page.locator("select#months");

        // Generate a random month
        String randomMonth = generateRandomMonth();

        // Select the random month from the dropdown
        monthDropdown.selectOption(randomMonth);
    }
    
    private static String generateRandomYear() {
    	List<String> YEARS = Arrays.asList("1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000");
    	Random random = new Random();
        int index = random.nextInt(YEARS.size());
        return YEARS.get(index);
    }

    private static void fillYearDropdown(Page page) {
        // Find the year dropdown field using a CSS selector
        Locator yearDropdown = page.locator("select#years");

        // Generate a random year
        String randomYear = generateRandomYear();

        // Select the random year from the dropdown
        yearDropdown.selectOption(randomYear);
    }
    
    private static void fillNewsletterCheckbox(Page page) {
        // Find the checkbox element using XPath
        Locator checkboxLocator = page.locator("//div[@class='checker']//input[@name='newsletter']");

        // Check the checkbox
        checkboxLocator.check();
    }
    
    private static void fillOptinCheckbox(Page page) {
        // Find the checkbox element using XPath
        Locator checkboxLocator = page.locator("//div[@class='checker']//input[@name='optin']");

        // Check the checkbox
        checkboxLocator.check();
    }
    
    private static void fillPersonalFields(Page page, String firstName, String lastName, String companyName, String adress) {
        // Locators
        Locator firstNameField = page.locator("//input[@data-qa='first_name']");
        Locator lastNameField = page.locator("//input[@data-qa='last_name']");
        Locator companyField = page.locator("//input[@data-qa='company']");
        Locator addressField = page.locator("//input[@data-qa='address']");
        // Fill the personal fields
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
        // Find the month dropdown field using a CSS selector
        Locator countryDropdown = page.locator("select#country");

        // Generate a random month
        String randomCountry = generateRandomCountry();

        // Select the random month from the dropdown
        countryDropdown.selectOption(randomCountry);
    }
    
    private static void fillLocationlFields(Page page, String state, String city, String zip, String mobile) {
        // Locators
        Locator stateField = page.locator("//input[@data-qa='state']");
        Locator cityField = page.locator("//input[@data-qa='city']");
        Locator zipField = page.locator("//input[@data-qa='zipcode']");
        Locator mobileField = page.locator("//input[@data-qa='mobile_number']");
        // Fill the personal fields
        stateField.fill(state);     
        cityField.fill(city);
        zipField.fill(zip);
        mobileField.fill(mobile);
    }
    private static void clickCreateAccountButton(Page page) {
        // Find the "Create Account" button using XPath
        Locator createAccountButton = page.locator("//button[@data-qa='create-account']");
        
        // Click the "Create Account" button
        createAccountButton.click();
    }


}
