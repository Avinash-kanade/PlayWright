package Day_2_playwright;

import com.microsoft.playwright.*;

import java.util.List;

public class Day_2_4_DragDrop_looping {
    public static void main(String[] args) throws InterruptedException{

        try (Playwright obj_playwright = Playwright.create()) {


            Browser obj_browser = obj_playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false));


            Page page = obj_browser.newPage();

            page.navigate("file:///C:/Users/ccst/Desktop/playwright%20files/LocatorFiltering.html");


            Locator sourcecomtaner = page.locator("#sourceContainer");
            Locator targetContainer = page.locator("#targetContainer");



            List<Locator> itemsList = sourcecomtaner.locator("draggable-item").all();


            for (int i = 0; i < itemsList.size(); i++) {
               Locator item = sourcecomtaner.locator("draggable-item").first();
                System.out.println(item.toString());
                item.dragTo(targetContainer);
                page.waitForTimeout(300);

            }

            page.waitForTimeout(2000);
            obj_browser.close();
        }
    }
}







