package revisionplaywright;

import com.microsoft.playwright.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Day_3_AssertionDemo1 {
    public static void main(String[] args) {

        try (Playwright obj_playwright = Playwright.create()) {

            Browser obj_browser = obj_playwright.chromium()
                    .launch(new BrowserType.LaunchOptions().setHeadless(false));

            BrowserContext obj_Context = obj_browser.newContext();

            Page obj_page = obj_Context.newPage();

            obj_page.navigate("https://www.saucedemo.com");

            Locator username = obj_page.locator("#username");
            Locator password = obj_page.locator("#password");
            Locator signin = obj_page.locator("//button[@data-testid='submit-btn']");

            username.fill("standard_user");
            password.fill("secret_sauce");
            signin.click();
               assertThat(obj_page).hasURL("https://www.saucedemo.com/inventory.html");


        }
    }
}