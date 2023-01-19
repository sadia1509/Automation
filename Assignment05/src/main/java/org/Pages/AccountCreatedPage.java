package org.Pages;

import org.bjit.sqa.ExcelDataGetter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountCreatedPage {
    WebDriver driver;
    WebDriverWait wait;
    int i;

    //Locator(s)
    By title = By.xpath("//a[contains(text(), 'Your Store')]");
    By line = By.xpath("//div[@id='content']/h1");
    By continueBtn = By.xpath("//a[contains(text(), 'Continue')]");

    //Constructor(s)
    public AccountCreatedPage(WebDriver driver) {
        this.driver = driver;
        wait =new WebDriverWait(driver, Duration.ofSeconds(40) );
    }




    //Method(s)

    // Account created page run to print and continue to the home page
    public void accountCreatedRun(ExcelDataGetter edg, int i){
        this.i =i;
         printTheLine(edg);
         clickContinue();

         MyAccountPage mcp = new MyAccountPage(driver);
         mcp.allVerify();

         refreshHomePage();
    }

    // Home Page redirection
    public void refreshHomePage() {
        wait.until(ExpectedConditions.elementToBeClickable(title)).click(); //driver.findElement(title).click();
        System.out.println("Home page link is clicked");
    }

    // For pressing the continue button after the success message
    public void clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(continueBtn)).click(); //driver.findElement(continueBtn).click();
        System.out.println("Continue button is clicked");
    }

    // Prints the success message after verifying it
    public void printTheLine(ExcelDataGetter edg) {
        String text= wait.until(ExpectedConditions.visibilityOfElementLocated(line)).getText();  //driver.findElement(line).getText();
        if (text.equals(edg.getCellData(i, "account created msg"))) // if (text.equals("Your Account Has Been Created!")) //
        System.out.println("10. Line: "+ text);
        else System.out.println("10. Please complete all the steps / agree to the policy");
    }

}
