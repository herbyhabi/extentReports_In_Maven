package util;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class BasicTest extends FunctionUtil {

    public static AppiumDriver driver;
    protected static ExtentTest test;
    public static String testName;
    public static String buildNumber;

    @BeforeSuite(alwaysRun =true)
    public void generateBuildNumber(){

        Date date=new Date();
        DateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");
        String time=format.format(date);
        buildNumber=time;
    }



    @BeforeClass
    public void setUp() throws InterruptedException, MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "6.0.1");
        capabilities.setCapability("appPackage", "com.xiaomi.notes");
        capabilities.setCapability("app", "C:\\Users\\herby\\Downloads\\xiaomiNotes.apk");
        capabilities.setCapability("clearSystemFiles", true);

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities );
        Thread.sleep(2000);

        testName = this.getClass().getName();
        test = ReportFactory.getTest(testName, "", "");

    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(Method caller, ITestResult iTestResult) {
        ReportFactory.closeTest(testName);
        if(iTestResult.getThrowable() != null && !iTestResult.getThrowable().toString().contains("java.lang.AssertionError")){
            test.log(LogStatus.FAIL, "Exception Type: " + iTestResult.getThrowable().getMessage());
//            String path = customAssertion.snapshot((TakesScreenshot) driver);
//            customAssertion.printError(path);
        }
        //ComplexReportFactory.closeTest(caller.getName(), pagename, caller.getDeclaringClass().getName());
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() throws Exception {
        ReportFactory.closeReport();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown(){
        driver.closeApp();

    }

    public void verifyFinalAssert(){
        test.log(LogStatus.INFO, "The final Verification");//make an empty row in the extent report
        Assert.assertEquals(ReportFactory.getTest(testName).getRunStatus().toString(), LogStatus.PASS.toString(), "the script status is :" + ReportFactory.getTest(testName).getRunStatus());
    }







}
