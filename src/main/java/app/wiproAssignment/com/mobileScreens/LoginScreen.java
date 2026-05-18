package app.wiproAssignment.com.mobileScreens;

import app.wiproAssignment.com.BaseScreen;
import app.wiproAssignment.com.Enums.WaitLogic;
import app.wiproAssignment.com.constants.Constants;
import app.wiproAssignment.com.driver.DriverManager;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.testng.Assert;
import org.openqa.selenium.By;

public class LoginScreen extends BaseScreen {

    public LoginScreen(){
        androidXpath.put("Username", "//android.widget.EditText[contains(@hint,'Username')]");

        androidXpath.put("Password", "//android.widget.EditText[contains(@hint,'Password')]");
        androidXpath.put("LoginBtn","Login");
        androidXpath.put("ErrorMessage","//android.view.View[contains(@content-desc,'Invalid')]");
        androidXpath.put("SignIn","Sign in to shop the latest styles");

        iosXpath.put("Username","//XCUIElementTypeTextField[@name='Username']");
        iosXpath.put("Password", "//*[@name='Password']");
        iosXpath.put("LoginBtn","Login");
        iosXpath.put("ErrorMessage","//XCUIElementTypeStaticText[contains(@value,'Invalid')]");
        iosXpath.put("SignIn","Sign in to shop the latest styles");


    }


    public void tapConfirmLoginButton() {
        tap(AppiumBy.accessibilityId(getLocator("LoginBtn")), WaitLogic.CLICKABLE, "Login button");
    }

    public void fillAllLoginInformation(String email, String password) {

        if(waitUntilElementIsVisible(AppiumBy.accessibilityId(getLocator("SignIn")))){
            sendKeys(By.xpath(getLocator("Username")), email, WaitLogic.VISIBLE, "Email");
            sendKeys(By.xpath(getLocator("Password")), password, WaitLogic.VISIBLE, "Password");
        }

    }

    public void validateLoginErrorMessageIsVisible() {
        Assert.assertTrue((isVisible(By.xpath(getLocator("ErrorMessage")), "Error Message")),"error message is not visible");
    }




}
