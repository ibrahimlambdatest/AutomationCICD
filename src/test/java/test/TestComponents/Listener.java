package test.TestComponents;

import java.io.IOException;

import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import test.resources.ExtentReport;
import org.openqa.selenium.WebDriver;

public class Listener extends BaseTest implements ITestListener{

		ExtentTest test;
		ExtentReports extent = ExtentReport.createReportObject();
		ThreadLocal<ExtentTest> tLocal = new ThreadLocal<ExtentTest>();
	    @Override		
	    public void onFinish(ITestContext arg0) {					
	        // TODO Auto-generated method stub		
	    	extent.flush();
	        		
	    }		

	    @Override		
	    public void onStart(ITestContext arg0) {					
	        // TODO Auto-generated method stub				
	        		
	    }		

	    @Override		
	    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {					
	        // TODO Auto-generated method stub				
	        		
	    }		

	    @Override		
	    public void onTestFailure(ITestResult arg0) {					
	        // TODO Auto-generated method stub	
	    	tLocal.get().fail(arg0.getThrowable());
			try {
				driver = (WebDriver) arg0.getTestClass().getRealClass().getField("driver").get(arg0.getInstance());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	    	//Take screenshot and attach it to the report
	    	String filePath = null;
	    	try {
				filePath = getScreenshot(arg0.getMethod().getMethodName(), driver);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        tLocal.get().addScreenCaptureFromPath(filePath, arg0.getMethod().getMethodName());		
	    }		

	    @Override		
	    public void onTestSkipped(ITestResult arg0) {					
	        // TODO Auto-generated method stub				
	        		
	    }		

	    @Override		
	    public void onTestStart(ITestResult arg0) {					
	        // TODO Auto-generated method stub	
			test = extent.createTest(arg0.getMethod().getMethodName());
			tLocal.set(test);
	    }		

	    @Override		
	    public void onTestSuccess(ITestResult arg0) {					
	        // TODO Auto-generated method stub	
	    	tLocal.get().log(Status.PASS, "test passed");
	        		
	    }		
}
