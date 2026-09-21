package Day_2_playwright;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

public class Day_2_6_LocaterFiltering {
    public static void main(String[] args) throws InterruptedException{

        try (Playwright obj_playwright = Playwright.create()) {


            Browser obj_browser = obj_playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext context = obj_browser.newContext();
            Page page = context.newPage();
            page.navigate("file:///C:/Users/ccst/Desktop/playwright%20files/LocatorFiltering.html");

            Locator sourcecontainer = page.locator("container").filter(new Locator.FilterOptions()
                    .setHas(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("To Do"))));


            Locator targetcontainer = page.locator("container").filter(new Locator.FilterOptions()
                    .setHas(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Done"))));


            System.out.println("source container is visibals"+sourcecontainer.isVisible());
            System.out.println("targetacontainer is visable"+ targetcontainer.isVisible());






            System.out.println();

        }
    }
}
