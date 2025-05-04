package co.ecubix.site.core;

import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class DriverManagementCore {
	
	private static ThreadLocal<WebDriver> tdriver = new ThreadLocal<WebDriver>();
	
	public void setDriver(WebDriver driver) {
		tdriver.set(driver);
	}

	public WebDriver getDriver() {
		return tdriver.get();
	}

	protected WebDriver driver;

//	public WebDriver driver;
	private FileInputStream fis;
	private Properties prop;
	private String browser;
	private String url;
	public UiAutomator2Options options;
	public AndroidDriver appium_driver;
	public AppiumDriverLocalService service;

	@BeforeClass
	public void loadPropertyFile() throws IOException {
		loadGlobalProperties();
		driverSetUpForWeb();
		openURL();
	}

	public void driverSetUpForWeb() throws IOException {
		setDriver(initDriver(browser));
		driver = getDriver();
//		driver = initDriver(browser);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
	}

	public void openURL() {
		driver.get(url);
	}

	private void loadGlobalProperties() throws IOException {
		String path = System.getProperty("user.dir");
		fis = new FileInputStream(path + "/src/main/resources/properties/global.properties");
		prop = new Properties();
		prop.load(fis);
//		If passing browser from terminal through maven command
		browser = System.getProperty("browser") != null ? System.getProperty("browser") : prop.getProperty("browser");
//		browser = prop.getProperty("browser");
		url = prop.getProperty("url");
	}

	private WebDriver initDriver(String browser) throws IOException {
		WebDriver driver = null;
		switch (browser) {
		case "chrome":
			ChromeOptions chromeOption = new ChromeOptions();
			settingDownloadPath(chromeOption);
//			chromeOption.addArguments("--incognito");
//			chromeOption.addArguments("--headless");
			driver = new ChromeDriver(chromeOption);
			break;
		case "firefox":
			FirefoxOptions firefoxOption = new FirefoxOptions();
//			firefoxOption.addArguments("--incognito");
//			firefoxOption.addArguments("--headless");
			driver = new FirefoxDriver(firefoxOption);
			break;
		case "edge":
			EdgeOptions edgeOption = new EdgeOptions();
//			edgeOption.addArguments("--incognito");
//			edgeOption.addArguments("--headless");
			driver = new EdgeDriver(edgeOption);
			break;
		}
		return driver;
	}

	public void startAppiumServer() throws MalformedURLException, URISyntaxException {
		service = new AppiumServiceBuilder()
				.withAppiumJS(new File("//usr//local//lib//node_modules//appium//build//lib//main.js"))
				.withIPAddress("127.0.0.1").usingPort(4723).build();
		service.start();
	}

	public void initiateAppiumDriver() throws MalformedURLException, URISyntaxException {
		options = new UiAutomator2Options();
		options.setDeviceName("RF8N30AXDHN");
		appium_driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);
	}

	public void launchApp(String application, UiAutomator2Options options) {
		String path = System.getProperty("user.dir");
		switch (application) {
		case "basf_agg":
			options.setApp(path + "/src/main/resources/application/BASF_Aggregation_Toller_24.03.20000.apk");
		case "basf_vns":
			options.setApp(path + "/src/main/resources/application/BASF_VNS_25.01.20000.apk");
			break;
		}
	}

	// Changes the default download directory path
	private void settingDownloadPath(ChromeOptions options) throws IOException {
		String downloadPath = System.getProperty("user.dir") + "/downloads";
		cleanDirectory(downloadPath);
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("download.default_directory", downloadPath);
		options.setExperimentalOption("prefs", prefs);
	}

	// Delete the already existing file in the folder
	public void cleanDirectory(String directory) throws IOException {
		File folder = new File(directory);
		FileUtils.cleanDirectory(folder);
	}
	
	// Gets the path of the downloaded file
	public File getLatestDownloadedFilePath() throws IOException {
		String downloadPath = System.getProperty("user.dir") + "/downloads";
		File dir = new File(downloadPath);
        if (!dir.exists() || !dir.isDirectory()) {
            System.out.println("Invalid directory: " + downloadPath);
            return null;
        }
        // Get all files in the directory
        File[] files = dir.listFiles((file) -> file.isFile());
        if (files != null && files.length > 0) {
            // Return the latest modified file
            return Arrays.stream(files)
                    .max(Comparator.comparingLong(File::lastModified))
                    .orElse(null);
        }
        return null; // No files found
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//"+ testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//"+ testCaseName + ".png";
	}
	
	public static String customerCode() {
		 Random random = new Random();
		 int number = 10000 + random.nextInt(90000);
	     return String.valueOf(number);
	}
	
	public static String ArticleCode() {
		 Random random = new Random();
		 int number = 1000 + random.nextInt(9000);
	     return String.valueOf(number);
	}
	
	public static String randomId() {
		 Random random = new Random();
		 int number = 1000 + random.nextInt(9000);
	     return String.valueOf(number);
	}

}
