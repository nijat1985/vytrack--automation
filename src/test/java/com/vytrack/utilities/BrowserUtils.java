package com.vytrack.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class BrowserUtils {
    public static List<String> getElementsText(List<WebElement> webElements){
        List<String> getElementsText = new ArrayList<>();
        for(WebElement element : webElements){
            String text = element.getText().trim();
            if (text.isEmpty()){
                text = element.getAttribute("innerHTML").trim();
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
}
