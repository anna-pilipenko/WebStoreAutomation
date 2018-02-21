package apilipen.crutchfield.utils;

import org.apache.log4j.Logger;

import static apilipen.crutchfield.utils.BaseUtils.makeScreenShot;

public class LogUtil {


    public static Logger log = Logger.getLogger(LogUtil.class.getName());//


    // Need to create these methods, so that they can be called
    public static void info(String message) {
        log.info(message);
    }

    public static void warn(String message) {
        log.warn(message);
    }

    public static void error(String message) {
        log.error(message);
        makeScreenShot( "See ScreenShot on failure .....");
    }


    public static void fatal(String message) {
        log.fatal(message);
    }

    public static void debug(String message) {
        log.debug(message);

    }


}
