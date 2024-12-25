package Tests;

import Pages.P01HomePage;
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

public class TC01HomePage {
    @BeforeMethod
    public void SetupWebsite() throws IOException {
        SetupDriver(getPropertyValue("environment","browser"));
        getDriver().get(getPropertyValue("environment","Home_URL"));
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    }
    @Owner("Ahmed Hassan")
    @Description("Verify that product thumbnail is displayed")
    @Test
    public void VerifyThatProductThumbnailIsDisplayed() throws IOException {
        Assert.assertTrue(new P01HomePage(getDriver()).CheckiftheProductImgIsDisplayed());
    }
    @Owner("Ahmed Hassan")
    @Description("Verify that user select product and redirected to product page successfully")
    @Test
    public void SelectProduct() throws IOException {
        //todo: Select Product
        new P01HomePage(getDriver()).ClickOnViewProduct();
        Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","Product_URL"));
    }



    @AfterMethod
    public void quite(){
        quitDriver();

    }
}
