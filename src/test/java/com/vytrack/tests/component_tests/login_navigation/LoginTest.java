package com.vytrack.tests.component_tests.login_navigation;

import com.vytrack.base.TestBase;
import com.vytrack.utilities.ConfigurationReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {
    @Test
    public void positive() throws InterruptedException {
//TODO 1. Login to Vytrack as a store manager
        loginPage.login(ConfigurationReader.getProperty("usernameStoreManager"), ConfigurationReader.getProperty("passwordStoreManager"));

//TODO 2. Verify name of the store manager is displayed on top right
        assertEquals(dashboardPage.accountHolderName.getText(), ConfigurationReader.getProperty("storeManagerName"));
        String actualNameOfStoreManager = dashboardPage.accountHolderName.getText();

//TODO 3. Verify Dashboad page is open
        assertEquals(driver.getTitle(), "Dashboard");

//TODO 4. Log out
        dashboardPage.logout();

//TODO 5. Login to Vytrack as a sales manager
        loginPage.login(ConfigurationReader.getProperty("usernamSalesManager"), ConfigurationReader.getProperty("passwordSalesManager"));

//TODO 6. Verify Dashboad page is open
        assertEquals(driver.getTitle(), "Dashboard");

//TODO 7. A different name should be displayed on top right
        assertNotEquals(dashboardPage.accountHolderName.getText(), actualNameOfStoreManager);
        String actualNameOfSalesManager = dashboardPage.accountHolderName.getText();
//TODO 8. Log out
        dashboardPage.logout();

//TODO 9. Login to Vytrack as a driver
        loginPage.login(ConfigurationReader.getProperty("usernameDriver"), ConfigurationReader.getProperty("passwordDriver"));

//TODO 10. Verify Dashboad/Quick Launchpad page is open
        assertEquals(driver.getTitle(), "Dashboard");
        assertEquals(dashboardPage.pageheader.getText(),"Quick Launchpad");

//TODO 11. A different name should be displayed on top right
        assertNotEquals(dashboardPage.accountHolderName.getText(), actualNameOfSalesManager);
    }

    @Test
    public void negative(){
//TODO 1. Open Vytrack login page
//     2. Enter valid username and invalid password information
//     3. Click login
        loginPage.login(ConfigurationReader.getProperty("usernameDriver"),"whatever");

//TODO 4. Message Invalid user name or password. should be displayed
        assertTrue(loginPage.errorMessage.isDisplayed());
        assertEquals(loginPage.errorMessage.getText(), "Invalid user name or password.");

//TODO 5. Page title and url should be same
        assertEquals(driver.getTitle(),"Login");
        assertEquals(driver.getCurrentUrl(),ConfigurationReader.getProperty("url"));

    }
}
