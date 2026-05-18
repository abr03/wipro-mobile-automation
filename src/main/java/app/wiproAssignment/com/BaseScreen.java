package app.wiproAssignment.com;

import app.wiproAssignment.com.Enums.WaitLogic;
import app.wiproAssignment.com.constants.Constants;
import app.wiproAssignment.com.driver.DriverManager;
import app.wiproAssignment.com.factories.ExplicitWaitFactory;
import com.google.common.collect.ImmutableList;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;

import static app.wiproAssignment.com.factories.ExplicitWaitFactory.waitExplicitlyForElement;

public class BaseScreen {


        protected HashMap<String, String> androidXpath = new HashMap<>();
        protected HashMap<String, String> iosXpath = new HashMap<>();

        public void tap(By by, WaitLogic waitStrategy, String elementName) {
            WebElement element = waitExplicitlyForElement(waitStrategy, by);
            element.click();
            System.out.println(elementName + " is clicked");

        }
        public void tap(WebElement webElement, String elementName) {
        webElement.click();
        System.out.println(elementName + " is clicked");

        }

        public String getTexts(By by, WaitLogic waitStrategy) {
            WebElement element = waitExplicitlyForElement(waitStrategy, by);
            return element.getText();
        }
        public String getATT(By by, WaitLogic waitStrategy, String attr) {
        WebElement element = waitExplicitlyForElement(waitStrategy, by);
        return element.getAttribute(attr);
         }

    public void sendKeys(By by, CharSequence value, WaitLogic waitStrategy, String elementName) {
        WebElement element = waitExplicitlyForElement(waitStrategy, by);
        element.click();
        element = DriverManager.getDriver().findElement(by);
        element.clear();
        element.sendKeys(value);
        System.out.println(value + " is entered successfully in " + elementName + " input field");
    }

    public static boolean isVisible(By by, String elementName) {
        try {
            WebElement element = DriverManager.getDriver().findElement(by);
            if (element.isDisplayed()) {
                System.out.println(elementName + " is visible");
                return true;
            }
            System.out.println(elementName + " is present but not visible");
            return false;
        } catch (Exception e) {
            System.out.println(elementName + " is not present");
            return false;
        }
    }

    public boolean isVisible(By by, String elementName, int timeout) {
        try {
            waitExplicitlyForElement(WaitLogic.VISIBLE, by, timeout);
            return isVisible(by, elementName);
        } catch (Exception e) {
            System.out.println(elementName + " is not visible");
            return false;
        }
    }

    public static void scrollTo(By by, String elementName) {
        scrollTo(by, elementName, "down");
    }

    public static void scrollTo(By by, String elementName, String scrollDirection) {

        int maxScrollAttempts = 15;
        int currentAttempt = 0;
        while (!isVisible(by, elementName) &&  currentAttempt < maxScrollAttempts) {
            currentAttempt++;
            Dimension size = DriverManager.getDriver().manage().window().getSize();
            Point midPoint = new Point((int) (size.width * 0.5), (int) (size.height * 0.5));
            int bottom = midPoint.y + (int) (midPoint.y * 0.2);
            int top = midPoint.y - (int) (midPoint.y * 0.2);

            Point start = null;
            Point end = null;

            if(scrollDirection.equalsIgnoreCase("down")) {
                start = new Point(midPoint.x, bottom);
                end = new Point(midPoint.x, top);
            }  else {
                start = new Point(midPoint.x, top);
                end = new Point(midPoint.x, bottom);
            }

            PointerInput input = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
            Sequence swipe = new Sequence(input, 0);
            swipe.addAction(input.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), start.x, start.y));
            swipe.addAction(input.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            swipe.addAction(input.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), end.x, end.y));
            swipe.addAction(input.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
            DriverManager.getDriver().perform(ImmutableList.of(swipe));

            WaitForMiliSec(100);
        }
        if (!isVisible(by, elementName)) {
            System.out.println(elementName + " not found after " + maxScrollAttempts + " attempts.");

        }
    }

    public static void WaitForMiliSec(int timeout) {
        try {
            Thread.sleep(timeout);
        } catch (Exception e) {
        }
    }
    public boolean waitUntilElementIsVisible(By locator) {
        boolean flag = false;
        for(int i = 1; i <= Constants.getExplicitWait(); i++)
        {
            try {
                if(DriverManager.getDriver().findElement(locator).isDisplayed() || DriverManager.getDriver().findElement(locator).isDisplayed())
                {
                    flag = true;
                    System.out.println(" was not able to find element with " + locator);

                    break;
                }
            }
            catch(Exception e) {
                WaitForMiliSec(1000);
                System.out.println("Trial " + i + " was not able to find element with " + locator);
            }
        }
        return flag;
    }

    public void clearField(By by, WaitLogic waitStrategy, String elementName) {
        WebElement element = waitExplicitlyForElement(waitStrategy, by);
        element.clear();
    }

    public String getLocator(String name) {
        if (DriverManager.getDriver().getClass().equals(AndroidDriver.class)) {
            return androidXpath.get(name);
        }

        return iosXpath.get(name);
    }

}
