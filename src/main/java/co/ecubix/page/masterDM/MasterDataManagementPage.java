package co.ecubix.page.masterDM;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import co.ecubix.site.core.PageObjectFacilitator;
import co.ecubix.utils.read_files.CreateLocator;

public class MasterDataManagementPage extends PageObjectFacilitator {

	WebDriver driver;

	public MasterDataManagementPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	public CustomerMasterPage goToCustomerMasterPage() {
		CreateLocator createLocator = new CreateLocator();
		By master_option = createLocator.getLocator("master_data", "master_subOptions");
		List<WebElement> suboptions = driver.findElements(master_option);
		suboptions.get(goToSubMaster("Customer Master")).click();
		return new CustomerMasterPage(driver);
 	}
	
	public BusinessGeoMasterPage goToBusinessGeoMasterPage() {
		CreateLocator createLocator = new CreateLocator();
		By master_option = createLocator.getLocator("master_data", "master_subOptions");
		List<WebElement> suboptions = driver.findElements(master_option);
		suboptions.get(goToSubMaster("Business Geo Master")).click();
		return new BusinessGeoMasterPage(driver);
 	}
	
	public C2BMappingPage goToC2BMasterPage() {
		CreateLocator createLocator = new CreateLocator();
		By master_option = createLocator.getLocator("master_data", "master_subOptions");
		List<WebElement> suboptions = driver.findElements(master_option);
		suboptions.get(goToSubMaster("C2B Mapping")).click();
		return new C2BMappingPage(driver);
 	}
	
	public ArticleMasterPage goToArticleMasterPage() {
		CreateLocator createLocator = new CreateLocator();
		By master_option = createLocator.getLocator("master_data", "master_subOptions");
		List<WebElement> suboptions = driver.findElements(master_option);
		suboptions.get(goToSubMaster("Article Master")).click();
		return new ArticleMasterPage(driver);
 	}
	
	public void openSchemeMenu() {
		CreateLocator createLocator = new CreateLocator();
		By menu = createLocator.getLocator("master_data", "side_menu");
		List<WebElement> lists = driver.findElements(menu);
		lists.get(goToMenu("Master")).click();
		lists.get(goToMenu("Scheme")).click();
	}
	
	public RegularSchemeMasterPage goToRegularSchemeMaster() {
		openSchemeMenu();
		CreateLocator createLocator = new CreateLocator();
		By scheme_option = createLocator.getLocator("master_data", "scheme_subOption");
		List<WebElement> suboptions = driver.findElements(scheme_option);
		suboptions.get(goToSubScheme("Regular Scheme Master")).click();
		return new RegularSchemeMasterPage(driver);
	}
	
	public int goToSubMaster(String option) {
		switch(option){
		case "Employee Master":
			return 0;
		case "Block Type":
			return 1;
		case "Variety Master":
			return 2;
		case "Template Configuration":
			return 3;
		case "Customer Master":
			return 4;
		case "Hardware Configuration":
			return 5;
		case "Price Master":
			return 6;
		case "Article Master":
			return 7;
		case "Variety Information":
			return 8;
		case "Commodity Master":
			return 9;
		case "Business Geo Master":
			return 10;
		case "C2C Mapping":
			return 11;
		case "Political to Business Mapping":
			return 12;
		case "E2B Mapping":
			return 13;
		case "C2B Mapping":
			return 14;
		}
		return 0;
	}
	
	public int goToSubScheme(String sub) {
		switch(sub){
		case "Regular Scheme Master":
			return 0;
		case "Scheme Allocation":
			return 1;
		case "Scheme Points":
			return 2;
		case "Custom Scheme Master":
			return 3;
		}
		return 0;
	}
	
	public int goToMenu(String menu) {
		switch(menu){
		case "Master":
			return 0;
		case "Scheme":
			return 1;
		case "Settings":
			return 2;
		}
		return 0;
	}

}
