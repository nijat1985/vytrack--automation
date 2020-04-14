package com.vytrack.tests.smoke_tests;

import com.vytrack.base.TestBase;
import com.vytrack.utilities.ConfigurationReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

public class MenuOptionsTest extends TestBase {

    /*
    1. Login    to Vytrack    as a  driver
    2. Navigate    to Fleet  -> Vehicles, verify page   title  Car - Entities - System - Car  - Entities -
        System,    page   name   Cars
    3. Navigate    to Customers  -> Accounts, verify page   title  Accounts   - Customers,   verify page
        name   Accounts
    4. Navigate    to Customers  -> Contacts, verify page   title  Contacts - Customers,  verify page   name
        Contacts
    5. Navigate    to Activities -> Calendar Events, verify page   title  Calendar   Events - Activities,  page
        name   Calendar   Events
     */
    @Test
    public void driver() throws InterruptedException {
        logger = report.createTest("Smoke test with driver");

        logger.info("1. Login to Vytrack as a driver");
        loginPage.login(ConfigurationReader.getProperty("usernameDriver"),ConfigurationReader.getProperty("passwordDriver"));

        logger.info("2. Navigate to Fleet  -> Vehicles");
        dashboardPage.changeMenu("Fleet", "Vehicles");
        wait.until(ExpectedConditions.titleContains("Car"));

        logger.info("verify page title  Car - Entities - System - Car - Entities - System");
        assertEquals(driver.getTitle(),"Car - Entities - System - Car - Entities - System");

        logger.info("verify page name Cars");
        assertEquals(vehiclesPage.pageheader.getText(),"Cars");

        logger.info("3. Navigate to Customers  -> Accounts");
        vehiclesPage.changeMenu("Customers", "Accounts");
        wait.until(ExpectedConditions.titleContains("Accounts"));

        logger.info("verify page title Accounts - Customers");
        assertEquals(driver.getTitle(),"Accounts - Customers");

        logger.info("verify page name Accounts");
        assertEquals(accountsPage.pageheader.getText(), "Accounts");

        logger.info("4. Navigate to Customers -> Contacts");
        accountsPage.changeMenu("Customers", "Contacts");
        wait.until(ExpectedConditions.titleContains("Contacts"));

        logger.info("verify page title Contacts - Customers");
        assertEquals(driver.getTitle(),"Contacts - Customers");

        logger.info("verify page name Contacts");
        assertEquals(contactsPage.pageheader.getText(), "Contacts");

        logger.info("5. Navigate to Activities -> Calendar Events");
        contactsPage.changeMenu("Activities","Calendar Events");
        wait.until(ExpectedConditions.titleContains("Calendar"));

        logger.info("verify page title Calendar Events - Activities");
        assertEquals(driver.getTitle(),"Calendar Events - Activities");

        logger.info("page name Calendar Events");
        assertEquals(calendarEventsPage.pageheader.getText(),"Calendar Events");

        logger.pass("Smoke test with driver Passed");
    }

    /*
    1. Login to Vytrack as a store manager
2. Navigate to Dashboards -> Dashboard, verify page title Dashboard - Dashboards, verify page name Dashboard
3. Navigate to Fleet -> Vehicles, verify page title All - Car - Entities - System - Car - Entities - System, page name All Cars
4. Navigate to Customers -> Accounts, verify page title All - Accounts – Customers, verify page name All Accounts
5. Navigate to Customers -> Contacts, verify page title All - Contacts - Customers, verify page name All Contacts
6. Navigate to Sales -> Opportunities, verify page title Open Opportunities - Opportunities - Sales, verify page name Open Opportunities
7. Navigate to Activities -> Calls verify page title All - Calls - Activities, page name All Calls
8. Navigate to Activities -> Calendar Events, verify page title Calendar Events - Activities, page
name All Calendar Events
     */
    @Test
    public void storeManager() throws InterruptedException {
        logger = report.createTest("Smoke test with a store manager");
        logger.info("1. Login to Vytrack as a store manager");
        loginPage.login(ConfigurationReader.getProperty("usernameStoreManager"), ConfigurationReader.getProperty("passwordStoreManager"));

        logger.info("2. Navigate to Dashboards -> Dashboard");
        dashboardPage.changeMenu("Dashboards", "Dashboard");
        wait.until(ExpectedConditions.titleContains("Dashboard - Dashboards"));

        logger.info("verify page title Dashboard - Dashboards");
        assertEquals(driver.getTitle(),"Dashboard - Dashboards");

        logger.info("verify page name Dashboard");
        assertEquals(dashboardPage.pageheader.getText(),"Dashboard");

        logger.info("3. Navigate to Fleet -> Vehicles");
        dashboardPage.changeMenu("Fleet", "Vehicles");
        wait.until(ExpectedConditions.titleContains("Car"));

        logger.info("verify page title All - Car - Entities - System - Car - Entities - System");
        assertEquals(driver.getTitle(),"Car - Entities - System - Car - Entities - System");
        wait.until(ExpectedConditions.textToBePresentInElement(vehiclesPage.pageheader,"All Cars"));

        logger.info("page name All Cars");
        assertEquals(vehiclesPage.pageheader.getText(),"All Cars");

        logger.info("4. Navigate to Customers -> Accounts");
        vehiclesPage.changeMenu("Customers", "Accounts");
        wait.until(ExpectedConditions.titleContains("Accounts - Customers"));

        logger.info("verify page title All - Accounts – Customers");
        assertEquals(driver.getTitle(),"Accounts - Customers");
        wait.until(ExpectedConditions.textToBePresentInElement(accountsPage.pageheader, "All Accounts"));

        logger.info("verify page name All Accounts");
        assertEquals(accountsPage.pageheader.getText(), "All Accounts");

        logger.info("5. Navigate to Customers -> Contacts");
        accountsPage.changeMenu("Customers", "Contacts");
        wait.until(ExpectedConditions.titleContains("All - Contacts - Customers"));

        logger.info("verify page title All - Contacts - Customers");
        assertEquals(driver.getTitle().trim(),"All - Contacts - Customers");
        wait.until(ExpectedConditions.textToBePresentInElement(contactsPage.pageheader,"All Contacts"));

        logger.info("verify page name All Contacts");
        assertEquals(contactsPage.pageheader.getText(), "All Contacts");

        logger.info("6. Navigate to Sales -> Opportunities");
        contactsPage.changeMenu("Sales", "Opportunities");
        wait.until(ExpectedConditions.titleContains("Open Opportunities - Opportunities - Sales"));

        logger.info("verify page title Open Opportunities - Opportunities - Sales");
        assertEquals(driver.getTitle(),"Open Opportunities - Opportunities - Sales");
        wait.until(ExpectedConditions.textToBePresentInElement(opportunitiesPage.pageheader,"Open Opportunities"));

        logger.info("verify page name Open Opportunities");
        assertEquals(opportunitiesPage.pageheader.getText(), "Open Opportunities");

        logger.info("7. Navigate to Activities -> Calls");
        opportunitiesPage.changeMenu("Activities", "Calls");
        wait.until(ExpectedConditions.titleContains("All - Calls - Activities"));

        logger.info("verify page title All - Calls - Activities");
        assertEquals(driver.getTitle().trim(),"All - Calls - Activities");
        wait.until(ExpectedConditions.textToBePresentInElement(callsPage.pageheader,"All Calls"));

        logger.info("page name All Calls");
        assertEquals(callsPage.pageheader.getText(), "All Calls");

        logger.info("8. Navigate to Activities -> Calendar Events");
        callsPage.changeMenu("Activities","Calendar Events");
        wait.until(ExpectedConditions.titleContains("All - Calendar Events - Activities"));

        logger.info("verify page title Calendar Events - Activities");
        assertEquals(driver.getTitle().trim(),"All - Calendar Events - Activities");
        wait.until(ExpectedConditions.textToBePresentInElement(calendarEventsPage.pageheader,"All Calendar Events"));

        logger.info("pagename All Calendar Events");
        assertEquals(calendarEventsPage.pageheader.getText(), "All Calendar Events");

        logger.pass("Smoke test with a store manager passed");
    }

}
