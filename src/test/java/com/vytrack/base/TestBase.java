package com.vytrack.base;

import com.vytrack.pages.*;
import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class TestBase {
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

    @BeforeMethod
    public void setUp(){
        driver = Driver.getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 20);

        String url = ConfigurationReader.getProperty("url");
        driver.get(url);

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
    public void tearDown(){
        Driver.closeDriver();
    }

}
