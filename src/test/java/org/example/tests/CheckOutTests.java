package org.example.tests;
import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;
import org.example.pages.*;
import org.example.testData.BillingData;
import org.example.utils.ExtentManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;

public class CheckOutTests {
    private WebDriver driver;  // Declare WebDriver at the class level

    @BeforeMethod
    public void setup() {
        // Initialize Extent Report and Start Test
            ExtentManager.initReport();

        // Set up WebDriver using WebDriverManager and ChromeDriver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        // Maximize window and set timeouts
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Navigate to the website
        driver.navigate().to("https://practicesoftwaretesting.com/");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();  // Quit WebDriver after the test completes
        }
    }

    @AfterSuite
    public void tearDownSuite() {
        ExtentManager.flushReport();
    }

    @Test(dataProvider = "billingAddressData", dataProviderClass = BillingData.class)
    public void ensureTheCheckoutProcessIsWorkingSmoothly(String address, String city, String state, String country, String postcode) {
        RegistrationPage  register = new RegistrationPage(driver);
        LoginPage         login    = new LoginPage(driver);
        HomePage          home     = new HomePage(driver);
        CartPage          cart     = new CartPage(driver);
        CheckOutPage      checkOut = new CheckOutPage(driver);
        try {
            ExtentManager.startTest("Checkout Process Test");
            ExtentManager.getTest().info("Starting User Registration");

        //Registration
        register.fillRegistrationForm();
        register.validateSuccessfulRegistration();
        ExtentManager.getTest().pass("User Registration Successful");

        //Login
        ExtentManager.getTest().info("Navigating to Login");
        login.loginWithGeneratedCredentials();
        login.assertUserLoggedInSuccessfully();
        ExtentManager.getTest().pass("User logged Successful");

        //Home
        ExtentManager.getTest().info("Navigating to Home Page");
        home.goToHomePage();
        home.addItemToCartIfInStock();
        home.assertProductAddedToCart();
        ExtentManager.getTest().pass("User Add item to the cart Successfully");

        //cart
        cart.openTheCart();
        cart.assertCartNotEmpty();
        //CheckOut
        ExtentManager.getTest().info("Navigating to Checkout");
        checkOut.proceedToCheckOut();
        ExtentManager.getTest().info("Filling the Billing Address");
        checkOut.fillBillingAddress(address, city, state, country, postcode);
        ExtentManager.getTest().info("Selecting Payment Method");
        checkOut.confirmPayment();
        checkOut.assertPaymentSuccess();
        ExtentManager.getTest().pass("Payment was successful");
        } catch (Exception e) {
            ExtentManager.getTest().fail("Test failed due to exception: " + e.getMessage());
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }
}
