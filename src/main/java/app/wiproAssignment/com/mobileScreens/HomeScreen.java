package app.wiproAssignment.com.mobileScreens;

import app.wiproAssignment.com.BaseScreen;
import app.wiproAssignment.com.Enums.WaitLogic;
import app.wiproAssignment.com.constants.Constants;
import app.wiproAssignment.com.driver.DriverManager;
import io.appium.java_client.AppiumBy;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomeScreen extends BaseScreen {

    public HomeScreen(){
        androidXpath.put("Menu","Open navigation menu");
        androidXpath.put("Cart","//android.widget.Button[@index='2']");
        androidXpath.put("ListOfMenu","//android.view.View[@content-desc='Shop by Category']//following-sibling::android.widget.ImageView");
        androidXpath.put("LogoutBtn","//android.widget.Button[@content-desc='Logout']");

        iosXpath.put("Menu","Open navigation menu");
        iosXpath.put("Cart","(//XCUIElementTypeButton[@traits='Button'])[2]");
        iosXpath.put("ListOfMenu","//XCUIElementTypeOther/following-sibling::XCUIElementTypeImage");
        iosXpath.put("LogoutBtn","//XCUIElementTypeButton[@name='Logout']");

    }

    public void validateMenuVisible(){
           Assert.assertTrue(waitUntilElementIsVisible(AppiumBy.accessibilityId((getLocator("Menu")))),"Menu is not visible");
    }
    public void validateCartVisible(){
        Assert.assertTrue(waitUntilElementIsVisible(By.xpath(getLocator("Cart"))),"Cart is not visible");
    }

    public void getListOfMenus(){
        if(isVisible(AppiumBy.accessibilityId(getLocator("Menu")),"Menu",Constants.getExplicitWait())){
            List<WebElement> menus= DriverManager.getDriver().findElements(By.xpath(getLocator("ListOfMenu")));
            Assert.assertTrue(menus.size()>1);
            tap(menus.getFirst(),"Category");
        }

    }
    public void validateUserAbleToLogOut(){
        tap(AppiumBy.accessibilityId(getLocator("Menu")), WaitLogic.CLICKABLE, "Menu");
        if(!isVisible(By.xpath(getLocator("LogoutBtn")),"LogoutBtn")){
            scrollTo(By.xpath(getLocator("LogoutBtn")),"LogoutBtn");
        }
        tap(By.xpath(getLocator("LogoutBtn")),WaitLogic.CLICKABLE,"logoutBtn");
        Assert.assertFalse(isVisible(AppiumBy.accessibilityId(getLocator("Menu")),"Menu"),"Menu Btb is visible");
    }
}
