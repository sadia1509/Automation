package org.Pages;

import org.bjit.sqa.Main;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MyAccountPage {
    WebDriver driver;
    WebDriverWait wait;

    //Locator(s)
    By myAccount = By.xpath("//h2[normalize-space()='My Account']");
    By myOrders = By.xpath("//h2[normalize-space()='My Orders']");
    By myAffiliateAccount = By.xpath("//h2[normalize-space()='My Affiliate Account']");
    By newsletters = By.xpath("//h2[normalize-space()='Newsletter']");

    //Constructor(s)
    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
        wait =new WebDriverWait(driver, Duration.ofSeconds(40) );
    }


    //Method(s)


    // Verify all
    public void allVerify(){
        System.out.println("12. All sections headers are verified:");
        Main.scroll(driver, myAccount);
         myAccountVerify();
        myOrdersVerify();
        myAffiliateAccountVerify();
        newsletterVerify();

    }

    // Verify my account text
    public void myAccountVerify(){
        System.out.println(wait.until(ExpectedConditions.visibilityOfElementLocated(myAccount)).getText());
    }

    // Verify my orders text
    public void myOrdersVerify(){
        System.out.println(wait.until(ExpectedConditions.visibilityOfElementLocated(myOrders)).getText());
    }


    // Verify my affiliate account text
    public void myAffiliateAccountVerify(){
        System.out.println(wait.until(ExpectedConditions.visibilityOfElementLocated(myAffiliateAccount)).getText());
    }

    // Verify newsletters text
    public void newsletterVerify(){
        System.out.println(wait.until(ExpectedConditions.visibilityOfElementLocated(newsletters)).getText());
    }



}
