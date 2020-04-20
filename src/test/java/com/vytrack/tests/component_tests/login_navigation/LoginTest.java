package com.vytrack.tests.component_tests.login_navigation;

import com.vytrack.base.TestBase;
import com.vytrack.utilities.ConfigurationReader;
import static org.testng.Assert.*;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {
    @Test
    public void positive() throws InterruptedException {
        logger = report.createTest("Login test positive");

        logger.info("1. Login to Vytrack as a store manager");
        loginPage.login(ConfigurationReader.getProperty("usernameStoreManager"), ConfigurationReader.getProperty("passwordStoreManager"));

        logger.info("2. Verify name of the store manager is displayed on top right");
        String actualNameOfStoreManager = dashboardPage.accountHolderName.getText();
        assertEquals(actualNameOfStoreManager, ConfigurationReader.getProperty("storeManagerName"));

        logger.info("3. Verify Dashboad page is open");
        assertEquals(driver.getTitle(), "Dashboard");

        logger.info("4. Log out");
        dashboardPage.logout();

        logger.info("5. Login to Vytrack as a sales manager");
        loginPage.login(ConfigurationReader.getProperty("usernamSalesManager"), ConfigurationReader.getProperty("passwordSalesManager"));

        logger.info("6. Verify Dashboad page is open");
        assertEquals(driver.getTitle(), "Dashboard");

        logger.info("7. A different name should be displayed on top right");
        assertNotEquals(dashboardPage.accountHolderName.getText(), actualNameOfStoreManager);
        String actualNameOfSalesManager = dashboardPage.accountHolderName.getText();

        logger.info("8. Log out");
        dashboardPage.logout();

        logger.info("9. Login to Vytrack as a driver");
        loginPage.login(ConfigurationReader.getProperty("usernameDriver"), ConfigurationReader.getProperty("passwordDriver"));

        logger.info("10. Verify Dashboad/Quick Launchpad page is open");
        wait.until(ExpectedConditions.titleIs("Dashboard"));
        assertEquals(driver.getTitle(), "Dashboard");
        assertEquals(dashboardPage.pageheader.getText(),"Quick Launchpad");

        logger.info("11. A different name should be displayed on top right");
        assertNotEquals(dashboardPage.accountHolderName.getText(), actualNameOfSalesManager);

        logger.pass("Login test positive passed");
    }

    @Test
    public void negative(){
        logger = report.createTest("Login test negative");
        logger.info("1. Open Vytrack login page 2. Enter valid username and invalid password information 3. Click login");
        loginPage.login(ConfigurationReader.getProperty("usernameDriver"),"whatever");

        logger.info("4. Message Invalid user name or password. should be displayed");
        assertTrue(loginPage.errorMessage.isDisplayed());
        assertEquals(loginPage.errorMessage.getText(), "Invalid user name or password.");

        logger.info("5. Page title should be same");
        assertEquals(driver.getTitle(),"Login");

        logger.info("url should be same");
        assertEquals(driver.getCurrentUrl(),ConfigurationReader.getProperty("url"));

        logger.pass("Login test negative passed");
    }
}
