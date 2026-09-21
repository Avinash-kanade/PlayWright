package Day_2_playwright;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

public class Day_2_2_Dropdown_element {

    public static void main(String[] args) {
        try (Playwright obj_playwright = Playwright.create()) {
            Browser obj_browser = obj_playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false));


            Page page = obj_browser.newPage();

            page.navigate("file:///C:/Users/ccst/Desktop/playwright%20files/ControlsPractice.html");

            Locator moduledropdown = page.locator("#module");
            moduledropdown.selectOption("CCST");
            Thread.sleep(2000);

            obj_browser.close();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
