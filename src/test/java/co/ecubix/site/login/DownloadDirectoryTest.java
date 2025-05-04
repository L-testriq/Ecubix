package co.ecubix.site.login;

import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeTest;

import co.ecubix.site.core.DriverManagementCore;

public class DownloadDirectoryTest extends DriverManagementCore {

	@BeforeTest
	public void initiateDriver() throws IOException {
		driverSetUpForWeb();
	}
	
	@Test
	public void downloadFile() throws InterruptedException, IOException {
		driver.get("https://samplelib.com/sample-jpeg.html");
		driver.findElements(By.xpath("//a[text()='Download']")).get(0).click();
		Thread.sleep(2000);
		File latestFile = getLatestDownloadedFilePath(); 
        if (latestFile != null) {
            System.out.println("Latest downloaded file: " + latestFile.getAbsolutePath());
        } else {
            System.out.println("No files found in the directory.");
        }
		driver.quit();
	}
	
}
