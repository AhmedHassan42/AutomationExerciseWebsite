package Pages;

import Utilities.LogsUtil;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P01HomePage {
    private final WebDriver driver;
    private final By ToProductButton= By.xpath("//div[@class='col-sm-4'][1]//li/a");
    private final By ProductImg= By.xpath("(//div[@class='col-sm-4'][1]//img)[2]");

    public P01HomePage(WebDriver driver) {
        this.driver=driver;
    }

    public P02ProductPage ClickOnViewProduct(){
        Utility.clickOnElement(driver,ToProductButton);
        return new P02ProductPage(driver);

    }

    public boolean CheckiftheProductImgIsDisplayed(){
        boolean ImgIsDisplayed= driver.findElement(ProductImg).isDisplayed();
        LogsUtil.info("the product thumbnail is displayed: "+ ImgIsDisplayed );
        return ImgIsDisplayed;
    }


}
