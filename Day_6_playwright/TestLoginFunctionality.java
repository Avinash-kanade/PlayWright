package Day_6_playwright;

import Pages.LoginFunctionality;
import com.microsoft.playwright.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import org.testng.asserts.SoftAssert;
import org.testng.annotations.*;

public class TestLoginFunctionality {

    Playwright playwright;
    Browser browser;
    BrowserContext context;
    Page page;
    LoginFunctionality loginPage;

    @BeforeMethod
    public void setUpContext() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false));
        context = browser.newContext();
        page = context.newPage();
        loginPage = new LoginFunctionality(page);
    }
    @Test(priority = 1)
    public void testValidLogin() {
        loginPage.performLogin("standard_user", "secret_sauce");
        assertThat(page).hasURL("https://www.saucedemo.com/inventory.html");
    }
    @Test(priority = 2)
    public void testInvalidUsernamePassword() {
        boolean isSuccess = loginPage.performLogin("fishnet", "read");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(isSuccess, "Login should fail for invalid credentials");
        softAssert.assertEquals(page.url(), "https://www.saucedemo.com/", "User should remain on login page");
        assertThat(page.locator("[data-test='error']")).isVisible();
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown() {
        playwright.close();
    }
}