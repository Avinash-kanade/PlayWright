
package Projectassgnment;
import com.microsoft.playwright.*;

public class LoginFunctionality {


    public static void login(String username, String password, Page page) {
        page.navigate("https://www.saucedemo.com/");
        page.getByPlaceholder("Username").fill(username);
        page.getByPlaceholder("Password").fill(password);
        page.locator("#login-button").click();
        page.waitForURL("**/inventory.html");

    }

    public static void enterCredentials(String username, String password, Page page) {

    }
}