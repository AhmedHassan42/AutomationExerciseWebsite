package Tests;

import Pages.P01HomePage;
import Pages.P02ProductPage;
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

public class TC02ProductPage {
    @BeforeMethod
    public void SetupWebsite() throws IOException {
        SetupDriver(getPropertyValue("environment","browser"));
        getDriver().get(getPropertyValue("environment","Home_URL"));
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    }
    @Owner("Ahmed Hassan")
    @Description("Verify that selected product is available in-stock")
    @Test
    public void CheckIftheProductisInStock() throws IOException {
        new P01HomePage(getDriver()).ClickOnViewProduct();
        Assert.assertTrue(new P02ProductPage(getDriver()).CheckIfProductIsAvailable());
    }
    @Owner("Ahmed Hassan")
    @Description("Verify that selected product is added to cart successfully")
    @Test
    public void AddProductToCart() throws IOException {
        //todo: Select Product
        new P01HomePage(getDriver()).ClickOnViewProduct();
        Assert.assertTrue(new P02ProductPage(getDriver()).CheckIfProductIsAvailable());
        new P02ProductPage(getDriver()).ClickOnAddToCart();
        Assert.assertTrue(new P02ProductPage(getDriver()).CheckProductIsAdded());
    }
    @Owner("Ahmed Hassan")
    @Description("Verify that user is redirected to cart successfully")
    @Test
    public void ClickOnViewCart() throws IOException {
        //todo: Select Product
        new P01HomePage(getDriver()).ClickOnViewProduct();
        Assert.assertTrue(new P02ProductPage(getDriver()).CheckIfProductIsAvailable());
        new P02ProductPage(getDriver()).ClickOnAddToCart();
        Assert.assertTrue(new P02ProductPage(getDriver()).CheckProductIsAdded());
        new P02ProductPage(getDriver()).ClickOnViewCart();
        Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","ViewCart_URL"));
    }



    @AfterMethod
    public void quite(){
        quitDriver();

    }
}
