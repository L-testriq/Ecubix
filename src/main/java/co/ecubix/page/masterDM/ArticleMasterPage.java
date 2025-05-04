package co.ecubix.page.masterDM;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import co.ecubix.site.core.PageObjectFacilitator;
import co.ecubix.utils.load_wait_stratagies.LoadAndWaitStrategies;
import co.ecubix.utils.read_files.CreateLocator;

public class ArticleMasterPage extends PageObjectFacilitator {
	WebDriver driver;

	public ArticleMasterPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void clickOnEnterTab() {
		waitForLoaderToDisappear();
		CreateLocator createLocator = new CreateLocator();
		By enter_tab = createLocator.getLocator("article_master", "entry_tab");
		clickButton(driver, enter_tab);
	}

	public void clickOnViewTab() throws InterruptedException {
		CreateLocator createLocator = new CreateLocator();
		By view_tab = createLocator.getLocator("article_master", "view_tab");
		clickButton(driver, view_tab);
		Thread.sleep(2000);
	}

	public void selectCommodity() {
		CreateLocator createLocator = new CreateLocator();
		By com_select = createLocator.getLocator("article_master", "commodity");
		WebElement commodity = driver.findElement(com_select);
		selectFromDropdown(commodity, "tester - tester");
	}

	public void selectVariety() {
		CreateLocator createLocator = new CreateLocator();
		By com_select = createLocator.getLocator("article_master", "variety");
		WebElement commodity = driver.findElement(com_select);
		selectFromDropdown(commodity, "MD - Vmonali");
	}

	public void enterArticleDetails(String articleCode) {
		clickOnEnterTab();
		selectCommodity();
		selectVariety();
		CreateLocator createLocator = new CreateLocator();
		By a_code = createLocator.getLocator("article_master", "article_code");
		By a_name = createLocator.getLocator("article_master", "article_name");
		By shelfLife = createLocator.getLocator("article_master", "shelf_life");
		By a_weight = createLocator.getLocator("article_master", "article_weight");
		By innerBox = createLocator.getLocator("article_master", "inner_boxes");
		By save_btn = createLocator.getLocator("article_master", "save_button");
		setTextInField(a_code, articleCode);
		setTextInField(a_name, "Test" + articleCode);
		selectMaterialPackaging();
		setTextInField(shelfLife, "2");
		selectUOM();
		setTextInField(a_weight, "200");
		selectPackType();
		selectProductType();
		setTextInField(innerBox, "5");
//		clickButton(driver, save_btn);
		clickButton(driver, save_btn);
	}
	
	public Boolean verifyArticleIsListed(String code) {
		boolean codeFound = false;
		CreateLocator createLocator = new CreateLocator();
		By search_code = createLocator.getLocator("article_master", "find_article_code");
		By searched_result = createLocator.getLocator("article_master", "searched_list");
		driver.findElement(search_code).sendKeys(code);
		List<WebElement> datas = driver.findElement(searched_result).findElements(By.tagName("td"));
		for(WebElement data : datas) {
			if(data.getText().trim().equals(code)) {
				codeFound = true;
				break;
			}
		}
		return codeFound;
	}

	public void enterSKUInfo() {
		CreateLocator createLocator = new CreateLocator();
		By qnt = createLocator.getLocator("article_master", "sku_addQnt");
		setTextInField(qnt, "5");
	}

	public void saveSKUInfo() {
		CreateLocator createLocator = new CreateLocator();
		By save = createLocator.getLocator("article_master", "sku_save");
		clickButton(driver, save);
	}

	public void selectMaterialPackaging() {
		CreateLocator createLocator = new CreateLocator();
		By com_select = createLocator.getLocator("article_master", "material_packaging");
		WebElement commodity = driver.findElement(com_select);
		selectFromDropdown(commodity, "DRUM");
	}

	public void selectUOM() {
		CreateLocator createLocator = new CreateLocator();
		By com_select = createLocator.getLocator("article_master", "uom");
		WebElement commodity = driver.findElement(com_select);
		selectFromDropdown(commodity, "KG");
	}

	public void selectPackType() {
		CreateLocator createLocator = new CreateLocator();
		By com_select = createLocator.getLocator("article_master", "pack_type");
		WebElement commodity = driver.findElement(com_select);
		selectFromDropdown(commodity, "Primary");
	}

	public void selectProductType() {
		CreateLocator createLocator = new CreateLocator();
		By com_select = createLocator.getLocator("article_master", "product_type");
		WebElement commodity = driver.findElement(com_select);
		selectFromDropdown(commodity, "BASF VNS");
	}

	public Boolean DataSavedSuccessfully() {
		CreateLocator createLocator = new CreateLocator();
		By pop_alert = createLocator.getLocator("article_master", "Data_Saved_successfully");
		WebElement alert = driver.findElement(pop_alert);
		return alert.isDisplayed();
	}

	public void closeSuccessPopAlert() {
		CreateLocator createLocator = new CreateLocator();
		By alert_OK = createLocator.getLocator("article_master", "success_OK");
		clickButton(driver, alert_OK);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, 0);");

	}

	public String getSuccessMessage() {
		CreateLocator createLocator = new CreateLocator();
		By pop_alert = createLocator.getLocator("article_master", "successMessage");
		return getTextFromField(pop_alert);
	}
	
	public void clickOnExecuteBtn() {
		CreateLocator createLocator = new CreateLocator();
		By execute = createLocator.getLocator("article_master", "execute_btn");
		clickButton(driver, execute);
		waitForLoaderToDisappear();
	}
	
	public void waitForLoaderToDisappear() {
		CreateLocator createLocator = new CreateLocator();
		By load = createLocator.getLocator("article_master", "loader");
		LoadAndWaitStrategies.waitTillElementIsInvisible(driver, driver.findElement(load));
	}

	public void selectFromDropdown(WebElement element, String text) {
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}

}
