package test.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {
	
	public static ExtentReports createReportObject() {
		//ExtentReports, ExtentSparkReporter
		String path = System.getProperty("user.dir")+"/Reports/index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setDocumentTitle("Testing Results");
		reporter.config().setReportName("Execution Results");
		
 		ExtentReports extent = new ExtentReports();
 		extent.attachReporter(reporter);
 		extent.setSystemInfo("Tester", "Ibrahim Said");
 		return extent;
	}

}
