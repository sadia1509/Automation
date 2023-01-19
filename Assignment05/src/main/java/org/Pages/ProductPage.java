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
    WebDriverWait  wait;
    int qtnGlobal ;

    //Locator(s)
    By title ;    //By titleHome = By.xpath("//h1[contains(text(), 'Your Store')]");
    By quantity = By.id("input-quantity");
    By buttonCart = By.id("button-cart");
    By buttonItems = By.xpath("//button[@class='btn btn-inverse btn-block btn-lg dropdown-toggle']");
    By successText = By.xpath("//div[@class='alert alert-success alert-dismissible']");
    By viewCartBtn = By.xpath("//i[@class='fa fa-shopping-cart']");

    By price = By.xpath("//h2");
    By qtnFromModal = By.xpath("//td[@class='text-right']");
    By totalPrice = By.xpath("//tbody//tr//td[3]");

    //Constructor(s)
    public ProductPage(WebDriver driver, String productName) {
        this.driver = driver;
        this.productName = productName;
        wait =new WebDriverWait(driver, Duration.ofSeconds(5) );
    }




    //Method(s)

    //Get method for title
    public By getTitle(){
        title = By.xpath("//h1[contains(text(), '"+productName+"')]");
        return title;
    }

    // Product page run to execute from add the selected product to the cart to view the entire cart
    public void productPageRun(int quantity){
        setQuantity(quantity);
        clickAddToCart();
        successTextPrint();
        clickItemsButton();
        clickViewCart();
    }

    //Get method for item Cart button
    public By getButtonItem(){
        return buttonItems;
    }


    //For setting quantity for the selected product
    public void setQuantity(int qtnNum) { qtnGlobal=qtnNum;
        WebElement qtn= wait.until(ExpectedConditions.visibilityOfElementLocated(quantity)); //driver.findElement(quantity);
        qtn.clear();
        qtn.sendKeys(qtnNum+"");
        System.out.println("19. Quantity is set");
    }

    // For clicking Add to Cart button
    public void clickAddToCart(){
        wait.until(ExpectedConditions.elementToBeClickable(buttonCart)).click(); // driver.findElement(buttonCart).click();
        System.out.println("20. Add to Cart button is clicked");
    }

    // For clicking view Cart button to view the full page
    public void clickViewCart(){
        driver.findElements(viewCartBtn).get(2).click(); // driver.findElement(buttonCart).click();
        System.out.println("24. View Cart button is clicked");
    }

    // For clicking item cart button
    public void clickItemsButton(){
        wait.until(ExpectedConditions.elementToBeClickable(buttonItems)).click();   //driver.findElement(buttonItems).click();
        System.out.println("22. Item button is clicked");
    }



    // For printing success message after adding the product to cart
    public void successTextPrint(){
        try {
            String text = wait.until(ExpectedConditions.visibilityOfElementLocated(successText)).getText(); // driver.findElement(successText).getText();
            System.out.println("21. Line> " + text.substring(0, text.length() - 2));
        } catch (Exception e){
            System.out.println("21. The expected text did not show up in the browser");
        }
    }

    // For checking or verify price and quantity
    public void totalPriceWithQuantity(){ //tbody tr td:nth-child(3)
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

}
