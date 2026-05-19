package testCases;

import app.wiproAssignment.com.BaseTest;
import app.wiproAssignment.com.DataSupplier;
import app.wiproAssignment.com.mobileScreens.HomeScreen;
import app.wiproAssignment.com.mobileScreens.LoginScreen;
import app.wiproAssignment.com.mobileScreens.PDPScreen;
import app.wiproAssignment.com.mobileScreens.ProductScreen;
import net.datafaker.Faker;
import org.testng.annotations.Test;

public class RegressionTest extends BaseTest {

    LoginScreen loginScreen= new LoginScreen();
    HomeScreen homeScreen= new HomeScreen();
    ProductScreen productScreen= new ProductScreen();
    PDPScreen pdpScreen= new PDPScreen();
    Faker faker= new Faker();



@Test(dataProvider = "loginData", dataProviderClass = DataSupplier.class,priority = 1,groups = {"Regression"},
    description = "Verify user login")

    public void verifyUserIsAbleToLoginSuccessfully(String email,String password) {
        if (platform.equalsIgnoreCase("android")) {
            loginScreen.fillAllLoginInformation(email,password);
            loginScreen.tapConfirmLoginButton();
        } else if (platform.equalsIgnoreCase("iOS")) {

            loginScreen.fillAllLoginInformation(email,password);
            loginScreen.tapConfirmLoginButton();
        }
    }

    @Test(priority = 2,groups = {"Regression"}, description = "Verify user is unable to login")
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
    @Test(dataProvider ="loginData", dataProviderClass = DataSupplier.class, priority = 3,groups = {"Regression"}, description = "Verify user is able to Navigate HomeScreen")
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
    @Test(dataProvider ="loginData", dataProviderClass = DataSupplier.class, priority = 4,groups = {"Regression"}, description = "Verify user is able to Select Category from HomeScreen")
    public void verifyUserAbleToAddSelectCategory(String email, String password) {
        if (platform.equalsIgnoreCase("android")) {
            loginScreen.fillAllLoginInformation(email,password);
            loginScreen.tapConfirmLoginButton();
            homeScreen.getListOfMenus();


        } else if (platform.equalsIgnoreCase("iOS")) {
            loginScreen.fillAllLoginInformation(email,password);
            loginScreen.tapConfirmLoginButton();
            homeScreen.getListOfMenus();
        }
    }

    @Test(dataProvider ="loginData", dataProviderClass = DataSupplier.class, priority = 5,groups = {"Regression"}, description = "Verify user is able to select Product from Category")
    public void verifyUserAbleToAddSelectProductFromCategory(String email, String password) {
        if (platform.equalsIgnoreCase("android")) {
            loginScreen.fillAllLoginInformation(email,password);
            loginScreen.tapConfirmLoginButton();
            homeScreen.getListOfMenus();
            productScreen.validateUserAbleToSelectProduct();


        } else if (platform.equalsIgnoreCase("iOS")) {
            loginScreen.fillAllLoginInformation(email,password);
            loginScreen.tapConfirmLoginButton();
            homeScreen.getListOfMenus();
            productScreen.validateUserAbleToSelectProduct();
        }
    }
    @Test(dataProvider ="loginData", dataProviderClass = DataSupplier.class, priority = 6,groups = {"Regression"}, description = "Verify user is able to Add product to shopping cart")
    public void verifyUserAbleToAddProductToCart(String email, String password) {
        if (platform.equalsIgnoreCase("android")) {
            loginScreen.fillAllLoginInformation(email,password);
            loginScreen.tapConfirmLoginButton();
            homeScreen.getListOfMenus();
            productScreen.validateUserAbleToSelectProduct();
            pdpScreen.validateUserAbleToAddProductToCart();

        } else if (platform.equalsIgnoreCase("iOS")) {
            loginScreen.fillAllLoginInformation(email,password);
            loginScreen.tapConfirmLoginButton();
            homeScreen.validateMenuVisible();
            homeScreen.getListOfMenus();
            productScreen.validateUserAbleToSelectProduct();
            pdpScreen.validateUserAbleToAddProductToCart();
        }
    }

    @Test(dataProvider ="loginData", dataProviderClass = DataSupplier.class, priority = 7,groups = {"Smoke"}, description = "Verify user is able to create new account by tapping Join button in main page")
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
