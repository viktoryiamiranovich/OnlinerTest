package com.itechart;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HomePage extends PageLoad{

    private static final String pathForVerify = "//header/h2/a[contains(text(), 'Каталог')]";
    public HomePage(WebDriver driver) {
        super(driver, pathForVerify);
    }

    private final String findCatalog = "//header/h2/a[contains(text(), '%s')]";
    public WebElement catalogLink (String text){
        waitForElementToBecomeClickable(By.xpath(String.format(findCatalog, text)));
        return driver.findElement(By.xpath(String.format(findCatalog,text)));
    }

    public void navigateToCatalog(String text){
        catalogLink(text).click();
    }

}
