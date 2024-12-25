package Tests;

import Pages.*;
import io.qameta.allure.Owner;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.*;
import static Utilities.DataUtil.getJsonData;
import static Utilities.DataUtil.getPropertyValue;

public class TC05CheckoutPage {
    @BeforeMethod
    public void SetupWebsite() throws IOException {
        SetupDriver(getPropertyValue("environment","browser"));
        getDriver().get(getPropertyValue("environment","Home_URL"));
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    }
    @Owner("Ahmed Hassan")
    @Description("Verify that delivery address is displayed correctly")
    @Test
    public void VerifyThatAddressDeliveryIsDisplayed() throws IOException {
        //todo: Select Product
        new P01HomePage(getDriver()).ClickOnViewProduct();
        Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","Product_URL"));
        Assert.assertTrue(new P02ProductPage(getDriver()).CheckIfProductIsAvailable());
        //todo: Add product to cart and redirected to cart
        new P02ProductPage(getDriver()).ClickOnAddToCart();
        Assert.assertTrue(new P02ProductPage(getDriver()).CheckProductIsAdded());
        new P02ProductPage(getDriver()).ClickOnViewCart();
        //todo: Redirected to Login Page
        Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","ViewCart_URL"));
        new P03ViewCartPage(getDriver()).ClickOnCheckoutButton().ClickOnLogin();
        Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","Login_URL"));
        //todo: Add Valid Login credentials
        new P04LoginPage(getDriver()).AddLoginData(getJsonData("ValidLoginData","email")
                ,getJsonData("ValidLoginData","password")).ClickOnLogin();
        Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","Home_URL"));
        Assert.assertTrue(new P04LoginPage(getDriver()).CheckThatLoginSuccessful());
        //todo: Proceed to Checkout
        new P05CheckoutPage(getDriver()).ProceedToCheckout();
        Assert.assertTrue(new P05CheckoutPage(getDriver()).CheckThatAddressDeliveryIsDisplayed());
    }
    @Owner("Ahmed Hassan")
    @Description("Verify that billing address is displayed correctly")
    @Test
    public void VerifyThatBillingAddressIsDisplayed() throws IOException {
        //todo: Select Product
        new P01HomePage(getDriver()).ClickOnViewProduct();
        Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","Product_URL"));
        Assert.assertTrue(new P02ProductPage(getDriver()).CheckIfProductIsAvailable());
        //todo: Add product to cart and redirected to cart
        new P02ProductPage(getDriver()).ClickOnAddToCart();
        Assert.assertTrue(new P02ProductPage(getDriver()).CheckProductIsAdded());
        new P02ProductPage(getDriver()).ClickOnViewCart();
        //todo: Redirected to Login Page
        Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","ViewCart_URL"));
        new P03ViewCartPage(getDriver()).ClickOnCheckoutButton().ClickOnLogin();
        Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","Login_URL"));
        //todo: Add Valid Login credentials
        new P04LoginPage(getDriver()).AddLoginData(getJsonData("ValidLoginData","email")
                ,getJsonData("ValidLoginData","password")).ClickOnLogin();
        Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","Home_URL"));
        Assert.assertTrue(new P04LoginPage(getDriver()).CheckThatLoginSuccessful());
        //todo: Proceed to Checkout
        new P05CheckoutPage(getDriver()).ProceedToCheckout();
        Assert.assertTrue(new P05CheckoutPage(getDriver()).CheckThatBillingAddressIsDisplayed());
    }
    @Owner("Ahmed Hassan")
    @Description("Verify that selected product is correct in checkout page")
    @Test
    public void VerifyThatProductNameIsCorrectInCheckout() throws IOException {
        //todo: Select Product
        new P01HomePage(getDriver()).ClickOnViewProduct();
        Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","Product_URL"));
        Assert.assertTrue(new P02ProductPage(getDriver()).CheckIfProductIsAvailable());
        String ProductName= new P02ProductPage(getDriver()).ProductName();
        //todo: Add product to cart and redirected to cart
        new P02ProductPage(getDriver()).ClickOnAddToCart();
        Assert.assertTrue(new P02ProductPage(getDriver()).CheckProductIsAdded());
        new P02ProductPage(getDriver()).ClickOnViewCart();
        //todo: Redirected to Login Page
        Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","ViewCart_URL"));
        new P03ViewCartPage(getDriver()).ClickOnCheckoutButton().ClickOnLogin();
        //todo: Add Valid Login credentials
        Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","Login_URL"));
        new P04LoginPage(getDriver()).AddLoginData(getJsonData("ValidLoginData","email")
                ,getJsonData("ValidLoginData","password")).ClickOnLogin();
        Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","Home_URL"));
        Assert.assertTrue(new P04LoginPage(getDriver()).CheckThatLoginSuccessful());
        //todo: Proceed to Checkout
        new P05CheckoutPage(getDriver()).ProceedToCheckout();
        Assert.assertEquals(ProductName,new P05CheckoutPage(getDriver()).ProductNameOnCheckout());
    }
    @Owner("Ahmed Hassan")
    @Description("Verify that the total price in checkout is calculated correctly")
    @Test
    public void VerifyThatProductPriceIsCalculatedCorrectly() throws IOException {
        //todo: Select Product
        new P01HomePage(getDriver()).ClickOnViewProduct();
        Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","Product_URL"));
        Assert.assertTrue(new P02ProductPage(getDriver()).CheckIfProductIsAvailable());
        String ProductName= new P02ProductPage(getDriver()).ProductName();
        //todo: Add product to cart and redirected to cart
        new P02ProductPage(getDriver()).ClickOnAddToCart();
        Assert.assertTrue(new P02ProductPage(getDriver()).CheckProductIsAdded());
        new P02ProductPage(getDriver()).ClickOnViewCart();
        //todo: Redirected to Login Page
        Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","ViewCart_URL"));
        new P03ViewCartPage(getDriver()).ClickOnCheckoutButton().ClickOnLogin();
        //todo: Add Valid Login credentials
        Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","Login_URL"));
        new P04LoginPage(getDriver()).AddLoginData(getJsonData("ValidLoginData","email")
                ,getJsonData("ValidLoginData","password")).ClickOnLogin();
        Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","Home_URL"));
        Assert.assertTrue(new P04LoginPage(getDriver()).CheckThatLoginSuccessful());
        //todo: Proceed to Checkout
        new P05CheckoutPage(getDriver()).ProceedToCheckout();
        Assert.assertEquals(ProductName,new P05CheckoutPage(getDriver()).ProductNameOnCheckout());
        Assert.assertTrue(new P05CheckoutPage(getDriver()).CalculateTotalPriceInCheckout());
    }
    @Owner("Ahmed Hassan")
    @Description("Verify that user is able to add comment successfully")
    @Test
    public void VerifyThatUserCanAddComment() throws IOException {
        //todo: Select Product
        new P01HomePage(getDriver()).ClickOnViewProduct();
        Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","Product_URL"));
        Assert.assertTrue(new P02ProductPage(getDriver()).CheckIfProductIsAvailable());
        String ProductName= new P02ProductPage(getDriver()).ProductName();
        //todo: Add product to cart and redirected to cart
        new P02ProductPage(getDriver()).ClickOnAddToCart();
        Assert.assertTrue(new P02ProductPage(getDriver()).CheckProductIsAdded());
        new P02ProductPage(getDriver()).ClickOnViewCart();
        //todo: Redirected to Login Page
        Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","ViewCart_URL"));
        new P03ViewCartPage(getDriver()).ClickOnCheckoutButton().ClickOnLogin();
        //todo: Add Valid Login credentials
        Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","Login_URL"));
        new P04LoginPage(getDriver()).AddLoginData(getJsonData("ValidLoginData","email")
                ,getJsonData("ValidLoginData","password")).ClickOnLogin();
        Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","Home_URL"));
        Assert.assertTrue(new P04LoginPage(getDriver()).CheckThatLoginSuccessful());
        //todo: Proceed to Checkout
        new P05CheckoutPage(getDriver()).ProceedToCheckout();
        Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","Checkout_URL"));
        Assert.assertEquals(ProductName,new P05CheckoutPage(getDriver()).ProductNameOnCheckout());
        Assert.assertTrue(new P05CheckoutPage(getDriver()).CalculateTotalPriceInCheckout());
        Assert.assertTrue(new P05CheckoutPage(getDriver()).CheckIfCommentFieldIsEnabled());
    }
    @Owner("Ahmed Hassan")
    @Description("Verify that the redirection to payment page is successful")
    @Test
    public void CheckthatuserRedirectedtoPayment() throws IOException {
        //todo: Select Product
        new P01HomePage(getDriver()).ClickOnViewProduct();
        Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","Product_URL"));
        Assert.assertTrue(new P02ProductPage(getDriver()).CheckIfProductIsAvailable());
        String ProductName= new P02ProductPage(getDriver()).ProductName();
        //todo: Add product to cart and redirected to cart
        new P02ProductPage(getDriver()).ClickOnAddToCart();
        Assert.assertTrue(new P02ProductPage(getDriver()).CheckProductIsAdded());
        new P02ProductPage(getDriver()).ClickOnViewCart();
        //todo: Redirected to Login Page
        Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","ViewCart_URL"));
        new P03ViewCartPage(getDriver()).ClickOnCheckoutButton().ClickOnLogin();
        //todo: Add Valid Login credentials
        Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","Login_URL"));
        new P04LoginPage(getDriver()).AddLoginData(getJsonData("ValidLoginData","email")
                ,getJsonData("ValidLoginData","password")).ClickOnLogin();
        Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","Home_URL"));
        Assert.assertTrue(new P04LoginPage(getDriver()).CheckThatLoginSuccessful());
        //todo: Proceed to Checkout
        new P05CheckoutPage(getDriver()).ProceedToCheckout();
        Assert.assertEquals(ProductName,new P05CheckoutPage(getDriver()).ProductNameOnCheckout());
        Assert.assertTrue(new P05CheckoutPage(getDriver()).CalculateTotalPriceInCheckout());
        Assert.assertTrue(new P05CheckoutPage(getDriver()).CheckIfCommentFieldIsEnabled());
        //todo: proceed to payment
        new P05CheckoutPage(getDriver()).ClickOnPlaceOrder();
        Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","Payment_URL"));
    }



    @AfterMethod
    public void quite(){
        quitDriver();
    }
}
