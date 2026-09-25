package Segrigationfile;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CheckoutFunctionility {
    private final Page page;

    private final Locator firstNameInput;
    private final Locator lastNameInput;
    private final Locator postalCodeInput;
    private final Locator continueButton;

    public CheckoutFunctionility(Page page) {
        this.page = page;
        this.firstNameInput = page.locator("#first-name");
        this.lastNameInput = page.locator("#last-name");
        this.postalCodeInput = page.locator("#postal-code");
        this.continueButton = page.locator("#continue");
    }

    public void fillCheckoutInformation(String firstName, String lastName, String postalCode) {
        firstNameInput.fill(firstName);
        lastNameInput.fill(lastName);
        postalCodeInput.fill(postalCode);
        continueButton.click();
    }

    public void CheckoutFunc(Page objPage) {
    }
}