package test.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import test.pageobjects.LandingPage;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landing;
	public WebDriver initializeDriver() throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//test//resources//GlobalData.properties");
		prop.load(fis);
		String browser = System.getProperty("browser")==null ? prop.getProperty("browser") : System.getProperty("browser");
		System.out.println("Variable from jenkins: " + browser);
		if(browser.contains("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			if(browser.contains("headless")) {
				options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900));
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			//add invoking firefox browser code here. 
			System.out.println("Firefox browser");
		}
		else {
			//add invoking edge browser code here.
			System.out.println("Edge browser");
		}
		driver.manage().window().maximize();
		return driver;
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		landing = new LandingPage(driver);
		landing.goTo();
		return landing;
	}
	
	@AfterClass
	public void tearDown() throws InterruptedException {
//		Thread.sleep(2000);
		driver.quit();
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String path) throws IOException{
		
		String jsonContent = FileUtils.readFileToString(new File(path),StandardCharsets.UTF_8);
		
		//using Jackson databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> xyz = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
		return xyz;
	}
	
	public String  getScreenshot(String testcaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File fs = new File(System.getProperty("user.dir") + "//reports//" + testcaseName + ".png");
		FileUtils.copyFile(src, fs);
		return System.getProperty("user.dir") + "//reports//" + testcaseName + ".png";
	}


}
