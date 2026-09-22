package revisionplaywright;

import com.microsoft.playwright.*;

public class Dynamic_Controls {
    public static void main(String[] args) {
        try (Playwright obj_playwright = Playwright.create()) {

            Browser obj_brwser = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

            BrowserContext context = obj_brwser.newContext();
            Page obj_page = context.newPage();

            obj_page.navigate("https://the-internet.herokuapp.com");
            Locator DC= obj_page.locator("//a[@href='/dynamic_controls']");
            DC.click();

            Locator checkboxClk=obj_page.locator("//input[@type='checkbox']");
            checkboxClk.click();
        Thread.sleep(2000);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}