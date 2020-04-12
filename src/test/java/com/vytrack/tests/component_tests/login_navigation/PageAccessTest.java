package com.vytrack.tests.component_tests.login_navigation;

import com.vytrack.base.TestBase;
import com.vytrack.utilities.ConfigurationReader;
import static org.testng.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class PageAccessTest extends TestBase {
    @Test
    public void vehicleContractsTestStoreManager(){
//TODO 1. Login to Vytrack as a store manager
        loginPage.login(ConfigurationReader.getProperty("usernameStoreManager"), ConfigurationReader.getProperty("passwordStoreManager"));

//TODO 2. Verify that you can access Vehicle contracts page
        dashboardPage.changeMenu("Fleet", "Vehicle Contracts");
        wait.until(ExpectedConditions.textToBePresentInElement(vehiclesPage.pageheader,"All Vehicle Contract"));
        assertEquals(vehicleContractsPage.pageheader.getText(), "All Vehicle Contract");
    }


    @Test
    public void vehicleContractsTestSalesManager(){
//TODO 1. Login to Vytrack as a sales manager
        loginPage.login(ConfigurationReader.getProperty("usernamSalesManager"), ConfigurationReader.getProperty("passwordSalesManager"));

//TODO 2. Verify that you can access Vehicle contracts page
        dashboardPage.changeMenu("Fleet", "Vehicle Contracts");
        wait.until(ExpectedConditions.textToBePresentInElement(vehiclesPage.pageheader,"All Vehicle Contract"));
        assertEquals(vehicleContractsPage.pageheader.getText(), "All Vehicle Contract");
    }


    @Test
    public void vehicleContractsTestDriver(){
//TODO 1. Login to Vytrack as a driver
        loginPage.login(ConfigurationReader.getProperty("usernameDriver"), ConfigurationReader.getProperty("passwordDriver"));

//TODO  2. Verify that you cannot access Vehicle contracts page
//      3. Message You do not have permission to perform this action. should be displayed
        dashboardPage.changeMenu("Fleet", "Vehicle Contracts");
        //wait.until(ExpectedConditions.textToBePresentInElement(vehiclesPage.pageheader,"All Vehicle Contract"));
        assertTrue(dashboardPage.doNotHavePermission.isDisplayed());
        assertEquals(dashboardPage.doNotHavePermission.getText(), "You do not have permission to perform this action.");


    }

}
