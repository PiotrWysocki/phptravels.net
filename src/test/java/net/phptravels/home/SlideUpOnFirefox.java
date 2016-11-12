package net.phptravels.home;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.ImagePath;
import org.sikuli.script.Screen;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SlideUpOnFirefox {

	WebDriver driver;

	@BeforeMethod
	public void beforeMethod() {

		// Launch Firefox browser
		driver = new FirefoxDriver();

		// Maximize the browser window
		driver.manage().window().maximize();

		// Navigate to http://phptravels.net
		driver.get("http://phptravels.net/");

		// Set implicitly timeout to 10 seconds
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@AfterMethod
	public void afterMethod() {

		// Close Firefox browser
		driver.quit();

	}

	@BeforeClass
	public void beforeClass() {

		// Set Firefox driver property
		System.setProperty("webdriver.gecko.driver", "./src/test/resources/drivers/geckodriver_v0.11.1.exe");
		
		// Path to sikuli images
		ImagePath.add("src/test/resources/images.sikuli");
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
		
		WebDriverWait wait = new WebDriverWait(driver, 10, 500);
		
		// Wait until, "fly from" appears
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("a1")));
		
		// Find "fly from" text field 
		WebElement flyFrom = driver.findElement(By.id("a1"));
		
		// Send the name of the city of departure
		flyFrom.sendKeys("Bergen");
						
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
	
	@Test(invocationCount = 8)
	public void isSearchResultsOpenAfterSearchTour() throws Exception{
		
		// Find Tours tab
		WebElement toursTab = driver.findElement(By.xpath("//*[@data-title='TOURS']"));

		// Click Tours tab
		toursTab.click();

		// Create instance of WebDriverWait
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
		// Wait until, visibility of search text field
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='TOURS']//input[@name='txtSearch']")));
		
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
		
		// Create a new screen object
		Screen screen = new Screen();
		
		// Click "Tour Type" menu
		if (screen.exists("select.png", 5) != null) {
			try {
				screen.click("select.png");

			} catch (FindFailed e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		// Click "Private option" menu
		if (screen.exists("private.png", 5) != null) {
			try {
				screen.click("private.png");

			} catch (FindFailed e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		// Create instance of JavascriptExecutor
		JavascriptExecutor js = (JavascriptExecutor)driver;

		// Find search button
		WebElement searchB = driver.findElement(By.xpath("//*[@id='TOURS']//button"));

		// Click search button
		js.executeScript("arguments[0].click();", searchB);

		// Wait until, element with search results appears
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='itemscontainer']//a[@title = 'Alexandria']")));

		// Verify is search result correct
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='itemscontainer']//a[@title = 'Alexandria']")).getAttribute("title").equals("Alexandria"));
	
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
		
		// Create instance of Screen
		Screen screen = new Screen();
		
		// Select "Pick Up Time" dropdown and click "9:00" option
		if(screen.exists("pickUpTime.png", 5) != null){
			try{
				screen.click("pickUpTime.png");
				screen.click("time900.png");
			}catch(FindFailed e){
				e.printStackTrace();
			}
		}
		
		// Find "Drop off date" field
		WebElement dropOffDate = driver.findElement(By.xpath("//*[@id='returncar']"));
		
		// Clear field and send date
		dropOffDate.clear();
		dropOffDate.sendKeys("12/12/2016");
		
		// Select "Drop Off Time" dropdown and click "15:00" option
		if(screen.exists("dropOffTime.png", 5) != null){
			try{
				screen.click("dropOffTime.png");
				screen.dragDrop("slider.png", "time1500.png");
				screen.click("time1500.png");
			}catch(FindFailed e){
				e.printStackTrace();
			}
		}
		
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
