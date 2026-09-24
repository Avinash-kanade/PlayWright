package Aotumate1;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

public class cart_add {
        public static void main(String[] args) {
            try (Playwright obj_playwright = Playwright.create()) {

                Browser obj_browser = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

                BrowserContext obj_context = obj_browser.newContext();

                Page page = obj_context.newPage();

                page.navigate("https://www.saucedemo.com");

                Thread.sleep(2000);

                Locator username = page.locator("#user-name");
                username.fill("standard_user");
                Thread.sleep(2000);
                Locator password = page.locator("#password");
                password.fill("secret_sauce");
                Thread.sleep(2000);
                Locator loginBtn = page.locator("#login-button");
                loginBtn.click();
                Thread.sleep(1000);
                Locator item1 = page.locator("#add-to-cart-sauce-labs-backpack");
                item1.click();
                Thread.sleep(200);
                Locator cart = page.locator("//a[@class='shopping_cart_link']");
                cart.click();
                Thread.sleep(1000);

               Locator checkout = page.getByRole(AriaRole.valueOf("btn btn_action btn_medium checkout_button"));
               checkout.click();
               Thread.sleep(2000);


            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

}
