package apilipen.crutchfield.pages;

import apilipen.crutchfield.template.HelperDriverBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.allure.annotations.Step;

public class ShoppingCart extends HelperDriverBase{

    ShoppingCart(WebDriver driver) {
        super(driver);

    }



    private final By CHECKOUT_btn = By.xpath(getLocator("Proceed_CHECKOUT_btn"));
    private final By QTY_input = By.xpath(getLocator("QTY_input"));


    @Step("Add quantity [{0}] of items ")
    public ShoppingCart addQuantity (String qty){

        setText(QTY_input, qty);

        return this;
    }

    @Step("Click 'CHECKOUT' button ")
    public void proccedCheckout(){
        clickElement(CHECKOUT_btn);
    }





}
