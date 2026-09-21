package Day_2_playwright;

import com.microsoft.playwright.*;
import java.util.Arrays;

public class Day_2_10_TimezoneCostomization {
    public static void main(String[] args) throws InterruptedException {

        try (Playwright obj_playwright = Playwright.create()) {

            Browser obj_browser = obj_playwright.chromium().launch(
                    new BrowserType.LaunchOptions()
                            .setHeadless(false)
                            .setArgs(Arrays.asList("--start-maximized"))
            );

            // setViewportSize(null) is required when using --start-maximized
            BrowserContext context = obj_browser.newContext(
                    new Browser.NewContextOptions()
                            .setTimezoneId("Australia/Sydney")
                            .setViewportSize(null)
            );

            Page page = context.newPage();
            page.navigate("https://www.wikipedia.org/");
            page.waitForTimeout(1000);

            String timezoneName = (String) page.evaluate("() => Intl.DateTimeFormat().resolvedOptions().timeZone");

            System.out.println("Configured Timezone: " + timezoneName);

            obj_browser.close();
        }
    }
}