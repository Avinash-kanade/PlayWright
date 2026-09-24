package Aotumate1;

import com.microsoft.playwright.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class checkout {

    public static void main(String[] args) {
        try (Playwright obj_playwright = Playwright.create()) {

            Browser obj_browser = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext obj_context = obj_browser.newContext();
            Page page = obj_context.newPage();

            page.navigate("https://www.saucedemo.com");

            // Perform Login
            Locator username = page.locator("#user-name");
            username.fill("standard_user");

            Locator password = page.locator("#password");
            password.fill("secret_sauce");

            Locator loginBtn = page.locator("#login-button");
            loginBtn.click();


            assertThat(page).hasURL("https://www.saucedemo.com/inventory.html");


            Locator item1 = page.locator("#add-to-cart-sauce-labs-backpack");
            item1.click();


            Locator cartBadge = page.locator(".shopping_cart_badge");
            assertThat(cartBadge).hasText("1");


            Locator cart = page.locator(".shopping_cart_link");
            cart.click();


            assertThat(page).hasURL("https://www.saucedemo.com/cart.html");
            Locator cartItem = page.locator(".cart_item .inventory_item_name");
            assertThat(cartItem).hasText("Sauce Labs Backpack");


            Locator checkout = page.locator("#checkout");
            checkout.click();


            assertThat(page).hasURL("https://www.saucedemo.com/checkout-step-one.html");


            assertThat(page.locator("#first-name")).isVisible();
            assertThat(page.locator("#last-name")).isVisible();
            assertThat(page.locator("#postal-code")).isVisible();

            // Brief pause to visually observe
            Thread.sleep(2000);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}