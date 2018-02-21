package apilipen.crutchfield.template;

import apilipen.crutchfield.utils.BaseUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.allure.annotations.Step;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class HelperDriverBase extends BaseUtils{

    protected WebDriver driver;
    public WebDriverWait wait;
    private Properties locatorsProperties = new Properties();


    public HelperDriverBase(WebDriver wd) {
        // TODO Auto-generated constructor stub
        this.driver = wd;
        wait = new WebDriverWait(this.driver, 60);

     /*
     Load locators from property file to the memory
      */
        try {
            String pathfile = "./src/test/java/apilipen/resources/locators/prodLocators.properties";
            locatorsProperties.load(new FileInputStream(pathfile));
        } catch (IOException e) {
            throw new RuntimeException("Can't load locators resources", e);			}
    }



    private List<WebElement> linksAllonPageTagA;
    private List<WebElement> imgAllonPageTagImg;


    @Step("Get value [{0}] from collection of properties")
    public String getLocator(String propKey) {

        return locatorsProperties.getProperty(propKey);
    }


    @Step("Get WebElement by locator [{0}] ")
    protected WebElement findWebElement(final By locator) {
        log.info("-> " + locator.toString());
        return highlight(driver.findElement(locator));
    }

    @Step("Find WebElement Editable By locator [{0}]")
    protected    WebElement findElementVisible(final By locator) {
        log.info("-> " + locator.toString());
        // return highlight(wait.until(ExpectedConditions.elementToBeClickable(locator)));
        return highlight(wait.until(ExpectedConditions.visibilityOfElementLocated(locator)));
    }

    @Step("Click on the button [{0}]")
    protected void clickElement(By locator) {
        findElementVisible(locator).click();

    }


    @Step("Wait to the element will be clickable [{0}]")
    public void clickElementclickable(final By locator) {
        log.info("-> " + locator.toString());
        (highlight(wait.until(ExpectedConditions.elementToBeClickable(locator)))).click();
        //findElement(locator).click();
    }

    @Step("Send text[{1}] to fild: [{0}]")
    protected void setText(By locator, String text) {
        findWebElement(locator).clear();
        findWebElement(locator).sendKeys(text);
    }

    @Step("Select visible text [{1}] from drop down list [{0}]")
    public void selectDropDown(final By locator, String option_Name  ) {

        new Select(findWebElement(locator)).selectByVisibleText(option_Name);

    }


    @Step("Wait of title [{0}]")

    public String getPageTitle (final String expectedTitle) {
          // wait.until(ExpectedConditions.titleContains(expectedTitle));
          wait.until(ExpectedConditions.titleIs(expectedTitle));

         log.info("get title from page " + driver.getCurrentUrl());
          return driver.getTitle();
      }



    @Step("Select option[{1}] ByValue  in dropdown By locator [{0}]")
    public void selectDropDown_byValue(final By locator, String option_Name  ) {

        new Select(findWebElement(locator)).selectByValue(option_Name);

    }

    @Step("Get text from element [{0}]")
    protected String  getTextOfElement(final By locator){
      return   findWebElement(locator).getText();

    }


    @Step("Open URI [{0}]")
    public void openPage(final String url) {
        driver.get(url.toString());
    }


    /* Highlight elements on the page */
    public WebElement highlight (final WebElement element) {
        //final String originalBackgroundColor  = element.getCssValue("backgroundColor");
        final JavascriptExecutor elementTestJS = ((JavascriptExecutor) driver); // java script
        elementTestJS.executeScript("arguments[0].style.backgroundColor = 'rgb(0,200,0)'",  element);

        return element;
    }




    @Step("Looking for elementS [{0}]")
    public List<WebElement> findElements(final By locator) {
      log.info("-> " + locator.toString());
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    /*
	 * Method return quantity of attributes 'href' with value 'null'. Verify
	 * there are no dead links and images on the page. (optional)
	 */
    @Step("Calculate - how many links with attribute 'href' == 'null'")
    public int howManyNullHrefAtrribute() {

        int howManyNullHref = 0;

		/* Get list of all HTML Element tag 'A' on the current page */
        linksAllonPageTagA = findElements(By.tagName("A"));

		/*
		 * Size of List 'linksAllPOMFutureHomePageTagA' (inside can be null and
		 * not null value) before 'clean'
		 */
        howManyNullHref = linksAllonPageTagA.size();

		/*
		 * This method clean List 'linksAllPOMFutureHomePageTagA' from 'null'
		 * value
		 */
        cleanArrayFromNull(linksAllonPageTagA, "href", "http");

		/* Size of List 'linksAllPOMFutureHomePageTagA' after cleaned up */
        howManyNullHref = howManyNullHref - linksAllonPageTagA.size();

		/* return different, between (before size - after size) */

       log.info("'NULL' attribute 'href' on the page: [" + howManyNullHref + "]");
        return howManyNullHref;
    }





    /* Method return quantity of attributes 'src' with value 'null' */
    @Step("Calculate - how many img with attribute 'src' = 'null', and clean Collection")
    public int howManyNullSrcAtrribute() {

		/* Get list of all HTML Element tag 'IMG' on the current page */
        imgAllonPageTagImg = findElements(By.tagName("IMG"));

        int howManyNullSrc = 0;
        howManyNullSrc = imgAllonPageTagImg.size();
        cleanArrayFromNull(imgAllonPageTagImg, "src", "/");

		/* Size of List 'linksAllPOMFutureHomePageTagA' after cleaned up */
        howManyNullSrc = howManyNullSrc - imgAllonPageTagImg.size();

		/* return different, between (before size - after size) */
        log.info("'NULL' attribute 'src' on the page: [" + howManyNullSrc + "]");

        return howManyNullSrc;
    }

    /* Method return quantity of attributes 'src' with value 'null' */
    @Step("Calculate - how many img with attribute 'alt'")
    public int howManySrcAtrribute() {

        int howManySrc = imgAllonPageTagImg.size();

		/* return different, between (before size - after size) */
        log.info("NOT 'null' attribute 'src' on the page: [" + howManySrc + "]");

        return howManySrc;
    }


    /* Method return quantity of attributes 'alt' on the page */
    @Step("Calculate - how many img with attribute 'alt'")
    public int howManyNullAltAtrribute() {

        int howManyAlt = 0;

        for (WebElement myElement : imgAllonPageTagImg) {

            if (myElement.getAttribute("alt").matches("."))
                ;
            howManyAlt++;
        }

		/* return different, between (before size - after size) */
        log.info("NOT 'null' attribute 'alt' on the page: [" + howManyAlt + "]");

        return howManyAlt;
    }


    @Step("Provide 'Connections' links on the page - OkHttp - DataProvider for Test")
    public Object[][] getStatusOfConnection() throws IOException {

        int howManyLinks = linksAllonPageTagA.size();
        log.info("Start create 2D Array for DataBinding\n" + "Total links: [" + linksAllonPageTagA.size() + "]");

        int i = 0; // Count, for 2D Array, from one line to next

        String[][] dataProvider = new String[howManyLinks][3];
        log.info("Processing: [" + howManyLinks + "] = 100 %");

        for (WebElement myElement : linksAllonPageTagA) {

            getResponseCode(myElement, dataProvider, i);

			/* Percent status - if a lot of links on the page */
            if (i % 10 == 0) {

                log.info("Processing: [" + (int) (((float) i / (float) howManyLinks) * 100) + "] %");
            }
             log.info(i+"="+dataProvider[i][0]+"-"+dataProvider[i][1]+"-"+dataProvider[i][2]);
            i++;
        }

        log.info("END create 2D Array for DataBinding");
        return dataProvider;
    }




}
