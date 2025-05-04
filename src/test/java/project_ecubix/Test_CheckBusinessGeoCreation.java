package project_ecubix;


import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;
import java.io.File;

import java.io.IOException;

import java.net.URISyntaxException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;

import co.ecubix.page.masterDM.BusinessGeoMasterPage;
import co.ecubix.page.masterDM.MasterDataManagementPage;
import co.ecubix.site.core.DriverManagementCore;
import co.ecubix.users.login.UsersLogin;
import co.ecubix.utils.excel.AddDataIntoBusinessGeo;
import co.ecubix.web.welcome.WelcomePage;

public class Test_CheckBusinessGeoCreation extends DriverManagementCore {

	UsersLogin userLogin;
	WelcomePage welcomePage;
	MasterDataManagementPage masterDataPage;
	BusinessGeoMasterPage businessGeoPage;
	AddDataIntoBusinessGeo addData;
	String randomNum = randomId();
	Object[] countryData = { 2025, "CON", "Test"+randomNum, "", "India", ""};
	Object[] asmData = { 2025, "ASM", "Test"+randomNum, "IN", "India", ""};
	Object[] tsmData = { 2025, "TSM", "Test"+randomNum, "ASM1", "India", ""};
	Object[] fdoData = { 2025, "FDO", "Test"+randomNum, "TSM1", "India", ""};

	@BeforeClass
	public void startBrowser() throws IOException, URISyntaxException {
		userLogin = new UsersLogin(driver);
		welcomePage = userLogin.webUserLogin();
		masterDataPage = welcomePage.goToMasterDataManagement();
		businessGeoPage = masterDataPage.goToBusinessGeoMasterPage();
	}

	@Test(priority = 1)
	public void Test_CheckBusinessGeoCreation_Country() throws Exception {
		businessGeoPage.selectType("Country");
		businessGeoPage.downloadData();
		File latestFile = getLatestDownloadedFilePath();
		String file = latestFile.getAbsolutePath();
		AddDataIntoBusinessGeo.appendOneRowToExistingExcel(file, countryData);
		businessGeoPage.addFile(file);
		businessGeoPage.clickIOnCheckData();
		Assert.assertTrue(businessGeoPage.DataSavedSuccessfully());
		Assert.assertEquals(businessGeoPage.getSuccessMessage(), "Record Validated Successfully.");
		businessGeoPage.closeSuccessPopAlert();
		
	}
	
	@Test(priority = 2)
	public void Test_CheckBusinessGeoCreation_ASM() throws Exception {
		businessGeoPage.clickOnCancel();
		businessGeoPage.selectType("ASM");
		businessGeoPage.downloadData();
		File latestFile = getLatestDownloadedFilePath();
		String file = latestFile.getAbsolutePath();
		AddDataIntoBusinessGeo.appendOneRowToExistingExcel(file, asmData);
		businessGeoPage.addFile(file);
		businessGeoPage.clickIOnCheckData();
		Assert.assertTrue(businessGeoPage.DataSavedSuccessfully());
		Assert.assertEquals(businessGeoPage.getSuccessMessage(), "Record Validated Successfully.");
		businessGeoPage.closeSuccessPopAlert();
	}
	
	@Test(priority = 3)
	public void Test_CheckBusinessGeoCreation_TSM() throws Exception {
		businessGeoPage.clickOnCancel();
		businessGeoPage.selectType("TSM");
		businessGeoPage.downloadData();
		File latestFile = getLatestDownloadedFilePath();
		String file = latestFile.getAbsolutePath();
		AddDataIntoBusinessGeo.appendOneRowToExistingExcel(file, tsmData);
		businessGeoPage.addFile(file);
		businessGeoPage.clickIOnCheckData();
		Assert.assertTrue(businessGeoPage.DataSavedSuccessfully());
		Assert.assertEquals(businessGeoPage.getSuccessMessage(), "Record Validated Successfully.");
		businessGeoPage.closeSuccessPopAlert();
	}
	
	@Test(priority = 4)
	public void Test_CheckBusinessGeoCreation_FDO() throws Exception {
		businessGeoPage.clickOnCancel();
		businessGeoPage.selectType("FDO");
		businessGeoPage.downloadData();
		File latestFile = getLatestDownloadedFilePath();
		String file = latestFile.getAbsolutePath();
		AddDataIntoBusinessGeo.appendOneRowToExistingExcel(file, fdoData);
		businessGeoPage.addFile(file);
		businessGeoPage.clickIOnCheckData();
		Assert.assertTrue(businessGeoPage.DataSavedSuccessfully());
		Assert.assertEquals(businessGeoPage.getSuccessMessage(), "Record Validated Successfully.");
		businessGeoPage.closeSuccessPopAlert();
	}
	
	@AfterClass
	public void tearDown() {
		driver.close();
	}

}
