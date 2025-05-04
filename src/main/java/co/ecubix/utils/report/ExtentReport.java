package co.ecubix.utils.report;
import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {
	
	private static ExtentReports extentReports;

	public static ExtentReports getReportObject() {
		if (extentReports == null) {
			String reportPath = System.getProperty("user.dir") + "//reports//index.html";
			ExtentSparkReporter sparkReporter = new ExtentSparkReporter(new File(reportPath));
			extentReports = new ExtentReports();
			sparkReporter.config().setDocumentTitle("Test Results");
			sparkReporter.config().setReportName("Nunhems Automation Test Report.");
			extentReports.attachReporter(sparkReporter);
			extentReports.setSystemInfo("OS", "MAC");
			extentReports.setSystemInfo("Author", "Testriq");
			extentReports.setSystemInfo("Browser", "Chrome");
			extentReports.setSystemInfo("Tester", "Lovelesh Khatarkar");
		}
		return extentReports;
	}
	
	
}
