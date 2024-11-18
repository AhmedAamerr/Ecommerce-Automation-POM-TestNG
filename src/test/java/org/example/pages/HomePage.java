package org.example.pages;

import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

public class HomePage {
    protected WebDriver driver;
    public HomePage(WebDriver driver) {
        // Directly get WebDriver instance from the Hooks class
        this.driver = driver;
    }
    public void goToHomePage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement homeBtn= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-test='nav-home']")));
        homeBtn.click();
        // Wait until the container with class "container" is displayed
        WebElement container = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("container")));
        Assert.assertTrue("Home page container is not visible.", container.isDisplayed());
    }

    public void addItemToCartIfInStock() {
        // Locate all product names using the data-test attribute
        List<WebElement> productList = driver.findElements(By.xpath("//*[@data-test='product-name']"));

        // Iterate through the product list and find a product that is in stock
        for (WebElement product : productList) {
            WebElement cardFooter = product.findElement(By.xpath("//*[@class='card']//div[@class='card-footer']"));

            // Check if the product is not out of stock
            if (!cardFooter.getText().contains("Out of stock")) {
                // Click the add to cart button within this product's card
               cardFooter.click();
                // Add item to the cart
                WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(5));
                WebElement addToCartBtn= wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn-add-to-cart")));
                
                addToCartBtn.click();
                return; // Exit after adding one product to the cart
            }
        }

        Assert.fail("No products available in stock to add to the cart.");
    }


    public void assertProductAddedToCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        try {
            // Wait for the alert to be visible
            WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(text(),'Product added to shopping cart.')]")));
            Assert.assertTrue(alert.isDisplayed());
        } catch (Exception e) {
            Assert.fail("The 'Product added to shopping cart.' alert was not found or appeared briefly.");
        }

        // Assert the cart icon number > 0
        int cartQuantity= Integer.parseInt(driver.findElement(By.xpath("//*[@data-test='cart-quantity']")).getText());
        Assert.assertTrue("Cart icon not indicates the added item",cartQuantity>0);
    }

}
