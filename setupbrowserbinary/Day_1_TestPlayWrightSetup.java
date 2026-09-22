package setupbrowserbinary;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class Day_1_TestPlayWrightSetup {

    public static void main(String[] args) {
        try (Playwright obj_playwright = Playwright.create()) {

            // Launch chromium browser (set headless to false if you want to see the UI)
            Browser obj_brower = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

            // Create a new browser context
            BrowserContext obj_context = obj_brower.newContext();

            // Call newPage() without the 'new' keyword
            Page obj_page = obj_context.newPage();

            obj_page.navigate("https://example.com");
            System.out.println("Browser version: " + obj_brower.version());
            System.out.println("Page title: " + obj_page.title());
            System.out.println("Playwright installation works correctly.");

            obj_page.waitForTimeout(2000);

            // Clean up resources
            obj_page.close();
            obj_context.close();
            obj_brower.close();
        } catch (Exception e) {
            System.out.println("Test failed with an exception:");
            e.printStackTrace();
        }
    }
}