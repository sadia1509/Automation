package org.Pages;

import org.apache.poi.ss.usermodel.Sheet;
import org.bjit.sqa.ExcelDataGetter;
import org.bjit.sqa.Main;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RegistrationPage {
    WebDriver driver;
    WebDriverWait wait;
    Boolean agree=false;
    ExcelDataGetter edg;
    int i;
    boolean goToSuccessPage=true;

    //Locator(s)
    By title = By.id("account") ;  // xpath("//a[contains(text(), 'Your Store')]");
    By registerTitle = By.xpath("//div[@class='col-sm-9']/h1");
    By firstName = By.id("input-firstname") ;
    By lastName = By.id("input-lastname") ;
    By email = By.id("input-email") ;
    By telephone = By.id("input-telephone") ;
    By password = By.id("input-password") ;
    By confirmedPassword = By.id("input-confirm") ;
    By agreeTic = By.xpath("//input[@name='agree']");
    By continueButton = By.xpath("//input[@value='Continue']");
    By newsletter = By.xpath("//input[@name='newsletter']");
    By alertText = By.xpath("//div[@class='alert alert-danger alert-dismissible']");
    By alertsAll = By.xpath("//div[@class='text-danger']");

    //Constructor(s)
    public RegistrationPage(WebDriver driver, ExcelDataGetter edg) {
        this.driver = driver;
        wait =new WebDriverWait(driver, Duration.ofSeconds(40) );
        this.edg = edg;
    }



    //Method(s)


    // Get method for title
    public By getTitle(){
        return title;
    }

    public boolean getFieldsClearance() { return goToSuccessPage; }


    // Registration page run for registering a user
    public void registrationRun(char yn, int i){
        printTheLine();
        fillAllFields(i);
        this.i =i;
        newsletterSelect(edg.getCellData(i, "Newsletter").toLowerCase().charAt(0));
        if (edg.getCellData(i, "Privacy Policy").toUpperCase().trim()=="TRUE")  clickAgree();

        clickContinue();
        printTheAtters();
        goToSuccessPage();
    }


    // Print the alters
    public void printTheAtters(){
        List<WebElement> warnings = driver.findElements(alertsAll);

      if(edg.getCellData(i, "First Name")=="" || edg.getCellData(i, "Last Name")==""
     || edg.getCellData(i, "E-Mail")=="" || edg.getCellData(i, "Telephone")==""
      || edg.getCellData(i, "Password")=="" || edg.getCellData(i, "Password")!= edg.getCellData(i, "Password Confirm")) {
            goToSuccessPage=false;
      }

      if(!goToSuccessPage){
          System.out.println("(Warning texts in the registration page):");
          for(int i=0;i<warnings.size();i++){
              System.out.println(warnings.get(i).getText());
          }
      }

    }

    // When The agree button is clicked
    public void positiveTestCaseForUserCreation(  ){
        clickAgree();
        clickContinue();
    }

    // When the agree button is not clicked
    public void negativeTestCaseForUserCreation( ){
        clickContinue();
    }

    // Verify the registration text
    public void printTheLine() {
        String text= wait.until(ExpectedConditions.visibilityOfElementLocated(registerTitle)).getText();  //driver.findElement(line).getText();
        if (text.equals(edg.getCellData(i, "Register")))  // if (text.equals("Register Account"))
            System.out.println("4. Line: "+ text);
    }

    // Filling all the fields here for registration
    public void fillAllFields(int i) {
        driver.findElement(firstName).sendKeys(edg.getCellData(i, "First Name"));
        driver.findElement(lastName).sendKeys(edg.getCellData(i, "Last Name"));

        if (edg.getCellData(i, "E-Mail")!="") {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMddHHmmss");
            LocalDateTime now = LocalDateTime.now();
            driver.findElement(email).sendKeys(dtf.format(now) + edg.getCellData(i, "E-Mail"));
        }
        driver.findElement(telephone).sendKeys(edg.getCellData(i, "Telephone"));
        driver.findElement(password).sendKeys(edg.getCellData(i, "Password"));
        driver.findElement(confirmedPassword).sendKeys(edg.getCellData(i, "Password Confirm"));
        System.out.println("5. All fields are filled");
    }

    // For clicking the agree button
    public void clickAgree( ) {
            wait.until(ExpectedConditions.elementToBeClickable(agreeTic)).click(); // driver.findElement(agreeTic).click();
            agree = driver.findElement(agreeTic).isSelected();
            System.out.println("9. Agree to privacy & policy is clicked");
    }

    // For clicking the continue button once everything is done filling
    public void clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();  //driver.findElement(continueButton).click();
        System.out.println("7.11. Continue button is pressed");

    }

    // When everything is okay, it will go to success page
    public void  goToSuccessPage (){
        if (agree && goToSuccessPage) {
            System.out.println(agree +" "+ goToSuccessPage);
            accountCreateSuccess(driver);
        }
        if(!agree ) System.out.println("8. When policy is not selected:\n"+wait.until(ExpectedConditions.elementToBeClickable(alertText)).getText());

    }

    //After creating a user, it will go to a success page where the result will be verified
    public  void accountCreateSuccess(WebDriver driver){
           AccountCreatedPage acp = new AccountCreatedPage(driver);
            System.out.println("When policy is selected:");
            acp.accountCreatedRun(edg, i);
    }

    // For selecting the newsletter radio button
    public void newsletterSelect(char value){
        switch (Character.toLowerCase(value)){
            case 'y':
                driver.findElements(newsletter).get(0).click();
                System.out.println("6. Yes is clicked for newsletter");
                break;
            case 'n':
                driver.findElements(newsletter).get(1).click();
                System.out.println("6. No is clicked for newsletter");
                break;
            default:
                System.out.println("6. Either choose y or n");
                break;
        }
    }



}
