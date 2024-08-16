package test.pageobjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import dev.failsafe.internal.util.Assert;
import test.AbstractComponents.AbstractComponent;

public class OrderConfirmationPage extends AbstractComponent{
	
	WebDriver driver;
	public OrderConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//label[contains(text(),'|')]") //driver.findElement(By.xpath("//label[contains(text(),'|')]"))
	WebElement orderEntry;
	
	@FindBy(css=".fa.fa-handshake-o") //driver.findElement(By.cssSelector(".fa.fa-handshake-o")).click();
	WebElement home;
	
	@FindBy(xpath="//tbody //th[1]")
	List<WebElement> firstColumn;
	
	@FindBy(css=".hero-primary")
	WebElement thankyouText;
	
	By labelEntry = By.xpath("//label");
	
	public void confirmEntry() {
		waitForElementToAppear(labelEntry);
		String value = orderEntry.getText().substring(2,orderEntry.getText().length()-2);
		System.out.println(value);	
		home.click();
		
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tbody //th[1]")));
		this.waitForPresenceOfElement(By.xpath("//tbody //th[1]"));
		System.out.println(firstColumn.stream().anyMatch(s->value.equalsIgnoreCase(s.getText())));
	}
	
	public String confirmThankyouMessage() {
		this.waitForElementToAppear(By.cssSelector(".hero-primary"));
		return thankyouText.getText();
	}
}
