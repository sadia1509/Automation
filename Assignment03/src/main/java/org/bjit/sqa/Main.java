package org.bjit.sqa;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.Pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class Main {
    public static void main(String[] args) {
        System.out.println("====(LOG)====");
        openBrowser("edge");
        System.out.println("===(DONE)===");
    }

    // Here the browser and iteration limit get selected, also based on these the driver runs
    public static void openBrowser(String browser) {
        WebDriver driver=null;
        boolean notBrowser=false;
        switch (browser.toLowerCase()){
            case "edge":
                WebDriverManager.edgedriver().arch64().setup();
                driver = new EdgeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().arch64().setup();
                driver = new FirefoxDriver();//ChromeDriver();
                break;
            case "chrome":
                WebDriverManager.chromedriver().arch64().setup();
                driver = new ChromeDriver();
                break;
            default:
                System.out.println("No browser is found named: " + browser);
                notBrowser=true;
                break;
        }
        if(!notBrowser) {
            System.out.println("Browser Name: "+browser);
            String productName ="MacBook";
                driver.get("http://tutorialsninja.com/demo");
                for (int i = 1; i <= 3; i++) {
                    System.out.println("\n-------iteration number (" + i + ")-------");
                   goToRegistraionPage(driver);
                    selectProduct(driver, productName);
                    productToCart(driver, productName, 2);
                   logout(driver);
                }
                driver.quit();

        }
    }


    // From homepage to registration page for creating a new user
    public static void goToRegistraionPage(WebDriver driver)  {
        YourStorePage ysp = new YourStorePage(driver);
        ysp.clickMyAccount();
        ysp.clickRegister();
        registerUser(driver);
    }

    // New user gets created
    public static void registerUser(WebDriver driver) {
        RegistrationPage rp = new RegistrationPage(driver);
    scroll(driver, rp.getTitle());
    rp.fillAllFields();
    rp.newsletterSelect('n');
    rp.clickAgree();
    rp.clickContinue();
    accountCreateSuccess(driver);
    }


    //After creating a user, it will go to a success page where the result will be verified
    public static void accountCreateSuccess(WebDriver driver){
        AccountCreatedPage acp = new AccountCreatedPage(driver);
        acp.printTheLine();
        acp.clickContinue();
        acp.refreshHomePage();
    }


    // Here the product gets selected and go to that selected product's page
    public static void selectProduct(WebDriver driver, String productName){
        YourStorePage ysp = new YourStorePage(driver, productName);
        scroll(driver, ysp.getProduct());
        ysp.clickProduct();
    }

    // Here the selected product will be added to the cart and then the quantity & price will be verified
    public static void productToCart(WebDriver driver, String productName, int quantity){
        ProductPage pp = new ProductPage(driver, productName);
        scroll(driver, pp.getTitle());
        pp.setQuantity(quantity);
        pp.clickAddToCart();
        scroll(driver, pp.getButtonItem());
        pp.successTextPrint();
        pp.clickItemsButton();
        pp.totalPriceWithQuantity();

    }

    // Logout gets done here
    public static void logout(WebDriver driver){
        YourStorePage ysp = new YourStorePage(driver);
        ysp.clickMyAccount();
        ysp.clickLogout();
        logoutSuccess(driver);
    }

    //After logging out the success message will be shown by this method
    public static void logoutSuccess(WebDriver driver){
        AccountLogoutPage alp = new AccountLogoutPage(driver);
        alp.printTheLine();
    }

    // For scrolling purpose where we need to pass the driver and the element's locator where we want to go by scrolling
    public static void scroll(WebDriver driver, By locator){
        WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(5), Duration.ofSeconds(2));
        WebElement element =  wait.until(ExpectedConditions.visibilityOfElementLocated(locator)); // driver.findElement(locator);
        JavascriptExecutor js=(JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView()", element);
        System.out.println("Scrolling...");
    }

   /*   public static void waitHere(int time){
     //   webDriver.manage().timeouts().implicitlyWait(time, TimeUnit.MILLISECONDS);

    try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

// WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(30), Duration.ofSeconds(3));
    } */

}



