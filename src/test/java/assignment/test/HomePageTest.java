package assignment.test;

import java.io.IOException;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import assignment.base.Base;
import assignment.pages.HomePage;
import assignment.pages.LoginPage;
import assignment.utility.ExtentReporter;

public class HomePageTest extends Base {
    
    LoginPage lp;
    HomePage hp;

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
        hp = new HomePage();
        lp = new LoginPage();
    }

    @Test(priority = 1)
    public void verifyTitle() {
        ExtentReporter.test = ExtentReporter.extent.createTest("Verify_Title");
        String ExpectedTitle = "Demo App";
        String ActualTitle = hp.verifyTitle();
        Assert.assertEquals(ExpectedTitle, ActualTitle);
        ExtentReporter.test.pass("Title verification passed.");
    }

    @Test(priority = 2)
    public void verifyUrl() {
        ExtentReporter.test = ExtentReporter.extent.createTest("Verify_Url after Login");
        String ExpectedUrl = "https://sakshingp.github.io/assignment/home.html";
        String ActualUrl = lp.verifyLogin();
        Assert.assertEquals(ExpectedUrl, ActualUrl);
        ExtentReporter.test.pass("URL verification passed.");
    }

    @Test(priority = 3)
    public void verifySort() throws InterruptedException {
        ExtentReporter.test = ExtentReporter.extent.createTest("verifySort");

        lp.verifyLogin();
        @SuppressWarnings("unused")
        String[] beforeSort = hp.extractData();
        String[] afterSort = hp.perfromSort();
        String[] sortedData = hp.sortedData();

        ExtentReporter.test.info("Expected (sortedData): " + Arrays.toString(sortedData));
        ExtentReporter.test.info("Actual (afterSort): " + Arrays.toString(afterSort));

        try {
            Assert.assertEquals(afterSort, sortedData);
            ExtentReporter.test.pass("Data extract test passed.");
        } catch (AssertionError e) {
            ExtentReporter.test.fail("Data extract test failed. Arrays differ.");
            throw e;
        }
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
