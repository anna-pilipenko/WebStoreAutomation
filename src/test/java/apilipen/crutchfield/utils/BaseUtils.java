package apilipen.crutchfield.utils;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static apilipen.crutchfield.pages.ApplicationManager.getWebDriver;


public class BaseUtils  {


    protected final Logger log = Logger.getLogger("[" + this.getClass());

    public  void log (String text){
        System.out.println("INFO: [" + text + "]");
    }

    @Step("Check Order Number for regex")
    public Boolean isCorrectValue(String value) {
        if (value.replaceAll(" ", "") == null  ){
            log.info("Value is empty");
            return false; }

        else if  (!value.matches("\\d{24}")) {
            log(value + " has illigal value ");
            return false;
        }
        return true;

    }


    /* This Method Clean 'List' - delete null elements inside Collection*/
    @Step("Clean Collection")
    public void cleanArrayFromNull(List<WebElement> inputElements, final String nameAttribute, final String content) {
        log.info("Start clean 'List<WebElement>'");
        log.info("Elements on the page before: [" + inputElements.size() + "]");
        Iterator<WebElement> iter = inputElements.iterator();

        while (iter.hasNext()) {

            WebElement tmp = iter.next();
            String value = tmp.getAttribute(nameAttribute);

            if ((value == null) || !(value.contains(content)) || (value.equals("")) || (value.equals(" ")))

            {
                iter.remove();
            }
        }

        log.info("Elements on the page after: [" + inputElements.size() + "]");
        log.info("END clean 'List<WebElement>'");
    }




    /*
         * This method return 'Object' (OkHttp - Library) from connection use 'href'
         * attribute from WebElement
         */
    @Step("Get response code - use OkHttp")
    public void getResponseCode(final WebElement myElement, String[][] array2D, final int line) throws IOException {
        final long connectTimeout = 300;

        String url = myElement.getAttribute("href");
        array2D[line][2] = url;

        String debugAttributeTitle = null;
        String debugAttributeTitleClass = null;

        String debugTextOfTag = null;

        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(connectTimeout, TimeUnit.MILLISECONDS).build();

        Request request = new Request.Builder().url(url).build();

        Response response = null;

        try {

            response = client.newCall(request).execute();

			/* if response without ERRORS */
            // if(response.isSuccessful())
            {
                array2D[line][0] = Integer.toString(response.code());
                array2D[line][1] = response.message();
                response.close();
            }

			/* if response with ERRORS */
            // if (!response.isSuccessful()) throw new IOException("Unexpected
            // code " + response);

        } catch (IOException | NullPointerException e) {

            debugAttributeTitle = myElement.getAttribute("title");
            debugAttributeTitleClass = myElement.getAttribute("class");
            debugTextOfTag = myElement.getText();

            array2D[line][0] = null;
            array2D[line][1] = "Unexpected code: [" + e.getMessage() + "], Timeout was more than: [" + connectTimeout
                    + "]";

			/* Duplicate */


            log.error("Connection - Fail. " + "Link: [" + url + "] - Timeout was more than: [" + connectTimeout
                    + "], Massege: [" + "[" + e.getMessage() + "]");


            log.error("Value of Attribute Tag 'a'. " + "Title: [" + debugAttributeTitle + "], Class: ["
                    + debugAttributeTitleClass + "], Name: [" + debugTextOfTag + "]");
        }

    }


    /* This method make Screen Shot for Allure report */
    @Attachment(value = "{0}", type = "image/png")
    public synchronized static byte[] makeScreenShot(String tmp) {
        return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }


    /* This method make Text attachment for Allure report */
    @Attachment(value = "{0}", type = "text/plain")
    public synchronized static String attachText( String bodyOfMassege) {
        return bodyOfMassege;
    }



    /* This Method generate String Time Stamp -> someTest_Win7_FF24_ [2014-12-03_09-40-35] */
    @Step("Generate name for attachment (_Time Stamp)")
    public static synchronized String getTimeStamp() {

        return new SimpleDateFormat("MM-dd-yyyy_HH-mm-ss").format(new Date());
    }






    // public  static  final String fileName = System.getProperty("csv");

    public static final  String fileName = "data";

    public static String  filePath = "src/test/java/apilipen/resources/csv/"  + fileName + ".csv";

    public    static String[] [] readInputDataTestID  (String testID)throws IOException {  // , String filePath
        String [][] dataProvider= null;
        int row = 0;
        String cvsLine = "";
        String[] a = null;
        ArrayList<String[]> al = new ArrayList<String[]>();
        BufferedReader br = new BufferedReader(new FileReader(filePath));

        System.out.println("readInputDataTestID for  parsing CSV  is running");
        while ((cvsLine = br.readLine()) != null ) {
            a = cvsLine.split(",");
            if (a[0].equalsIgnoreCase(testID)) {
                System.out.println("first field is:  "   +   a[0]);
                dataProvider = new String [1][a.length];
                for (int n=0; n<a.length; n++) {
                    dataProvider[0][n] = a[n];
                }
            }
        }
        br.close();
        System.out.println("Done parsing CSV");
        return dataProvider;
    }


    ArrayList<String> csvResults = new ArrayList<String>();


    public void storeTestRunResults (String testID, String tag, String orderNum) {

        StringBuilder sb = new StringBuilder(100);
        sb.append(testID);
        sb.append(",");
        sb.append(tag);
        sb.append(",");
        sb.append(orderNum);
        String s = sb.toString();
        log.info("storeTestRunResults: added" + s + "to csvResults");

        log.info("Upload data to List. Capasity String Bulder  [" + sb.capacity()     + "]");
        csvResults.add(s);

        // csvResults.add(testID + "," + tag + "," + orderNum);
    }



    public void writeOutputCsv(String path) throws IOException  {
        String fname = path + "" +  "results.csv";
        File file = new File(fname);
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        BufferedWriter bwriter = new BufferedWriter(new FileWriter(file));
        for (String line : csvResults) {
            bwriter.write(line); bwriter.newLine();
            System.out.println("writeOutputCsv: " + line);
        }
        bwriter.flush();
        bwriter.close();
    }







}
