package tests;

import com.it.pages.HomePage;
import com.it.pages.CartPage;
import com.it.pages.SignupLoginPage;
import com.it.pages.ProductsPage;
import com.it.pages.LoginPage;
import com.it.pages.AddressPage;
import com.it.pages.PaymentPage;
import com.it.pages.ContactUsPage;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Request;
import com.microsoft.playwright.BrowserType.LaunchOptions;

public class MainE2E {
	public static void main(String[] args) {
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new LaunchOptions().setHeadless(false));
		Page page = browser.newPage();
		 try {
			
		performE2ETestScenario(page);
				
		 } catch (Exception e) {
	            e.printStackTrace();
	        }
		
		}
	private static void performE2ETestScenario(Page page) {
        // Create instances of the page objects
        HomePage homePage = new HomePage(page);
        SignupLoginPage signupLoginPage = new SignupLoginPage(page);
        ProductsPage productsPage = new ProductsPage(page);
        CartPage cartPage = new CartPage(page);
        LoginPage loginPage = new LoginPage(page);
        AddressPage addressPage = new AddressPage(page);
        PaymentPage paymentPage = new PaymentPage(page);
        ContactUsPage contactUsPage = new ContactUsPage(page);

        // Execute the test scenario
        blockAds(page);
        homePage.navigateToHomePage();
        homePage.middlePageProductSelector();
        productsPage.fillProductAmount(page);
        productsPage.clickOnCartButton(page);
        productsPage.clickOnViewCartLink(page);
        cartPage.clickCheckOutCart(page);
        cartPage.clickRegisterLink(page);
        loginPage.fillSignupForm(page, "Joe Mion6r", "ma064944d@joe.com");
        signupLoginPage.fillSignUpFields(page);
        addressPage.fillAddressPageInfo(page);
        paymentPage.fillPaymentFields(page);
        homePage.clickLogoutLink(page);
        loginPage.fillLoginpForm(page, "ma064944d@joe.com", "MyStrongPassword123!");
        homePage.clickContactUsLink(page);
        contactUsPage.fillOutContactUsForm(page, "Joe Mion6r", "ma064944d@joe.com", "report", "The report is given and submitted");
        contactUsPage.clickLogoutLink(page);
        
        page.close();
        page.context().browser().close();
        
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
	

}
