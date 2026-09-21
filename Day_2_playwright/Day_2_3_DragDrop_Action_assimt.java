package Day_2_playwright;

import com.microsoft.playwright.*;

public class Day_2_3_DragDrop_Action_assimt {
    public static void main(String[] args) {
        try (Playwright obj_playwright = Playwright.create()) {

            // Launch Chromium browser
            Browser obj_browser = obj_playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false));

            // Create a new page
            Page page = obj_browser.newPage();

            page.navigate("file:///C:/Users/ccst/Desktop/playwright%20files/LocatorFiltering.html");

            Locator Item1 = page.locator("#item1");
            Locator targetContainer = page.locator("#targetContainer");

            Item1.dragTo(targetContainer);
            Thread.sleep(1000);

            Locator item2 = page.locator("#item2");
            Locator targetContainer2 = page.locator("#targetContainer");

           item2.dragTo(targetContainer2);
            Thread.sleep(1000);


            Locator item3 = page.locator("#item3");
            Locator targetContainer3 = page.locator("#targetContainer");

            item3.dragTo(targetContainer3);

            Locator result =page.locator("#result");


            if (targetContainer.locator("#item1, #item2, #item3").count() == 3) {
                System.out.println("Drag and drop succeeded! All items are located inside the target container.");
            } else {
                System.out.println("Drag and drop failed ");
            }



            Thread.sleep(1000);

            obj_browser.close();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}







