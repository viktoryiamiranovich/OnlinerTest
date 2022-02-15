package com.itechart;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;


public class PageLoad {

    private WebDriverWait wait;
    protected WebDriver driver;
    protected WebElement uniqueElement;
    protected boolean correctPage;


    public PageLoad(WebDriver driver, String pathForVerify) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(ConfigLoader.getProperty("durationOfSeconds"))));
        waitForPageToLoad();
        verifyOpenedPage(pathForVerify);
    }

    private void verifyOpenedPage(String pathToUniqueElement) {
        uniqueElement = driver.findElement(By.xpath(String.format(pathToUniqueElement)));
        correctPage = uniqueElement.isDisplayed();
        Assert.assertTrue(correctPage, "Incorrect page");
    }

    protected void waitForElementToBecomeVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void waitForElementToBecomeClickable(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void scrollToElement(WebElement obj) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", obj);
    }

    protected void waitForPageToLoad() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(ConfigLoader.getProperty("pageLoadTimeout").toString())));
        try {
            wait.until((ExpectedCondition<Boolean>) d -> {
                if (!(d instanceof JavascriptExecutor)) {
                    return true;
                }
                Object result = ((JavascriptExecutor) d)
                        .executeScript("return document['readyState'] ? 'complete' == document.readyState : true");
                return result instanceof Boolean && (Boolean) result;
            });
        } catch (Exception e) {
            System.out.println("Browser.page.timeout");
        }
    }
}
