package apilipen.crutchfield.pages;

import apilipen.crutchfield.utils.BaseUtils;
import apilipen.crutchfield.utils.Config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.qatools.allure.annotations.Step;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

//


public class ApplicationManager extends BaseUtils{

 // Config c = new Config();


    private static WebDriver driver ;


    public static WebDriver getWebDriver(){


        return driver;
    }


    private HomePage homePage ;
    private ProductPage productPage;
    private ShoppingCart shoppingBag;
 //   private LoginPage login;
    private CheckoutPage checkoutPage;


    private String startUrl = null;

    // getters of pages classes
    public HomePage onHomePage() {
        return homePage;
    }
    public ProductPage onProdPage() {
        return productPage;
    }
    public ShoppingCart onShoppingCartPage() {
        return shoppingBag;
    }

    public CheckoutPage onCheckoutPage(){


        return checkoutPage;
}





    @Step("Check driver is NOT null")
    public void startExecuteTests ( String driver){
       // openHomePage(prodUrl);


        if (driver == null) {  log("BeforeMethod: driver is null!");}

        try {

                openHomePage();

        } catch (Exception ex) {
            log.error(ex.getStackTrace());
        }

    }




    @Step("Initialise browser type: [{0}]")
    public void init(String browser) throws IOException {

        setWebDriver(browser);
        homePage = new HomePage( driver);
        productPage = new ProductPage(driver);
        shoppingBag = new ShoppingCart(driver);
        checkoutPage = new CheckoutPage(driver);

    }


    @Step("Open start web page")
    public void openHomePage() {

      String startIp = new Config().getPropertyOut(Config.START_URL);



      if (startIp.contains("qa")) {
            String usr = Config.getPropertyOut(Config.USR); // "qauser";
            String psw = Config.getPropertyOut(Config.PSW); // "qapassw";

                     startUrl = "https://" + usr + ":" + psw + "@" + startIp;
       }

        else {
            startUrl = startIp;

          log.info("Initial start_url:  " + startUrl);
        }

        homePage.openPage(startUrl);

        log.info("openHomePage()   Ends");

    }


    @Step("Close driver ")
    public void tearDown()  throws IOException {

        // driver.close();
        driver.quit();

    }







    @Step("Set up type of driver [{0}] from  enter parameter")
    public  void setWebDriver(String browser) throws IOException {  // String browser
        // String browser = driverType ;//System.getProperty("browser");
     log.info("Driver type is: ["  +   browser   + "]");
        Logger logger = Logger.getLogger("");
        logger.setLevel(Level.OFF);
        String driverPath = "";
        String osname = System.getProperty("os.name");
     log.info("os.name = " + osname + "; browser = " + browser);

        if ((browser.toLowerCase().equals("ff")) && (System.getProperty("os.name").toUpperCase().contains("MAC"))) {
            driverPath = "./src/webdrivers/mac/geckodriver.sh";
     log("identified driver = " + driverPath); } // geckodriver.sh"

        else if ((browser.toLowerCase().equals("firefox")) && (System.getProperty("os.name").toUpperCase().contains("WINDOWS")))
        {driverPath = "./src/webdrivers/pc/geckodriver.exe";}

        else  if (( browser.toLowerCase().equals("firefox"))  &&   (System.getProperty("os.name").toUpperCase().contains("LINUX")))  {
            driverPath = "./src/test/webdrivers/lnx/geckodriver.sh";
     log("identified driver = " + driverPath) ;}


// /Users/anna/NewNeonWorks/WebStoreAutomation/src/webdrivers/mac/chromedriver

        else if ((browser.toLowerCase().equals("chrome")) && (System.getProperty("os.name").toUpperCase().contains("MAC")))
        {driverPath = "src/webdrivers/mac/chromedriver";
     log("identified driver = " + driverPath);   }

        else if  ((browser.toLowerCase() .equals("chrome")) && (System.getProperty("os.name").toUpperCase().contains("LINUX")) ) {
            driverPath = "./src/webdrivers/lnx/chromedriver64";

     log("identified driver = " + driverPath); }

        else if ((browser.toLowerCase().contains("chrome")) && (System.getProperty("os.name").toUpperCase().contains("WINDOWS")))

        {driverPath = "./src/webdrivers/pc/chromedriver.exe";}



        else   {throw new IllegalArgumentException("Unknown OS");}




        switch (browser) {


            case "ff":
                System.setProperty("webdriver.gecko.driver", driverPath);  // String binaryPath = "/Applications/Firefox54.app/Contents/MacOS/firefox-bin";

//               String binaryPath = "/Applications/Firefox54.app/Contents/MacOS/firefox-bin";
//               FirefoxOptions options = new FirefoxOptions();;
//               options.setBinary(binaryPath); //This is the location where you have installed Firefox on your machine
//               DesiredCapabilities capabilities = DesiredCapabilities.firefox();
//               capabilities.setCapability("moz:firefoxOptions", options);
//               capabilities.setCapability(FirefoxDriver.MARIONETTE, true);
//               driver = new FirefoxDriver(capabilities);
//               driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//               driver.manage().window().maximize();

                driver = new FirefoxDriver();
                driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
                driver.manage().window().maximize();
                break;


            case  "Chrome":
                System.setProperty("webdriver.chrome.driver", driverPath);
                System.setProperty("webdriver.chrome.silentOutput", "true");



                ChromeOptions option = new ChromeOptions();
                option.addArguments("disable-infobars");
                option.addArguments("--disable-notifications");

                if (System.getProperty("os.name").toUpperCase().contains("MAC"))
                    option.addArguments("-start-fullscreen");

                else if (System.getProperty("os.name").toUpperCase().contains("LINUX"))
                    option.addArguments("-start-fullscreen");

                else if (System.getProperty("os.name").toUpperCase().contains("WINDOWS"))

                    option.addArguments("--start-maximized");

                else {throw new IllegalArgumentException("Unknown OS");}
                driver = new ChromeDriver(option);
                driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);





                break;

            default: throw new IllegalArgumentException("Unknown Browser");
        }
    }










}
