package com.itechart;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CatalogPage extends PageLoad {

    private static final String pathForVerify = "//div[@class = 'catalog-navigation__title'][contains(text(), 'Каталог')]";
    private final String menuItem = "//span[@class = 'catalog-navigation-classifier__item-title-wrapper'][contains(text(), '%s')]";
    private final String submenuItem = "//div[@class='catalog-navigation-list__aside-title'][contains(text(), '%s')]";
    private final String dropdownItem = "//span[@class='catalog-navigation-list__dropdown-title'][contains(text(), '%s')]";

    public CatalogPage(WebDriver driver) {
        super(driver, pathForVerify);
    }

    private WebElement MenuItem(String menuItemName){
        waitForElementToBecomeClickable(By.xpath(String.format(menuItem, menuItemName)));
        return driver.findElement(By.xpath(String.format(menuItem, menuItemName)));
    }

    private WebElement SubmenuItem(String submenuItemName){
        waitForElementToBecomeClickable(By.xpath(String.format(submenuItem, submenuItemName)));
        return driver.findElement(By.xpath(String.format(submenuItem, submenuItemName)));
    }

    private WebElement DropdownItem(String dropdownItemName){
        waitForElementToBecomeClickable(By.xpath(String.format(dropdownItem, dropdownItemName)));
        return driver.findElement(By.xpath(String.format(dropdownItem, dropdownItemName)));
    }

    public void navigateMenuItem(String text){
        MenuItem(text).click();
    }

    public void navigateSubmenuItem(String text){
        SubmenuItem(text).click();
    }

    public void navigateDropdownItem(String text){
        DropdownItem(text).click();
    }
}
