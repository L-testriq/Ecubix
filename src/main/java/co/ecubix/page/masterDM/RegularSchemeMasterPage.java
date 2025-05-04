package co.ecubix.page.masterDM;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import co.ecubix.site.core.PageObjectFacilitator;
import co.ecubix.utils.load_wait_stratagies.LoadAndWaitStrategies;
import co.ecubix.utils.read_files.CreateLocator;

public class RegularSchemeMasterPage extends PageObjectFacilitator {

	WebDriver driver;

	public RegularSchemeMasterPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void clickOnExecuteBtn() {
		CreateLocator createLocator = new CreateLocator();
		By execute_btn = createLocator.getLocator("regular_scheme", "execute");
		waitForLoaderToDisappear();
		clickButton(driver, execute_btn);
//		LoadAndWaitStrategies.waitTillElementIsClickable(driver, driver.findElement(execute_btn));
//		driver.findElement(execute_btn).click();
		waitForLoaderToDisappear();

	}

	public void waitForLoaderToDisappear() {
		CreateLocator createLocator = new CreateLocator();
		By load = createLocator.getLocator("article_master", "loader");
		LoadAndWaitStrategies.waitTillElementIsInvisible(driver, driver.findElement(load));
	}

	public int getSchemeNamePosition() {
		int position = 0;
		CreateLocator createLocator = new CreateLocator();
		By header = createLocator.getLocator("regular_scheme", "t_header");
		List<WebElement> datas = driver.findElement(header).findElements(By.tagName("th"));
		for (WebElement data : datas) {
			position += 1;
			if (data.getText().trim().equals("Scheme Name")) {
				break;
			}
		}
		return position;
	}

	public String getSchemeName() {
		int position = getSchemeNamePosition() - 1;
		CreateLocator createLocator = new CreateLocator();
		By row = createLocator.getLocator("regular_scheme", "t_first_row");
		List<WebElement> datas = driver.findElement(row).findElements(By.tagName("td"));
		return datas.get(position).getText();
	}

	public void goToGroupDetailOfFirstScheme() {
		CreateLocator createLocator = new CreateLocator();
		By row = createLocator.getLocator("regular_scheme", "t_first_row");
		List<WebElement> datas = driver.findElement(row).findElements(By.tagName("td"));
		datas.get(datas.size()-2).findElement(By.tagName("a")).click();
	}
	
	public String getSchemeNameFromDefineScheme() {
		CreateLocator createLocator = new CreateLocator();
		By name = createLocator.getLocator("regular_scheme", "scheme_name");
		return getTextFromField(name);
	}
	
	public void selectVariety() {
		CreateLocator createLocator = new CreateLocator();
		By var = createLocator.getLocator("regular_scheme", "variety");
		WebElement variety = driver.findElement(var);
		selectFromDropdown(variety, "dhrumit");
		waitForLoaderToDisappear();
	}
	
	public void selectArticle() {
		CreateLocator createLocator = new CreateLocator();
		By art = createLocator.getLocator("regular_scheme", "article");
		WebElement article = driver.findElement(art);
		selectFromDropdown(article, "Test article master - HB article [Te]");
	}
	
	public void enterGroupDetails() {
		CreateLocator createLocator = new CreateLocator();
		By point = createLocator.getLocator("regular_scheme", "point_on_scanning");
		By save_btn = createLocator.getLocator("regular_scheme", "save");
//		WebElement pointOnScan = driver.findElement(point);
		selectVariety();
		selectArticle();
		setTextInField(point, "2");
//		pointOnScan.sendKeys("2");
		clickButton(driver, save_btn);
//		driver.findElement(save_btn).click();
	}
	
	public Boolean DataSavedSuccessfully() {
		CreateLocator createLocator = new CreateLocator();
		By pop_alert = createLocator.getLocator("regular_scheme", "Data_Saved_successfully");
		WebElement alert = driver.findElement(pop_alert);
		return alert.isDisplayed();
	}
	
	public String getSuccessMessage() {
		CreateLocator createLocator = new CreateLocator();
		By pop_alert = createLocator.getLocator("regular_scheme", "successMessage");
//		WebElement messgage = driver.findElement(pop_alert);
		return getTextFromField(pop_alert);
	}
	
	public void closeSuccessPopAlert() {
		CreateLocator createLocator = new CreateLocator();
		By alert_OK = createLocator.getLocator("regular_scheme", "success_OK");
		clickButton(driver, alert_OK);
//		driver.findElement(alert_OK).click();
	}
	
	public void deleteRecord() {
		CreateLocator createLocator = new CreateLocator();
		By del = createLocator.getLocator("regular_scheme", "delete_btn");
		By del_cnf = createLocator.getLocator("regular_scheme", "Data_Saved_successfully");
		By cnf = createLocator.getLocator("regular_scheme", "confirm_del");
		clickButton(driver, del);
//		driver.findElement(del).click();
		LoadAndWaitStrategies.waitTillElementIsVisible(driver, driver.findElement(del_cnf));
		clickButton(driver, cnf);
//		driver.findElement(cnf).click();
	}
	
	public Boolean successGroupDeletion() {
		boolean alert;
		CreateLocator createLocator = new CreateLocator();
		By popUp = createLocator.getLocator("regular_scheme", "Data_Saved_successfully");
		By alert_OK = createLocator.getLocator("regular_scheme", "success_OK");
		LoadAndWaitStrategies.waitTillElementIsVisible(driver, driver.findElement(popUp));
		alert = driver.findElement(popUp).isDisplayed();
		clickButton(driver, alert_OK);
//		driver.findElement(alert_OK).click();
		return alert;
	}

	public void selectFromDropdown(WebElement element, String text) {
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}
}
