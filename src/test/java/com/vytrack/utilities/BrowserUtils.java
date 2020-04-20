package com.vytrack.utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class BrowserUtils {
    public static List<String> getElementsText(List<WebElement> webElements){
        List<String> getElementsText = new ArrayList<>();
        for(WebElement element : webElements){
            String text = element.getText().trim();
            if (text.isEmpty()){
                text = element.getAttribute("innerHTML");
            }
            getElementsText.add(text);
        }
        return getElementsText;
    }

    public static void waitForUIOverlay(){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 5);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.loader-mask.shown")));
    }

    public static void sendKeysWithJs(WebElement locator, String text){
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].setAttribute('value', '" + text + "')", locator);
    }

    /**
     * Clicks on an element using JavaScript
     *
     * @param element
     */
    public static void clickWithJS(WebElement element) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", element);
    }

    /*
     * takes screenshot
     * @param name
     * take a name of a test and returns a path to screenshot takes
     */
    public static String getScreenshot(String name) throws IOException {
        String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);
        String target = System.getProperty("user.dir") + "/test-output/Screenshots/" + name + date + ".png";
        File finalDestination = new File(target);
        FileUtils.copyFile(source, finalDestination);
        return target;
    }

    public static boolean isListSortedAscending(List<WebElement> list){
        boolean isListSorted = false;
        List<String> listTextRowMaterial = getElementsText(list);
        List<String> listUppercase = new ArrayList<>();
        for (int i = 0; i < listTextRowMaterial.size(); i++) {
            listUppercase.add(listTextRowMaterial.get(i).toUpperCase());
        }
        List<String> listToSort = new ArrayList<>(listUppercase);
        Collections.sort(listToSort);
        isListSorted = listToSort.equals(listUppercase);
        return isListSorted;
    }

    public static boolean isListSortedDescending(List<WebElement> list){
        boolean isListSortedDescending = false;
        List<String> listTextRowMaterial = getElementsText(list);
        List<String> listUppercase = new ArrayList<>();
        for (int i = 0; i < listTextRowMaterial.size(); i++) {
            listUppercase.add(listTextRowMaterial.get(i).toUpperCase());
        }
        List<String> listToSort = new ArrayList<>(listUppercase);
        Collections.sort(listToSort,Collections.reverseOrder());
        isListSortedDescending = listToSort.equals(listUppercase);

        return isListSortedDescending;
    }

    public static void wait(int seconds) {
        try {
            Thread.sleep(1000 * seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
