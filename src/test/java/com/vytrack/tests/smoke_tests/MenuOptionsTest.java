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
//TODO 1. Login to Vytrack as a  driver
        loginPage.login(ConfigurationReader.getProperty("usernameDriver"),ConfigurationReader.getProperty("passwordDriver"));

//TODO 2. Navigate to Fleet  -> Vehicles, verify page title  Car - Entities - System - Car - Entities - System, page name Cars
        dashboardPage.changeMenu("Fleet", "Vehicles");
        wait.until(ExpectedConditions.titleContains("Car"));
        assertEquals(driver.getTitle(),"Car - Entities - System - Car - Entities - System");
        assertEquals(vehiclesPage.pageheader.getText(),"Cars");

//TODO 3. Navigate to Customers  -> Accounts, verify page title Accounts - Customers, verify page name Accounts
        vehiclesPage.changeMenu("Customers", "Accounts");
        wait.until(ExpectedConditions.titleContains("Accounts"));
        assertEquals(driver.getTitle(),"Accounts - Customers");
        assertEquals(accountsPage.pageheader.getText(), "Accounts");

//TODO 4. Navigate to Customers -> Contacts, verify page title Contacts - Customers, verify page name Contacts
        accountsPage.changeMenu("Customers", "Contacts");
        wait.until(ExpectedConditions.titleContains("Contacts"));
        assertEquals(driver.getTitle(),"Contacts - Customers");
        assertEquals(contactsPage.pageheader.getText(), "Contacts");

//TODO 5. Navigate to Activities -> Calendar Events, verify page title Calendar Events - Activities, page name Calendar Events
        contactsPage.changeMenu("Activities","Calendar Events");
        wait.until(ExpectedConditions.titleContains("Calendar"));
        assertEquals(driver.getTitle(),"Calendar Events - Activities");
        assertEquals(calendarEventsPage.pageheader.getText(),"Calendar Events");
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
//TODO 1. Login to Vytrack as a store manager
        loginPage.login(ConfigurationReader.getProperty("usernameStoreManager"), ConfigurationReader.getProperty("passwordStoreManager"));

//TODO 2. Navigate to Dashboards -> Dashboard, verify page title Dashboard - Dashboards, verify page name Dashboard
        dashboardPage.changeMenu("Dashboards", "Dashboard");
        wait.until(ExpectedConditions.titleContains("Dashboard - Dashboards"));
        assertEquals(driver.getTitle(),"Dashboard - Dashboards");
        assertEquals(dashboardPage.pageheader.getText(),"Dashboard");

//TODO 3. Navigate to Fleet -> Vehicles, verify page title All - Car - Entities - System - Car - Entities - System, page name All Cars
        dashboardPage.changeMenu("Fleet", "Vehicles");
        wait.until(ExpectedConditions.titleContains("Car"));
        assertEquals(driver.getTitle(),"Car - Entities - System - Car - Entities - System");
        wait.until(ExpectedConditions.textToBePresentInElement(vehiclesPage.pageheader,"All Cars"));
        assertEquals(vehiclesPage.pageheader.getText(),"All Cars");

//TODO 4. Navigate to Customers -> Accounts, verify page title All - Accounts – Customers, verify page name All Accounts
        vehiclesPage.changeMenu("Customers", "Accounts");
        wait.until(ExpectedConditions.titleContains("Accounts - Customers"));
        assertEquals(driver.getTitle(),"Accounts - Customers");
        wait.until(ExpectedConditions.textToBePresentInElement(accountsPage.pageheader, "All Accounts"));
        assertEquals(accountsPage.pageheader.getText(), "All Accounts");

//TODO 5. Navigate to Customers -> Contacts, verify page title All - Contacts - Customers, verify page name All Contacts
        accountsPage.changeMenu("Customers", "Contacts");
        wait.until(ExpectedConditions.titleContains("All - Contacts - Customers"));
        assertEquals(driver.getTitle().trim(),"All - Contacts - Customers");
        wait.until(ExpectedConditions.textToBePresentInElement(contactsPage.pageheader,"All Contacts"));
        assertEquals(contactsPage.pageheader.getText(), "All Contacts");

//TODO 6. Navigate to Sales -> Opportunities, verify page title Open Opportunities - Opportunities - Sales, verify page name Open Opportunities
        contactsPage.changeMenu("Sales", "Opportunities");
        wait.until(ExpectedConditions.titleContains("Open Opportunities - Opportunities - Sales"));
        assertEquals(driver.getTitle(),"Open Opportunities - Opportunities - Sales");
        wait.until(ExpectedConditions.textToBePresentInElement(opportunitiesPage.pageheader,"Open Opportunities"));
        assertEquals(opportunitiesPage.pageheader.getText(), "Open Opportunities");

//TODO 7. Navigate to Activities -> Calls verify page title All - Calls - Activities, page name All Calls
        opportunitiesPage.changeMenu("Activities", "Calls");
        wait.until(ExpectedConditions.titleContains("All - Calls - Activities"));
        assertEquals(driver.getTitle().trim(),"All - Calls - Activities");
        wait.until(ExpectedConditions.textToBePresentInElement(callsPage.pageheader,"All Calls"));
        assertEquals(callsPage.pageheader.getText(), "All Calls");

//TODO 8. Navigate to Activities -> Calendar Events, verify page title Calendar Events - Activities, pagename All Calendar Events
        callsPage.changeMenu("Activities","Calendar Events");
        wait.until(ExpectedConditions.titleContains("All - Calendar Events - Activities"));
        assertEquals(driver.getTitle().trim(),"All - Calendar Events - Activities");
        wait.until(ExpectedConditions.textToBePresentInElement(calendarEventsPage.pageheader,"All Calendar Events"));
        assertEquals(calendarEventsPage.pageheader.getText(), "All Calendar Events");

    }

}
