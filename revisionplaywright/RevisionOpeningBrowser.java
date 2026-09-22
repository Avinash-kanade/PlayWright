package revisionplaywright;
 import com.microsoft.playwright.*;

public class RevisionOpeningBrowser {
    public static void main(String[] args) {
         //Step1 create playwright object
        try (Playwright playwright = Playwright.create()) {
            //Step 2 launch the browser
            Browser browser = playwright.chromium()
                    .launch(new BrowserType.LaunchOptions().setHeadless(false));
            //step 3 create  a broser context
            BrowserContext context = browser.newContext();

            //step 4 create a page
            Page obj_page = context.newPage();
            obj_page.navigate("https://www.saucedemo.com");
            System.out.println(obj_page.title());

            //enter username
            Locator obj_username = obj_page.locator("#user-name");
            obj_username.fill("standard_user");
            //enter password
            Locator obj_password = obj_page.locator("#password");
            obj_password.fill("secret_sauce");
           //click login page

            Thread.sleep(2000);

            //click login Btn
            Locator loginBtn = obj_page.locator("#login-button");
            loginBtn.click();
            Thread.sleep(2000);

            System.out.println("Current url"+obj_page.url());

            if(obj_page.url().contains("inventory.html")){
                System.out.println("Pass:login successfully !");
            }else{
                System.out.println("Fail: login failed ");
            }


        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}