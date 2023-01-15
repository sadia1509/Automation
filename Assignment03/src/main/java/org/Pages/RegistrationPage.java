package org.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RegistrationPage {
    WebDriver driver;
    WebDriverWait wait;

    //Constructor(s)
    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        wait =new WebDriverWait(driver, Duration.ofSeconds(40), Duration.ofSeconds(10));
    }

    //Locator(s)
    By title = By.id("account") ;  // xpath("//a[contains(text(), 'Your Store')]");
    By firstName = By.id("input-firstname") ;
    By lastName = By.id("input-lastname") ;
    By email = By.id("input-email") ;
    By telephone = By.id("input-telephone") ;
    By password = By.id("input-password") ;
    By confirmedPassword = By.id("input-confirm") ;
    By agreeTic = By.xpath("//input[@name='agree']");
    By continueButton = By.xpath("//input[@value='Continue']");
    By newsletter = By.xpath("//input[@name='newsletter']");

    //Method(s)

    // Get method for title
    public By getTitle(){
        return title;
    }


    // Filling all the fields here for registration
    public void fillAllFields() {
        driver.findElement(firstName).sendKeys("Sadia");
        driver.findElement(lastName).sendKeys("Afrose");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMddHHmmss");
        LocalDateTime now = LocalDateTime.now();
        driver.findElement(email).sendKeys("sadia"+dtf.format(now)+"@gmail.com");
        driver.findElement(telephone).sendKeys("0123456789");
        driver.findElement(password).sendKeys("12345");
        driver.findElement(confirmedPassword).sendKeys("12345");
        System.out.println("All fields are filled");
    }

    // For clicking the agree button
    public void clickAgree() {
        wait.until(ExpectedConditions.elementToBeClickable(agreeTic)).click(); // driver.findElement(agreeTic).click();
        System.out.println("Agree is clicked");
    }

    // For clicking the continue button once everything is done filling
    public void clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();  //driver.findElement(continueButton).click();
        System.out.println("Continue button is pressed");
    }

    // For selecting the newsletter radio button
    public void newsletterSelect(char value){

        switch (Character.toLowerCase(value)){
            case 'y':
                driver.findElements(newsletter).get(0).click();
                System.out.println("Yes is clicked for newsletter");
                break;
            case 'n':
                driver.findElements(newsletter).get(1).click();
                System.out.println("No is clicked for newsletter");
                break;
            default:
                System.out.println("Either choose y or n");
                break;
        }
    }

}
