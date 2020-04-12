package com.vytrack.tests.component_tests.activities;

import com.vytrack.base.TestBase;
import com.vytrack.utilities.ConfigurationReader;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import static org.testng.Assert.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class DailyRepeatTests extends TestBase {

    @BeforeMethod
    public void setUpDailyRepeatTests(){
//TODO 1. Log in as Valid user
        loginPage.login(ConfigurationReader.getProperty("usernameDriver"),ConfigurationReader.getProperty("passwordDriver"));

//TODO 2. Go to Activities -> Calendar Events
        dashboardPage.changeMenu("Activities", "Calendar Events");

//TODO 3. Click on create new calendar event
        wait.until(ExpectedConditions.invisibilityOf(calendarEventsPage.loader));
        calendarEventsPage.creatCalendarEventButton.click();

//TODO 4. Click on Repeat checkbox
        wait.until(ExpectedConditions.invisibilityOf(calendarEventsPage.loader));
        calendarEventsPage.repeatCheckBox.click();
    }


//TODO 1) Daily repeat option, Repeat every, summary

    @Test
    public void summary() throws InterruptedException {

//TODO 5. Verify that Daily is selected by default
        Select repeatsDropdown = new Select(calendarEventsPage.repeatsDropdown);
        assertEquals(repeatsDropdown.getFirstSelectedOption().getText(),"Daily");

//TODO 6. Verify day(s) checkbox is selected and default value is 1
        assertTrue(calendarEventsPage.daysRadioButton.isSelected());
        assertEquals(calendarEventsPage.daysBox.getAttribute("value"),"1");

//TODO 7. Verify summary says Daily every 1 day
        assertEquals(calendarEventsPage.summaryMessage.getText(),"Daily every 1 day");

//TODO 8. Check the weekday checkbox
        calendarEventsPage.weekdayRadioButton.click();

//TODO 9. Verify that days input now disabled
        assertFalse(calendarEventsPage.daysBox.isEnabled());

//TODO 10. Verify summary says Daily every weekday
        assertEquals(calendarEventsPage.summaryMessage.getText(),"Daily, every weekday");
    }



//TODO 2) Daily repeat option, Repeat every, default values
    @Test
    public void defaultValues(){

//TODO 5. Verify that Daily is selected by default
        Select repeatsDropdown = new Select(calendarEventsPage.repeatsDropdown);
        assertEquals(repeatsDropdown.getFirstSelectedOption().getText(),"Daily");

//TODO 6. Verify day(s) checkbox is selected and default value is 1
        assertTrue(calendarEventsPage.daysRadioButton.isSelected());
        assertEquals(calendarEventsPage.daysBox.getAttribute("value"),"1");

//TODO 7. Verify summary says Daily every 1 day
        assertEquals(calendarEventsPage.summaryMessage.getText(),"Daily every 1 day");

    }

//TODO 3) Daily repeat option, Repeat every day(s), error messages
    @Test
    public void daysErrorMessages(){

//TODO 5. Test the day(s) input entering different values (boundary value analysis)
//TODO 6. Verify error messages The value have not to be less than 1. and The value have not to be more than 99. occur when values are too big or small
//TODO 7. Verify that error messages disappear when valid values are entered
        List<String> dataForTest = Arrays.asList("0","1","99","100");
        for(String each : dataForTest) {
            calendarEventsPage.daysBox.clear();

            calendarEventsPage.daysBox.sendKeys(each);
            if (Integer.parseInt(each) < 1) {
                assertEquals(calendarEventsPage.daysErrorMessages.getText(), "The value have not to be less than 1.");
            } else if (Integer.parseInt(each) > 99) {
                assertEquals(calendarEventsPage.daysErrorMessages.getText(), "The value have not to be more than 99.");
            } else {
                assertFalse(calendarEventsPage.daysErrorMessages.isDisplayed());
            }
        }

    }

//TODO 4) Daily repeat option, Repeat every day(s), functionality

    @Test
    public void daysFunctionality(){

//TODO 5. Enter random value to the day(s) field
        Random rd = new Random();
        calendarEventsPage.daysBox.clear();
        String randomNumber = "" + rd.nextInt(99);
        calendarEventsPage.daysBox.sendKeys(randomNumber);
        calendarEventsPage.repeatsDropdown.click();

//TODO 6. Verify that Summary says Daily every <random number> day
        String expectedSummary = "Daily every " + randomNumber + " days";
        assertEquals(calendarEventsPage.summaryMessage.getText(),expectedSummary);

//TODO 7. Enter another random value to the day(s) field
        calendarEventsPage.daysBox.clear();
        randomNumber = "" + rd.nextInt(99);
        calendarEventsPage.daysBox.sendKeys(randomNumber);
        calendarEventsPage.repeatsDropdown.click();

//TODO 8. Verify that Summary updated with Daily every <random number> day
        expectedSummary = "Daily every " + randomNumber + " days";
        assertEquals(calendarEventsPage.summaryMessage.getText(),expectedSummary);

    }

//TODO 5) Daily repeat option, blank fields

    @Test
    public void blankFields(){

//TODO 5. Clear the value of the day(s) field
        calendarEventsPage.daysBox.clear();

//TODO 6. Message This value should not be blank. should come up
        String expectedMessage = "This value should not be blank.";
        assertEquals(calendarEventsPage.daysErrorMessages.getText(),expectedMessage);

//TODO 7. Enter a valid value to the day(s) field the
        Random rd = new Random();
        String randomValidNumber = "" + rd.nextInt(99);
        calendarEventsPage.daysBox.sendKeys(randomValidNumber);

//TODO 8. Message This value should not be blank. should disappear
        assertFalse(calendarEventsPage.daysErrorMessages.isDisplayed());

//TODO 9. Clear the value of the After occurrences field
        calendarEventsPage.afterRadioButton.click();
        calendarEventsPage.occurrencesBox.click();
        calendarEventsPage.repeatsDropdown.click();


//TODO 10. Message This value should not be blank. should come up
        assertEquals(calendarEventsPage.occurrencesErrorMessages.getText(),expectedMessage);

//TODO 11. Enter a valid value to the After occurrences field the
        randomValidNumber = "" + rd.nextInt(999);
        calendarEventsPage.occurrencesBox.sendKeys(randomValidNumber);

//TODO 12. Message This value should not be blank. should disappear
        assertFalse(calendarEventsPage.occurrencesErrorMessages.isDisplayed());
    }

//TODO 6) Daily repeat option, Ends, error messages

    @Test
    public void endsErrorMessages(){

//TODO 5. Test the After occurrences input entering different values (boundary value analysis)
//TODO 6. Verify error messages The value have not to be less than 1. and The value have not to be more than 99. occur when values are too big or small
//TODO 7. Verify that error messages disappear when valid values are entered
        List<String> dataForTest = Arrays.asList("0","1","999","1000");
        for(String each : dataForTest) {
            calendarEventsPage.occurrencesBox.click();
            calendarEventsPage.occurrencesBox.clear();

            calendarEventsPage.occurrencesBox.sendKeys(each);
            calendarEventsPage.repeatsDropdown.click();
            if (Integer.parseInt(each) < 1) {
                assertEquals(calendarEventsPage.occurrencesErrorMessages.getText(), "The value have not to be less than 1.");
            } else if (Integer.parseInt(each) > 999) {
                assertEquals(calendarEventsPage.occurrencesErrorMessages.getText(), "The value have not to be more than 999.");
            } else {
                assertFalse(calendarEventsPage.occurrencesErrorMessages.isDisplayed());
            }
        }

    }


//TODO 7) Daily repeat option, Ends, functionality
    @Test
    public void endsFunctionality(){

//TODO 5. Enter random value to the After occurrences field
        Random rd = new Random();
        String randomValidNumber = "" + rd.nextInt(999);
        calendarEventsPage.afterRadioButton.click();
        calendarEventsPage.occurrencesBox.sendKeys(randomValidNumber);
        calendarEventsPage.repeatsDropdown.click();

//TODO 6. Verify that Summary says Daily every 1 day, end after <random number> occurrences
        String expectedSummaryMessage = "Daily every 1 day, end after " + randomValidNumber + " occurrences";
        assertEquals(calendarEventsPage.summaryMessage.getText(), expectedSummaryMessage);

//TODO 7. Enter another random value to the After occurrences field
        randomValidNumber = "" + rd.nextInt(999);
        calendarEventsPage.occurrencesBox.clear();
        calendarEventsPage.occurrencesBox.sendKeys(randomValidNumber);
        calendarEventsPage.repeatsDropdown.click();

//TODO 8. Verify that Summary updated with Daily every 1 day, end after <random number> occurrences
        expectedSummaryMessage = "Daily every 1 day, end after " + randomValidNumber + " occurrences";
        assertEquals(calendarEventsPage.summaryMessage.getText(), expectedSummaryMessage);
    }

}
