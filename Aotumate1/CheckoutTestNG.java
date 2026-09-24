package Aotumate1;

import com.microsoft.playwright.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutTestNG {

    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    private Page page;

    @BeforeMethod
    public void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        context = browser.newContext();
        page = context.newPage();
    }

    @Test
    public void testCheckoutProcess() {
        page.navigate("https://www.saucedemo.com");


        page.locator("#user-name").fill("standard_user");
        page.locator("#password").fill("secret_sauce");
        page.locator("#login-button").click();


        assertThat(page).hasURL("https://www.saucedemo.com/inventory.html");


        page.locator("#add-to-cart-sauce-labs-backpack").click();


        Locator cartBadge = page.locator(".shopping_cart_badge");
        assertThat(cartBadge).hasText("1");


        page.locator(".shopping_cart_link").click();


        assertThat(page).hasURL("https://www.saucedemo.com/cart.html");
        Locator cartItem = page.locator(".cart_item .inventory_item_name");
        assertThat(cartItem).hasText("Sauce Labs Backpack");


        page.locator("#checkout").click();


        assertThat(page).hasURL("https://www.saucedemo.com/checkout-step-one.html");


        assertThat(page.locator("#first-name")).isVisible();
        assertThat(page.locator("#last-name")).isVisible();
        assertThat(page.locator("#postal-code")).isVisible();
    }

    @AfterMethod
    public void tearDown() {
         browser.close();
    }
}