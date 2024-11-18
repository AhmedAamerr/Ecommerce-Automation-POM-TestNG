package org.example.pages;
import junit.framework.Assert;
import org.example.testData.UserData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class LoginPage extends RegistrationPage {


    public LoginPage(WebDriver driver) {
        super(driver);
    }
    UserData userData = UserData.getInstance();


    private void FillEmail(){
        WebElement emailField= driver.findElement(By.xpath("//*[@data-test='email']"));
        emailField.sendKeys(userData.getEmail());
    }
    private void FillPassword(){
        WebElement passwordField= driver.findElement(By.xpath("//*[@data-test='password']"));
        passwordField.sendKeys(userData.getPassword());
    }
    private  void LoginSubmit(){
        WebElement loginBtn= driver.findElement(By.xpath("//*[@data-test='login-submit']"));
        loginBtn.click();
    }
    public void loginWithGeneratedCredentials() {
        FillEmail();
        FillPassword();
        LoginSubmit();
    }
    public void assertTitleContainsMyAccount() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement pageTitleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-test='page-title']")));

        // Check if the text of the page title is "My Account"
        String actualTitle = pageTitleElement.getText();
        Assert.assertEquals("User did not log in successfully. 'My Account' page title not found.","My account", actualTitle);

    }
    public void assertDropdownContainsMySignOut() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Click on the dropdown menu
        WebElement dropdownMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@data-test='nav-menu']")));
        dropdownMenu.click();

        // Wait for the dropdown list to appear
        WebElement dropdownList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".dropdown-menu.show")));

        // Check if "Sign out" is one of the visible options
        List<WebElement> menuItems = dropdownList.findElements(By.xpath("//*[@data-test='nav-my-account']"));

        // Ensure that at least one "My Account" element is visible
        boolean SignOutVisible = menuItems.stream().anyMatch(WebElement::isDisplayed);
        Assert.assertTrue("'My Account' is not visible in the dropdown menu.", SignOutVisible);
    }

    public void assertUserLoggedInSuccessfully(){
        assertTitleContainsMyAccount();
        assertDropdownContainsMySignOut();
    }
}
