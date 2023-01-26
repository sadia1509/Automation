package org.testcase;

import org.bjit.Main;
import org.data.ExcelDataGetter;
import org.openqa.selenium.WebDriver;
import org.report.ExtentManager;
import org.report.Log;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.*;

public class TestCaseClass {
    WebDriver driver;
    ExcelDataGetter excel;
    @BeforeTest(alwaysRun = true)
    @Parameters({"browser", "baseUrl"})
    public void setDriver(String browser, String url) {
        ExtentManager.startReport();
        driver = Main.getDriver(browser);
        if (driver==null)   throw new SkipException("Driver is null");
        driver.get(url);
    }

  //  @BeforeMethod(alwaysRun = true)
  //  public void setBaseURL() { driver.get(baseurl); }

    @Test(priority = 0)
    public void verifyTitle() {
        ExtentManager.logTest("Title verification");
        excel = new ExcelDataGetter("FinalProject.xlsx", "Sheet1");
        Assert.assertEquals(excel.getCellData(1, "Column"), driver.getTitle(), "Title is not working");
        Log.logger("Done");
    }

    @AfterMethod(alwaysRun = true)
    public void endMethod(ITestResult result) throws Exception {
        ExtentManager.getResult(result, driver);
    }

    @AfterTest(alwaysRun = true)
    public void endSession() {
        ExtentManager.stopReport();
        if (driver==null)   throw new SkipException("Driver is null");
        driver.quit();
    }



}
