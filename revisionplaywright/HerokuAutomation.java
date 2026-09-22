package revisionplaywright;

import com.microsoft.playwright.*;

public class HerokuAutomation {
    public static void main(String[] args) {
        try (Playwright obj_playwright = Playwright.create()) {

            Browser obj_brwser = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

            BrowserContext context = obj_brwser.newContext();
            Page obj_page = context.newPage();

            obj_page.navigate("https://the-internet.herokuapp.com");

            Locator clickElement = obj_page.locator("//a[@href='/add_remove_elements/']");
            clickElement.click();
            Thread.sleep(1000);

            if(obj_page.url().contains("/add_remove_elements/")){
                System.out.println("Pass: new page is open");
            }else {
                System.out.println("Fail: element is not clicked");
            }

            Locator AddElemntBtn = obj_page.locator("//button[@onclick='addElement()']");
            AddElemntBtn.click();
            Thread.sleep(1000);

            Locator DeleteBtn = obj_page.locator("//button[@class='added-manually']");

            if (DeleteBtn.isVisible()){
                System.out.println("Element is visible");
            }else {
                System.out.println("Element is not visible");
            }

            //dlgt
            DeleteBtn.click();


            if (DeleteBtn.isVisible()){
                System.out.println("Element is visible");
            }else {
                System.out.println("Element is not visible");
            }


            Thread.sleep(1000);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}

