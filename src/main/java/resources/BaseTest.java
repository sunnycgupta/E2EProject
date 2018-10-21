package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.asserts.SoftAssert;

public class BaseTest {

	public static WebDriver driver;
	public static Properties prop;
	String actualText=null;
	WebDriverWait wait ;
	SoftAssert softAssert =new SoftAssert();
	public static Boolean status =true ;
	
	public static String getProperty(String property) throws Exception {
		prop = new Properties();
		FileInputStream fis = new FileInputStream("src\\main\\java\\resources\\data.properties");

		prop.load(fis);
		String browserName = prop.getProperty(property);
		return browserName;
	}

	public static WebDriver initializeDriver(String browserName) throws IOException {

		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "src\\main\\java\\resources\\chromedriver.exe");
			driver = new ChromeDriver();
			// execute in chrome driver

		} else if (browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", "src\\main\\java\\resources\\geckodriver.exe");

			WebDriver driver = new FirefoxDriver();

			// firefox code
		} else if (browserName.equals("IE")) {
			System.setProperty("webdriver.ie.driver", "src\\main\\java\\resources\\MicrosoftWebDriver.exe");
			WebDriver driver = new InternetExplorerDriver();
			// IE code
		}
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		return driver;

	}

	public void getScreenshot(String result) throws IOException {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(src, new File("C://test//" + result + "screenshot.png"));

	}

	public void waitForVisible(WebElement locator)
	{
		actualText=locator.toString();
		wait=new WebDriverWait(getDriver(),60);
		wait.until(ExpectedConditions.visibilityOf(locator));
		Reporter.log(actualText.substring(65) +": Object Loaded successfully. ");
		Reporter.log("\n");
		System.out.println(actualText.substring(65) +": Object Loaded successfully. ");
	}
	@DataProvider
	public Object[][] getData() {
		// Row stands for how many different data types test should run
		// coloumn stands for how many values per each test

		// Array size is 2
		// 0,1
		Object[][] data = new Object[2][3];
		// 0th row
		data[0][0] = "nonrestricteduser@qw.com";
		data[0][1] = "123456";
		data[0][2] = "Restrcited User";
		// 1st row
		data[1][0] = "restricteduser@qw.com";
		data[1][1] = "456788";
		data[1][2] = "Non restricted user";

		return data;

	}

	@BeforeMethod()
	public void initialize() throws Exception {

		driver = initializeDriver(getProperty("browser"));

	}

	@AfterMethod()
	public void teardown() {

		driver.close();
		driver = null;

	}
	public void verifyText(WebElement locator,String expectText)
	{

		try{
			actualText=locator.getText() ;
			if(actualText.equals(expectText)){
				Reporter.log("<br> Passed : Actual Text:= ["+actualText+"] Expected Text:= ["+expectText+"]");
				System.out.println("Passed : Actual Text:= ["+actualText+"] Expected Text:= ["+expectText+"]");
				softAssert.assertEquals(actualText, expectText);
				
				
			}
			else
			{
				Reporter.log("<br> Failed : Actual Text:= ["+actualText+"] Expected Text:= ["+expectText+"]");
				System.out.println("Failed : Actual Text:= ["+actualText+"] Expected Text:= ["+expectText+"]");
				softAssert.assertEquals(actualText, expectText);
				status =false ;
				
			}
		}
		catch(Exception error)
		{
			error.printStackTrace();
			Reporter.log(error.getMessage());
			
		}
		
	}
	
	public void verifyPartialText(WebElement locator,String expectText)
	{

		try{
			actualText=locator.getText() ;
			if(actualText.contains(expectText)){
				Reporter.log("<br> Passed : Actual Text:= ["+actualText+"] Expected Text:= ["+expectText+"]");
				System.out.println("Passed : Actual Text:= ["+actualText+"] Expected Text:= ["+expectText+"]");
				softAssert.assertTrue(actualText.contains(expectText), "Text is Present");
				
				
			}
			else
			{
				Reporter.log("<br> Failed : Actual Text:= ["+actualText+"] Expected Text:= ["+expectText+"]");
				System.out.println("Failed : Actual Text:= ["+actualText+"] Expected Text:= ["+expectText+"]");
				softAssert.assertTrue(!actualText.contains(expectText), "Text is Not Present");
				status =false ;
				
			}
		}
		catch(Exception error)
		{
			error.printStackTrace();
			Reporter.log(error.getMessage());
			
		}
		
	}
	
	public void verifyAttributeValue(WebElement locator,String attibutrVal,String expectText) {
		try{
			actualText=locator.getAttribute(attibutrVal) ;
			if(actualText.equals(expectText)){
				Reporter.log("<br> Passed : Actual Text:= ["+actualText+"] Expected Text:= ["+expectText+"]");
				System.out.println("Passed : Actual Text:= ["+actualText+"] Expected Text:= ["+expectText+"]");
				softAssert.assertEquals(actualText, expectText);
				
				
			}
			else
			{
				Reporter.log("<br> Failed : Actual Text:= ["+actualText+"] Expected Text:= ["+expectText+"]");
				System.out.println("Failed : Actual Text:= ["+actualText+"] Expected Text:= ["+expectText+"]");
				softAssert.assertEquals(actualText, expectText);
				status =false ;
				
			}
		}
		catch(Exception error)
		{
			error.printStackTrace();
			Reporter.log(error.getMessage());
			
		}
		
	}
	public static WebDriver getDriver()
	{
		return driver;
	}	
	public boolean isPresent(String xpath) {
		try {
			List<WebElement> listElement = getDriver().findElements(By.xpath(xpath));
			if (listElement.size() >= 1) {
				Reporter.log("<br>WebElement is Visible with xpath : "+xpath );
				return true;
			} else {
				Reporter.log("<br>Element is not Visible qith xpath : "+xpath);
				Assert.assertFalse(listElement.size() == 0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	
	
}
