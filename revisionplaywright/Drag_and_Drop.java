package revisionplaywright;

import com.microsoft.playwright.*;

public class Drag_and_Drop {
    public static void main(String[] args) {
        try (Playwright obj_playwright = Playwright.create()) {

            Browser obj_brwser = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

            BrowserContext context = obj_brwser.newContext();
            Page obj_page = context.newPage();

            obj_page.navigate("https://the-internet.herokuapp.com");

            Locator dragdrop =obj_page.locator("//a[@href='/drag_and_drop']");
            dragdrop.click();
            Thread.sleep(2000);

           Locator containerA =obj_page.locator("#column-a");
            Locator containerB =obj_page.locator("#column-b");
            containerA.dragTo(containerB);

            System.out.println(containerA.innerText());
            System.out.println(containerA.textContent());
            Thread.sleep(2000);

            if (containerA.innerText().contains("B")){
                System.out.println("drag and drop successful...!");
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}