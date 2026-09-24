package Aotumate1;

import com.microsoft.playwright.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

    public class ProductsTestNG {

        Playwright playwright;
        Browser browser;
        BrowserContext context;
        Page page;

        @BeforeMethod
        public void setUp() {
            playwright = Playwright.create();
            browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            context = browser.newContext();
            page = context.newPage();
        }

        @Test
        public void testProductsDisplayedOnDashboard() {
            // Navigate and Login
            page.navigate("https://www.saucedemo.com");
            page.locator("#user-name").fill("standard_user");
            page.locator("#password").fill("secret_sauce");
            page.locator("#login-button").click();


            assertThat(page).hasURL("https://www.saucedemo.com/inventory.html");


            Locator title = page.locator(".title");
            assertThat(title).isVisible();
            assertThat(title).hasText("Products");


            Locator inventoryItems = page.locator(".inventory_item");
            assertThat(inventoryItems).hasCount(6);
            assertThat(inventoryItems.first()).isVisible();
        }

        @AfterMethod
        public void tearDown() {
        browser.close();

        }
    }

