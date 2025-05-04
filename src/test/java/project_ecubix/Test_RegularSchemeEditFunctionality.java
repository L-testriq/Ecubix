package project_ecubix;

import org.testng.annotations.Test;

import org.testng.Assert;
import org.testng.annotations.AfterClass;

import co.ecubix.page.masterDM.MasterDataManagementPage;
import co.ecubix.page.masterDM.RegularSchemeMasterPage;
import co.ecubix.site.core.DriverManagementCore;
import co.ecubix.users.login.UsersLogin;
import co.ecubix.web.welcome.WelcomePage;

public class Test_RegularSchemeEditFunctionality extends DriverManagementCore {

	UsersLogin userLogin;
	WelcomePage welcomePage;
	MasterDataManagementPage masterDataPage;
	RegularSchemeMasterPage regularSchemePage;

	@Test
	public void test_RegularSchemeEditFunctionality() throws Exception {
		System.out.println(Thread.currentThread().threadId());
		userLogin = new UsersLogin(driver);
		welcomePage = userLogin.webUserLogin();
		masterDataPage = welcomePage.goToMasterDataManagement();
		regularSchemePage = masterDataPage.goToRegularSchemeMaster();
		regularSchemePage.clickOnExecuteBtn();
		String expect_schemeName = regularSchemePage.getSchemeName();
		regularSchemePage.goToGroupDetailOfFirstScheme();
		String actual_schemeName = regularSchemePage.getSchemeNameFromDefineScheme();
		Assert.assertEquals(actual_schemeName, expect_schemeName);
		regularSchemePage.enterGroupDetails();
		Assert.assertTrue(regularSchemePage.DataSavedSuccessfully());
		Assert.assertEquals(regularSchemePage.getSuccessMessage(), "Data Saved Successfully.");
		regularSchemePage.closeSuccessPopAlert();
		regularSchemePage.deleteRecord();
		Assert.assertTrue(regularSchemePage.successGroupDeletion());
	}
	
	
	@AfterClass
	public void tearDown() {
		driver.close();
	}

}
