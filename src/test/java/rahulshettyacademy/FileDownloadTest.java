package rahulshettyacademy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class FileDownloadTest {

    @Test
    public void fileUploadTest() throws IOException, InterruptedException {

        String downloadPath = System.getProperty("user.dir")+"/src/main/resources/downloads";
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("download.default_directory", downloadPath);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("prefs", prefs);


        System.setProperty("webdriver.chrome.driver","src/main/resources/drivers/chromedriver");
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

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

        System.out.println("File found");
        Assert.assertTrue(true);
        driver.quit();

    }

}
