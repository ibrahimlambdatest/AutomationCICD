package test.testcases;

import test.pageobjects.LandingPage;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Collector;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandaloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.get("https://rahulshettyacademy.com/client");
			
		LandingPage landing = new LandingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("ibrahim.said@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("@Ibrahim123");
		driver.findElement(By.id("login")).click();
		
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
//		//find the element that has Zara in it and click add to cart
		List<WebElement> productList = products.stream().filter(s->s.getText().contains("ZARA")).collect(Collectors.toList());
//		productList.get(0).findElement(By.xpath("//button[contains(@class,'btn w-10 rounded')]")).click();
		//find the element that has Zara in it and click add to cart - another way to do it. 
		WebElement prod = products.stream().filter(s->s.getText().contains("ZARA")).findFirst().orElse(null);
		prod.findElement(By.xpath("//button[contains(@class,'btn w-10 rounded')]")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();		

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='btn btn-primary']")));
		List<WebElement> buyButtons = driver.findElements(By.xpath("//button[@class='btn btn-primary']"));
		buyButtons.stream().filter(s->s.getText().contains("Checkout")).collect(Collectors.toList()).get(0).click();
	
		driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys("India");
		List<WebElement> options = driver.findElements(By.cssSelector(".ta-item.list-group-item.ng-star-inserted"));
		options.stream().filter(s->s.getText().equalsIgnoreCase("India")).collect(Collectors.toList()).get(0).click();
		
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		//new code
		String orderId = driver.findElement(By.xpath("//label[contains(text(),'|')]")).getText();
		driver.findElement(By.cssSelector(".fa.fa-handshake-o")).click();
		
	}

}
