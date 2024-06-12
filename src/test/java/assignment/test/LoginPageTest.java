package assignment.test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import assignment.base.Base;
import assignment.pages.LoginPage;
import assignment.utility.ExtentReporter;

public class LoginPageTest extends Base {

    LoginPage lp;

    @BeforeSuite
    public void setUpReport() {
        ExtentReporter.setUp();
    }

    @AfterSuite
    public void tearDownReport() {
        ExtentReporter.endReport();
    }

    @BeforeMethod
    public void launch() throws IOException {
        initialization();
        lp = new LoginPage();
    }

    @Test(priority = 3)
    public void verifyTitle() {
        ExtentReporter.test = ExtentReporter.extent.createTest("Verify_Title");
        String ExpectedTitle = "Demo App";
        String ActualTitle = lp.verifyTitle();
        Assert.assertEquals(ExpectedTitle, ActualTitle);
        ExtentReporter.test.pass("Title verification passed.");
    }

    @Test(priority = 2)
    public void verifyUrl() {
        ExtentReporter.test = ExtentReporter.extent.createTest("Verify_Url before Login");
        String ExpectedUrl = "https://sakshingp.github.io/assignment/login.html";
        String ActualUrl = lp.verifyUrl();
        Assert.assertEquals(ExpectedUrl, ActualUrl);
        ExtentReporter.test.pass("URL verification passed.");
    }

    @Test(priority = 1)
    public void verifyLogo() {
        ExtentReporter.test = ExtentReporter.extent.createTest("Verify_Logo");
        Boolean result = lp.logoPresent();
        Assert.assertEquals(result, true);
        ExtentReporter.test.pass("Logo verification passed.");
    }

    @Test(priority = 5)
    public void verifyLoginWithCredentials() {
        ExtentReporter.test = ExtentReporter.extent.createTest("Verify Login With Valid Credentials");
        String ExpectedUrl = "https://sakshingp.github.io/assignment/home.html";
        String ActualUrl = lp.verifyLogin();
        Assert.assertEquals(ExpectedUrl, ActualUrl);
        ExtentReporter.test.pass("Login with valid credentials passed.");
    }

    @Test(priority = 6)
    public void invalidLoginTestWithOutUserName() {
        ExtentReporter.test = ExtentReporter.extent.createTest("Invalid Login Test Without Username");
        String invalidUsername = "";
        String invalidPassword = "Swapnil@09";
        String expectedErrorMessage = "Username must be present";

        String actualErrorMessage = lp.invalidLogin(invalidUsername, invalidPassword);

        ExtentReporter.test.info("Expected Error Message: " + expectedErrorMessage);
        ExtentReporter.test.info("Actual Error Message: " + actualErrorMessage);

        try {
            Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
            ExtentReporter.test.pass("Invalid login test without username passed.");
        } catch (AssertionError e) {
            ExtentReporter.test.fail("Invalid login test without username failed. Expected error message: " + expectedErrorMessage
                    + ", but found: " + actualErrorMessage);
            throw e;
        }
    }

    @Test(priority = 7)
    public void invalidLoginTestWithoutPassword() {
        ExtentReporter.test = ExtentReporter.extent.createTest("Invalid Login Test Without Password");
        String invalidUsername = "Swapnil123";
        String invalidPassword = "";
        String expectedErrorMessage = "Password must be present";

        String actualErrorMessage = lp.invalidLogin(invalidUsername, invalidPassword);

        ExtentReporter.test.info("Expected Error Message: " + expectedErrorMessage);
        ExtentReporter.test.info("Actual Error Message: " + actualErrorMessage);

        try {
            Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
            ExtentReporter.test.pass("Invalid login test without password passed.");
        } catch (AssertionError e) {
            ExtentReporter.test.fail("Invalid login test without password failed. Expected error message: " + expectedErrorMessage
                    + ", but found: " + actualErrorMessage);
            throw e;
        }
    }

    @Test(priority = 8)
    public void invalidLoginTestWithoutCredentials() {
        ExtentReporter.test = ExtentReporter.extent.createTest("Invalid Login Test Without Credentials");
        String invalidUsername = "";
        String invalidPassword = "";
        String expectedErrorMessage = "Both Username and Password must be present";

        String actualErrorMessage = lp.invalidLogin(invalidUsername, invalidPassword);

        ExtentReporter.test.info("Expected Error Message: " + expectedErrorMessage);
        ExtentReporter.test.info("Actual Error Message: " + actualErrorMessage);

        try {
            Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
            ExtentReporter.test.pass("Invalid login test without credentials passed.");
        } catch (AssertionError e) {
            ExtentReporter.test.fail("Invalid login test without credentials failed. Expected error message: " + expectedErrorMessage
                    + ", but found: " + actualErrorMessage);
            throw e;
        }
    }

    @Test(priority = 0)
    public void verifyfbLogo() {
        ExtentReporter.test = ExtentReporter.extent.createTest("Verify Facebook Logo");
        Boolean result = lp.verifyfbLogo();
        Assert.assertEquals(result, true);
        ExtentReporter.test.pass("Facebook logo verification passed.");
    }

    @Test(priority = 0)
    public void verifyInLogo() {
        ExtentReporter.test = ExtentReporter.extent.createTest("Verify LinkedIn Logo");
        Boolean result = lp.verifyInLogo();
        Assert.assertEquals(result, true);
        ExtentReporter.test.pass("LinkedIn logo verification passed.");
    }

    @Test(priority = 0)
    public void verifyTwitterLogo() {
        ExtentReporter.test = ExtentReporter.extent.createTest("Verify Twitter Logo");
        Boolean result = lp.verifyTwitterLogo();
        Assert.assertEquals(result, true);
        ExtentReporter.test.pass("Twitter logo verification passed.");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
