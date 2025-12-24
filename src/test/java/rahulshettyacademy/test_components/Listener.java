package rahulshettyacademy.test_components;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import rahulshettyacademy.utlities.Report;
import rahulshettyacademy.utlities.Screenshot;


public class Listener extends BaseTest implements ITestListener{

    ExtentReports extentReports = Report.getExtentReports();
    ExtentTest extentTest;

    @Override
    public void onTestStart(ITestResult result) {

       extentTest =  extentReports.createTest(result.getMethod().getMethodName());

    }

    @Override
    public void onTestFailure(ITestResult result) {

        extentTest.fail(result.getThrowable());
        //take screenshot then attach to the report
        Object testInstance = result.getInstance();  // Gets the test class object
        this.driver = ((BaseTest) testInstance).driver;  // Accesses its driver field
        String screenShotPath = Screenshot.takeScreenshot(driver);
        extentTest.addScreenCaptureFromPath(screenShotPath);

    }


    @Override
    public void onFinish(ITestContext context) {

        extentReports.flush();

    }



}
