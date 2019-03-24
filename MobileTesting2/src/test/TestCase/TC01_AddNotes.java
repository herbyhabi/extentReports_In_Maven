package TestCase;

import Pages.HomePage;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import util.BasicTest;
import util.CustomizeAssertion;


public class TC01_AddNotes extends BasicTest {

    private static Logger log = Logger.getLogger(TC01_AddNotes.class);

   HomePage homePage;
   CustomizeAssertion assertion;

    @Test
    public void AddNotes() throws InterruptedException {
        homePage = new HomePage();
        assertion = new CustomizeAssertion();

        test.log(LogStatus.INFO,"Test it test test test");
        log.info("Test log to console");

        homePage.btnOfAdd.click();
        assertion.assertTrue(true);
        Thread.sleep(2000);
        homePage.areaOfText.sendKeys( "Test to add notes");

        verifyFinalAssert();

    }
}
