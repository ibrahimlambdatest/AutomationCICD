package test.testcases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.TestComponents.BaseTest;
import test.pageobjects.CheckoutPage;
import test.pageobjects.LandingPage;
import test.pageobjects.OrderConfirmationPage;
import test.pageobjects.ProductCatalogue;
import test.pageobjects.PlaceOrderPage;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Collector;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest {

	OrderConfirmationPage orderConfirmationPage;
	@Test(dataProvider= "getData", groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> map) throws IOException, InterruptedException
	{
		ProductCatalogue pLogue = landing.loginApplication(map.get("email"), map.get("password"));
		CheckoutPage chkoutPage = pLogue.clickAddToCart(map.get("product"));
		PlaceOrderPage poPage = chkoutPage.clickCheckout();
		orderConfirmationPage = poPage.placeOrder();
//		orderConfirmationPage.confirmEntry();
	}
	
	@Test(dependsOnMethods= {"confirmThankyou"})
	public void checkOrderHistory() throws IOException, InterruptedException
	{
		orderConfirmationPage.confirmEntry();
	}

	@Test(dependsOnMethods= {"submitOrder"})
	public void confirmThankyou() throws IOException, InterruptedException
	{
		Assert.assertTrue(orderConfirmationPage.confirmThankyouMessage().equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException 
	{
//		return new Object[][] {{"ibrahim.said@gmail.com", "@Ibrahim123", "ZARA"},{"ibrahim.said@gmail.com", "@Ibrahim123", "ADIDAS"}};
		
/*		HashMap<String,String> map = new HashMap<String,String>();
		map.put("email", "ibrahim.said@gmail.com");
		map.put("password", "@Ibrahim123");
		map.put("product", "ZARA");
		
		HashMap<String, String> map1 = new HashMap<String,String>();
		map1.put("email", "ibrahim.said@gmail.com");
		map1.put("password", "@Ibrahim123");
		map1.put("product", "ADIDAS");
		
		return new Object[][] {{map},{map1}};*/
		
		List<HashMap<String,String>> map = this.getJsonDataToMap("/Users/ibrahim/Ibby/eclipse-workspace/SeleniumFrameworkDesign/src/test/java/test/data/PurchaseOrder.json");
		return new Object[][] {{map.get(0)},{map.get(1)}};
	}
}
