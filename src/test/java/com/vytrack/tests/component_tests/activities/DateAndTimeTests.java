package com.vytrack.tests.component_tests.activities;

import com.vytrack.base.TestBase;
import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.ConfigurationReader;
import static org.testng.Assert.*;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Random;

public class DateAndTimeTests extends TestBase {

//TODO 1) Date Time, End date auto adjust
    @Test
    public void endDateAutoAdjust(){
        logger = report.createTest("Date Time, End date auto adjust tests");
        logger.info("1. Log in as Valid user");
        loginPage.login(ConfigurationReader.getProperty("usernameDriver"),ConfigurationReader.getProperty("passwordDriver"));

        logger.info("2. Go to Activities -> Calendar Events");
        dashboardPage.changeMenu("Activities", "Calendar Events");

        logger.info("3. Click on create new calendar event");
        calendarEventsPage.creatCalendarEventButton.click();

        logger.info("4. Change the start date to future date");
        calendarEventsPage.startDate.click();
        calendarEventsPage.nextButtonInStartDate.click();
        Random rd = new Random();
        calendarEventsPage.daysList.get(rd.nextInt(28)).click();

        logger.info("5. Verify that end date changes to the same date");
        assertEquals(calendarEventsPage.endDate.getText(),calendarEventsPage.startDate.getText());

        logger.info("6. Change back the start date to today’s date");
        calendarEventsPage.todayButtonInStratDate.click();

        logger.info("7. Verify that end date changes back to today’s date");
        assertEquals(calendarEventsPage.endDate.getAttribute("value"),calendarEventsPage.startDate.getAttribute("value"));

        logger.pass("Date Time, End date auto adjust tests PASSED");
    }


//TODO 2) Date Time, End time auto adjust
    @Test
    public void endTimeAutoAdjust() {
        logger = report.createTest("Date Time, End time auto adjust tests");
        logger.info("1. Log in as Valid user");
        loginPage.login(ConfigurationReader.getProperty("usernameDriver"),ConfigurationReader.getProperty("passwordDriver"));

        logger.info("2. Go to Activities -> Calendar Events");
        dashboardPage.changeMenu("Activities", "Calendar Events");

        logger.info("3. Click on create new calendar event");
        calendarEventsPage.creatCalendarEventButton.click();

        logger.info("4. Change the start time to any other time");
        calendarEventsPage.startTime.click();
        Random rd = new Random();
        calendarEventsPage.startTimeList.get(rd.nextInt(47)).click();

        logger.info("5. Verify that end time changes exactly 1 hours later");
        assertTrue(calendarEventsPage.compareTime(calendarEventsPage.startTime.getAttribute("value"),calendarEventsPage.endTime.getAttribute("value")));

        logger.pass("Date Time, End time auto adjust tests PASSED");
    }


//TODO 3) Date Time, End date/time auto adjust
    @Test
    public void endDateTimeAutoAdjust() {
        logger = report.createTest("Date Time, End time auto adjust tests");
        logger.info("1. Log in as Valid user");
        loginPage.login(ConfigurationReader.getProperty("usernameDriver"),ConfigurationReader.getProperty("passwordDriver"));

        logger.info("2. Go to Activities -> Calendar Events");
        dashboardPage.changeMenu("Activities", "Calendar Events");

        logger.info("3. Click on create new calendar event");
        wait.until(ExpectedConditions.elementToBeClickable(calendarEventsPage.creatCalendarEventButton));
        calendarEventsPage.creatCalendarEventButton.click();

        logger.info("4. Change the start time to 11.30 PM");
        calendarEventsPage.startTime.click();
        for (int i = 0; i < calendarEventsPage.startTimeList.size(); i++) {
            if (calendarEventsPage.startTimeList.get(i).getText().equals("11:30 PM")){
                calendarEventsPage.startTimeList.get(i).click();
                break;
            }
        }

        logger.info("5. Verify that end date shows tomorrows date");
        assertTrue(calendarEventsPage.compareDate(calendarEventsPage.startDate.getAttribute("value"),calendarEventsPage.endDate.getAttribute("value")));

        logger.info("6. Verify that end time is 12:30 AM");
        assertTrue(calendarEventsPage.compareTime(calendarEventsPage.startTime.getAttribute("value"),calendarEventsPage.endTime.getAttribute("value")));

        logger.pass("Date Time, End time auto adjust tests PASSED");
    }

}
