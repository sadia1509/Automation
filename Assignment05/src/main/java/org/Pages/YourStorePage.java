package org.Pages;

import org.bjit.sqa.ExcelDataGetter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class YourStorePage {
    WebDriver driver;
    String productName;
    WebDriverWait wait;

    //Locator(s)
    By title = By.xpath("//a[contains(text(), 'Your Store')]");
    By myAccount = By.xpath("//li[@class='dropdown']");
    By register = By.xpath("//a[contains(text(), 'Register')]");
    By logout = By.xpath("//a[contains(text(), 'Logout')]");
    By desktopDropdown = By.xpath("//a[normalize-space()='Desktops']");
    By showAll = By.xpath("//a[@class='see-all']");
    By product;

    //Constructor(s)
    public YourStorePage(WebDriver driver) {
        this.driver = driver;
        wait =new WebDriverWait(driver, Duration.ofSeconds(40) );
    }


    public YourStorePage(WebDriver driver, String productName) {
        this.driver = driver;
        this.productName = productName;
        wait =new WebDriverWait(driver, Duration.ofSeconds(40) );
    }






    //Method(s)

    // Logout run for logging out from current session
    public void logoutRun(ExcelDataGetter edg, int i){
        clickMyAccount();
        clickLogout();
        logoutSuccess(driver, edg, i);
    }


    // For returning the product locator
    public By getProductPath(){
        product = By.xpath("//a[contains(text(), '"+productName+"')]");
        return product;
    }

    // Sorting options for the products redirection
    public void nevigateToSortingOption(){
        hoverOnDesktop();
        showAllClick();
    }

    // Hover on desktop dropdown
    public void hoverOnDesktop(){
        Actions action = new Actions(driver);
        action.moveToElement(wait.until(ExpectedConditions.visibilityOfElementLocated(desktopDropdown))).perform();
System.out.println("13. Hovering on the desktop dropdown");
    }

    public void showAllClick(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(showAll)).click();
        System.out.println("14. Show all desktop is clicked");
    }

    // For clicking My account dropdown
    public void clickMyAccount() {
        wait.until(ExpectedConditions.elementToBeClickable(myAccount)).click(); //driver.findElement(myAccount).click();
        System.out.println("2.26. My Account is clicked");
    }

    // For selecting Register option from the dropdown
    public void clickRegister() {
        wait.until(ExpectedConditions.elementToBeClickable(register)).click(); // driver.findElement(register).click();
        System.out.println("3. Registration page is opened");
    }

    // For clicking to select the desired product to add into the cart
    public void clickProduct() {
        wait.until(ExpectedConditions.elementToBeClickable(product)).click(); // driver.findElement(product).click();
        System.out.println(productName+" is clicked");
    }

    // For logout click from the dropdown
    public void clickLogout() {
        wait.until(ExpectedConditions.elementToBeClickable(logout)).click();  // driver.findElement(logout).click();
        System.out.println("27. Logout button is pressed");
    }

    //After logging out the success message will be shown by this method
    public static void logoutSuccess(WebDriver driver, ExcelDataGetter edg, int i){
        AccountLogoutPage alp = new AccountLogoutPage(driver);
        alp.printTheLine(edg, i);
    }
}
