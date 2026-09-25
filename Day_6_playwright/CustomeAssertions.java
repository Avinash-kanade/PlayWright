package Day_6_playwright;
import com.microsoft.playwright.Locator;
public class CustomeAssertions {
    private final Locator locator;
    private CustomeAssertions(Locator locator) {
        this.locator = locator;
    }
    public static CustomeAssertions assertThatCustom(Locator locator) {
        return new CustomeAssertions(locator);
    }
    public CustomeAssertions hasCssClass(String className){
        String actualclass = locator.getAttribute("class");
        System.out.println("In Custom Assertion");
        System.out.println("Locator" + locator.toString());

        if(actualclass == null || !actualclass.contains(className)) {
            throw new AssertionError(
                    "Expected element to have class'" + className + "' but found :" + actualclass);
        }
        return this;
    }}