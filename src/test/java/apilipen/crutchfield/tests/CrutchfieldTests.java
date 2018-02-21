package apilipen.crutchfield.tests;


import apilipen.crutchfield.model.AddressData;
import apilipen.crutchfield.model.PaymentData;
import apilipen.crutchfield.template.TestBase;
import apilipen.crutchfield.utils.BaseListener;
import apilipen.crutchfield.utils.Config;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.*;
import ru.yandex.qatools.allure.model.SeverityLevel;

import java.io.IOException;

import static apilipen.crutchfield.utils.Config.TITLE;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@Listeners(BaseListener.class)



public class CrutchfieldTests extends TestBase{


    @Features({ "Features: CHF-001" })
    @Stories({ "Stories: CIR-098" })
    @TestCaseId("TestCaseId: TC-001")
    @Issues({ @Issue("Issue: CIR-001"), @Issue("Issue: CIR-002") })
    @Severity(SeverityLevel.BLOCKER)

    @Test  (groups = {"startPage", "regression"}, enabled = true  )
    public void startPageTitleTest () throws Exception {
            String actualTitle = app.onHomePage().getPageTitle(Config.getPropertyOut(TITLE));
        assertThat(actualTitle,  describedAs("Actual Title is: " , is(Config.getPropertyOut(TITLE))));

    }





    @Features({ "Features: WCC-003" })
    @Stories({ "Stories: CIR-03" })
    @TestCaseId("TestCaseId: TC-003")
    @Severity(SeverityLevel.NORMAL)
    @Test(  enabled = true , groups = {"startPage"}, dependsOnMethods = "startPageTitleTest")
    public void howManyNullHrefAtrribute() {
        assertThat(app.onHomePage()
                        .howManyNullHrefAtrribute(),
                describedAs("Expected '0' - broken links", is(0))   );
    }





    @Features({ "Features: WCC-003" })
    @Stories({ "Stories: CIR-04" })
    @TestCaseId("TestCaseId: TC-004")
    @Severity(SeverityLevel.NORMAL)
    @Test( enabled = true, groups = {"startPage"}, dependsOnMethods = "startPageTitleTest" )
    public void howManyNullImgAtrribute() {
        assertThat(app.onHomePage()
                        .howManyNullSrcAtrribute(),
                describedAs("Expected '0' - broken IMG 'src' = NULL", is(0))  );
    }



    @Features({ "Features: WCC-003" })
    @Stories({ "Stories: CIR-05" })
    @TestCaseId("TestCaseId: TC-005")
    @Severity(SeverityLevel.NORMAL)
    @Test(enabled = true ,groups = {"startPage"},  dependsOnMethods = "howManyNullImgAtrribute")
    public void howManyScrAltAttrubute() {
        int expected = app.onHomePage().howManySrcAtrribute();
        int actual = app.onHomePage().howManyNullAltAtrribute();
        assertThat(actual, describedAs("Expected 'src' = 'alt' attribute", is(expected))   );
    }




    @DataProvider(name = "StatusOfConnection" )   // parallel = true
    public Object[][] arrayOfStatusOfConnection() throws IOException {
        return app.onHomePage().getStatusOfConnection();
    }

    @Description("We provide link validation")
    @Severity(SeverityLevel.MINOR)
    @Test(dataProvider = "StatusOfConnection", groups = {"startPage"},  dependsOnMethods = "startPageTitleTest", enabled = false)
    public void linkStatusTest(String responseCode, String responseMessage, String url) {
        assertThat(responseCode,
                describedAs("Expected Not 'Null' response Code. Msg: [" + responseMessage + "], href: [" + url + "]",
                        notNullValue()));
        assertThat(responseCode, describedAs("Must be not '404' HTTP response code", not(is("404")))     );
    }




    @DataProvider(name = "searchItem")
    public static Object[][] searchItem() throws IOException {
        return readInputDataTestID("tcID_01_UC");
    }

    // mvn test
    @Features({ "Features: WCC-002" })
    @Stories({ "Stories: CIR-099" })
    @TestCaseId("TestCaseId: TC-002")
    @Issues({ @Issue("Issue: CIR-003"), @Issue("Issue: CIR-004") })
    @Severity(SeverityLevel.NORMAL)
    @Test  (enabled = true, groups = {"regression"} , dataProvider = "searchItem" , dependsOnMethods = "startPageTitleTest" )
    public void shoudAddItemsToCartAndPurchase (  String... param ) throws Exception {  //


         app.onHomePage().searchItem(param[1]);
         app.onProdPage().addItemAndGoToCart();
         app.onShoppingCartPage().addQuantity(param[2]).proccedCheckout();
         app.onCheckoutPage()
                             .provideAddress(new AddressData()
                 .withFullName(param[3])
                 .withAddress(param[4])
                 .withZip(param[5])
                 .withPhone(param[6])
                 .withEmail(param[7]).withCity(param[8]).withState(param[9])   )
                             .providePayment(new PaymentData()
                 .withCardNumber(param[10]).withExpData(param[11]).withSecCod(param[12]));
         String orderNum =  app.onCheckoutPage().recordOrderNumber();
         assertThat( isCorrectValue(orderNum),
                     is(allOf(  describedAs("[" + orderNum + "] is valid Order Number",
                                is(true)),
                                instanceOf(Boolean.class)   )));
         storeTestRunResults(param[0], "CF", orderNum);

            }






}
