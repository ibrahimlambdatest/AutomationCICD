package test.pageobjects;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import test.AbstractComponents.AbstractComponent;

public class PlaceOrderPage extends AbstractComponent{
	
	WebDriver driver;
	public PlaceOrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[placeholder='Select Country']") //driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys("India");
	WebElement countryDropdown;
	
	@FindBy(css=".ta-item.list-group-item.ng-star-inserted") //driver.findElements(By.cssSelector(".ta-item.list-group-item.ng-star-inserted"));
	List<WebElement> suggestiveDropdown; 
	
	@FindBy(css=".action__submit") //driver.findElement(By.cssSelector(".action__submit"));
	WebElement submitButton;
	
	By taresults = By.cssSelector(".input.txt.text-validated");
	
	public OrderConfirmationPage placeOrder() throws InterruptedException {
		countryDropdown.sendKeys("India");
		waitForPresenceOfElement(By.cssSelector(".ta-item.list-group-item.ng-star-inserted"));
		suggestiveDropdown.stream().filter(s->s.getText().equalsIgnoreCase("India")).collect(Collectors.toList()).get(0).click();
		
		submitButton.click();
		OrderConfirmationPage orderConfirmationPage = new OrderConfirmationPage(driver);
		return orderConfirmationPage;
	}
	

}
