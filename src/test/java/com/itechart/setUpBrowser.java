package com.itechart;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;

public class setUpBrowser {

    protected WebDriver driver;
    private static setUpBrowser instance;


    public static  setUpBrowser getInstance() {
        if (instance == null) instance = new setUpBrowser();
        return instance;
    }

    public WebDriver SetUp() {
        driver = WebDriverManager.getInstance(ConfigLoader.getProperty("browser")).create();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.navigate().to(ConfigLoader.getProperty("websiteURL"));
        return driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void teardown(){
        try {
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
