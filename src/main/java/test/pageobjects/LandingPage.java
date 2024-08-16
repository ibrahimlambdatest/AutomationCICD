package test.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import test.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
	
	WebDriver driver;
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(id="userEmail")
	WebElement userEmail;

	@FindBy(id="userPassword")
	WebElement usrPassword;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css=".toast-bottom-right.toast-container")
	WebElement errorMessage;
	
	By errorText = By.cssSelector(".toast-bottom-right.toast-container");
	
	public ProductCatalogue loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		usrPassword.sendKeys(password);
		submit.click();
		ProductCatalogue pCatalogue = new ProductCatalogue(driver);
		return pCatalogue;
		
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String returnErrorMessage() {
		
		waitForElementToAppear(errorText);
		String err = errorMessage.getText();
		return err;
	}
	
	
}
