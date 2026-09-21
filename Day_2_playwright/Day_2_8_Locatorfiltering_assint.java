package Day_2_playwright;

import com.microsoft.playwright.*;

public class Day_2_8_Locatorfiltering_assint{
    public static void main(String[] args) {
    try (Playwright obj_playwright = Playwright.create()) {


        Browser obj_browser = obj_playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false));


        Page page = obj_browser.newPage();

        page.navigate("file:///C:/Users/ccst/Desktop/playwright%20files/ControlsPractice.html");


        Locator dropdownOptions = page.locator("#module");
        Locator options = dropdownOptions.locator("option");


        Locator targetOption = options.filter(
                new Locator.FilterOptions().setHasText("CCST")
        );


        String selectedText = targetOption.getAttribute("value");


        dropdownOptions.selectOption(selectedText);

        System.out.println("Extracted Value: " + selectedText);





        Thread.sleep(2000);

    } catch (InterruptedException e) {
        throw new RuntimeException(e);
    }
}

}

