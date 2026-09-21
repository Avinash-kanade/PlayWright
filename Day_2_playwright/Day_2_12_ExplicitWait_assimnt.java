package Day_2_playwright;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;

public class Day_2_12_ExplicitWait_assimnt {
    public static void main(String[] args) {
        try (Playwright obj_playwright = Playwright.create()) {

            Browser obj_browser = obj_playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false));

            BrowserContext context = obj_browser.newContext();
            Page page = context.newPage();

            page.navigate("C:\\Users\\ccst\\Desktop\\playwright files\\ControlsPractice.html");


            Locator moduledropdown = page.locator("#module");
            moduledropdown.selectOption("CCST");


            page.waitForSelector("table tbody tr:has(input[type='number'])",
                    new Page.WaitForSelectorOptions().setState(WaitForSelectorState.ATTACHED));


            Locator rows = page.locator("table tbody tr").filter(new Locator.FilterOptions()
                    .setHas(page.locator("input[type='number']")));


            int rowCount = rows.count();

            for (int i = 0; i < rowCount; i++) {
                Locator row = rows.nth(i);
                Locator marksInput = row.locator("input[type='number']");


                marksInput.waitFor();
                marksInput.fill("89");

            }
            System.out.println("Number of rows: " + rowCount);
            obj_browser.close();
        }
    }
}