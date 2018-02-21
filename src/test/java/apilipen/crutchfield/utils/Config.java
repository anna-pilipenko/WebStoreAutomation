package apilipen.crutchfield.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class Config   {


 // we define Keys in Property file
    //prop from particular folder
    public static final String START_IP = "start.ip";
    public static final String TITLE = "title";
    public static final String USR = "user";
    public static final String PSW = "password";
    public  static final  String START_URL =  "url";   // key for new property

/*
We create object Properties,with take all properties from System (including command line)   */
    private static final Properties ENV_PROPERTIES = System.getProperties()  ;   // getProperties(); // collection of System.properties

    public static final String ENV_NAME =  (String) ENV_PROPERTIES.getProperty("env"); // get("env"); // -Denv=prod

    /* configure path to prop. file depending on ENV from com.lime*/


    public  String PATH_TO_ENV_PROPERTIES;

     /**
     * Create new Object (Collection) with properties System + external_PF
     */
   public static Properties properties = new Properties();

    public Config() {
        PATH_TO_ENV_PROPERTIES = new String("./src/test/java/apilipen/resources/config/").concat(ENV_NAME).concat(".properties") ;

        try {
            properties.load(new FileInputStream(PATH_TO_ENV_PROPERTIES));  //load all props from File
            properties.putAll(ENV_PROPERTIES);  //  added to Collection props from System (as Map(keys :val))
            properties.put(START_URL, getURL());

//            START_URL = properties.getProperty("start.ip");

        } catch (IOException e) {
            throw new RuntimeException("Can't load config resources", e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




        private static final String getURL() {
            String endsURL =  "http://" + getPropertyOut(START_IP);

            return endsURL;
        }

//synchronized
    public static synchronized String getPropertyOut(String propKey) {
        return properties.getProperty(propKey);
    }



}// EndOfClass
