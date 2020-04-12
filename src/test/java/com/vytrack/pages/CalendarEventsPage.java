package com.vytrack.pages;

import com.vytrack.base.VytrackPageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class CalendarEventsPage extends VytrackPageBase {
    @FindBy(css = "a[title='Create Calendar event']")
    public WebElement creatCalendarEventButton;

    @FindBy(css = "input[class='input-small datepicker-input start hasDatepicker']")
    public WebElement startDate;

    @FindBy(css = "a[class='ui-datepicker-next ui-corner-all']")
    public WebElement nextButtonInStartDate;

    @FindBy(xpath = "//a[@href='#' and @class='ui-state-default']")
    public List<WebElement> daysList;

    @FindBy(xpath = "//button[@data-handler='today']")
    public WebElement todayButtonInStratDate;

    @FindBy(css = "input[class='input-small timepicker-input start ui-timepicker-input']")
    public WebElement startTime;

    @FindBy(css = "ul[class='ui-timepicker-list']>li")
    public List<WebElement> startTimeList;

    @FindBy(css = "input[class='input-small datepicker-input end hasDatepicker']")
    public WebElement endDate;

    @FindBy(css = "input[class='input-small timepicker-input end ui-timepicker-input']")
    public WebElement endTime;

    @FindBy(xpath = "//input[@data-name='recurrence-repeat']")
    public WebElement repeatCheckBox;

    @FindBy(className = "recurrence-repeats__select")
    public WebElement repeatsDropdown;

    @FindBy(css = ".loader-mask")
    public WebElement loader;

    @FindBy(xpath = "(//label[@data-role='control-section-switcher']/input[@type='radio'])[1]")
    public WebElement daysRadioButton;

    @FindBy(xpath = "(//label[@data-role='control-section-switcher']/input[@type='radio'])[2]")
    public WebElement weekdayRadioButton;

    @FindBy(xpath = "//span[.='day(s)']/preceding-sibling::input[@type='text']")
    public WebElement daysBox;

    @FindBy(css = ".control-group.recurrence-summary.alert-info>div[class='controls']")
    public WebElement summaryMessage;

    @FindBy(xpath = "//div[@data-name='recurrence-daily']//span[@class='validation-failed']")
    public WebElement daysErrorMessages;

    @FindBy(xpath = "(//label[@data-role='control-section-switcher']/input[@type='radio'])[4]")
    public WebElement afterRadioButton;

    @FindBy(xpath = "//span[.='occurrences']/preceding-sibling::input[@type='text']")
    public WebElement occurrencesBox;

    @FindBy(xpath = "//div[@data-name='recurrence-ends']//span[@class='validation-failed']")
    public WebElement occurrencesErrorMessages;


    public boolean compareTime(String startTime, String endTime){
        boolean anHourLater = false;
        int startHour = Integer.parseInt(startTime.split(":")[0]);
        int endHour = Integer.parseInt(endTime.split(":")[0]);

        String startMinute = startTime.split(":")[1];
        String endMinute = endTime.split(":")[1];

        if ((startHour == 12 && endHour == 1) && (startMinute.equals(endMinute))){
            anHourLater = true;
        }else if ((startHour == 11 && endHour == 12) && (startMinute.startsWith(endMinute.substring(0,2)))){
            anHourLater = true;
        }else if (((startHour + 1) == endHour) && startMinute.equals(endMinute)){
            anHourLater = true;
        }
        return anHourLater;
    }

    public boolean compareDate(String startDate, String endDate){
        boolean tomorrowsDate = false;
        int startYear = Integer.parseInt(startDate.split(",")[1].trim());
        int endYear = Integer.parseInt(endDate.split(",")[1].trim());

        int startDay = Integer.parseInt(startDate.split(",")[0].split(" ")[1].trim());
        int endDay = Integer.parseInt(endDate.split(",")[0].split(" ")[1].trim());

        List<String> monthsList = Arrays.asList("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");
        List<Integer> monthNumberList = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12);

        int startMonth = 0;
        int endMonth = 0;

        for (int i = 0; i < monthsList.size(); i++) {
            if (monthsList.get(i).equals(startDate.split(",")[0].split(" ")[0])){
                startMonth = monthNumberList.get(i);
            }
            if (monthsList.get(i).equals(endDate.split(",")[0].split(" ")[0])){
                endMonth = monthNumberList.get(i);
            }
        }

        LocalDate startDateFull = LocalDate.of(startYear,startMonth,startDay);
        startDateFull = startDateFull.plusDays(1);
        LocalDate endDateFull = LocalDate.of(endYear,endMonth,endDay);
        if (startDateFull.toString().equals(endDateFull.toString())){
            tomorrowsDate = true;
        }

        return tomorrowsDate;
    }




}
