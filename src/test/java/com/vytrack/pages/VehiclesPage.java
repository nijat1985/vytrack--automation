package com.vytrack.pages;

import com.github.javafaker.Faker;
import com.vytrack.base.VytrackPageBase;
import com.vytrack.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class VehiclesPage extends VytrackPageBase {

    @FindBy(xpath = "//div[@class='page-size pull-right form-horizontal']/div/div")
    public WebElement viewPerPage;

    @FindBy(xpath = "//div[@class='page-size pull-right form-horizontal']/div/div/ul/li")
    public List<WebElement> viewPerPageList;

    @FindBy(xpath = "//input[@data-role='select-row-cell']")
    public List<WebElement> checkBoxList;

    @FindBy(xpath = "//div[@class='btn-group dropdown']/button/input")
    public WebElement checkAll;

    @FindBy(xpath = "//a[@title='Create Car']")
    public WebElement createCar;

    @FindBy(xpath = "//input[@name='custom_entity_type[Tags][]']")
    public List<WebElement> tagCheckboxes;

    @FindBy(xpath = "//button[@data-handler='today']")
    public WebElement todayButtonInCalendarInCreateCar;

    @FindBy(xpath = "(//input[@placeholder='Choose a date'])[1]")
    public WebElement immatriculationDate;

    @FindBy(xpath = "(//input[@placeholder='Choose a date'])[2]")
    public WebElement firstContractDate;

    @FindBy(xpath = "(//span[@class='select2-chosen'])[1]")
    public WebElement transmission;

    @FindBy(xpath = "(//span[@class='select2-chosen'])[2]")
    public WebElement fuelType;

    @FindBy(xpath = "//button[@class='btn btn-success action-button']")
    public WebElement saveAndClose;

    @FindBy(css = ".select2-result-label")
    public List<WebElement> transmissionAndFuelTypeDropdown;

    @FindBy(xpath = "//a[@title='Delete Car']")
    public WebElement delete;

    @FindBy(xpath = "//a[@class='btn ok btn-danger']")
    public WebElement deleteConfirmation;


    public void createCar(){
        Faker fakeData = new Faker();
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(),10);
        wait.until(ExpectedConditions.elementToBeClickable(createCar));
        createCar.click();
        String licensePlate = fakeData.number().numberBetween(10,99) + fakeData.name().firstName().substring(0,2).toUpperCase() + fakeData.number().numberBetween(1000,9999);
        carsInformationFields("LicensePlate").sendKeys(licensePlate);
        tagCheckboxes.get(fakeData.number().numberBetween(1,7)).click();
        carsInformationFields("Driver").sendKeys(fakeData.name().fullName());
        carsInformationFields("Location").sendKeys("Nijat did this :)" + fakeData.address().cityName());
        carsInformationFields("ChassisNumber").sendKeys(fakeData.number().digits(8));
        carsInformationFields("ModelYear").sendKeys(fakeData.number().numberBetween(1990,2020)+"");
        carsInformationFields("LastOdometer").sendKeys(fakeData.number().numberBetween(0,400000)+"");
        immatriculationDate.click();
        todayButtonInCalendarInCreateCar.click();
        firstContractDate.click();
        todayButtonInCalendarInCreateCar.click();
        carsInformationFields("CatalogValue").sendKeys(fakeData.number().numberBetween(10000,99999)+"");
        carsInformationFields("SeatsNumber").sendKeys(fakeData.number().numberBetween(2,8)+"");
        carsInformationFields("DoorsNumber").sendKeys(fakeData.number().numberBetween(2,4)+"");
        carsInformationFields("Color").sendKeys(fakeData.color().name());
        transmission.click();
        transmissionAndFuelTypeDropdown.get(fakeData.number().numberBetween(0,1)).click();
        fuelType.click();
        transmissionAndFuelTypeDropdown.get(fakeData.number().numberBetween(0,4)).click();
        carsInformationFields("CO2Emissions").sendKeys(fakeData.number().digits(10));
        carsInformationFields("Horsepower").sendKeys(fakeData.number().numberBetween(4,500)+"");
        carsInformationFields("HorsepowerTaxation").sendKeys(fakeData.number().numberBetween(100,1000)+"");
        carsInformationFields("Power").sendKeys(fakeData.number().numberBetween(500,5000)+"");
        saveAndClose.click();
    }

    public WebElement carsInformationFields(String fieldName){
        String xpath = "//input[@name='custom_entity_type[" + fieldName + "]']";
        WebElement field = Driver.getDriver().findElement(By.xpath(xpath));
        return field;
    }

    public WebElement tableHeader(String header){
        String xpath = "//a[@class='grid-header-cell__link']/span[.='" + header + "']";
        WebElement tableHeader = Driver.getDriver().findElement(By.xpath(xpath));
        return tableHeader;
    }

    public List<WebElement> tableDataByHeader(String header){
        String xpath = "//td[@data-column-label='" + header + "']";
        List<WebElement> tableData = Driver.getDriver().findElements(By.xpath(xpath));
        return tableData;
    }


    public static void main(String[] args) {
        Faker faker = new Faker();
        System.out.println(faker.number().numberBetween(10,99));
        System.out.println(faker.name().firstName().substring(0,2).toUpperCase());
    }
}
