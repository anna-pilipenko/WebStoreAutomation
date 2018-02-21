package apilipen.crutchfield.pages;

import apilipen.crutchfield.model.AddressData;
import apilipen.crutchfield.model.PaymentData;
import apilipen.crutchfield.template.HelperDriverBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.allure.annotations.Step;

public class CheckoutPage extends HelperDriverBase{


    CheckoutPage(WebDriver driver) {
        super(driver);

    }

    private final By FULLNAME_input = By.xpath( getLocator("fullName_input"));
    private final By ADDRESS_STREET_input = By.xpath(getLocator("addressStreet_input"));
    private final By ZIP_input = By.xpath( getLocator("zip_c_input") );

    private final By CITY_input = By.xpath( getLocator("city_input"));
    private final By STATE_input = By.xpath( getLocator("state_input"));

    private final By EMAIL_input = By.xpath(getLocator("emailAddr_input"));
    private final By PHONE_input = By.xpath(getLocator("phoneNum_input"));

    private final By NEXTShipPay_btn = By.xpath(getLocator("nextShipPay_btn"));

    private final By CCNumber_input = By.xpath(getLocator("ccNumber_input"));
    private final By CCExpires_input = By.xpath( getLocator("ccExpires_input"));
    private final By CSVCode_input = By.xpath(getLocator("csvCode_input"));

    private final By PLACE_ORDER_btn = By.xpath(getLocator("PLACE_ORDER_btn"));

    private final By ORDERnumber = By.xpath(getLocator("ordertext"));





    @Step("Get order Number after PLACE ORDER")
    public String recordOrderNumber(){
        String order = "";
        String text = findWebElement(ORDERnumber).getText();


       String [] array = text.split(" ");

       for (String string : array){

           order = order + string.length();
       }
                log.info("Order No_ :  " +  order);


   return order;
    }







    @Step("Fill Credit Cart Info Form")
    public CheckoutPage providePayment (PaymentData paymentData){

        setText( CCNumber_input, paymentData.getCardNumber() );
        setText( CCExpires_input,paymentData.getExpData() );
        setText( CSVCode_input,  paymentData.getSecCod() );
        clickElement(NEXTShipPay_btn);
        clickElement(PLACE_ORDER_btn);
        return  this;
    }


    @Step("Enter Shipping Address and select \"useUsBilling\" option")
    public CheckoutPage provideAddress (AddressData addressData )  throws  Exception{

        enter_BillingAddress(FULLNAME_input, ADDRESS_STREET_input,
                ZIP_input, PHONE_input, EMAIL_input, CITY_input, STATE_input, addressData);

        clickElement(NEXTShipPay_btn);

        return this;
    }



    @Step("Fill the AddressData form with Full name [{0}]," + " Address [{1}]," + " Zip [{2}]," + " Phone [{3}],"
            + " email [{4}]," + " City [{5}]" + "State[{6}]")
    public void enter_BillingAddress(By fullname, By street, By  zip, By phone, By email, By city, By state,AddressData addressData   ) throws InterruptedException  //   final String ph_num  )
    {
        setText(fullname, addressData.getFullName());
        setText(street, addressData.getAddress());
        setText(zip, addressData.getZip());
        setText(phone, addressData.getPhone() );
        setText(email, addressData.getEmail());
        clickElement(NEXTShipPay_btn);
        setText(city, addressData.getCity());
        selectDropDown( state, addressData.getState());

    }







}
