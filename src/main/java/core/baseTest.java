package core;

import com.relevantcodes.extentreports.LogStatus;
import helper.Helper;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import utils.ExtentReport;

import java.io.IOException;

public class baseTest {


    @BeforeSuite(alwaysRun = true)
    public void config()  throws IOException
    {
        String subFolderPath=System.getProperty("user.dir")+"/reports/"+ Helper.TimeStamp();

        Helper.CreateFolder(subFolderPath);

        ExtentReport.initialize(subFolderPath+"/"+"API_Execution_Automation.html");
    }

    @AfterMethod(alwaysRun = true)

    public void getResult(ITestResult result) {

        if (result.getStatus() == ITestResult.SUCCESS) {

            ExtentReport.extentlog.log(LogStatus.PASS, "Test Case : "+ result.getName()+" is passed ");

        } else if (result.getStatus() == ITestResult.FAILURE) {

            ExtentReport.extentlog.log(LogStatus.FAIL, "Test case : "+ result.getName()+" is failed ");
            ExtentReport.extentlog.log(LogStatus.FAIL, "Test case is failed due to:  " + result.getThrowable());

        } else if (result.getStatus() == ITestResult.SKIP) {
            ExtentReport.extentlog.log(LogStatus.SKIP, "Test case is Skiped " + result.getName());
        }
        ExtentReport.extentreport.endTest(ExtentReport.extentlog);
    }

    @AfterSuite(alwaysRun = true)
    public void endReport() {
        //ExtentReport.extentreport.flush();
        ExtentReport.extentreport.close();
        //Logging.setinstanceNull();
    }
}
