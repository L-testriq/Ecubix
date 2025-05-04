package co.ecubix.web.welcome;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import co.ecubix.page.masterDM.MasterDataManagementPage;
import co.ecubix.site.core.PageObjectFacilitator;
import co.ecubix.utils.load_wait_stratagies.LoadAndWaitStrategies;
import co.ecubix.utils.read_files.CreateLocator;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class WelcomePage extends PageObjectFacilitator {

	WebDriver driver;
	
	public WelcomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".wel_t h1")
	private WebElement pageTitle;
	
	@FindBy(xpath = "//div[text()='Reports']/ancestor::li")
	private WebElement report;
	
	@FindBy(xpath = "//div[text()='Master Data Management']/ancestor::li")
	private WebElement masterDataManagement;
	
	@AndroidFindBy(xpath = "//android.widget.ImageView")
	private List<WebElement> galary;
	
	@AndroidFindBy(xpath = "(//android.widget.FrameLayout[@resource-id='com.sec.android.gallery3d:id/deco_view_layout'])[1]")
	private WebElement photos;
	
	public void openQRImage() throws InterruptedException {
//		galary.get(2).click();
//		Thread.sleep(2000);
		photos.click();
		Thread.sleep(2000);
	}
	
	public MasterDataManagementPage goToMasterDataManagement() {
		CreateLocator createLocator = new CreateLocator();
		By master_DM = createLocator.getLocator("welcome_page", "master_DM");
		By master_DM_title = createLocator.getLocator("welcome_page", "master_DM_title");
		clickButton(driver, master_DM);
//		driver.findElement(master_DM).click();
		WebElement title =  driver.findElement(master_DM_title);
		LoadAndWaitStrategies.waitTillElementIsVisible(driver, title);
		return new MasterDataManagementPage(driver);
	}
	
	public void goToReports() {
		CreateLocator createLocator = new CreateLocator();
		By reports = createLocator.getLocator("welcome_page", "reports");
		clickButton(driver, reports);
		//driver.findElement(reports).click();
	}
	
	
}
