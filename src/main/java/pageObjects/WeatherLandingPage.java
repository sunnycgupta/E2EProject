package pageObjects;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import resources.BaseTest;

public class WeatherLandingPage extends BaseTest {

	
	public WebDriver driver;
	
	@FindBy(xpath="//img[@alt='openweathermap']")
	public WebElement imageOpenWeatherMap;
	
	@FindBy(xpath="//input[@placeholder='Your city name']")
	public WebElement labelYourCityName;
	
	@FindBy(xpath="//button[@type='submit' and @class='btn btn-orange']")
	public WebElement buttonSearch;
	
	@FindBy(xpath="//div[@class='col-lg-9 col-md-9 col-sm-9  hidden-xs']//a[normalize-space(.)='Sign In']")
	public WebElement linkSignIn;
	
	@FindBy(xpath="//div[@class='col-lg-9 col-md-9 col-sm-9  hidden-xs']//a[normalize-space(.)='Sign Up']")
	public WebElement linkSignUp;
	
	@FindBy(xpath="//h2[text()='Current weather and forecasts in your city']")
	public WebElement headerCurrentWeatherAndForcast;
	

	
	public WeatherLandingPage(WebDriver driver)  {
		
			this.driver=driver;
			PageFactory.initElements(driver, this);
			
	}
	
	public void invoke() throws Exception {
		driver.get(BaseTest.getProperty("url"));
		waitForPageToLoad();
		
	}

	
	
	public void waitForPageToLoad() {
		try{
	if(isPresent("//h2[text()='Current weather and forecasts in your city']")){
		headerCurrentWeatherAndForcast.isDisplayed();
		Reporter.log("<br>"+getClass().getName()+": Page Load Successfully.");
	}
		}
	catch(Exception error)
	{
		Reporter.log(getClass().getName()+": Page not Loaded.");
		error.getStackTrace();
		
		
	}
}

	
	
	}



	
	
	

