package Projectassgnment;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class checkout {
    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    Page page;

    @BeforeMethod
    public void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        browserContext = browser.newContext();
        page = browserContext.newPage();
    }

    @Test(priority = 3)
    public void CheckoutTest (){

            page.navigate("https://www.saucedemo.com/");


            page.getByPlaceholder("Username").fill("standard_user");
            page.getByPlaceholder("Password").fill("secret_sauce");
            page.locator("#login-button").click();

            page.waitForURL("**/inventory.html");

            page.locator("#add-to-cart-sauce-labs-backpack").click();
            page.locator("#add-to-cart-sauce-labs-bike-light").click();
            page.locator("#add-to-cart-sauce-labs-bolt-t-shirt").click();


            page.locator("#shopping_cart_container a").click();
            page.waitForURL("**/cart.html");


            page.locator("#checkout").click();
            page.waitForURL("**/checkout-step-one.html");


            page.getByPlaceholder("First Name").fill("Nakul");
            page.getByPlaceholder("Last Name").fill("Kapre");
            page.getByPlaceholder("Zip/Postal Code").fill("767676");
            page.locator("#continue").click();

            page.waitForURL("**/checkout-step-two.html");
            page.getByText("Finish").click();


            page.waitForURL("**/checkout-complete.html");
            page.getByText("Back Home").click();


            assertThat(page).hasURL("https://www.saucedemo.com/inventory.html");

        }

        @AfterMethod
        public void teardown() {
            playwright.close();
        }

    }


