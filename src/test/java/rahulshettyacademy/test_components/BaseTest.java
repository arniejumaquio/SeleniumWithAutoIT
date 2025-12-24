package rahulshettyacademy.test_components;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class BaseTest {

    protected  WebDriver driver;


    /**
     * Initializes WebDriver before each test with browser configuration from GlobalParam.properties.
     * Supports Chrome, Firefox, Safari, and Edge with optional headless mode.
     * Browser selection: System property (-DbrowserName) overrides properties file.
     */
    @BeforeMethod
    public void setup(){

        String downloadPath = System.getProperty("user.dir")+"/src/main/resources/downloads";
        Map<String, Object> preferences = new HashMap<String, Object>();


        FileInputStream fileInputStream = null;
        try {
           fileInputStream = new FileInputStream("src/main/resources/GlobalParam.properties");
        }catch (FileNotFoundException f){
            System.out.println("Properties file not found.Please double check file path specified.");
        }

        Properties properties = new Properties();

        try {
            properties.load(fileInputStream);
        }catch (IOException i){
            System.out.println("Error while reading the properties file inside the fileinputstream.Please check");
        }

        String browserName = System.getProperty("browserName")!=null ?   System.getProperty("browserName") :properties.getProperty("browserName");

        if(browserName.contains("chrome")){

            ChromeOptions chromeOptions = new ChromeOptions();
            preferences.put("download.default_directory", downloadPath);
            chromeOptions.setExperimentalOption("prefs", preferences);

            if(browserName.contains("headless")){
                chromeOptions.addArguments("headless");
            }

            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(chromeOptions);

        }else if(browserName.contains("firefox")){

            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addPreference("browser.download.dir", downloadPath);

            if(browserName.contains("headless")){
                firefoxOptions.addArguments("--headless");
            }

            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver(firefoxOptions);

        }else if(browserName.contains("safari")){


            driver = new SafariDriver();

        }else if(browserName.contains("edge")){

            EdgeOptions edgeOptions = new EdgeOptions();
            preferences.put("download.default_directory", downloadPath);
            edgeOptions.setExperimentalOption("prefs", preferences);

            if(browserName.contains("headless")){
                edgeOptions.addArguments("headless");
            }

            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver(edgeOptions);

        }else {
            System.out.println("Invalid browser name.");
        }


        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));




    }



    /**
     * Closes browser and terminates WebDriver session after each test.
     */
    @AfterMethod
    public void teardown(){

        driver.quit();

    }


}
