package Day_2_playwright;

import com.microsoft.playwright.*;

public class Day_2_7_LocatorFiltering_assimnt {
    public static void main(String[] args) {
        try (Playwright obj_playwright = Playwright.create()) {

            // Launch Chromium browser
            Browser obj_browser = obj_playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false));

            // Create a new page
            Page page = obj_browser.newPage();

            page.navigate("file:///C:/Users/ccst/Desktop/playwright%20files/ControlsPractice.html");

            // Locate the dropdown element
            Locator dropdownOptions = page.locator("#module");
            Locator options = dropdownOptions.locator("option");

            // Filter the option containing text "CCST"
            Locator targetOption = options.filter(
                    new Locator.FilterOptions().setHasText("CCST")
            );

            // Fetch the 'value' attribute of the filtered option
            String selectedText = targetOption.getAttribute("value");

            // Select the option in the dropdown using the retrieved value
            dropdownOptions.selectOption(selectedText);

            System.out.println("Extracted Value: " + selectedText);

            // Optional pause to visually verify selection before closing
            Thread.sleep(2000);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

