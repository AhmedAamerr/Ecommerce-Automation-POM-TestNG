package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckOutPage {
    protected WebDriver driver;

    public CheckOutPage(WebDriver driver) {
        // Directly get WebDriver instance from the Hooks class
        this.driver = driver;
    }

    public void proceedToCheckOut() {
        WebElement proceedBtn1 = driver.findElement(By.xpath("//button[@data-test='proceed-1']"));
        proceedBtn1.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // Wait for next page
        WebElement proceedBtn2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@data-test='proceed-2']")));
        proceedBtn2.click();
    }

    // Locators for billing address fields
    private WebElement addressField() {
        return driver.findElement(By.xpath("//*[@data-test='address']"));
    }

    private WebElement cityField() {
        return driver.findElement(By.xpath("//*[@data-test='city']"));
    }

    private WebElement stateField() {
        return driver.findElement(By.xpath("//*[@data-test='state']"));
    }

    private WebElement countryField() {
        return driver.findElement(By.xpath("//*[@data-test='country']"));
    }

    private WebElement postcodeField() {
        return driver.findElement(By.xpath("//*[@data-test='postcode']"));
    }

    private WebElement proceedToCheckOutBtn() {
        return driver.findElement(By.xpath("//*[@data-test='proceed-3']"));
    }

    // Method to fill in the billing address
    public void fillBillingAddress(String address, String city, String state, String country, String postcode) {
        addressField().sendKeys(address);
        cityField().sendKeys(city);
        stateField().sendKeys(state);
        countryField().sendKeys(country);
        postcodeField().sendKeys(postcode);
        proceedToCheckOutBtn().click();
    }

    public void confirmPayment() {
        Select select = new Select(driver.findElement(By.xpath("//*[@data-test='payment-method']")));
        select.selectByVisibleText("Bank Transfer");
        // Fill the Bank Name
        WebElement bankName = driver.findElement(By.id("bank_name"));
        bankName.sendKeys("NBE");
        // Fill the Account Name
        WebElement accName = driver.findElement(By.id("account_name"));
        accName.sendKeys("Test");
        //Fill the Account Number
        WebElement accNumber = driver.findElement(By.id("account_number"));
        accNumber.sendKeys("123456789");

        //Confirm the Payment
        WebElement confirmBtn = driver.findElement(By.xpath("//*[@data-test='finish']"));
        confirmBtn.click();
    }

    public void assertPaymentSuccess() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'Payment was successful')]")));

        // Assertion to check if the message is displayed
        if (successMessage.isDisplayed()) {
            System.out.println("Payment was successful.");
        } else {
            throw new AssertionError("Payment success message not displayed.");
        }

    }
}
