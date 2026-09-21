package Day_2_playwright;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;

public class Day_2_11_Explicit_Wait_Function {
    public static void main(String[] args) throws InterruptedException {

        try (Playwright obj_playwright = Playwright.create()) {


            Browser obj_browser = obj_playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext context = obj_browser.newContext();
            Page page = context.newPage();
            Locator massage = page.locator("#message");
            page.navigate("file:///C:/Users/ccst/Desktop/playwright%20files/welcome%20(1).html");
            long start = System.currentTimeMillis();

            massage.waitFor(new Locator.WaitForOptions()
                    .setState(WaitForSelectorState.VISIBLE));
            String lableText = massage.innerText();
            long elaped = System.currentTimeMillis() - start;
            System.out.println("Lable text:" + lableText);
            System.out.println("Time waited :" + elaped + "ms");

        }
    }
}
