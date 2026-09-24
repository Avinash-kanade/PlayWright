package Aotumate1;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginPageTestNG {

    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    private Page page;

    @BeforeMethod
    public void setUp() {
        // Initialize Playwright and open a fresh browser context before each test
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );
        context = browser.newContext();
        page = context.newPage();
    }

    @Test
    public void testSuccessfulLogin() {

        page.navigate("https://www.saucedemo.com");


        Locator username = page.locator("#user-name");
        Locator password = page.locator("#password");
        Locator loginBtn = page.locator("#login-button");

        username.fill("standard_user");
        password.fill("secret_sauce");
        loginBtn.click();
        assertThat(page).hasURL("https://www.saucedemo.com/inventory.html");
    }
    @AfterMethod
    public void tearDown() {
       playwright.close();
    }
}