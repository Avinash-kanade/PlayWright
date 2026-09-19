package OpenBroser;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.Geolocation;

import java.util.Arrays;
import java.util.Map;

public class Day_1_Geolocation_Setup {
    public static void main(String[] args) {
        try (Playwright obj_playwright = Playwright.create()) {

            // Launch Chromium browser in headed mode
            Browser obj_brower = obj_playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false));

            // Create browser context with mobile emulation and Geolocation permissions
            BrowserContext obj_context = obj_brower.newContext(new Browser.NewContextOptions()
                    .setUserAgent("Mozilla/5.0 (BB10; Touch) AppleWebKit/537.10+ (KHTML, like Gecko) Version/10.0.9.2372 Mobile Safari/537.10+")
                    .setViewportSize(360, 640)
                    .setDeviceScaleFactor(2)
                    .setIsMobile(true)
                    .setHasTouch(true)
                    .setGeolocation(new Geolocation(48.8566, 2.3522))
                    .setPermissions(Arrays.asList("geolocation")));

            Page obj_page = obj_context.newPage();

            Thread.sleep(2000);
            obj_page.navigate("https://www.wikipedia.org");

            // Evaluate browser geolocation via JavaScript
            @SuppressWarnings("unchecked")
            Map<String, Object> location = (Map<String, Object>) obj_page.evaluate(
                    "() => new Promise((resolve, reject) => {" +
                            "  navigator.geolocation.getCurrentPosition(" +
                            "    p => resolve({ lat: p.coords.latitude, lng: p.coords.longitude })," +
                            "    e => reject(e.message)" +
                            "  );" +
                            "})"
            );

            System.out.println("Latitude: " + location.get("lat"));
            System.out.println("Longitude: " + location.get("lng"));

            // Navigate back to your login page if running the login test flow
            obj_page.navigate("C:\\Users\\ccst\\Desktop\\playwright files\\login.html");

            obj_page.locator("[data-testid = 'username-input']").fill("validUser");
            obj_page.locator("[data-testid = 'password-input']").fill("validPassword");
            Thread.sleep(2000);

            // Wait for new page upon clicking submit
            Page obj_controlsPage = obj_context.waitForPage(() -> {
                obj_page.locator("[data-testid='submit-btn']").click();
            });

            Thread.sleep(2000);
            obj_controlsPage.waitForLoadState();

            String url = obj_controlsPage.url();
            System.out.println("Navigated to: " + url);

            // Print page title
            String title = obj_controlsPage.title();
            System.out.println("Page title: " + title);

            // Verify navigation target
            if (!url.contains("ControlsPractice.html")) {
                throw new AssertionError("Navigation failed. Expected 'ControlsPractice.html', but got: " + url);
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}