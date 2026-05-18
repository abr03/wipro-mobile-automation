package app.wiproAssignment.com.mobileScreens;

import app.wiproAssignment.com.BaseScreen;
import app.wiproAssignment.com.constants.Constants;
import app.wiproAssignment.com.driver.DriverManager;
import io.appium.java_client.ios.IOSDriver;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductScreen extends BaseScreen {

    public ProductScreen() {

        androidXpath.put("selectProduct", "//android.view.View//following-sibling::android.widget.ImageView");
        iosXpath.put("selectProduct", "(//XCUIElementTypeImage)[last()-2]");

    }

    public void validateUserAbleToSelectProduct() {

            if (isVisible(By.xpath(getLocator("selectProduct")), "Dress", Constants.getExplicitWait())) {
                List<WebElement> casualDresses = DriverManager.getDriver().findElements(By.xpath(getLocator("selectProduct")));
                Assert.assertTrue(casualDresses.getFirst().isEnabled(), "Dress is not visible");
                casualDresses.getFirst().click();
            }

    }
}
