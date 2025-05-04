package project_ecubix;

import org.testng.annotations.AfterClass;


import org.testng.annotations.Test;

import org.testng.Assert;

import co.ecubix.page.masterDM.CustomerMasterPage;
import co.ecubix.page.masterDM.MasterDataManagementPage;
import co.ecubix.site.core.DriverManagementCore;
import co.ecubix.users.login.UsersLogin;
import co.ecubix.web.welcome.WelcomePage;

public class Test_CustormerCreationAndUserCreation extends DriverManagementCore {

	UsersLogin userLogin;
	WelcomePage welcomePage;
	MasterDataManagementPage masterDataPage;
	CustomerMasterPage customerPage;
	String customer_code = customerCode();
	String user_Id = "test"+customerCode();

	@Test
	public void test_CustormerCreationAndUserCreation() throws Exception {
		System.out.println(Thread.currentThread().threadId());
		userLogin = new UsersLogin(driver);
		welcomePage = userLogin.webUserLogin();
		masterDataPage = welcomePage.goToMasterDataManagement();
		customerPage = masterDataPage.goToCustomerMasterPage();
		customerPage.enterCustomerDetails(customer_code);
		Assert.assertTrue(customerPage.DataSavedSuccessfully());
		Assert.assertEquals(customerPage.getSuccessMessage(), "Customer Data Saved Successfully.");
		customerPage.closeSuccessPopAlert();
		customerPage.enterUserDetails(user_Id);
		Assert.assertTrue(customerPage.DataSavedSuccessfully());
		Assert.assertEquals(customerPage.getSuccessMessage(), "Data Added Successfully");
		customerPage.closeSuccessPopAlert();
		customerPage.viewAddedUser();
		Assert.assertTrue(customerPage.verifyCustomerIsListed(customer_code));
	}
	
	@AfterClass
	public void tearDown() {
		driver.close();
	}

}
