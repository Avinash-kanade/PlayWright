package Segrigationfile;

import Pages.LoginFunctionality;
import com.microsoft.playwright.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
public class TestaddtoCartFunc {
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
    @Test(priority = 1, dataProvider = "loginCredentials", dataProviderClass = DataProvider.class)
    public void testAddtoCart(String username, String password) {
        LoginFunctionality loginPage = new LoginFunctionality(obj_page);
        loginPage.performLogin(username, password);

        AddtoCartFunc add = new AddtoCartFunc(obj_page);
        add.addProductsToCart();

        assertThat(add.getShoppingCartBadge()).hasText("3");
        softAssert.assertAll();
    }
    @AfterMethod
    public void teardown() {
         obj_playwright.close();
    }
}