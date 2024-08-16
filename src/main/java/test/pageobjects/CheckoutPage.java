package test.pageobjects;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import test.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent{

	WebDriver driver;	

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".totalRow button") //By.xpath("//button[@class='btn btn-primary']")
	List<WebElement> chkoutButtons;

	By chkOutButtonsDiv = By.cssSelector(".totalRow button");
	
	public PlaceOrderPage clickCheckout()
	{
		this.waitForElementToAppear(chkOutButtonsDiv);
		this.chkoutButtons.stream().filter(s->s.getText().contains("Checkout")).collect(Collectors.toList()).get(0).click();
		PlaceOrderPage poPage = new PlaceOrderPage(driver);
		return poPage;
	}

}
