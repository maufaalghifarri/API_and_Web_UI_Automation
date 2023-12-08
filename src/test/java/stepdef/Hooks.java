package stepdef;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Hooks extends BaseTest{
    @Before("@web")
    public void beforeTest() {
        getDriver();
    }

    @After("@web")
    public void afterTest() {
        driver.quit();
    }
    }
