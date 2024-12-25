package Pages;

import Utilities.LogsUtil;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P05CheckoutPage {
    private final By Cartbutton= By.xpath("//div[@class='col-sm-8']//li[3]");
    private final By CheckoutButton= By.cssSelector("#do_action .col-sm-6 a");
    private final By AddressDeliveryBody= By.id("address_delivery");
    private final By ProductpriceText= By.xpath("//td[@class='cart_price']//p");
    private final By TotalpriceText= By.xpath("(//p[@class='cart_total_price'])[1]");
    private final By CartQuantity= By.xpath("//td[@class='cart_quantity']//button");
    private final By BillingAddressBody= By.id("address_invoice");
    private final By ProductNameText= By.xpath("//td[@class='cart_description']//a");
    private final By Commentstext= By.className("form-control");
    private final By PlaceOrderButton= By.xpath("//*[text()='Place Order']");


    private final WebDriver driver;

    public P05CheckoutPage(WebDriver driver) {
        this.driver=driver;
    }


    public P05CheckoutPage ProceedToCheckout(){
        Utility.clickOnElement(driver,Cartbutton);
        Utility.clickOnElement(driver,CheckoutButton);

        return this;
    }

    public boolean CheckThatBillingAddressIsDisplayed(){
        return driver.findElement(BillingAddressBody).isDisplayed();
    }
    public boolean CheckThatAddressDeliveryIsDisplayed(){
        return driver.findElement(AddressDeliveryBody).isDisplayed();
    }
    public String ProductNameOnCheckout(){
        String ProductNameOnCheckout= Utility.getText(driver,ProductNameText);
        LogsUtil.info("Product name on Checkout is "+ProductNameOnCheckout);
        return ProductNameOnCheckout;
    }
    public boolean CalculateTotalPriceInCheckout(){
        String Price= Utility.getText(driver,ProductpriceText);
        String TotalPrice= Utility.getText(driver,TotalpriceText);
        String Quatity= Utility.getText(driver,CartQuantity);

        Float priceFloat= Float.parseFloat(Price.replace("Rs.",""));
        Float TotalpriceFloat= Float.parseFloat(TotalPrice.replace("Rs.",""));
        Float quantity = Float.valueOf(Quatity);
        LogsUtil.info("the product price in Checkout is "+priceFloat);
        LogsUtil.info("the product total price in Checkout is "+TotalpriceFloat);
        LogsUtil.info("the quantity in Checkout is "+quantity);
        return TotalpriceFloat.equals(quantity * priceFloat);

    }

    public boolean CheckIfCommentFieldIsEnabled(){
        Boolean IsEnabled=driver.findElement(Commentstext).isEnabled();
        Boolean IsDisplayed= driver.findElement(Commentstext).isDisplayed();
        LogsUtil.info("Message field is enabled and displayed");
        return IsEnabled && IsDisplayed;

    }
    public P06PaymentPage ClickOnPlaceOrder(){
        Utility.clickOnElement(driver,PlaceOrderButton);
        return new P06PaymentPage(driver);
    }
}
