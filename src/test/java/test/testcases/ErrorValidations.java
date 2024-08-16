package test.testcases;

import org.testng.annotations.Test;

import org.testng.AssertJUnit;
import org.testng.IRetryAnalyzer;

import test.TestComponents.BaseTest;
import test.TestComponents.RetryTest;
import test.pageobjects.CheckoutPage;
import test.pageobjects.LandingPage;
import test.pageobjects.OrderConfirmationPage;
import test.pageobjects.ProductCatalogue;
import test.pageobjects.PlaceOrderPage;

import java.io.IOException;
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
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidations extends BaseTest {

	@Test(retryAnalyzer = RetryTest.class)
	public void errorValidation() throws IOException
	{
		landing.loginApplication("ibrahim.said@mail.com", "@Ibrahim");
		Assert.assertEquals(landing.returnErrorMessage(),"Incorrect email or password.");
		
	}

}
