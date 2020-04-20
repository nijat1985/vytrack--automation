package com.vytrack.tests.component_tests.fleet;

import com.vytrack.base.TestBase;
import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.Driver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class VehiclesTests extends TestBase {

    @Test(priority = 1)
    public void createTestData(){
        loginPage.login(ConfigurationReader.getProperty("usernameStoreManager"), ConfigurationReader.getProperty("passwordStoreManager"));
        dashboardPage.changeMenu("Fleet", "Vehicles");
        for (int i = 0; i < 10; i++) {
            vehiclesPage.createCar();
            vehiclesPage.changeMenu("Fleet", "Vehicles");
        }

    }



    //TODO 1) Verify default order

    @Test(priority = 2)
    public void verifyDefaultOrder(){
        logger = report.createTest("Verify default order test");
        logger.info("1. Log in as Valid user");
        loginPage.login(ConfigurationReader.getProperty("usernameStoreManager"), ConfigurationReader.getProperty("passwordStoreManager"));

        logger.info("Go to Fleet -> Vehicles");
        dashboardPage.changeMenu("Fleet", "Vehicles");

        logger.info("Verify that all records that are displayed are sorted by LICENSE PLATE in Ascending order");
        assertTrue(BrowserUtils.isListSortedAscending(vehiclesPage.tableDataByHeader("License Plate")));

        logger.info("Click on header LICENSE PLATE again");
        vehiclesPage.tableHeader("License Plate").click();

        logger.info("Verify that all records that are displayed are sorted by LICENSE PLATE in Descending order");
        assertTrue(BrowserUtils.isListSortedDescending(vehiclesPage.tableDataByHeader("License Plate")));

        logger.pass("Verify default order test PASSED");
    }


//TODO 2) Verify that all records that are displayed can be sorted by DRIVER column
    @Test(priority = 3)
    public void verifySortedByDriver() throws InterruptedException {
        logger = report.createTest("Verify Sorted By Driver");
        logger.info("1. Log in as Valid user");
        loginPage.login(ConfigurationReader.getProperty("usernameStoreManager"), ConfigurationReader.getProperty("passwordStoreManager"));

        logger.info("Go to Fleet -> Vehicles");
        dashboardPage.changeMenu("Fleet", "Vehicles");

        logger.info("Click on column name DRIVER to sort the events by title");
        BrowserUtils.wait(2);
        vehiclesPage.tableHeader("Driver").click();

        BrowserUtils.wait(2);

        vehiclesPage.viewPerPage.click();
        vehiclesPage.viewPerPageList.get(vehiclesPage.viewPerPageList.size()-1).click();

        BrowserUtils.wait(2);

        logger.info("Verify that all records that are displayed are sorted by DRIVER in Ascending order");
        assertTrue(BrowserUtils.isListSortedAscending(vehiclesPage.tableDataByHeader("Driver")));

        logger.info("Click on header DRIVER again");
        vehiclesPage.tableHeader("Driver").click();

        BrowserUtils.wait(2);

        logger.info("Verify that all records that are displayed are sorted by DRIVER in Descending order");
        assertTrue(BrowserUtils.isListSortedDescending(vehiclesPage.tableDataByHeader("Driver")));

        logger.pass("Verify Sorted By Driver PASSED");
    }


//TODO 3) Select All checkbox
    @Test(priority = 4)
    public void selectAllCheckbox(){
        logger = report.createTest("Select All checkbox test");
        logger.info("Log in as Valid user");
        loginPage.login(ConfigurationReader.getProperty("usernameStoreManager"), ConfigurationReader.getProperty("passwordStoreManager"));

        logger.info("Go to Fleet -> Vehicles");
        dashboardPage.changeMenu("Fleet", "Vehicles");

        logger.info("Verify that none of the checkboxes on the left side of the table are selected");
        for(WebElement each : vehiclesPage.checkBoxList){
            assertFalse(each.isSelected());
        }

        logger.info("Click on the checkbox in the headers row");
        vehiclesPage.checkAll.click();

        logger.info("Verify that all of the checkboxes are now selected");
        for(WebElement each : vehiclesPage.checkBoxList){
            assertTrue(each.isSelected());
        }

    }

}


//This code bellow is about create data and then clean up. But I didn't find solution scroll to the right in order to be able to click delete. May be I will find it in the future.

//    @Test
//    public void test(){
//        loginPage.login(ConfigurationReader.getProperty("usernameStoreManager"), ConfigurationReader.getProperty("passwordStoreManager"));
//        dashboardPage.changeMenu("Fleet", "Vehicles");
//        for (int i = 0; i < 1; i++) {
//            vehiclesPage.createCar();
//            vehiclesPage.changeMenu("Fleet", "Vehicles");
//        }
//
//        vehiclesPage.tableHeader("Location").click();
//        vehiclesPage.tableHeader("Location").click();
//        List<WebElement> location = vehiclesPage.tableDataByHeader("Location");
//        for (int i = 0; i < location.size(); i++) {
//            BrowserUtils.wait(1);
//            if (location.get(i).getText().startsWith("Nijat")){
//                wait.until(ExpectedConditions.elementToBeClickable(location.get(i)));
//                location.get(i).click();
//                wait.until(ExpectedConditions.elementToBeClickable(vehiclesPage.delete));
//                vehiclesPage.delete.click();
//                wait.until(ExpectedConditions.elementToBeClickable(vehiclesPage.deleteConfirmation));
//                vehiclesPage.deleteConfirmation.click();
//                location = vehiclesPage.tableDataByHeader("Location");
//            }
//        }
//    }
