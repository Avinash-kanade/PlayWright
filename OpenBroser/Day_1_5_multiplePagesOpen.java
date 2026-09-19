package OpenBroser;

import com.microsoft.playwright.*;

public class Day_1_5_multiplePagesOpen {
    public static void main(String[] args) {
        try (Playwright obj_playwright = Playwright.create()) {

            // Launch Chromium browser
            Browser obj_brower = obj_playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false));

            // Create a new browser context
            BrowserContext obj_context = obj_brower.newContext();

            // Create a new page
            Page obj_page = obj_context.newPage();
            Page obj_page2 = obj_context.newPage();
            obj_page.navigate("https://www.saucedemo.com/");
            obj_page2.navigate("https://shala.com/");

            String pageTitle = obj_page.title();
            if (pageTitle.equals("Swag Labs")) {
                System.out.println("Test passed");
            } else {
                System.out.println("Test failed: " + pageTitle);
            }
            String pageTitle2 = obj_page.title();
            if (pageTitle2.equals("Shala")) {
                System.out.println("Test passed");
            } else {
                System.out.println("Test failed: " + pageTitle);
            }
        }
    }
}