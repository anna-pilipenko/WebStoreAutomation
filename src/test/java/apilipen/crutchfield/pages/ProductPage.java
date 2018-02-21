package apilipen.crutchfield.pages;

import apilipen.crutchfield.template.HelperDriverBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.allure.annotations.Step;

public class ProductPage extends HelperDriverBase {

    ProductPage (WebDriver driver) {
        super(driver);

    }
    //new
    private final By ADDTOCART_btn = By.xpath(getLocator("ADDTOCART_btn")) ;
    private final By GOTOCARTonPoUp_btn = By.xpath(getLocator("onPoUp_GtC_btn")) ;


// //*[@id="atcContents"]/div[4]/div[2]/div/a       //a[@class="btn btn-lg btn-success goToCartLink tracking"]


    @Step("Click 'GO to CART' button ")
    public void goToCard (){
        clickElementclickable(GOTOCARTonPoUp_btn);

    }

    @Step("Click 'ADD to CART' button ")
    public void addToCart(){
        clickElementclickable(ADDTOCART_btn);


    }

    @Step("ADD item to the CART ")
    public void addItemAndGoToCart(){
        addToCart();
        goToCard ();
    }




}
