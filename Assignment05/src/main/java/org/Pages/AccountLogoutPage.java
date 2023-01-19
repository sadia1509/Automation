package org.Pages;

import org.bjit.sqa.ExcelDataGetter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountLogoutPage {
    WebDriver driver;
    WebDriverWait wait;

    //Locator(s)
    By line = By.xpath("//div[@id='content']/h1");

    //Constructor(s)
    public AccountLogoutPage(WebDriver driver) {
        this.driver = driver;
        wait =new WebDriverWait(driver, Duration.ofSeconds(40) );
    }



    //Method(s)


    //Prints the logout message after verifying it
    public void printTheLine(ExcelDataGetter edg, int i) {
        String text= wait.until(ExpectedConditions.visibilityOfElementLocated(line)).getText(); //driver.findElement(line).getText();
        if (text.equals(edg.getCellData(i, "logout msg")))  // if (text.equals("Account Logout"))
            System.out.println("28. Line: "+ text);
    }
}
