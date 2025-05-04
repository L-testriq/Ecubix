package co.ecubix.site.login;

import org.testng.annotations.Test;

import java.io.IOException;

import org.testng.annotations.BeforeTest;


import co.ecubix.site.core.DriverManagementCore;
import co.ecubix.utils.reading_serialNo.GetSerialNumber;

public class PDF_PNG_QR extends DriverManagementCore {
	
	@BeforeTest
	public void initiateDriver() throws IOException {
		driverSetUpForWeb();
	}

	@Test
	public void readQRFromPDF() {
		int pageNumber = 1;
		int QR_number = 2; // should be  1 to 3
		String inOrout = "in";
		GetSerialNumber getSerialNumber = new GetSerialNumber();
		String serial_Number = getSerialNumber.extractQRCodeData(pageNumber-1, QR_number, inOrout);
		System.out.println(serial_Number);
		driver.quit();
	}
	
}
