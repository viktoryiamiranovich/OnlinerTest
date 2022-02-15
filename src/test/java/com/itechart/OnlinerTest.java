package com.itechart;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.List;


public class OnlinerTest {
    @BeforeSuite
    public void initBrowser(){
        setUpBrowser.getInstance().SetUp();
    }

    @AfterTest
    public  void closeBrowser(){
        setUpBrowser.getInstance().teardown();
    }

    @Parameters({"brand", "price", "screenResolution", "diagonalMin", "diagonalMax"})
    @BeforeTest
    public  void TestOnliner(String brand, String price, String screenResolution, String diagonalMin, String diagonalMax) throws CloneNotSupportedException, InterruptedException {
        HomePage tv = new HomePage(setUpBrowser.getInstance().getDriver());
        tv.navigateToCatalog("Каталог");
        CatalogPage catalog = new CatalogPage(setUpBrowser.getInstance().getDriver());
        catalog.navigateMenuItem("Электроника");
        catalog.navigateSubmenuItem("Телевидение и");
        catalog.navigateDropdownItem("Телевизоры");
        TVPage tvpage = new TVPage(tv.driver);
        tvpage.findByCheckBox(brand);
        tvpage.findByMaxPrice(price);
        tvpage.findByDiagonalMin(diagonalMin);
        tvpage.findByDiagonalMax(diagonalMax);
        tvpage.findByCheckBox(screenResolution);
    }

    @Parameters({"brand", "price", "screenResolution", "diagonalMin", "diagonalMax"})
    @Test
    public void Resultscheck (String brand, String price, String screenResolution, String diagonalMin, String diagonalMax) throws CloneNotSupportedException {
        TVPage tv = new TVPage(setUpBrowser.getInstance().getDriver());
        WebDriverWait wait = new WebDriverWait(tv.driver, 15);
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(tv.driver.findElement(By.xpath("//div[@class = 'schema-product__group']")))));
        List<WebElement> BrandandType = tv.driver.findElements(By.xpath("//div[@class='schema-product__title']"));
        for (int i = 0; i < BrandandType.size(); i++) {
            WebElement webElement = BrandandType.get(i);
            Assert.assertTrue(webElement.getText().contains(brand));
        }
        List<WebElement> BrandandPrice = tv.driver.findElements(By.xpath("//a[@class='schema-product__price-value schema-product__price-value_primary']/span"));
        for (int i = 0; i < BrandandPrice.size(); i++) {
            WebElement webElement = BrandandPrice.get(i);
            String resol = webElement.getText();
            double priceTV = Double.parseDouble(resol.replace(" р.", "").replace(',', '.'));
            Assert.assertTrue(priceTV <= Double.parseDouble(price));
        }

        List<WebElement> Screensize = tv.driver.findElements(By.className("schema-product__description"));
        for (int i = 0; i < Screensize.size(); i++) {
            WebElement webElement = Screensize.get(i);
            String diagonal = webElement.getText();
            double diagonalTV = Double.parseDouble(diagonal.substring(0, diagonal.indexOf("\"")));
            double min = Double.parseDouble(diagonalMin.substring(0, diagonalMin.length()-1));
            double max = Double.parseDouble(diagonalMax.substring(0, diagonalMin.length()-1));
            Assert.assertTrue(min <= diagonalTV && max >= diagonalTV);
        }


        List<WebElement> ResolutionandSize = tv.driver.findElements(By.className("schema-product__description"));
        for (int i = 0; i < ResolutionandSize.size(); i++) {
            WebElement webElement = ResolutionandSize.get(i);
            Assert.assertTrue(webElement.getText().contains(screenResolution));
        }
    }

}
