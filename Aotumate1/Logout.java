package Aotumate1;

import com.microsoft.playwright.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Logout {
    public static void main(String[] args) {
        try (Playwright obj_playwright = Playwright.create()) {

            Browser obj_browser = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext obj_context = obj_browser.newContext();
            Page page = obj_context.newPage();

            page.navigate("https://www.saucedemo.com");

            Locator username = page.locator("#user-name");
            username.fill("standard_user");

            Locator password = page.locator("#password");
            password.fill("secret_sauce");

            Locator loginBtn = page.locator("#login-button");
            loginBtn.click();


            assertThat(page).hasURL("https://www.saucedemo.com/inventory.html");


            Locator item1 = page.locator("#add-to-cart-sauce-labs-backpack");
            item1.click();


            Locator cart = page.locator(".shopping_cart_link");
            cart.click();


            assertThat(page).hasURL("https://www.saucedemo.com/cart.html");


            Locator checkout = page.locator("#checkout");
            checkout.click();

            assertThat(page).hasURL("https://www.saucedemo.com/checkout-step-one.html");

            Locator menuBtn = page.locator("#react-burger-menu-btn");
            menuBtn.click();
            Locator logoutLink = page.locator("#logout_sidebar_link");
            assertThat(logoutLink).isVisible();
            logoutLink.click();
            assertThat(page).hasURL("https://www.saucedemo.com/");
            assertThat(page.locator("#login-button")).isVisible();


            Thread.sleep(2000);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}