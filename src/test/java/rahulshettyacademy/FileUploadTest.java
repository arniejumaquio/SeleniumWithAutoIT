package rahulshettyacademy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

public class FileUploadTest {

    @Test
    public void fileUploadTest() throws IOException {

        System.setProperty("webdriver.chrome.driver","src/main/resources/drivers/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://qa-automation-practice.netlify.app/file-upload.html");

        //String projectPath = System.getProperty("user.dir");
        //String fileToUploadPath = projectPath+"/src/main/resources/test.pdf";
        //WebElement chooseFileButton = driver.findElement(By.cssSelector("input#file_upload"));
        //chooseFileButton.sendKeys(fileToUploadPath);

        Runtime.getRuntime().exec("src/main/resources/AutoIT/install/UploadFile.exe");


        driver.quit();
    }

}
