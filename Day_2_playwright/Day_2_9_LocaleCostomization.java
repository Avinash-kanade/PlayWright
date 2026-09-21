package Day_2_playwright;

import com.microsoft.playwright.*;

public class Day_2_9_LocaleCostomization {
    public static void main(String[] args) throws InterruptedException {

        try (Playwright obj_playwright = Playwright.create()) {


            Browser obj_browser = obj_playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext context = obj_browser.newContext(new Browser.NewContextOptions().setLocale("ja-JP"));
            Page page = context.newPage();
            page.navigate("https://www.wikipedia.org/");
            page.waitForTimeout(1000);
            System.out.println("Page title:" + page.title());
            Locator langLable = page.locator("#jsLangLable");
            String langValue = langLable.textContent();
            System.out.println("language" + langValue);

            obj_browser.close();
        }
    }
}