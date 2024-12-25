package Tests;

import Pages.P01HomePage;
import Pages.P02ProductPage;
import Pages.P03ViewCartPage;
import io.qameta.allure.Owner;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.*;
import static Utilities.DataUtil.getPropertyValue;

public class TC03ViewCartPage {
    @BeforeMethod
    public void SetupWebsite() throws IOException {
        SetupDriver(getPropertyValue("environment","browser"));
        getDriver().get(getPropertyValue("environment","Home_URL"));
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    }
    @Owner("Ahmed Hassan")
    @Description("Verify that user is able to delete product in cart")
    @Test
    public void VerifyThatProductsDeleteButtonInCart() throws IOException {
        //todo: Select Product
        new P01HomePage(getDriver()).ClickOnViewProduct();
        Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","Product_URL"));
        Assert.assertTrue(new P02ProductPage(getDriver()).CheckIfProductIsAvailable());
        //todo: Add product to cart and redirected to cart
        new P02ProductPage(getDriver()).ClickOnAddToCart();
        Assert.assertTrue(new P02ProductPage(getDriver()).CheckProductIsAdded());
        new P02ProductPage(getDriver()).ClickOnViewCart();
        Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","ViewCart_URL"));
        Assert.assertTrue(new P03ViewCartPage(getDriver()).CheckProductsIsDeleted());
    }
    @Owner("Ahmed Hassan")
    @Description("Verify that selected product is same product in cart")
    @Test
    public void VerifyThatProductIsCorrectInCart() throws IOException {
        //todo: Select Product
        new P01HomePage(getDriver()).ClickOnViewProduct();
        Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","Product_URL"));
        Assert.assertTrue(new P02ProductPage(getDriver()).CheckIfProductIsAvailable());
        //todo: Add product to cart and redirected to cart
        String ProductName= new P02ProductPage(getDriver()).ProductName();
        new P02ProductPage(getDriver()).ClickOnAddToCart();
        Assert.assertTrue(new P02ProductPage(getDriver()).CheckProductIsAdded());
        new P02ProductPage(getDriver()).ClickOnViewCart();
        Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","ViewCart_URL"),"The user is redirected to cart page successfully");
        Assert.assertEquals(ProductName,new P03ViewCartPage(getDriver()).ProductNameOnCart());
    }
    @Owner("Ahmed Hassan")
    @Description("Verify that selected product price is the same price in cart")
    @Test
    public void VerifyThatProductPriceIsCorrectInCart() throws IOException {
        //todo: Select Product
        new P01HomePage(getDriver()).ClickOnViewProduct();
        Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","Product_URL"));
        Assert.assertTrue(new P02ProductPage(getDriver()).CheckIfProductIsAvailable());
        //todo: Add product to cart and redirected to cart
        String ProductPrice= new P02ProductPage(getDriver()).ProductPrice();
        new P02ProductPage(getDriver()).ClickOnAddToCart();
        Assert.assertTrue(new P02ProductPage(getDriver()).CheckProductIsAdded());
        new P02ProductPage(getDriver()).ClickOnViewCart();
        Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","ViewCart_URL"),"The user is redirected to cart page successfully");
        Assert.assertEquals(ProductPrice,new P03ViewCartPage(getDriver()).ProductPriceInCart());
    }
    @Owner("Ahmed Hassan")
    @Description("Verify that user is able to change quantity of products in cart")
    @Test
    public void VerifyThatProductQuantityBoxIsEnabled() throws IOException {
        //todo: Select Product
        new P01HomePage(getDriver()).ClickOnViewProduct();
        Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","Product_URL"));
        Assert.assertTrue(new P02ProductPage(getDriver()).CheckIfProductIsAvailable());
        //todo: Add product to cart and redirected to cart
        new P02ProductPage(getDriver()).ClickOnAddToCart();
        Assert.assertTrue(new P02ProductPage(getDriver()).CheckProductIsAdded());
        new P02ProductPage(getDriver()).ClickOnViewCart();
        Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","ViewCart_URL"));
        Assert.assertNotEquals(new P03ViewCartPage(getDriver()).CartQuatityIsEnabled(),"disabled");
    }
    @Owner("Ahmed Hassan")
    @Description("Verify that the calculated total price in cart is correct")
    @Test
    public void VerifyThatCalculateTotalPriceInCart() throws IOException {
        //todo: Select Product
        new P01HomePage(getDriver()).ClickOnViewProduct();
        Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","Product_URL"));
        Assert.assertTrue(new P02ProductPage(getDriver()).CheckIfProductIsAvailable());
        //todo: Add product to cart and redirected to cart
        new P02ProductPage(getDriver()).ClickOnAddToCart();
        Assert.assertTrue(new P02ProductPage(getDriver()).CheckProductIsAdded());
        new P02ProductPage(getDriver()).ClickOnViewCart();
        Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","ViewCart_URL"));
        Assert.assertTrue(new P03ViewCartPage(getDriver()).CalculateTotalPriceIncart());
    }
    @Owner("Ahmed Hassan")
    @Description("Verify that Unlogin user is unable to redirected to checkout page")
    @Test
    public void VerifyThatUnLoginUserCannotRedirectedToCheckout() throws IOException {
        //todo: Select Product
        new P01HomePage(getDriver()).ClickOnViewProduct();
        Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","Product_URL"));
        Assert.assertTrue(new P02ProductPage(getDriver()).CheckIfProductIsAvailable());
        //todo: Add product to cart and redirected to cart
        new P02ProductPage(getDriver()).ClickOnAddToCart();
        Assert.assertTrue(new P02ProductPage(getDriver()).CheckProductIsAdded());
        new P02ProductPage(getDriver()).ClickOnViewCart();
        Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","ViewCart_URL"));
        new P03ViewCartPage(getDriver()).ClickOnCheckoutButton();
        Assert.assertTrue(new P03ViewCartPage(getDriver()).UnLoginUserCannotProceedToCheckout());
    }
    @Owner("Ahmed Hassan")
    @Description("Verify that system redirected unlogin users to login page successfully")
    @Test
    public void VerifyThatUserRedirectedToLoginPage() throws IOException {
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
    }



    @AfterMethod
    public void quite(){
        quitDriver();
    }
}
