package rahulshettyacademy.utlities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class Screenshot {

    public static String takeScreenshot(WebDriver driver){

       File screenShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
       String screenShotPath  = null;

        try {

            screenShotPath = System.getProperty("user.dir")+"/src/main/resources/screenshots/"+ UUID.randomUUID().toString()+".jpg";
            FileUtils.copyFile(screenShot, new File(screenShotPath));

        } catch (IOException e) {

            System.out.println("IOException occur.Please check");
        }


       return  screenShotPath;

    }

}
