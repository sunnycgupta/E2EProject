package testCases;


import org.testng.annotations.Test;

import pageObjects.WeatherLandingPage;
import pageObjects.WeatherSignInPage;
import pageObjects.WeatherCityFoundPage;
import pageObjects.WeatherCityNotFoundPage;
import resources.BaseTest;

public class WeatherForcastTestCase extends BaseTest{
	
	 
	
	@Test() // First Test to verify important information
	public void landingAndVerifyingFieldValues() throws Exception
	{
	
		WeatherLandingPage landingPage=new WeatherLandingPage(getDriver());
		landingPage.invoke();
		landingPage.verifyText(landingPage.headerCurrentWeatherAndForcast, BaseTest.getProperty("homepagetext"));
		landingPage.verifyText(landingPage.linkSignIn, BaseTest.getProperty("textSignOn"));
		landingPage.verifyText(landingPage.linkSignUp, BaseTest.getProperty("textSignUp"));
		landingPage.verifyAttributeValue(landingPage.imageOpenWeatherMap, "alt", BaseTest.getProperty("imagetext"));
		landingPage.verifyAttributeValue(landingPage.labelYourCityName, "placeholder", BaseTest.getProperty("labelValue"));

		
		}

	@Test() // Check for an invalid City Name
	public void  verifyInvalidCityName() throws Exception
	{
	
		WeatherLandingPage landingPage=new WeatherLandingPage(getDriver());
		landingPage.invoke();
		waitForVisible(landingPage.labelYourCityName);
		landingPage.labelYourCityName.sendKeys(BaseTest.getProperty("invalidCityName"));
		landingPage.buttonSearch.click();
		WeatherCityNotFoundPage weatherCityNotFoundPage=new WeatherCityNotFoundPage(getDriver());
		weatherCityNotFoundPage.waitForPageToLoad();
		waitForVisible(weatherCityNotFoundPage.textNotFound);
		weatherCityNotFoundPage.verifyPartialText(weatherCityNotFoundPage.textNotFound, BaseTest.getProperty("invalidText"));
		weatherCityNotFoundPage.verifyAttributeValue(weatherCityNotFoundPage.labelEnteredCityName, "value", BaseTest.getProperty("invalidCityName"));
		
		}
	
	@Test() // Check for an Valid City Name
	public void  verifyValidCityName() throws Exception
	{
	
		WeatherLandingPage landingPage=new WeatherLandingPage(getDriver());
		landingPage.invoke();
		waitForVisible(landingPage.labelYourCityName);
		landingPage.labelYourCityName.sendKeys(BaseTest.getProperty("validCityName"));
		landingPage.buttonSearch.click();
		WeatherCityFoundPage weatherCityFoundPage=new WeatherCityFoundPage(getDriver());
		weatherCityFoundPage.waitForPageToLoad();
		waitForVisible(weatherCityFoundPage.linkTextLondonGB);
		weatherCityFoundPage.verifyText(weatherCityFoundPage.linkTextLondonGB, BaseTest.getProperty("validCityName"));
		weatherCityFoundPage.verifyAttributeValue(weatherCityFoundPage.labelEnteredCityName, "value", BaseTest.getProperty("validCityName"));
		waitForVisible(weatherCityFoundPage.textWeatherDetails);
		landingPage.verifyText(weatherCityFoundPage.textWeatherDetails, BaseTest.getProperty("validWeatherDetails"));
		}
	
	
	@Test() // Fourth Test To verify Valid Sign In
	public void checkForValidAndInvalidSignIn() throws Exception
	{
	
		WeatherLandingPage landingPage=new WeatherLandingPage(getDriver());
		landingPage.invoke();
		landingPage.linkSignIn.click();
		WeatherSignInPage WeatherSignInPage=new WeatherSignInPage(getDriver());
		WeatherSignInPage.waitForPageToLoad();
		waitForVisible(WeatherSignInPage.inputEnterEmail);
		WeatherSignInPage.inputEnterEmail.clear();
		WeatherSignInPage.inputEnterEmail.sendKeys(BaseTest.getProperty("invalidUsername"));
		WeatherSignInPage.inputEnterPassword.clear();
		WeatherSignInPage.inputEnterPassword.sendKeys(BaseTest.getProperty("invalidPassword"));
		WeatherSignInPage.buttonSubmit.click();
		waitForVisible(WeatherSignInPage.textInvalidEmailAndPassword);
		WeatherSignInPage.verifyPartialText(WeatherSignInPage.textInvalidEmailAndPassword, BaseTest.getProperty("invalidemailorpswd"));
		waitForVisible(WeatherSignInPage.inputEnterEmail);
		WeatherSignInPage.inputEnterEmail.clear();
		WeatherSignInPage.inputEnterEmail.sendKeys(BaseTest.getProperty("validUserName"));
		WeatherSignInPage.inputEnterPassword.clear();
		WeatherSignInPage.inputEnterPassword.sendKeys(BaseTest.getProperty("validPassword"));
		WeatherSignInPage.buttonSubmit.click();
		waitForVisible(WeatherSignInPage.textSignedInSuccess);
		WeatherSignInPage.verifyPartialText(WeatherSignInPage.textSignedInSuccess, BaseTest.getProperty("validSignIn"));
	}
	

	
	
}
