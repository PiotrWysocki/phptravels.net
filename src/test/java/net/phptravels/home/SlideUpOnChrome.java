package net.phptravels.home;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SlideUpOnChrome {

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
	public void isSearchResultsOpenAfterSearchByHotel(){
		
		// Find text field
		WebElement searchTF = driver.findElement(By.xpath(".//*[@id='HOTELS']//*[@name='txtSearch']"));
		
		// Send name hotel
		searchTF.sendKeys("Oasis Beach Tower");
		
		// Find search button
		WebElement searchB = driver.findElement(By.xpath(".//*[@id='HOTELS']//button"));
		
		// Click search button
		searchB.click();
		
		// Wait until title appear
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.titleContains("Search Results"));
		
		// Verify if search result correct
		Assert.assertTrue(driver.findElement(By.xpath("//b[contains(.,'Oasis Beach Tower')]")).getText().equals("Oasis Beach Tower"));
			
	}
	
	@Test
	public void isSearchResultsOpenAfterSearchByFlight() throws Exception{
		
		// Find flights tab
		WebElement flightsTab = driver.findElement(By.xpath(".//*[@data-title='DOHOP']"));
		
		// click flights tab
		flightsTab.click();
		
		// Find "fly from" text field 
		WebElement flyFrom = driver.findElement(By.id("a1"));
		
		// Send the name of the city of departure
		flyFrom.sendKeys("Bergen");
		
		WebDriverWait wait = new WebDriverWait(driver, 10, 500);
		
		// Wait and click element with "Bergen (BGO)"
		try{
 		wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath("//li[contains(.,'Bergen (BGO)')]"))));
		}
		catch(Exception e){	
		}
		 		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//li[contains(.,'Bergen (BGO)')]")));
 		
		// Find "Arrive at" text field 
		WebElement arriveAt = driver.findElement(By.id("a2"));
		
		// Send the name of the city of arrival
		arriveAt.sendKeys("Rabat");
		
		// Wait and click element with "Rabat (RBA)"
		try{
 		wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath("//li[contains(.,'Rabat (RBA)')]"))));
		}
		catch(Exception e){			
		}
 		
 		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//li[contains(.,'Rabat (RBA)')]")));
		
		// Find depart field
		WebElement departF = driver.findElement(By.xpath("//input[@name='d1']"));
		
		// Send date
		departF.sendKeys("11/11/2016");
		
		// Click depart field
		js.executeScript("arguments[0].click();", departF);
		
		// Find return field
		WebElement returnF = driver.findElement(By.xpath("//input[@name='d2']"));
		
		// Send date
		returnF.sendKeys("11/18/2016");
		
		// Click return field
		js.executeScript("arguments[0].click();", returnF);
		
		// Find dropdown trip menu
		WebElement trip = driver.findElement(By.xpath("//select[@id='trip']"));
		
		// Click trip menu
		js.executeScript("arguments[0].click();", trip);
		
		// Find element of trip menu
		WebElement roundTrip = driver.findElement(By.xpath("//select[@id='trip']/option[contains(., 'Round Trip')]"));
		
		// Click roundTrip
		js.executeScript("arguments[0].click();", roundTrip);
		
		// Find search button
		WebElement searchB = driver.findElement(By.xpath(".//*[@id='DOHOP']//button"));
		
		// Click search button
		js.executeScript("arguments[0].click();", searchB);
		
		// Switch driver to new open tab
		for (String winHandle : driver.getWindowHandles()) { 
	        driver.switchTo().window(winHandle);                      
	    }
	
		// Wait until, element with search results appears
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='destination']")));
		
		// Verify if search result correct			
		Assert.assertTrue(driver.findElement(By.xpath("//div[@data-reactid='.0.1.0.0.0.0']")).getText().contains("Bergen") && driver.findElement(By.xpath("//div[@data-reactid='.0.1.0.0.0.2']")).getText().contains("Rabat"));

	}
	
	@Test(invocationCount = 6)
	public void isSearchResultsOpenAfterSearchTour() throws Exception{
		
		// Find Tours tab
		WebElement toursTab = driver.findElement(By.xpath(".//*[@data-title='TOURS']"));
		
		// Click Tours tab
		toursTab.click();
		
		// Find search text field
		WebElement searchTF = driver.findElement(By.xpath("//div[@id='TOURS']//input[@name='txtSearch']"));
		
		// Send name of city
		searchTF.sendKeys("Alexandria");
		
		// Find "Check in" field
		WebElement checkIn = driver.findElement(By.xpath("//input[@name='date']"));
		
		// Clear field and send date
		checkIn.clear();
		checkIn.sendKeys("11/12/2016");
		
		// Find "Guests" field
		WebElement guests = driver.findElement(By.xpath("//*[@id='TOURS']//*[@id='adults']"));
		
		// Clear field and send number of guests
		guests.clear();
		guests.sendKeys("4");
		
		// Select "Tour Type" menu
		Select tourType = new Select(driver.findElement(By.id("tourtype")));
		
		// Select "Private" option
		tourType.selectByVisibleText("Private");
		
		// Create instance of JavascriptExecutor
		JavascriptExecutor js = (JavascriptExecutor)driver;
				
		// Find search button
		WebElement searchB = driver.findElement(By.xpath(".//*[@id='TOURS']//button"));
		
		// Click search button
		js.executeScript("arguments[0].click();", searchB);
		
		// Create instance of WebDriverWait
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
		// Wait until, element with search results appears
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='itemscontainer']//a[contains(@title, 'Alexandria')]")));
		
		// Verify is search result correct
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='itemscontainer']//a[contains(@title, 'Alexandria')]")).getText().equals("Alexandria"));
	
	}
	
	@Test
	public void isSearchResultsOpenAfterSearchCar() throws Exception{
		
		// Find Cars tab
		WebElement carsTab = driver.findElement(By.xpath(".//*[@data-title='CARS']"));
		
		// Click Cars tab
		carsTab.click();
		
		// Create instance of WebDriverWait
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
		// Wait until, element "pick up location" appears
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='s2id_carlocations']/a/span[1]")));
		
		// Find "pick up location" field
		WebElement pickUpLocationF = driver.findElement(By.xpath("//*[@id='s2id_carlocations']/a/span[1]"));
		
		// Click "pick up location" field
		pickUpLocationF.click();
		
		// Find input "pick up location" field
		WebElement pickUpLocationInputF = driver.findElement(By.xpath("//*[@id='select2-drop']/div/input"));
		
		// Send pick up location
		pickUpLocationInputF.sendKeys("Dubai", Keys.ENTER);
		
		// Wait and click "drop off location" field
		try{
 		wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath("//*[@id='s2id_carlocations2']/a/span[1]"))));

		}
		catch(Exception e){			
		}

		driver.findElement(By.xpath("//*[@id='s2id_carlocations2']/a/span[1]")).click();
		
		// Find input "drop off location" field
		WebElement dropOffLocationInputF = driver.findElement(By.xpath("//*[@id='select2-drop']/div/input"));
		
		// Send drop off location
		dropOffLocationInputF.sendKeys("Dubai", Keys.ENTER);
		
		// Find "Pick up date" field
		WebElement pickUpDate = driver.findElement(By.xpath("//*[@id='departcar']"));
		
		// Clear field and send date
		pickUpDate.clear();
		pickUpDate.sendKeys("11/12/2016");
		
		// Select "Pick Up Time" dropdown
		Select pickUpTime = new Select(driver.findElement(By.name("pickupTime")));
		
		// Select "09:00" option
		pickUpTime.selectByVisibleText("09:00");
		
		// Find "Drop off date" field
		WebElement dropOffDate = driver.findElement(By.xpath("//*[@id='returncar']"));
		
		// Clear field and send date
		dropOffDate.clear();
		dropOffDate.sendKeys("12/12/2016");
		
		// Select "Drop Off Time" dropdown
		Select dropOffTime = new Select(driver.findElement(By.name("dropoffTime")));
		
		// Select "15:00" option
		dropOffTime.selectByVisibleText("15:00");
		
		// Find search button
		WebElement searchB = driver.findElement(By.xpath(".//*[@id='CARS']//button"));
				
		// Create instance of JavascriptExecutor
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		// Click search button
		js.executeScript("arguments[0].click();", searchB);
		
		// Wait until, element with search results appears
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='itemscontainer']//b[contains(., 'Hyundai i10 or similar')]")));
		
		// Verify is search result correct
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='itemscontainer']//b[contains(., 'Hyundai i10 or similar')]")).getText().equals("Hyundai i10 or similar"));
	
	}
}
