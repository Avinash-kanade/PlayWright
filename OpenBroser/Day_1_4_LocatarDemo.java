package OpenBroser;

import com.microsoft.playwright.*;

public class Day_1_4_LocatarDemo {
    // SauceDemo uses "standard_user" (singular)
    static String userName = "standard_user";
    static String password = "secret_sauce";

    // Locators - updated id_username to match SauceDemo DOM (#user-name)
    static String xPath_LoginButtton = "//input[@data-test='login-button']";
    static String css_loginButton = "input[type=\"submit\"][value=\"Login\"]";
    static String id_username = "#user-name";
    static String name_userName = "[name=\"user-name\"]";
    static String placeholder_userNAme = "input[placeholder=\"Username\"]";
    static String id_password = "#password";

    public static void main(String[] args) {
        try (Playwright obj_playwright = Playwright.create()) {

            // Launch Chromium browser
            Browser obj_brower = obj_playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false));

            // Create a new browser context
            BrowserContext obj_context = obj_brower.newContext();

            // Create a new page
            Page obj_page = obj_context.newPage();

            obj_page.navigate("https://www.saucedemo.com/");

            try {
                Thread.sleep(2000);

                Locator obj_userNameLocatar = obj_page.locator(id_username);
                obj_userNameLocatar.fill(userName);

                obj_page.locator(id_password).fill(password);
                obj_page.locator(css_loginButton).click();

                Thread.sleep(2000); // Give page a moment to process login

                String pageTitle = obj_page.title();

                if (pageTitle.equals("Swag Labs")) {
                    System.out.println("Test passed");
                } else {
                    System.out.println("Test failed: " + pageTitle);
                }

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}