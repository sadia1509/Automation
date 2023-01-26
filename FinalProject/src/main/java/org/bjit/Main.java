package org.bjit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.data.ExcelDataGetter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.report.ExtentManager;
import org.report.Log;
import org.testng.SkipException;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    public static WebDriver getDriver(String browser){
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
                ExtentManager.logTest("Driver is Null");
                Log.logger("No browser is found named: " + browser);
                break;
        }
        return driver;
    }
}