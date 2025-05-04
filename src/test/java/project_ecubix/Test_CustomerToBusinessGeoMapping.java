package project_ecubix;

import org.testng.annotations.Test;


import org.testng.annotations.AfterClass;

import co.ecubix.page.masterDM.MasterDataManagementPage;
import co.ecubix.site.core.DriverManagementCore;
import co.ecubix.users.login.UsersLogin;
import co.ecubix.web.welcome.WelcomePage;

public class Test_CustomerToBusinessGeoMapping extends DriverManagementCore {

	UsersLogin userLogin;
	WelcomePage welcomePage;
	MasterDataManagementPage masterDataPage;


	@Test
	public void test_CustormerCreationAndUserCreation() throws Exception {
		System.out.println(Thread.currentThread().threadId());
		userLogin = new UsersLogin(driver);
		welcomePage = userLogin.webUserLogin();
		masterDataPage = welcomePage.goToMasterDataManagement();
		masterDataPage.goToC2BMasterPage();
	}

	@Test
	public void test() throws Exception {
		System.out.println(Thread.currentThread().threadId());
	}
	
	
	@AfterClass
	public void tearDown() {
		driver.close();
	}

}
