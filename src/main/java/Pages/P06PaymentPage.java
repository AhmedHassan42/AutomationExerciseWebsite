package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P06PaymentPage {
    private final WebDriver driver;
    private final By CardHolder= By.xpath("//input[@name='name_on_card']");
    private final By CardNumber= By.xpath("//input[@name='card_number']");
    private final By Cvc= By.xpath("//input[@name='cvc']");
    private final By ExpiryMonth= By.xpath("//input[@name='expiry_month']");
    private final By ExpiryYear= By.xpath("//input[@name='expiry_year']");
    private final By SubmitButton= By.id("submit");


    public P06PaymentPage(WebDriver driver) {
        this.driver=driver;
    }
    public P06PaymentPage CardCredentials(String HolderText,String CardNO, String CVC, String MON, String Year){
        Utility.SendData(driver,CardHolder,HolderText);
        Utility.SendData(driver,CardNumber,CardNO);
        Utility.SendData(driver,Cvc,CVC);
        Utility.SendData(driver,ExpiryMonth,MON);
        Utility.SendData(driver,ExpiryYear,Year);
        return this;
    }
    public P07PlaceOrder ClickOnPay(){
        Utility.clickOnElement(driver,SubmitButton);
        return new P07PlaceOrder(driver);

    }
}
