package revisionplaywright;

import com.microsoft.playwright.*;

import javax.naming.Context;

public class Login_Page {

    public static void main(String[] args) {

        try(Playwright obj_playwright = Playwright.create()){

            Browser obj_browser = obj_playwright.chromium()
                    .launch(new BrowserType.LaunchOptions().setHeadless(false));

            BrowserContext obj_Context = obj_browser.newContext();

            Page obj_page = obj_Context.newPage();

            obj_page.navigate("file:///C:/Users/ccst/Desktop/playwright%20files/login.html");

            Locator username = obj_page.locator("#username");
            Locator password = obj_page.locator("#password");
            Locator signin = obj_page.locator("//button[@data-testid='submit-btn']");

            username.fill("admin");
            password.fill("admin");
//            signin.click();

            Page obj_newpage =obj_Context.waitForPage(() ->{
               signin.click();
            });

Thread.sleep(2000);
             if(obj_newpage.title().contains("Student Performance Report")){
                 System.out.println("Pass: page title is : " + obj_newpage.title());
             }else {
                 System.out.println("fail:page not come up!"+ obj_newpage.title());}

//            obj_browser.close();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
