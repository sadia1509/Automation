package org.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ShoppingCartPage {

    WebDriver driver;
    WebDriverWait wait;
    //Locator(s)
    By quantity = By.xpath("//*[@id='content']/form/div/table/tbody/tr/td[4]/div/input");
    By totalPrice = By.xpath("//*[@id='content']/form/div/table/tbody/tr/td[6]");
    By unitPrice = By.xpath("//*[@id='content']/form/div/table/tbody/tr/td[5]");



    //Constructor(s)
    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
        wait =new WebDriverWait(driver, Duration.ofSeconds(40) );
    }



    //Method(s)

    // Get the price and quantity calculation
    public void calculation() {
        String uniPrice = wait.until(ExpectedConditions.visibilityOfElementLocated(unitPrice)).getText();  //driver.findElement(title).click();
        String totPrice = wait.until(ExpectedConditions.visibilityOfElementLocated(totalPrice)).getText();
        String qtn = wait.until(ExpectedConditions.visibilityOfElementLocated(quantity)).getAttribute("value");

        int qtnNum = Integer.parseInt(qtn);
        double priceT = Double.parseDouble(totPrice.substring(1).replaceAll(",", ""));
        double price = Double.parseDouble(uniPrice.substring(1).replaceAll(",", ""));

        if (((qtnNum)*price)==(priceT)){
            System.out.println("23.25. Qutantity: " + qtn + " is checked");
            System.out.println("23.25. Unit Price: " + uniPrice + " is checked");
            System.out.println("23.25. Total Price: " + totPrice + " is checked");
        } else  System.out.println("23.25. Calculation is not matched");

    }


}
