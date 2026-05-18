package app.wiproAssignment.com.mobileScreens;

import app.wiproAssignment.com.BaseScreen;
import app.wiproAssignment.com.Enums.WaitLogic;
import app.wiproAssignment.com.constants.Constants;
import app.wiproAssignment.com.driver.DriverManager;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.testng.Assert;
import org.openqa.selenium.By;

public class PDPScreen extends BaseScreen {

    public PDPScreen(){

        androidXpath.put("AddToCart","Add to Cart");
        androidXpath.put("Price", "//android.view.View[contains(@content-desc,'$')]");
        androidXpath.put("ViewCart", "VIEW CART");


        iosXpath.put("AddToCart","Add to Cart");
        iosXpath.put("Price", "//XCUIElementTypeStaticText[contains(@value,'$')]");
        iosXpath.put("ViewCart", "VIEW CART");


        //VIEW CART

    }

    public void validateUserAbleToAddProductToCart(){
        Assert.assertTrue( DriverManager.getDriver().findElement(AppiumBy.accessibilityId("Color")).isEnabled(),"color not visible");
        Assert.assertTrue(isVisible(AppiumBy.accessibilityId(getLocator("AddToCart")), "Add to cart Btn", Constants.getExplicitWait()), "Add to cart button is not visible");
        if(DriverManager.getDriver() instanceof IOSDriver){
            String actualPrice = getATT(By.xpath(getLocator("Price")), WaitLogic.VISIBLE, "name");
            Assert.assertFalse(actualPrice.isEmpty(), "Price is empty");
        }
        else {
            String actualPrice = getATT(By.xpath(getLocator("Price")), WaitLogic.VISIBLE, "content-desc");
            Assert.assertFalse(actualPrice.isEmpty(), "Price is empty");
        }

        tap(AppiumBy.accessibilityId(getLocator("AddToCart")),WaitLogic.CLICKABLE,"AddToCartBtn");
        Assert.assertTrue(isVisible(AppiumBy.accessibilityId(getLocator("ViewCart")),"View Cart"),"Product not added to the cart");
    }
}
