package revisionplaywright;

import com.microsoft.playwright.*;

public class Drop_Down {
    public static void main(String[] args) {
        try (Playwright obj_playwright = Playwright.create()) {

            Browser obj_brwser = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

            BrowserContext context = obj_brwser.newContext();
            Page obj_page = context.newPage();
            obj_page.navigate("https://the-internet.herokuapp.com");

            Locator elementclick =obj_page.locator("//a[@href='/dropdown']");
            elementclick.click();
            if (obj_page.url().contains("dropdown")){
                System.out.println("pass: page opne sucessfuly !");

            }else{
                System.out.println("fail:page not opened !");
            }
           Locator obj_dropdown = obj_page.locator("#dropdown");
            obj_dropdown.click();

           Thread.sleep(1000);

            obj_dropdown.selectOption("Option 1");

            Thread.sleep(2000);

            if (obj_dropdown.inputValue().equals("1")){
                System.out.println("Pass: value is one");
            }else {
                System.out.println("Fail: Option 1 is not selected");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}