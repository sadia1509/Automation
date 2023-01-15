package org.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class YourStorePage {
    WebDriver driver;
    String productName;
    WebDriverWait wait;

    //Constructor(s)
    public YourStorePage(WebDriver driver) {
        this.driver = driver;
        wait =new WebDriverWait(driver, Duration.ofSeconds(40), Duration.ofSeconds(10));
    }
    public YourStorePage(WebDriver driver, String productName) {
        this.driver = driver;
        this.productName = productName;
        wait =new WebDriverWait(driver, Duration.ofSeconds(40), Duration.ofSeconds(10));
    }

    //Locator(s)
    By title = By.xpath("//a[contains(text(), 'Your Store')]");
    By myAccount = By.xpath("//li[@class='dropdown']");
    By register = By.xpath("//a[contains(text(), 'Register')]");
    By logout = By.xpath("//a[contains(text(), 'Logout')]");
    By product;

    //Method(s)

    // For returning the product locator
    public By getProduct(){
        product = By.xpath("//a[contains(text(), '"+productName+"')]");
        return product;
    }

    // For clicking My account dropdown
    public void clickMyAccount() {
        wait.until(ExpectedConditions.elementToBeClickable(myAccount)).click(); //driver.findElement(myAccount).click();
        System.out.println("My Account is clicked");
    }

    // For selecting Register option from the dropdown
    public void clickRegister() {
        wait.until(ExpectedConditions.elementToBeClickable(register)).click(); // driver.findElement(register).click();
        System.out.println("Registration page is opened");
    }

    // For clicking to select the desired product to add into the cart
    public void clickProduct() {
        wait.until(ExpectedConditions.elementToBeClickable(product)).click(); // driver.findElement(product).click();
        System.out.println(productName+" is clicked");
    }

    // For logout click from the dropdown
    public void clickLogout() {
        wait.until(ExpectedConditions.elementToBeClickable(logout)).click();  // driver.findElement(logout).click();
        System.out.println("Logout button is pressed");
    }
}
