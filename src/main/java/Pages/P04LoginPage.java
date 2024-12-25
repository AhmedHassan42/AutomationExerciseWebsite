package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P04LoginPage {
    private final WebDriver driver;
    private final By emailField= By.cssSelector("input[data-qa=login-email]");
    private final By passwordField= By.cssSelector("input[data-qa=login-password]");
    private final By LoginButton= By.cssSelector("button[data-qa=login-button]");
    private final By LogoutButton= By.xpath("//div[@class='col-sm-8']//li//a[@href='/logout']");


    public P04LoginPage(WebDriver driver) {
        this.driver=driver;
    }
    public P04LoginPage AddLoginData(String email, String password){
        Utility.SendData(driver,emailField, email);
        Utility.SendData(driver,passwordField, password);
        return this;
    }
    public P05CheckoutPage ClickOnLogin(){
        Utility.clickOnElement(driver,LoginButton);
        return new P05CheckoutPage(driver);
    }
    public boolean CheckThatLoginSuccessful(){
        return driver.findElement(LogoutButton).isDisplayed();
    }


}
