package co.ecubix.page.masterDM;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import co.ecubix.site.core.PageObjectFacilitator;
import co.ecubix.utils.read_files.CreateLocator;

public class CustomerMasterPage extends PageObjectFacilitator {

	WebDriver driver;

	public CustomerMasterPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void clickOnEnterTab() {
		CreateLocator createLocator = new CreateLocator();
		By enter_tab = createLocator.getLocator("customer_master", "entry_tab");
		clickButton(driver, enter_tab);
//		driver.findElement(enter_tab).click();
	}

	public void clickOnViewTab() {
		CreateLocator createLocator = new CreateLocator();
		By view_tab = createLocator.getLocator("customer_master", "view_tab");
		clickButton(driver, view_tab);
//		driver.findElement(view_tab).click();
	}

	public void viewAddedUser() {
		clickOnViewTab();
		CreateLocator createLocator = new CreateLocator();
		By type_select = createLocator.getLocator("customer_master", "view_cType");
		By search = createLocator.getLocator("customer_master", "view_cType_search");
		By select_search = createLocator.getLocator("customer_master", "view_cType_select");
		By execute_btn = createLocator.getLocator("customer_master", "execute_btn");
//		WebElement typeSelect = driver.findElement(type_select);
//		WebElement searchType = driver.findElement(search);
//		WebElement selectSearch = driver.findElement(select_search);
//		WebElement executeButton = driver.findElement(execute_btn);
		clickButton(driver, type_select);
		setTextInField(search, "Plant");
//		typeSelect.click();
//		searchType.sendKeys("Plant");
		clickButton(driver, select_search);
		clickButton(driver, execute_btn);
//		selectSearch.click();
//		executeButton.click();
	}

	public Boolean verifyCustomerIsListed(String code) {
		boolean codeFound = false;
		CreateLocator createLocator = new CreateLocator();
		By search_code = createLocator.getLocator("customer_master", "find_custcode");
		By searched_result = createLocator.getLocator("customer_master", "searched_customer");
		driver.findElement(search_code).sendKeys(code);
		List<WebElement> datas = driver.findElement(searched_result).findElements(By.tagName("td"));
		for (WebElement data : datas) {
			if (data.getText().trim().equals(code)) {
				codeFound = true;
				break;
			}
		}
		return codeFound;
	}

	public void clickOnAddUser() {
		CreateLocator createLocator = new CreateLocator();
		By addUser = createLocator.getLocator("customer_master", "add_user");
		clickButton(driver, addUser);
//		driver.findElement(addUser).click();
	}

	public void enterCustomerDetails(String code) throws InterruptedException {
		CreateLocator createLocator = new CreateLocator();
		By c_type = createLocator.getLocator("customer_master", "customer_type");
		By c_code = createLocator.getLocator("customer_master", "customer_code");
		By c_name = createLocator.getLocator("customer_master", "customer_name");
		By c_mobile = createLocator.getLocator("customer_master", "customer_number");
		By c_address = createLocator.getLocator("customer_master", "customer_address");
		By c_pincode = createLocator.getLocator("customer_master", "customer_pincode");
		By state_select = createLocator.getLocator("customer_master", "state");
		By city_select = createLocator.getLocator("customer_master", "city");
		By saveBtn = createLocator.getLocator("customer_master", "save_button");
		WebElement customerType = driver.findElement(c_type);
//		WebElement customerCode = driver.findElement(c_code);
//		WebElement customerName = driver.findElement(c_name);
//		WebElement customerMobile = driver.findElement(c_mobile);
//		WebElement customerAddress = driver.findElement(c_address);
//		WebElement customerPincode = driver.findElement(c_pincode);
		WebElement state = driver.findElement(state_select);
		WebElement city = driver.findElement(city_select);
//		WebElement save_btn = driver.findElement(saveBtn);
		clickOnEnterTab();
		selectFromDropdown(customerType, "Plant/Toller");
		setTextInField(c_code, code);
//		customerCode.sendKeys(code);
		setTextInField(c_name, "Testriq_000");
//		customerName.sendKeys("Testriq_000");
		setTextInField(c_mobile, MobileNumber());
//		customerMobile.sendKeys(MobileNumber());
		setTextInField(c_address, "New Delhi");
//		customerAddress.sendKeys("New Delhi");
//		customerPincode.sendKeys("123456");
		setTextInField(c_pincode, "123456");
		selectFromDropdown(state, "Delhi");
		selectFromDropdown(city, "New Delhi");
		clickButton(driver, saveBtn);
//		save_btn.click();
	}

	public void enterUserDetails(String userid) {
		clickOnAddUser();
		CreateLocator createLocator = new CreateLocator();
		By u_Id = createLocator.getLocator("customer_master", "user_id");
		By u_name = createLocator.getLocator("customer_master", "user_firstname");
		By u_pass = createLocator.getLocator("customer_master", "user_password");
		By u_cnfPass = createLocator.getLocator("customer_master", "user_cnfpassword");
		By u_mobile = createLocator.getLocator("customer_master", "user_mobile");
		By save_btn = createLocator.getLocator("customer_master", "user_save");
//		WebElement userID = driver.findElement(u_Id);
//		WebElement userFirstName = driver.findElement(u_name);
//		WebElement userPassword = driver.findElement(u_pass);
//		WebElement userCnfPassword = driver.findElement(u_cnfPass);
//		WebElement userMobileNo = driver.findElement(u_mobile);
//		WebElement saveBtn = driver.findElement(save_btn);
		setTextInField(u_Id, userid);
		setTextInField(u_name, "Testriq");	
		setTextInField(u_pass, "Test@123");
		setTextInField(u_cnfPass, "Test@123");
		setTextInField(u_mobile, MobileNumber());
		clickButton(driver, save_btn);
//		userID.sendKeys(userid);
//		userFirstName.sendKeys("Testriq");
//		userPassword.sendKeys("Test@123");
//		userCnfPassword.sendKeys("Test@123");
//		userMobileNo.sendKeys(MobileNumber());
//		saveBtn.click();
	}

	public void selectFromDropdown(WebElement element, String text) {
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}

	public Boolean DataSavedSuccessfully() {
		CreateLocator createLocator = new CreateLocator();
		By pop_alert = createLocator.getLocator("customer_master", "Data_Saved_successfully");
		WebElement alert = driver.findElement(pop_alert);
		return alert.isDisplayed();
	}

	public String getSuccessMessage() {
		CreateLocator createLocator = new CreateLocator();
		By pop_alert = createLocator.getLocator("customer_master", "successMessage");
		return getTextFromField(pop_alert);
//		WebElement messgage = driver.findElement(pop_alert);
//		return messgage.getText();
	}

	public void closeSuccessPopAlert() {
		CreateLocator createLocator = new CreateLocator();
		By alert_OK = createLocator.getLocator("customer_master", "success_OK");
		clickButton(driver, alert_OK);
//		driver.findElement(alert_OK).click();
	}

	public static String MobileNumber() {
		Random random = new Random();
		int number = 1000000000 + random.nextInt(900000000);
		return String.valueOf(number);
	}

}
