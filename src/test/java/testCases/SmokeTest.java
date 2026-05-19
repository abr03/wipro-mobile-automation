package testCases;

import app.wiproAssignment.com.BaseTest;
import app.wiproAssignment.com.DataSupplier;
import app.wiproAssignment.com.mobileScreens.HomeScreen;
import app.wiproAssignment.com.mobileScreens.LoginScreen;
import net.datafaker.Faker;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SmokeTest extends BaseTest {

    LoginScreen loginScreen= new LoginScreen();
    HomeScreen homeScreen= new HomeScreen();
    Faker faker= new Faker();



@Test(dataProvider = "loginData", dataProviderClass = DataSupplier.class,priority = 1,groups = {"Smoke"},
    description = "Verify user login")

    public void verifyUserIsAbleToLoginSuccessfully(String email,String password) {
        if (platform.equalsIgnoreCase("android")) {
            loginScreen.fillAllLoginInformation(email,password);
            loginScreen.tapConfirmLoginButton();
            homeScreen.validateMenuVisible();
        } else if (platform.equalsIgnoreCase("iOS")) {

            loginScreen.fillAllLoginInformation(email,password);
            loginScreen.tapConfirmLoginButton();
            homeScreen.validateMenuVisible();

        }
    }

    @Test(priority = 2,groups = {"Smoke"}, description = "Verify user is unable to login using invalid")
    public void verifyUserIsUnableToLoginUsingInvalidCred() {

       String email= faker.internet().emailAddress();
        String password= faker.internet().password();

        if (platform.equalsIgnoreCase("android")) {
            loginScreen.fillAllLoginInformation(email,password);
            loginScreen.tapConfirmLoginButton();
            loginScreen.validateLoginErrorMessageIsVisible();

        } else if (platform.equalsIgnoreCase("iOS")) {
            loginScreen.fillAllLoginInformation(email,password);
            loginScreen.tapConfirmLoginButton();
            loginScreen.validateLoginErrorMessageIsVisible();

        }
    }
    @Test(dataProvider ="loginData", dataProviderClass = DataSupplier.class, priority = 3,groups = {"Smoke"}, description = "Verify user is able to see HomeScreen")
    public void verifyUserAbleToSeeHomeScreenOnSuccessfulLogin(String email, String password) {
        if (platform.equalsIgnoreCase("android")) {
            loginScreen.fillAllLoginInformation(email,password);
            loginScreen.tapConfirmLoginButton();
            homeScreen.validateMenuVisible();
            homeScreen.validateCartVisible();

        } else if (platform.equalsIgnoreCase("iOS")) {
            loginScreen.fillAllLoginInformation(email,password);
            loginScreen.tapConfirmLoginButton();
            homeScreen.validateMenuVisible();
            homeScreen.validateCartVisible();
        }
    }
    @Test(dataProvider ="loginData", dataProviderClass = DataSupplier.class, priority = 4,groups = {"Smoke"}, description = "Verify user is able to Logout")
    public void verifyUserAbleToLogoutFromHomeScreen(String email, String password) {
        if (platform.equalsIgnoreCase("android")) {
            loginScreen.fillAllLoginInformation(email,password);
            loginScreen.tapConfirmLoginButton();
            homeScreen.validateUserAbleToLogOut();


        } else if (platform.equalsIgnoreCase("iOS")) {
            loginScreen.fillAllLoginInformation(email,password);
            loginScreen.tapConfirmLoginButton();
            homeScreen.validateUserAbleToLogOut();
        }
    }

}
