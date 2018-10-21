package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import resources.BaseTest;

public class WeatherSignInPage extends BaseTest {

	public WebDriver driver;

	@FindBy(xpath = "//img[@alt='openweathermap']")
	public WebElement imageOpenWeatherMap;

	@FindBy(xpath = "//div[@class='input-group']//input[@id='user_email']")
	public WebElement inputEnterEmail;

	@FindBy(xpath = "//div[@class='input-group']//input[@id='user_password']")
	public WebElement inputEnterPassword;
	
	@FindBy(xpath = "//input[@type='submit' and @value='Submit']")
	public WebElement buttonSubmit;

	
	@FindBy(xpath = "//h3[normalize-space(.)='Sign In']")
	public WebElement headerSignIn;

	@FindBy(xpath = "//div[@class='panel-body']")
	public WebElement textInvalidEmailAndPassword;
	
	@FindBy(xpath = "//div[@class='panel-body']")
	public WebElement textSignedInSuccess;
	public WeatherSignInPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public void invoke() throws Exception {
		driver.get(BaseTest.getProperty("url"));
		waitForPageToLoad();

	}

	
	public void waitForPageToLoad() {
		try {
			if (isPresent("//h3[normalize-space(.)='Sign In']")) {
				headerSignIn.isDisplayed();
				Reporter.log("<br>"+getClass().getName() + ": Page Load Successfully.");
			}
		} catch (Exception error) {
			Reporter.log(getClass().getName() + ": Page not Loaded.");
			error.getStackTrace();

		}
	}

}


