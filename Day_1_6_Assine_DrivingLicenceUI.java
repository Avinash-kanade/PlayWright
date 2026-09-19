import com.microsoft.playwright.*;

public class Day_1_6_Assine_DrivingLicenceUI {
    // Text field selectors
    static String fullName = "#fullname";
    static String address = "#address";
    static String age = "#age";
    static String placeOfBirth = "#placeofbirth";


    static String genderMaleRadio = "#Male";
    static String colourblindness = "input[name='color_no']";

    static String submit = "button[type=\"submit\"]";

    public static void main(String[] args) {
        try (Playwright obj_playwright = Playwright.create()) {

            // Launch Chromium browser in headed mode
            Browser obj_browser = obj_playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false));

            // Create a new browser context and page
            BrowserContext obj_context = obj_browser.newContext();
            Page obj_page = obj_context.newPage();

            // Navigate to the local HTML file
            obj_page.navigate("file:///C:/Users/ccst/Desktop/playwright%20files/TestcasesClassAssignment-drivingLicenseUI%20(1).html");

            // --- Filling Text Fields using .fill() ---
            obj_page.locator(fullName).fill("Avinash");
            obj_page.locator(address).fill("Nashik");
            obj_page.locator(age).fill("25");
            obj_page.locator(placeOfBirth).fill("Pune");

            // --- Selecting Radio Buttons using .check() ---
            obj_page.locator(genderMaleRadio).check();
            obj_page.locator(colourblindness).check();
            obj_page.locator(submit).click();

            obj_page.waitForTimeout(1000);
        }
    }
}