package apilipen.crutchfield.template;

import apilipen.crutchfield.pages.ApplicationManager;
import apilipen.crutchfield.utils.BaseUtils;
import org.apache.log4j.xml.DOMConfigurator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

public class TestBase  extends BaseUtils {


    public static final ApplicationManager app = new ApplicationManager();


    public static String driverType = System.getProperty("driverType"); //"FF";




    @BeforeClass
    public void  setUp()   throws  Exception{
        DOMConfigurator.configure("log4j.xml");
        app.init(driverType);
        log.info("In setUp method driverType is: " + driverType);
        app.startExecuteTests( driverType );
    }



    @AfterMethod
    public void afterTest() {
        //   app.afterEachTest();
    }

    @AfterClass
    public void afterClass() throws IOException {
        writeOutputCsv("./src/test/java/apilipen/resources/outputData");  // "/Users/anna/Desktop/"
        app. tearDown()  ;

    }







}
