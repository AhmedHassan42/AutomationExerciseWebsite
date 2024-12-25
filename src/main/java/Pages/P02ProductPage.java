package Pages;

import Utilities.LogsUtil;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class P02ProductPage {
    private final WebDriver driver;
    private final By InStoctText= By.xpath("//*[text()=' In Stock']");
    private final By AddToCartButton= By.xpath("//button[@type='button']");
    private final By ProductAddedText= By.xpath("//*[text()='Your product has been added to cart.']");
    private final By ViewCartButton= By.xpath("//div[@class='modal-body']//a");
    private final By ProductName= By.xpath("//div[@class='product-information']//h2");
    private final By ProductPrice= By.xpath("(//div[@class='product-information']//span)[2]");

    public boolean CheckIfProductIsAvailable(){
        return driver.findElement(InStoctText).isDisplayed();
    }
    public boolean CheckProductIsAdded(){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(ProductAddedText));
        return driver.findElement(ProductAddedText).isDisplayed();
    }
    public P03ViewCartPage ClickOnViewCart(){
        Utility.clickOnElement(driver,ViewCartButton);
        return new P03ViewCartPage(driver);
    }
    public P02ProductPage ClickOnAddToCart(){
        Utility.clickOnElement(driver,AddToCartButton);
        return this;
    }

    public P02ProductPage(WebDriver driver) {
        this.driver=driver;
    }
    public String ProductName(){
        String productName = Utility.getText(driver,ProductName);
        LogsUtil.info("the product name is "+productName);
        return productName;
    }
    public String ProductPrice(){
        new WebDriverWait(driver,Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(ProductPrice));
        String Price= Utility.getText(driver,ProductPrice);
        Float priceFloat= Float.parseFloat(Price.replace("Rs.",""));
        LogsUtil.info("the product price is "+priceFloat);
        return String.valueOf(priceFloat);

    }
}
