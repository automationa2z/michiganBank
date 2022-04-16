package hooks;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class Apphooks{
    public static WebDriver driver;
    @Parameters("browser")
    @BeforeClass
    protected WebDriver setUp( @Optional("firefox") String browser) {


//        System.out.println("Before Method is running first before each test.....! ");
//        System.setProperty("webdriver.gecko.driver", "../michiganBank/src/main/resources/driver/geckodriver");
        if(browser.equals("firefox"))
            // System.out.println("Before Method is running first before each test.....! ");
            System.setProperty("webdriver.gecko.driver", "../michiganBank/src/main/resources/driver/geckodriver");
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            return driver;



    }

    @AfterClass
    public void tearDown() {
        //driver.close();
        //driver.quit();
    }
    public void pageWait() {
        try {
            driver.wait(5);

        } catch (InterruptedException e) {
            System.out.println("Throwing interrupted exception");
        }
    }
   // @AfterMethod
    public void take_screenShoot_close_window(){
        TakesScreenshot ts = (TakesScreenshot)driver;
        File file = ts.getScreenshotAs(OutputType.FILE);
        try{
            FileUtils.copyFile(file, new File("./src/test/screenShoots_folder"+timestamp()+".png"));
        } catch(IOException e){
            e.printStackTrace();
        }
        System.out.println("Screenshot...........taken");
        //driver.close();

    }
    public String timestamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
    }
    @AfterMethod

    public void take_screenShot_failed_tests(ITestResult result) throws Exception{
        // Here will compare if test is failing then only it will enter into the if condition
        if(ITestResult.FAILURE == result.getStatus()){
            try {
                // create reference for TakesScreenshot
                TakesScreenshot ts = (TakesScreenshot) driver;
                // call method to capture screenshot
                File source = ts.getScreenshotAs(OutputType.FILE);
                //copy the prouduce screenshot to a file in specific location
                FileHandler.copy(source,new File("src/test/screenShoots_folder/"+result.getName()+".png") );
                System.out.println("Screenshot taken for failed test");

            }
            catch(Exception e){
                System.out.println("Exception while taking screenshot "+ e.getMessage());
            }

        }
        driver.close();
    }


}






