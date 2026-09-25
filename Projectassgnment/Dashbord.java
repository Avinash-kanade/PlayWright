package Projectassgnment;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Dashbord {
    public class LoginFunctionalit {
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

        @Test(priority = 2)
        public void Dashbord() {

            page.navigate("https://www.saucedemo.com/");

            page.getByPlaceholder("Username").fill("standard_user");
            page.getByPlaceholder("Password").fill("secret_sauce");
            page.locator("#login-button").click();

            page.waitForURL("**/inventory.html");

            page.locator("#add-to-cart-sauce-labs-backpack").click();
            page.locator("#add-to-cart-sauce-labs-bike-light").click();
            page.locator("#add-to-cart-sauce-labs-bolt-t-shirt").click();

            assertThat(page.locator("#remove-sauce-labs-backpack")).hasText("Remove");
            assertThat(page.locator("#remove-sauce-labs-bike-light")).hasText("Remove");
            assertThat(page.locator("#remove-sauce-labs-bolt-t-shirt")).hasText("Remove");


        }

        @AfterClass
        public void teardown() {
            playwright.close();
        }
    }
}
