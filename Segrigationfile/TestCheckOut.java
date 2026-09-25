package Segrigationfile;

import Pages.LoginFunctionality;
import com.microsoft.playwright.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class TestCheckOut {
    private Playwright obj_playwright;
    private Browser obj_browser;
    private BrowserContext obj_context;
    private Page obj_page;
    private SoftAssert softAssert;

    @BeforeMethod
    public void setUp() {
        softAssert = new SoftAssert();
        obj_playwright = Playwright.create();
        obj_browser = obj_playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(500)
        );
        obj_context = obj_browser.newContext();
        obj_page = obj_context.newPage();
        obj_page.navigate("https://www.saucedemo.com/");
    }

    @Test(priority = 1)
    public void testCheckoutProduct() {
        LoginFunctionality loginPage = new LoginFunctionality(obj_page);
        loginPage.performLogin("standard_user", "secret_sauce");

        AddtoCartFunc add = new AddtoCartFunc(obj_page);
        add.addProductsToCart();
        obj_page.locator(".shopping_cart_link").click();

        obj_page.locator("#checkout").click();

        CheckoutFunctionility check = new CheckoutFunctionility(obj_page);
        check.fillCheckoutInformation("Standard", "User", "411001");

        obj_page.locator("#finish").click();

        softAssert.assertEquals(obj_page.url(), "https://www.saucedemo.com/checkout-complete.html");
        assertThat(obj_page.locator(".complete-header")).hasText("Thank you for your order!");
        softAssert.assertAll();
    }

    @AfterMethod
    public void teardown() {
       obj_playwright.close();
    }
}