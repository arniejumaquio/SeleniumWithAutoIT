package rahulshettyacademy;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import rahulshettyacademy.test_components.BaseTest;

import java.io.File;
import java.io.IOException;


public class FileDownloadTest extends BaseTest {



    @Test
    public void fileDownloadTest() throws IOException, InterruptedException {

        String downloadPath = System.getProperty("user.dir")+"/src/main/resources/downloads";
        driver.get("https://demo.automationtesting.in/FileDownload.html");

        WebElement downloadButton = driver.findElement(By.cssSelector("a.btn.btn-primary"));
        downloadButton.click();

        File downloadedFile = new File(downloadPath+"/samplefile.pdf");
        while (!downloadedFile.exists()){

            //wait
            Thread.sleep(500);

            if(downloadedFile.exists()){
                break;
            }

        }

        System.out.println("File found.");
        Assert.assertTrue(false);


    }

}
