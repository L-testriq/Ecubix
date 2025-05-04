package co.ecubix.users.login;

import java.io.FileInputStream;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import co.ecubix.site.core.PageObjectFacilitator;
import co.ecubix.utils.read_files.CreateLocator;
import co.ecubix.web.welcome.WelcomePage;

public class UsersLogin extends PageObjectFacilitator {
	
	WebDriver driver;
	private FileInputStream fis;
	private Properties prop;
	
	public UsersLogin(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	private void loadCredentials() throws IOException {
		String path = System.getProperty("user.dir");
		fis = new FileInputStream(path + "/src/main/resources/properties/credentials.properties");
		prop = new Properties();
		prop.load(fis);
	}

	public WelcomePage webUserLogin() throws IOException {
		loadCredentials();
		CreateLocator createLocator = new CreateLocator();
		By username_field = createLocator.getLocator("login_page", "username_field");
		By password_field = createLocator.getLocator("login_page", "password_field");
		By signIn_button = createLocator.getLocator("login_page", "signIn_button");
		String username = prop.getProperty("web_user");
		String password = prop.getProperty("web_pass");
		setTextInField(username_field, username);
		setTextInField(password_field, password);
		clickButton(driver, signIn_button);
//		driver.findElement(username_field).sendKeys(username);
//		driver.findElement(password_field).sendKeys(password);
//		driver.findElement(signIn_button).click();
		return new WelcomePage(driver);
	}

}
