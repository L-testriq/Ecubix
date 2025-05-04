package co.ecubix.page.masterDM;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import co.ecubix.site.core.PageObjectFacilitator;
import co.ecubix.utils.read_files.CreateLocator;

public class BusinessGeoMasterPage extends PageObjectFacilitator {

	WebDriver driver;

	public BusinessGeoMasterPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void selectType(String geo_type) {
		CreateLocator createLocator = new CreateLocator();
		By type = createLocator.getLocator("business_geo_master", "geo_type");
		WebElement geoType = driver.findElement(type);
		selectGeoType(geoType, geo_type);
	}

	public void downloadData() throws InterruptedException {
		CreateLocator createLocator = new CreateLocator();
		By downloadBtn = createLocator.getLocator("business_geo_master", "downloadData");
		clickButton(driver, downloadBtn);
//		driver.findElement(downloadBtn).click();
		Thread.sleep(2000);
	}

	public void addFile(String filePath) {
		CreateLocator createLocator = new CreateLocator();
		By file_Path = createLocator.getLocator("business_geo_master", "addFile");
		driver.findElement(file_Path).sendKeys(filePath);
	}

	public void clickIOnCheckData() {
		CreateLocator createLocator = new CreateLocator();
		By checkBtn = createLocator.getLocator("business_geo_master", "checkData");
		clickButton(driver, checkBtn);
//		driver.findElement(checkBtn).click();
	}

	public Boolean DataSavedSuccessfully() {
		CreateLocator createLocator = new CreateLocator();
		By pop_alert = createLocator.getLocator("business_geo_master", "Data_Saved_successfully");
		WebElement alert = driver.findElement(pop_alert);
		return alert.isDisplayed();
	}

	public String getSuccessMessage() {
		CreateLocator createLocator = new CreateLocator();
		By pop_alert = createLocator.getLocator("business_geo_master", "successMessage");
		return getTextFromField(pop_alert);
//		WebElement messgage = driver.findElement(pop_alert);
//		return messgage.getText();
	}
	
	public void closeSuccessPopAlert() {
		CreateLocator createLocator = new CreateLocator();
		By alert_OK = createLocator.getLocator("business_geo_master", "success_OK");
		clickButton(driver, alert_OK);
//		driver.findElement(alert_OK).click();
	}
	
	public void clickOnCancel() throws InterruptedException {
		CreateLocator createLocator = new CreateLocator();
		By cancelBtn = createLocator.getLocator("business_geo_master", "cancel_btn");
		clickButton(driver, cancelBtn);
//		driver.findElement(cancelBtn).click();
		Thread.sleep(2000);
	}

	public void selectGeoType(WebElement element, String type) {
		Select select = new Select(element);
		select.selectByVisibleText(type);
	}
}
