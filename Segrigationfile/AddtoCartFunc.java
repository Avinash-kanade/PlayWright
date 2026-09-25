package Segrigationfile;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class AddtoCartFunc {
    private final Page page;

    private final Locator addToCartBackpack;
    private final Locator addToCartBikeLight;
    private final Locator addToCartBoltTShirt;
    private final Locator shoppingCartBadge;

    public AddtoCartFunc(Page page) {
        this.page = page;
        this.addToCartBackpack = page.locator("[data-test='add-to-cart-sauce-labs-backpack']");
        this.addToCartBikeLight = page.locator("[data-test='add-to-cart-sauce-labs-bike-light']");
        this.addToCartBoltTShirt = page.locator("[data-test='add-to-cart-sauce-labs-bolt-t-shirt']");
        this.shoppingCartBadge = page.locator(".shopping_cart_badge");
    }

    public void addProductsToCart() {
        addToCartBackpack.click();
        addToCartBikeLight.click();
        addToCartBoltTShirt.click();
    }

    public Locator getShoppingCartBadge() {
        return shoppingCartBadge;
    }
}