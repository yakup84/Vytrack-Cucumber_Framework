package Step_Definitions;

import Utilities.DBUtils;
import Utilities.Driver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.concurrent.TimeUnit;

public class Hooks {

    @Before
    public void setUp(){
        System.out.println("\tthis is coming from BEFORE");
        Driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Driver.getDriver().manage().window().maximize();

    }

    @After
    public void tearDown(Scenario scenario){
        if(scenario.isFailed()){
            final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot,"image/png","screenshot");
        }

      Driver.closeDriver();

    }

    @Before("@db")
    public void setUpDB(){
        System.out.println("Connecting to database...");
        DBUtils.createConnection();
    }

    @After("@db")
    public void tearDownDB(){
        System.out.println("close database connection...");
        DBUtils.destroy();
    }




}
