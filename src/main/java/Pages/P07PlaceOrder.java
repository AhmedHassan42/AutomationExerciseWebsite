package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P07PlaceOrder {
    private final WebDriver driver;
    private final By OrderConfirmed= By.xpath("//h2[@data-qa]");


    public P07PlaceOrder(WebDriver driver) {
        this.driver=driver;
    }

    public boolean CheckIfOrderPlacedIsDisplayed()
    {
        return driver.findElement(OrderConfirmed).isDisplayed();
    }
}
