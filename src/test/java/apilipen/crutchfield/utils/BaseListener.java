package apilipen.crutchfield.utils;

import org.testng.*;

import static apilipen.crutchfield.utils.BaseUtils.attachText;
import static apilipen.crutchfield.utils.BaseUtils.getTimeStamp;
import static apilipen.crutchfield.utils.BaseUtils.makeScreenShot;

public class BaseListener implements ITestListener, IInvokedMethodListener {


    /* Methods of Interface 'ITestListener' */
    @Override
    public void onTestStart(ITestResult result) {
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        makeScreenShot( "See ScreenShot on Success .....");
    }
    @Override
    public void onTestFailure(ITestResult result) {
        attachText(result.toString());
        makeScreenShot( "See ScreenShot on failure ....."  + "_" + getTimeStamp());
        //  attachText("User Agent information" );
        // attachText("Browser logs" , getDriverContainer().manage().logs().get("browser").getAll().toString());
    }
    @Override
    public void onTestSkipped(ITestResult result) {
    }
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

        makeScreenShot( "See ScreenShot on Success .....TestFailedButWithinSuccessPercentage");
        //  makeScreenShot(getFileNameSysPart() + "_" + getTimeStamp());
        // attachText("User Agent information" , getUaInfo());
        // attachText("Browser logs" , getDriverContainer().manage().logs().get("browser").getAll().toString());
    }
    @Override
    public void onStart(ITestContext context) {
        // TODO Auto-generated method stub
        // getUAinfo(getWebDriver());
    }
    @Override
    public void onFinish(ITestContext context) {
    }
    @Override
    public void afterInvocation(IInvokedMethod arg0, ITestResult arg1) {
    }
    @Override
    public void beforeInvocation(IInvokedMethod arg0, ITestResult arg1) {
    }




}
