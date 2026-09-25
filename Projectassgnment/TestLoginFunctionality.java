package Projectassgnment;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class TestLoginFunctionality {
     Playwright playwright;
     Browser browser;
     BrowserContext browserContext;
     Page page;

    @BeforeMethod
    public void setup(){
        playwright  = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        browserContext = browser.newContext();
        page = browserContext.newPage();
    }

    @DataProvider(name = "csvDataProvider")
    public Object[][] getCsvData() {
        return CsvDataUtils.readCsvData("src/test/java/Projectassgnment/loginData.csv");
    }

    @Test(priority = 1)
    public void loginTestPasswala(){
        LoginFunctionality.login("standard_user", "secret_sauce",page);
        assertThat(page).hasURL("https://www.saucedemo.com/inventory.html");
        }

    @Test(priority = 2)
    public void loginTestFailwala(){
        LoginFunctionality.login("Nakul", "123456",page);
        assertThat(page).not().hasURL("https://www.saucedemo.com/inventory.html");
    }
    @Test(priority = 3, dataProvider = "csvDataProvider")
    public void loginDataDrivenTest(String username, String password) {
        LoginFunctionality.enterCredentials(username, password, page);
        assertThat(page).h;
    }

    @AfterClass
    public void teardown(){
        playwright.close();
        }
    }

