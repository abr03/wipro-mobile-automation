package app.wiproAssignment.com;

import org.testng.annotations.DataProvider;

public class DataSupplier {

    @DataProvider(name = "loginData")
    public Object[][] loginData() {

        Object [][] loginData=new Object[1][2];
        loginData[0][0]="emma@demoapp.com";
        loginData[0][1]="10203040";
        return loginData;
    }
}