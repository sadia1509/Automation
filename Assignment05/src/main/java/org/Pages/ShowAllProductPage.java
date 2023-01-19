package org.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class ShowAllProductPage {
    WebDriver driver;
    String productNameAll, productName;
    WebDriverWait wait;

    //Locator(s)
    By dropdownSort = By.id("input-sort");
    By fristProduct = By.xpath("//h4");
    By firstProductPrice = By.xpath("//p[@class='price']");

    //Constructor(s)
    public ShowAllProductPage(WebDriver driver, String productNameAll) {
        this.driver = driver;
        this.productNameAll=productNameAll;
        wait =new WebDriverWait(driver, Duration.ofSeconds(40) );
    }



    //Method(s)

    // Run showAll method
    public void sort(String sortOption){
        clickSortDropdown();
        clickSortingOption( sortOption ); //
        checkFirstProduct();
     //   clickSortingOption(  "Price (High > Low)");
     //   checkFirstProduct();
    }

    // Click on the dropdown for sorting to select a sort
    public void clickSortDropdown(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(dropdownSort)).click();
        System.out.println("Dropdown for sorting is clicked");
    }

    // Sorting option is selected here
    public void clickSortingOption(  String msg){
        Select droplist = new Select(driver.findElement(dropdownSort));
        droplist.selectByVisibleText(msg);
        System.out.println("15.17. "+msg+" is selected");
    }

    // First product is selected here
    public void checkFirstProduct(){
        List<WebElement> options = driver.findElements(fristProduct);
        productName= options.get(0).getText();
     //  WebElement firstProduct = wait.until(ExpectedConditions.visibilityOfElementLocated(fristProduct));
     //   productName=firstProduct.getText();
        System.out.println( productName +" is selected");
        String [] str= wait.until(ExpectedConditions.visibilityOfElementLocated(firstProductPrice)).getText().split(" ", 2);
        System.out.println( "16. Price: "+ str[0] );
        //    firstProduct.click();
    }

    // Get the product name [for the 1st product here] after sorting
    public String getProductName(){
        return productName;
    }


}
