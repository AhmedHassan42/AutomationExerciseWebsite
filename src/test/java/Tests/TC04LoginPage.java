package Tests;

import Pages.P01HomePage;
import Pages.P02ProductPage;
import Pages.P03ViewCartPage;
import Pages.P04LoginPage;
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

public class TC04LoginPage {
    @BeforeMethod
    public void SetupWebsite() throws IOException {
        SetupDriver(getPropertyValue("environment","browser"));
        getDriver().get(getPropertyValue("environment","Home_URL"));
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    }
    @Owner("Ahmed Hassan")
    @Description("Verify that user can login successfully with valid data")
    @Test
    public void VerifyThatUserAddValidLoginData() throws IOException {
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
    }



    @AfterMethod
    public void quite(){
        quitDriver();
    }
}
