package util;

import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;

public class CustomizeAssertion extends BasicTest {
        ITestResult result = Reporter.getCurrentTestResult();


        public boolean assertTrue(boolean description) {
        try {
            Assert.assertTrue(description);
            return true;
        } catch (AssertionError e) {
            test.log(LogStatus.FAIL,"Failed");
            return false;
        }
    }


    public void printError(){

    }
}
