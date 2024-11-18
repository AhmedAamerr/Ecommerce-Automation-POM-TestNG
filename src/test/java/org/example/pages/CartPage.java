package org.example.pages;

import io.netty.handler.codec.spdy.SpdyWindowUpdateFrame;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage {
    protected WebDriver driver;
    public CartPage(WebDriver driver) {
        // Directly get WebDriver instance from the Hooks class
        this.driver = driver;
    }

    public void openTheCart(){
        // Scroll to the cart icon
        WebElement cartIcon = driver.findElement(By.cssSelector("[data-icon='cart-shopping']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cartIcon);

// Wait for a moment to ensure visibility is updated
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(cartIcon));

// Click the cart icon
        cartIcon.click();
    }
    public void assertCartNotEmpty(){
        WebElement items= driver.findElement(By.xpath("//*[@class='table table-hover']"));
        Assert.assertTrue(items.isDisplayed());
    }
}
