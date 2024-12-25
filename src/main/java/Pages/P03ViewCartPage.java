package Pages;

import Utilities.LogsUtil;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class P03ViewCartPage {
    private final WebDriver driver;
    private final By ProductNameText= By.xpath("//td[@class='cart_description']//a");
    private final By ProductpriceText= By.xpath("//td[@class='cart_price']//p");
    private final By TotalpriceText= By.xpath("//p[@class='cart_total_price']");
    private final By CartQuantity= By.xpath("//td[@class='cart_quantity']//button");
    private final By CartQuantityDeleted= By.className("cart_quantity_delete");
    private final By CartEmptyText= By.xpath("//b");
    private final By CheckoutButton= By.cssSelector("#do_action .col-sm-6 a");
    private final By LoginText= By.xpath("//div[@class='modal-body']//p[@class='text-center'][1]");
    private final By LoginButton= By.cssSelector("#do_action .text-center a");


    public P03ViewCartPage(WebDriver driver) {
        this.driver=driver;
    }
        public String CartQuatityIsEnabled(){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(CartQuantity));
        String classname= driver.findElement(CartQuantity).getAttribute("class");
        LogsUtil.info("Quantity Button is "+classname);
        return classname;
    }
    public boolean CheckProductsIsDeleted(){
        Utility.clickOnElement(driver,CartQuantityDeleted);
        new WebDriverWait(driver,Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(CartEmptyText));
        LogsUtil.info("Products are deleted Successfully");
        return driver.findElement(CartEmptyText).isDisplayed();
    }
    public String ProductNameOnCart(){
        String ProductNameOncart= Utility.getText(driver,ProductNameText);
        LogsUtil.info("Product name on cart is "+ProductNameOncart);
        return ProductNameOncart;
    }
    public String ProductPriceInCart(){
        String Price= Utility.getText(driver,ProductpriceText);
        Float priceFloat= Float.parseFloat(Price.replace("Rs.",""));
        LogsUtil.info("the product price in cart is "+priceFloat);
        return String.valueOf(priceFloat);

    }
    public P03ViewCartPage ClickOnCheckoutButton(){
        Utility.clickOnElement(driver,CheckoutButton);
        return this;
    }
    public P04LoginPage ClickOnLogin(){
        Utility.clickOnElement(driver,LoginButton);
        return new P04LoginPage(driver);

    }
    public boolean UnLoginUserCannotProceedToCheckout(){
        LogsUtil.info("Login to proceed to checkout page is displayed");
        return driver.findElement(LoginText).isDisplayed();
    }

    public boolean CalculateTotalPriceIncart(){
        String Price= Utility.getText(driver,ProductpriceText);
        String TotalPrice= Utility.getText(driver,TotalpriceText);
        String Quatity= Utility.getText(driver,CartQuantity);

        Float priceFloat= Float.parseFloat(Price.replace("Rs.",""));
        Float TotalpriceFloat= Float.parseFloat(TotalPrice.replace("Rs.",""));
        Float quantity = Float.valueOf(Quatity);
        LogsUtil.info("the product price in cart is "+priceFloat);
        LogsUtil.info("the product total price in cart is "+TotalpriceFloat);
        LogsUtil.info("the quantity in cart is "+quantity);
        return TotalpriceFloat.equals(quantity * priceFloat);

    }
}
