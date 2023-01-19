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

    //variables
    public static boolean clearance;

    //Main method
    public static void main(String[] args) {
        System.out.println("====(LOG)====");
        openBrowser("Chrome");
        System.out.println("===(DONE)===");
    }

    // Here the browser and iteration limit get selected, also based on these the driver runs
    public static void openBrowser(String browser) {
        WebDriver driver=null;
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
                break;
        }
        if(driver!=null) {
            System.out.println("Browser Name: "+browser);
               driver.get("http://tutorialsninja.com/demo");
               ExcelDataGetter edg = new ExcelDataGetter();
               driverRun(driver, edg);
            // edg.updateTheMails(); // For creating new emails
            driver.quit();

        }
    }

    // When driver is not null
    public static void driverRun(WebDriver driver, ExcelDataGetter edg){
        String productName ="MacBook";
        for (int i = 1; i <= 4; i++) {
            System.out.println("\n-------iteration number (" + i + ")-------");
            System.out.println("1. Navigate to the given url");
            goToRegistraionPage(driver);
            registerUser(driver, edg,i);
            if (edg.dataFromRowColumn(i, 7).toLowerCase().equals("false") || !clearance)
                continue;
           productName = getTheProductBySorting(driver, edg.getCellData(i, "sort Option"));
            selectProduct(driver, productName);
            productToCart(driver, productName, Integer.parseInt(edg.getCellData(i, "qtn")));
            verifyThepriceQuantity(driver);
            logout(driver,edg, i);


        }
    }


    // Sorting the products and then return the first item
    public static String getTheProductBySorting(WebDriver driver, String sortingOption){
        YourStorePage ysp = new YourStorePage(driver);
        ysp.nevigateToSortingOption();
        ShowAllProductPage spp = new ShowAllProductPage(driver, "Desktop");
        spp.sort(sortingOption);
        return spp.getProductName();
    }

    // From homepage to registration page for creating a new user
    public static void goToRegistraionPage(WebDriver driver)  {
        YourStorePage ysp = new YourStorePage(driver);
        ysp.clickMyAccount();
        ysp.clickRegister();
    }

    // New user gets created
    public static void registerUser(WebDriver driver, ExcelDataGetter edg, int i) {
        RegistrationPage rp = new RegistrationPage(driver, edg);
    scroll(driver, rp.getTitle());
    rp.registrationRun('n', i);
    clearance = rp.getFieldsClearance();
    }


    // Here the product gets selected and go to that selected product's page
    public static void selectProduct(WebDriver driver, String productName){
        YourStorePage ysp = new YourStorePage(driver, productName);
        scroll(driver, ysp.getProductPath());
        ysp.clickProduct();
    }

    // Here the selected product will be added to the cart and then the quantity & price will be verified
    public static void productToCart(WebDriver driver, String productName, int quantity){
        ProductPage pp = new ProductPage(driver, productName);
        scroll(driver, pp.getTitle());
        pp.productPageRun(quantity);

    }

    // For verifying the price and quantity after going to view cart page
    public static void verifyThepriceQuantity(WebDriver driver){
        ShoppingCartPage scp = new ShoppingCartPage(driver);
        scp.calculation();
    }


    // Logout gets done here
    public static void logout(WebDriver driver, ExcelDataGetter edg, int i){
        YourStorePage ysp = new YourStorePage(driver);
        ysp.logoutRun(edg, i);

    }



    // For scrolling purpose where we need to pass the driver and the element's locator where we want to go by scrolling
    public static void scroll(WebDriver driver, By locator){
        WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement element =  wait.until(ExpectedConditions.visibilityOfElementLocated(locator)); // driver.findElement(locator);
        JavascriptExecutor js=(JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView()", element);
        System.out.println("Scrolling...");
    }



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

