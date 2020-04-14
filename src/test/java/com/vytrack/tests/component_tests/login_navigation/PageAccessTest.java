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
        logger = report.createTest("Vehicle Contracts Test with Store Manager");
        logger.info("1. Login to Vytrack as a store manager");
        loginPage.login(ConfigurationReader.getProperty("usernameStoreManager"), ConfigurationReader.getProperty("passwordStoreManager"));

        logger.info("2. Go to Vehicle Contracts page");
        dashboardPage.changeMenu("Fleet", "Vehicle Contracts");
        wait.until(ExpectedConditions.textToBePresentInElement(vehiclesPage.pageheader,"All Vehicle Contract"));

        logger.info("3. Verify that you can access Vehicle contracts page");
        assertEquals(vehicleContractsPage.pageheader.getText(), "All Vehicle Contract");

        logger.pass("Vehicle Contracts Test with Store Manager PASSED");
    }


    @Test
    public void vehicleContractsTestSalesManager(){
        logger = report.createTest("Vehicle Contracts Test with Sales Manager");
        logger.info("1. Login to Vytrack as a sales manager");
        loginPage.login(ConfigurationReader.getProperty("usernamSalesManager"), ConfigurationReader.getProperty("passwordSalesManager"));

        logger.info("2. Go to Vehicle Contracts page");
        dashboardPage.changeMenu("Fleet", "Vehicle Contracts");
        wait.until(ExpectedConditions.textToBePresentInElement(vehiclesPage.pageheader,"All Vehicle Contract"));

        logger.info("3. Verify that you can access Vehicle contracts page");
        assertEquals(vehicleContractsPage.pageheader.getText(), "All Vehicle Contract");

        logger.pass("Vehicle Contracts Test with Sales Manager PASSED");
    }


    @Test
    public void vehicleContractsTestDriver(){
        logger = report.createTest("Vehicle Contracts Test with Driver");
        logger.info("1. Login to Vytrack as a driver");
        loginPage.login(ConfigurationReader.getProperty("usernameDriver"), ConfigurationReader.getProperty("passwordDriver"));

        logger.info("2. Go to Vehicle Contracts page");
        dashboardPage.changeMenu("Fleet", "Vehicle Contracts");

        logger.info("3. Verify that you cannot access Vehicle contracts page");
        assertNotEquals(vehicleContractsPage.pageheader.getText(), "All Vehicle Contract");

        logger.info("4. Message You do not have permission to perform this action. should be displayed");
        assertTrue(dashboardPage.doNotHavePermission.isDisplayed());
        assertEquals(dashboardPage.doNotHavePermission.getText(), "You do not have permission to perform this action.");

        logger.pass("Vehicle Contracts Test with Driver PASSED");
    }

}
