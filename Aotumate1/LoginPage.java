package Aotumate1;

import com.microsoft.playwright.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginPage {

    public static void main(String[] args) {
        try (Playwright obj_playwright = Playwright.create()) {

            Browser obj_browser = obj_playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false)
            );

            BrowserContext obj_context = obj_browser.newContext();
            Page page = obj_context.newPage();


            page.navigate("https://www.saucedemo.com");


            Locator username = page.locator("#user-name");
            Locator password = page.locator("#password");
            Locator loginBtn = page.locator("#login-button");


            username.fill("standard_user");
            password.fill("secret_sauce");
            loginBtn.click();

            assertThat(page).hasURL("https://www.saucedemo.com/inventory.html");

            System.out.println("Login Test Passed Successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}