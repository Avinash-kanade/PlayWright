package Aotumate1;

import com.microsoft.playwright.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LogoutTestNG {

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
    public void testUserLogoutProcess() {
        page.navigate("https://www.saucedemo.com");

        // Perform Login
        page.locator("#user-name").fill("standard_user");
        page.locator("#password").fill("secret_sauce");
        page.locator("#login-button").click();

        // 1. Assert redirection to inventory page
        assertThat(page).hasURL("https://www.saucedemo.com/inventory.html");

        // Add item and navigate to cart
        page.locator("#add-to-cart-sauce-labs-backpack").click();
        page.locator(".shopping_cart_link").click();

        // 2. Assert navigation to Cart page
        assertThat(page).hasURL("https://www.saucedemo.com/cart.html");

        // Proceed to Checkout
        page.locator("#checkout").click();

        // 3. Assert navigation to Checkout Step One page
        assertThat(page).hasURL("https://www.saucedemo.com/checkout-step-one.html");

        // Open menu and perform logout
        page.locator("#react-burger-menu-btn").click();
        Locator logoutLink = page.locator("#logout_sidebar_link");
        assertThat(logoutLink).isVisible();
        logoutLink.click();


        assertThat(page).hasURL("https://www.saucedemo.com/");
        assertThat(page.locator("#login-button")).isVisible();
    }

    @AfterMethod
    public void tearDown() {
            browser.close();
    }
}