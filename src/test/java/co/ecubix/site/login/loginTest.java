package co.ecubix.site.login;

import org.testng.annotations.AfterMethod;

import org.testng.annotations.Test;
import java.io.IOException;


import java.net.URISyntaxException;

import co.ecubix.site.core.DriverManagementCore;
import co.ecubix.users.login.UsersLogin;
import co.ecubix.utils.excel.ReadExcelSheet;
import co.ecubix.web.welcome.WelcomePage;

public class loginTest extends DriverManagementCore {
	
	UsersLogin userLogin;
	WelcomePage welcomePage;

//	@BeforeClass
	public void startBrowser() throws IOException, URISyntaxException  {
		driverSetUpForWeb();
		openURL();
		userLogin = new UsersLogin(driver);
	}
	
//	@Test
	@Test
	public void test() throws IOException {
		welcomePage = userLogin.webUserLogin();
	}
	
	@Test
	public void printingExcelValues() throws IOException {
		String filePath = "/Users/testriq_20/Downloads/Nunhems_Test_Cases1.xlsx";
		ReadExcelSheet readExcelSheet = new ReadExcelSheet();
		readExcelSheet.dataReading(filePath);
	}
	
//	@AfterClass
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
}
