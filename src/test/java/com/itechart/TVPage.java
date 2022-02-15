package com.itechart;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;



public class TVPage extends PageLoad {

    protected Select select;

    private static final String pathForVerify = "//div[@class = 'schema-header']//h1[contains(text(), 'Телевизоры')]";
    private final String waitWhenOpeningPage = "//div[@class = 'schema-filter__fieldset']";
    private  final String checkbox = "//li/label[@class='schema-filter__checkbox-item']/span[text()='%s']";
    private String diagonalMin = "//span[contains(text(), 'Диагональ')]/../following-sibling::div//select[contains(@data-bind, 'value: facet.value.from')]";
    private String diagonalMax = "//span[contains(text(), 'Диагональ')]/../following-sibling::div//select[contains(@data-bind, 'value: facet.value.to')]";
    private final String price = "//input[@class='schema-filter-control__item schema-filter__number-input schema-filter__number-input_price'][contains(@data-bind, 'value: facet.value.to')]";

    public TVPage(WebDriver driver) {
        super(driver, pathForVerify);
        waitForElementToBecomeVisible(By.xpath((waitWhenOpeningPage)));
    }

    private  WebElement filterByCheckBox(String text){ return driver.findElement(By.xpath(String.format(checkbox,text)));}
    private  WebElement filterMaxPrice(){ return driver.findElement(By.xpath(String.format(price)));}
    private  WebElement filterDiagonalMin(){ return driver.findElement(By.xpath(String.format(diagonalMin)));}
    private  WebElement filterDiagonalMax(){ return driver.findElement(By.xpath(String.format(diagonalMax)));}



    public void findByCheckBox(String text) {
        scrollToElement(filterByCheckBox(text));
        Actions action = new Actions(driver);
        action.moveToElement(filterByCheckBox(text)).click().build().perform();
    }

    public void findByMaxPrice(String text) {
        scrollToElement(filterMaxPrice());
        filterMaxPrice().sendKeys(text);
    }

    public void findByDiagonalMin(String text) {
        scrollToElement(filterDiagonalMin());
        select = new Select(filterDiagonalMin());
        select.selectByVisibleText(text);
    }

    public void findByDiagonalMax(String text) {
        scrollToElement(filterDiagonalMax());
        select = new Select(filterDiagonalMax());
        select.selectByVisibleText(text);
    }
}
