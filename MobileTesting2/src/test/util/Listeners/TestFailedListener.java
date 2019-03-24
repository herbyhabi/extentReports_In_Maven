package util.Listeners;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import util.BasicTest;
import util.FunctionUtil;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TestFailedListener extends TestListenerAdapter {
    BasicTest basicTest = new BasicTest();

    @Override
    public void onTestFailure(ITestResult result) {
        captureScreenShot(result);
    }

    public byte[] captureScreenShot(ITestResult result){

        byte[] screenshot = ((TakesScreenshot)BasicTest.driver).getScreenshotAs(OutputType.BYTES);

        File srcFile = basicTest.driver.getScreenshotAs(OutputType.FILE);
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        File location = new File(FunctionUtil.screenshotPathName);
        String dest = result.getMethod().getRealClass().getSimpleName()+"."+result.getMethod().getMethodName();
        File targetFile = new File(location.getAbsolutePath()+File.separator+dest+dateFormat.format(new Date())+".png");
        System.out.println("----------------- file is " + targetFile.getPath());

        try {
            FileUtils.copyFile(srcFile, targetFile);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return screenshot;

    }

}
