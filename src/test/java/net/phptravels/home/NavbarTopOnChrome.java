package net.phptravels.home;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NavbarTopOnChrome {

	WebDriver driver;

	@BeforeMethod
	public void beforeMethod() {

		// Launch Chrome browser
		driver = new ChromeDriver();

		// Maximize the browser window
		driver.manage().window().maximize();

		// Navigate to http://phptravels.net
		driver.get("http://phptravels.net/");

		// Set implicitly timeout to 10 seconds
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@AfterMethod
	public void afterMethod() {

		// Close Chrome browser
		driver.quit();

	}

	@BeforeClass
	public void beforeClass() {

		// Set Chrome driver property
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/chromedriver_2.25.exe");
	}

	@Test
	public void isHomePageOpenWhenClickLogo(){
		
		// Find "Hotels" link
		WebElement hotels = driver.findElement(By.xpath("//ul[contains(@class, 'navbar-nav')]//a[contains(.,'Hotels')]"));
		
		// Click "Hotels" link
		hotels.click();
		
		// Wait until page is loaded
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.urlMatches("http://phptravels.net/hotels"));
		
		// Find logo
		WebElement logo = driver.findElement(By.cssSelector(".navbar-brand"));
		
		// Click logo
		logo.click();
		
		// Wait until page is loaded
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.urlMatches("http://phptravels.net/"));
		
		// Verify page title
		Assert.assertTrue(driver.getTitle().equals("Travel Business Partner"));
	}
	
	@Test
	public void isHomePageOpenWhenClickHome(){
		
		// Find "Hotels" link
		WebElement hotels = driver.findElement(By.xpath("//ul[contains(@class, 'navbar-nav')]//a[contains(.,'Hotels')]"));
		
		// Click "Hotels" link
		hotels.click();
		
		// Wait until page is loaded
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.urlMatches("http://phptravels.net/hotels"));
		
		// Find "Home" link
		WebElement home = driver.findElement(By.xpath("//ul[contains(@class, 'navbar-nav')]//a[contains(.,'Home')]"));
		
		// Click home
		home.click();
		
		// Wait until page is loaded
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.urlMatches("http://phptravels.net/"));
		
		// Verify page title
		Assert.assertTrue(driver.getTitle().equals("Travel Business Partner"));
	}
	
	@Test
	public void isHotelsPageOpenWhenClickHotels(){
		
		// Find "Hotels" link
		WebElement hotels = driver.findElement(By.xpath("//ul[contains(@class, 'navbar-nav')]//a[contains(.,'Hotels')]"));
		
		// Click "Hotels" link
		hotels.click();
		
		// Wait until page is loaded
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.urlMatches("http://phptravels.net/hotels"));
		
		// Verify page title
		Assert.assertTrue(driver.getTitle().equals("Hotels Listings"));
	}
	
	@Test
	public void isToursPageOpenWhenClickTours(){
		
		// Find "Tours" link
		WebElement tours = driver.findElement(By.xpath("//ul[contains(@class, 'navbar-nav')]//a[contains(.,'Tours')]"));
		
		// Click "Tours" link
		tours.click();
		
		// Wait until page is loaded
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.urlMatches("http://phptravels.net/tours"));
		
		// Verify page title
		Assert.assertTrue(driver.getTitle().equals("Tours Listings"));
	}
	
	@Test
	public void isFlightsPageOpenWhenClickFlights(){
		
		// Find "Flights" link
		WebElement flights = driver.findElement(By.xpath("//ul[contains(@class, 'navbar-nav')]//a[contains(.,'Flights')]"));
		
		// Click "Flights" link
		flights.click();
		
		// Wait until page is loaded
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.urlMatches("http://phptravels.net/flightsd"));
		
		// Verify page title
		Assert.assertTrue(driver.getTitle().equals("Flights"));
	}
	
	@Test
	public void isCarsPageOpenWhenClickCars(){
		
		// Find "Cars" link
		WebElement cars = driver.findElement(By.xpath("//ul[contains(@class, 'navbar-nav')]//a[contains(.,'Cars')]"));
		
		// Click "Cars" link
		cars.click();
		
		// Wait until page is loaded
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.urlMatches("http://phptravels.net/cars"));
		
		// Verify page title
		Assert.assertTrue(driver.getTitle().equals("Cars Listings"));
	}
	
	@Test
	public void isOffersPageOpenWhenClickOffers(){
		
		// Find "Offers" link
		WebElement offers = driver.findElement(By.xpath("//ul[contains(@class, 'navbar-nav')]//a[contains(.,'Offers')]"));
		
		// Click "Offers" link
		offers.click();
		
		// Wait until page is loaded
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.urlMatches("http://phptravels.net/offers"));
		
		// Verify page title
		Assert.assertTrue(driver.getTitle().equals("Special Offers"));
	}
	
	@Test
	public void isBlogPageOpenWhenClickBlog(){
		
		// Find "Blog" link
		WebElement blog = driver.findElement(By.xpath("//ul[contains(@class, 'navbar-nav')]//a[contains(.,'Blog')]"));
		
		// Click "Blog" link
		blog.click();
		
		// Wait until page is loaded
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.urlMatches("http://phptravels.net/blog"));
		
		// Verify page title
		Assert.assertTrue(driver.getTitle().equals("Blog"));
	}
	
	@Test
	public void isLoginPageOpenWhenClickLoginFromMyAccount(){
		
		// Find "My Account" dropdown menu
		WebElement myAccount = driver.findElement(By.xpath("//ul[contains(@class, 'navbar-nav')]//a[contains(.,'My Account')]"));
		
		// Click "My Account"
		myAccount.click();
		
		// Find "Login" link
		WebElement login = driver.findElement(By.xpath("//ul[contains(@class, 'navbar-nav')]//a[contains(.,'Login')]"));
		
		// Click "Login" link
		login.click();
		
		// Wait until page is loaded
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.urlMatches("http://phptravels.net/login"));
		
		// Verify page title
		Assert.assertTrue(driver.getTitle().equals("Login"));
	}
	
	@Test
	public void isSignUpPageOpenWhenClickLoginFromMyAccount(){
		
		// Find "My Account" dropdown menu
		WebElement myAccount = driver.findElement(By.xpath("//ul[contains(@class, 'navbar-nav')]//a[contains(.,'My Account')]"));
		
		// Click "My Account"
		myAccount.click();
		
		// Find "SignUp" link
		WebElement signUp = driver.findElement(By.xpath("//ul[contains(@class, 'navbar-nav')]//a[contains(.,'Sign Up')]"));
		
		// Click "SignUp" link
		signUp.click();
		
		// Wait until page is loaded
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.urlMatches("http://phptravels.net/register"));
		
		// Verify page title
		Assert.assertTrue(driver.getTitle().equals("Register"));
	}
}
