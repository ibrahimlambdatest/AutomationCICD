package test.pageobjects;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import test.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{
	
	WebDriver driver;
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
		
	@FindBy(css="[routerlink*='cart']") //By.xpath("//*[@routerlink="/dashboard/cart"]")
	WebElement cartButton;
	
	
	By byProducts = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector("button[class*='w-10']");
	By successfulMesg = By.cssSelector("#toast-container");
	By animation = By.cssSelector(".ng-animating");
	By byCartButton = By.cssSelector("[routerlink*='cart']");
	
	public List<WebElement> getProductList() {
		waitForElementToAppear(byProducts);
		return products;
	}
	
	
	public WebElement getProductName(String product) throws IOException {
		return getProductList().stream().filter(s->s.getText().contains(product)).findFirst().get();
	}
	
	
	public CheckoutPage clickAddToCart(String product) throws InterruptedException, IOException {
		getProductName(product).findElement(addToCart).click();
		waitForElementToAppear(successfulMesg);
		waitForElementToDisappear(animation);
//		this.waitForPresenceOfElement(byCartButton);
		Thread.sleep(3000);
		cartButton.click();
		CheckoutPage chkoutPage = new CheckoutPage(driver);
		return chkoutPage;
	}
}

