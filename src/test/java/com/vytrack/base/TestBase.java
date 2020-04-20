package com.vytrack.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.vytrack.pages.*;
import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public abstract class TestBase {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected LoginPage loginPage;
    protected DashboardPage dashboardPage;
    protected VehiclesPage vehiclesPage;
    protected AccountsPage accountsPage;
    protected ContactsPage contactsPage;
    protected CalendarEventsPage calendarEventsPage;
    protected OpportunitiesPage opportunitiesPage;
    protected CallsPage callsPage;
    protected VehicleContractsPage vehicleContractsPage;
    protected static ExtentReports report;
    private ExtentHtmlReporter htmlReporter;
    protected static ExtentTest logger;

    @BeforeSuite
    public void setUpReport(){
        report = new ExtentReports();
        String reportPath = System.getProperty("user.dir") + "/test-output/report.html";
        htmlReporter = new ExtentHtmlReporter(reportPath);
        report.attachReporter(htmlReporter);

        htmlReporter.config().setReportName("Vytrack automation tests");

        report.setSystemInfo("Environment","QA1" );
        report.setSystemInfo("URL",ConfigurationReader.getProperty("url"));
        report.setSystemInfo("browser",ConfigurationReader.getProperty("browser"));
    }

    @Parameters("url")
    @BeforeMethod
    public void setUp(@Optional String url){


        driver = Driver.getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 20);

        if (url == null) {
            driver.get(ConfigurationReader.getProperty("url"));
        }else {
            driver.get(url);
        }

        loginPage = new LoginPage();
        dashboardPage = new DashboardPage();
        vehiclesPage = new VehiclesPage();
        accountsPage = new AccountsPage();
        contactsPage = new ContactsPage();
        calendarEventsPage = new CalendarEventsPage();
        opportunitiesPage = new OpportunitiesPage();
        callsPage = new CallsPage();
        vehicleContractsPage = new VehicleContractsPage();
    }

    @AfterMethod
    public void tearDown(ITestResult iTestResult) throws IOException {
        if (iTestResult.getStatus() == ITestResult.FAILURE){
            logger.fail(iTestResult.getName());
            String screenShot = BrowserUtils.getScreenshot(iTestResult.getName());
            logger.addScreenCaptureFromPath(screenShot);
        }

        Driver.closeDriver();
    }

    @AfterSuite
    public void tearDownReport(){
        report.flush();
    }

}
