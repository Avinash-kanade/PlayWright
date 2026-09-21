package Day_2_playwright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;

public class Day_2_1_inbult_locatars {

    public static void main(String[] args) {
            try (Playwright obj_playwright = Playwright.create()) {

                // Launch Chromium browser
                Browser obj_browser = obj_playwright.chromium().launch(
                        new BrowserType.LaunchOptions().setHeadless(false));

                // Create a new page
                Page page = obj_browser.newPage();

                page.navigate("file:///C:/Users/ccst/Desktop/playwright%20files/login.html");

                page.getByLabel("username").fill("admin");
                page.getByLabel("password").fill("admin");

                page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign In")).click();

                String paragraphText = page.getByText("Forgot Your Password ?").textContent();
                System.out.println("PASS:Found paragraph using getByText()" + paragraphText);

                obj_browser.close();
            }
        }
    }

