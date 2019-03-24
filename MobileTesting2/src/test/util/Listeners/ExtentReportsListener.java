package util.Listeners;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.aventstack.extentreports.ResourceCDN;
import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import com.aventstack.extentreports.Status;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import static java.lang.System.getProperty;

public class ExtentReportsListener implements IReporter {

    private static final String OUTPUT_FOLDER = getProperty("user.dir")+"/test_output/";
    private static final String FILE_NAME = "Extent.html";
    private static ExtentReports extent;
    public static ExtentTest test;

    @Override
    public void generateReport(List xmlSuites, List suites, String outputDirectory) {
        init();
        for (Object suite : suites) {
            Map result = ((ISuite) suite).getResults();
            for (Object r : result.values()) {
                ITestContext context = ((ISuiteResult) r).getTestContext();
                buildTestNodes(context.getFailedTests(), Status.FAIL);
                buildTestNodes(context.getSkippedTests(), Status.SKIP);
                buildTestNodes(context.getPassedTests(), Status.PASS);

            }
        }

        for (String s : Reporter.getOutput()) {
            extent.setTestRunnerOutput(s);
        }

        extent.flush();
    }

    private void init() {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(OUTPUT_FOLDER + FILE_NAME);
        htmlReporter.config().setResourceCDN(ResourceCDN.EXTENTREPORTS); //解决报告没有css样式的问题
        htmlReporter.config().setDocumentTitle("ExtentReports - Created by TestNG Listener");
        htmlReporter.config().setReportName("ExtentReports - Created by TestNG Listener");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
        htmlReporter.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setReportUsesManualConfiguration(true);
        extent.setSystemInfo("Tester","Herby he");
        extent.setSystemInfo("System","Windows 10");
        extent.setSystemInfo("Monitor","MuMu");
        extent.setSystemInfo("app system","Android");
    }



    private void buildTestNodes(IResultMap tests, Status status) {


        if (tests.size() > 0) {
            for (ITestResult result : tests.getAllResults()) {
                test = extent.createTest(result.getMethod().getMethodName());

                for (String group : result.getMethod().getGroups())
                    test.assignCategory(group);

                if (result.getThrowable() != null) {
                    test.log(status, result.getThrowable());
                }
                else {
                    test.log(status, "Test " + status.toString().toLowerCase() + "ed");
                }

                test.getModel().setStartTime(getTime(result.getStartMillis()));
                test.getModel().setEndTime(getTime(result.getEndMillis()));
            }
        }
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }

}
