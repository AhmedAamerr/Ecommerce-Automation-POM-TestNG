package org.example.pages;

import com.github.javafaker.Faker;
import junit.framework.Assert;
import org.example.testData.UserData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Random;

public class RegistrationPage {
    protected WebDriver driver;
    public RegistrationPage(WebDriver driver) {
        // Directly get WebDriver instance from the Hooks class
        this.driver = driver;
    }
    Faker faker = new Faker();
    // Use the shared UserData instance
    UserData userData = UserData.getInstance();


    private void ClickSignInBtn() {
        WebElement signInBtn = driver.findElement(By.xpath("//*[@data-test='nav-sign-in']"));
        signInBtn.click();
    }
    private void ClickRegisterLink() {
        WebElement registerLink = driver.findElement(By.xpath("//*[@data-test='register-link']"));
        registerLink.click();
    }

    private void FillFirstName(){
        WebElement fName= driver.findElement(By.xpath("//*[@data-test='first-name']"));
        fName.sendKeys(faker.name().firstName());
    }
    private void FillLastName(){
        WebElement lName= driver.findElement(By.xpath("//*[@data-test='last-name']"));
        lName.sendKeys(faker.name().lastName());
    }

    private void FillDateOfBirth(){
        WebElement dob= driver.findElement(By.xpath("//*[@data-test='dob']"));
        Date birthday = faker.date().birthday(18,75);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String formattedDate = dateFormat.format(birthday);
        dob.sendKeys(formattedDate);
    }

    private void FillAddress(){
        WebElement address= driver.findElement(By.id("address"));
        address.sendKeys(faker.address().streetAddress());
    }
    private void FillPostCode(){
        WebElement postCode= driver.findElement(By.xpath("//*[@data-test='postcode']"));
        postCode.sendKeys(faker.address().countryCode());
    }
    private void FillCity(){
        WebElement city= driver.findElement(By.xpath("//*[@data-test='city']"));
        city.sendKeys(faker.address().city());
    }
    private void FillState(){
        WebElement state= driver.findElement(By.xpath("//*[@data-test='state']"));
        state.sendKeys(faker.address().state());
    }
    private void SelectCountry(){
        // Locate the country dropdown element
        WebElement countryDropdown= driver.findElement(By.xpath("//*[@data-test='country']"));
        // Use Select class to interact with the dropdown
        Select select = new Select(countryDropdown);
        select.selectByVisibleText("Egypt");;
    }
    private void FillPhone(){
        WebElement phone= driver.findElement(By.xpath("//*[@data-test='phone']"));
        phone.sendKeys("01"+faker.number().numberBetween(0,2)+faker.number().digits(8));
    }
    private String GeneratedEmail(){
        String GeneratedEmail=faker.name().firstName()+faker.name().lastName()+faker.number().randomDigit()+"@"+"mailinator.com";
        return GeneratedEmail;
    }
    public String GetGeneratedEmail(){
         String generatedEmail=GeneratedEmail();
        userData.setEmail(generatedEmail); // Save email in UserData
        return generatedEmail;
    }
    private void FillEmail(){
        WebElement email= driver.findElement(By.xpath("//*[@data-test='email']"));
        email.sendKeys(GetGeneratedEmail());

    }

    private String generatePassword() {
        String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String specialChars = "@#$%^&*!_";

        StringBuilder password = new StringBuilder();
        Random rand = new Random();

        // Add at least one uppercase letter, one lowercase letter, one number, and one special character
        password.append(upperCaseLetters.charAt(rand.nextInt(upperCaseLetters.length())));
        password.append(lowerCaseLetters.charAt(rand.nextInt(lowerCaseLetters.length())));
        password.append(numbers.charAt(rand.nextInt(numbers.length())));
        password.append(specialChars.charAt(rand.nextInt(specialChars.length())));

        // Add random characters to make the password at least 8 characters long
        String allChars = upperCaseLetters + lowerCaseLetters + numbers + specialChars;
        while (password.length() < 8) {
            password.append(allChars.charAt(rand.nextInt(allChars.length())));
        }

        // Shuffle the characters to avoid predictable patterns
        return shuffleString(password.toString());
    }

    // Method to shuffle the characters of the password
    private String shuffleString(String password) {
        Random rand = new Random();
        char[] passwordArray = password.toCharArray();
        for (int i = 0; i < passwordArray.length; i++) {
            int j = rand.nextInt(passwordArray.length);
            char temp = passwordArray[i];
            passwordArray[i] = passwordArray[j];
            passwordArray[j] = temp;
        }
        return new String(passwordArray);
    }

    // the generated password to be usable
    public String GetGeneratedPassword(){
        String generatedPassword= generatePassword();
        userData.setPassword(generatedPassword); // Save password in UserData
        return generatedPassword;
    }
    private void FillPassword(){
        WebElement email= driver.findElement(By.xpath("//*[@data-test='password']"));
        email.sendKeys(GetGeneratedPassword());
    }
    private void SubmitRegistration(){
        WebElement registerSubmit= driver.findElement(By.xpath("//*[@data-test='register-submit']"));
        registerSubmit.click();
    }


    // Assertion method to validate successful redirection to the login page
    public void validateSuccessfulRegistration() {
        String expectedUrl = "https://practicesoftwaretesting.com/auth/login";
        String actualUrl = driver.getCurrentUrl();

        //System.out.println("Actual URL after registration: " + actualUrl);

        // Wait for up to 10 seconds for the URL to change to the expected login URL
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean isRedirected = wait.until(ExpectedConditions.urlToBe(expectedUrl));

        // Validate the redirection to the login page
        Assert.assertTrue("User is not redirected to the login page after registration.", isRedirected);
    }


    public void fillRegistrationForm(){
        ClickSignInBtn();
        ClickRegisterLink();
        FillFirstName();
        FillLastName();
        FillDateOfBirth();
        FillAddress();
        FillPostCode();
        FillCity();
        FillState();
        SelectCountry();
        FillPhone();
        FillEmail();
        FillPassword();
        SubmitRegistration();
    }

}