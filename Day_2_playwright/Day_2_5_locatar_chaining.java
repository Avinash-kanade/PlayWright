package Day_2_playwright;

import com.microsoft.playwright.*;

import java.util.List;

public class Day_2_5_locatar_chaining {

        public static void main(String[] args) {
            try (Playwright obj_playwright = Playwright.create()) {

                Browser obj_browser = obj_playwright.chromium().launch(
                        new BrowserType.LaunchOptions().setHeadless(false));

               BrowserContext context = obj_browser.newContext();
                Page page = context.newPage();


                page.navigate("C:\\Users\\ccst\\Desktop\\playwright files\\ControlsPractice.html");

                Locator moduledropdown = page.locator("#module");
                moduledropdown.click();
                List<String>optionText = moduledropdown.locator("option").allInnerTexts();
                System.out.println("Dropdown option found"+ optionText);

                moduledropdown.selectOption("CCST");


                Locator rows = page.locator("table").locator("tbody").locator("tr");

                int rowCount = rows.count();
                System.out.println("row count:"+rowCount);

                Thread.sleep(1000);
                for (int i = 0; i < rowCount ; i++) {
                    Locator row = rows.nth(i);
                    Locator marksInput = row.locator("input[type = 'number']");

                    marksInput.fill("89");

                }
                Locator saveBtn = page.locator("#saveButton");
                System.out.println("saveBtn enable "+ saveBtn.isEnabled());

                saveBtn.click();

                page.waitForTimeout(2000);
                context.close();

                obj_browser.close();

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

}

