package apilipen.crutchfield.pages;

import apilipen.crutchfield.template.HelperDriverBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.allure.annotations.Step;

public class HomePage  extends HelperDriverBase {

    HomePage(WebDriver driver) {
        //  this.driver = wd;
        super(driver);
    }


    private final By SEARCH_input = By.xpath(getLocator("SEARCH_input"));  // "//input[@name=\"search\"]" // getLocator("search_field")

    @Step("Search item: [{0}] ")
    public void searchItem ( String sku){
        setText(SEARCH_input, sku);
        findWebElement(SEARCH_input).sendKeys(Keys.ENTER);
    }





}
