package org.Pages;

import org.bjit.sqa.Main;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ProductPage {
    WebDriver driver;
    String productName;
    int qtnGlobal ;
    WebDriverWait  wait;

    //Constructor(s)
    public ProductPage(WebDriver driver, String productName) {
        this.driver = driver;
        this.productName = productName;
        wait =new WebDriverWait(driver, Duration.ofSeconds(5), Duration.ofSeconds(2));
    }

    //Locator(s)
    By title ;
    //By titleHome = By.xpath("//h1[contains(text(), 'Your Store')]");
    By quantity = By.id("input-quantity");
    By buttonCart = By.id("button-cart");
    By buttonItems = By.xpath("//button[@class='btn btn-inverse btn-block btn-lg dropdown-toggle']");
    By price = By.xpath("//h2");
    By totalPrice = By.xpath("//td[@class='text-right']");
    By successText = By.xpath("//div[@class='alert alert-success alert-dismissible']");
    By qtnFromModal = By.xpath("//td[@class='text-right']");

    //Method(s)

    //Get method for title
    public By getTitle(){
        title = By.xpath("//h1[contains(text(), '"+productName+"')]");
        return title;
    }

    //Get method for item Cart button
    public By getButtonItem(){
        return buttonItems;
    }


    //For setting quantity for the selected product
    public void setQuantity(int qtnNum) {
        qtnGlobal=qtnNum;
        WebElement qtn= wait.until(ExpectedConditions.visibilityOfElementLocated(quantity)); //driver.findElement(quantity);
        qtn.clear();
        qtn.sendKeys(qtnNum+"");
        System.out.println("Quantity is set");
    }

    // For clicking Add to Cart button
    public void clickAddToCart(){
        wait.until(ExpectedConditions.elementToBeClickable(buttonCart)).click(); // driver.findElement(buttonCart).click();
        System.out.println("Add to Cart button is clicked");
    }

    // For clicking item cart button
    public void clickItemsButton(){
        wait.until(ExpectedConditions.elementToBeClickable(buttonItems)).click();   //driver.findElement(buttonItems).click();
        System.out.println("Item button is clicked");
    }

    // For checking or verify price and quantity
    public void totalPriceWithQuantity(){
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        //Quantity check
        String qtnStr = driver.findElements(qtnFromModal).get(0).getText().substring(2);
        int qtn = Integer.parseInt(qtnStr);
        if(qtn == qtnGlobal) System.out.println("Qutantity: "+ qtnStr +" is checked");
        else System.out.println("Quantity is not matching");

        //Price check
        String priceStr = driver.findElements(price).get(1).getText().substring(1);
        Double total = Double.parseDouble(priceStr) * qtnGlobal;
        priceStr = driver.findElements(totalPrice).get(9).getText();
        Double gotten = Double.parseDouble( priceStr.substring(1).replaceAll(",",""));
       // System.out.println( total+" "+ gotten);
        if (gotten.equals(total)) System.out.println("Total Price: "+ priceStr +" is checked");
        else System.out.println("Price is not matching");
    }


    // For printing success message after adding the product to cart
    public void successTextPrint(){
        try {
            String text = wait.until(ExpectedConditions.visibilityOfElementLocated(successText)).getText(); // driver.findElement(successText).getText();
            System.out.println("Line> " + text.substring(0, text.length() - 2));
        } catch (Exception e){
            System.out.println("The expected text did not show up in the browser");
        }
    }



}
