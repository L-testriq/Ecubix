package project_ecubix;

import org.testng.annotations.AfterMethod;



import org.testng.annotations.Test;

import org.testng.Assert;

import co.ecubix.page.masterDM.ArticleMasterPage;
import co.ecubix.page.masterDM.MasterDataManagementPage;
import co.ecubix.site.core.DriverManagementCore;
import co.ecubix.users.login.UsersLogin;
import co.ecubix.web.welcome.WelcomePage;

public class Test_ArticleCreation extends DriverManagementCore {

	UsersLogin userLogin;
	WelcomePage welcomePage;
	MasterDataManagementPage masterDataPage;
	ArticleMasterPage articleMaster;
	String article_code = ArticleCode();

	@Test
	public void test_ArticleCreation() throws Exception {
		System.out.println(Thread.currentThread().threadId());
		userLogin = new UsersLogin(driver);
		welcomePage = userLogin.webUserLogin();
		masterDataPage = welcomePage.goToMasterDataManagement();
		articleMaster = masterDataPage.goToArticleMasterPage();
		articleMaster.enterArticleDetails(article_code);
		Assert.assertTrue(articleMaster.DataSavedSuccessfully());
		Assert.assertEquals(articleMaster.getSuccessMessage(), "Data save Successfully.");
		articleMaster.closeSuccessPopAlert();
		articleMaster.enterSKUInfo();
		articleMaster.saveSKUInfo();
		Assert.assertTrue(articleMaster.DataSavedSuccessfully());
		Assert.assertEquals(articleMaster.getSuccessMessage(), "Data updated successfully.");
		articleMaster.closeSuccessPopAlert();
		articleMaster.clickOnViewTab();
		articleMaster.clickOnExecuteBtn();
		Assert.assertTrue(articleMaster.verifyArticleIsListed(article_code));
	}
	
	@AfterMethod
	public void tearDown() {
		driver.close();
	}
}
