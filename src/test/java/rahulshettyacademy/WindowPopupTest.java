package rahulshettyacademy;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import rahulshettyacademy.test_components.BaseTest;

import java.net.URI;
import java.time.Duration;
import java.util.function.Predicate;

public class WindowPopupTest  extends BaseTest {

    @Test
    public void windowPopupTest() throws InterruptedException {

        String username = "admin";
        String password = "admin";
        String url = "the-internet.herokuapp.com/";
        //Predicate<URI> predicate = uri -> uri.getHost().contains("the-internet.herokuapp.com");
        //((HasAuthentication)driver).register(predicate, UsernameAndPassword.of(username, password));

        //Note: http://Username:Password@SiteURL
        driver.get("http://"+username+":"+password+"@"+url);
        WebElement basiAuthLink = driver.findElement(By.xpath("//a[text()='Basic Auth']"));
        basiAuthLink.click();



    }

}
