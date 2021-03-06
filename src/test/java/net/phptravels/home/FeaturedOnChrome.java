package net.phptravels.home;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class FeaturedOnChrome {
	
	WebDriver driver;

  @BeforeMethod
  public void beforeMethod() {
	  
	  // Launch Chrome browser
	  driver = new ChromeDriver();
	  
	  // Maximize the browser window
	  driver.manage().window().maximize();
	  
	  // Navigate to the http://phptravels.net/
	  driver.get("http://phptravels.net/");
	  
	  // set implicitly timeout to 10 seconds
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
  public void isHotelPageOpenAfterClickImgInFeaturedHotels(){
	  
	  // Wait until element to be clickable
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='top']//div/a[@href='http://phptravels.net/hotels/singapore/singapore/Rendezvous-Hotels']")));
	  
	  // Find hotel
	  WebElement hotel = driver.findElement(By.xpath("//*[@id='top']//div/a[@href='http://phptravels.net/hotels/singapore/singapore/Rendezvous-Hotels']"));
	  
	  // Click hotel
	  hotel.click();
	  
	  // Wait and verify page title
	  wait.until(ExpectedConditions.titleContains("Rendezvous Hotels"));
	  Assert.assertTrue(driver.getTitle().equals("Rendezvous Hotels"));
	  
  }
  
  @Test
  public void isTourPageOpenAfterClickImgInFeaturedTours(){
	  
	  // Wait until element to be invisibility
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='collapse1']//div/a[@href='http://phptravels.net/tours/malasia/legoland/Legoland-Malaysia-Day-Pass']")));
	    
	  // Find tour
	  WebElement tour = driver.findElement(By.xpath("//*[@id='collapse1']//div/a[@href='http://phptravels.net/tours/malasia/legoland/Legoland-Malaysia-Day-Pass']"));
	  
	  // Create instance of JavascriptExecutor
	  JavascriptExecutor js = (JavascriptExecutor)driver;
	  
	  // Scroll to tour and click
	  js.executeScript("arguments[0].scrollIntoView(true); arguments[0].click();", tour);
	  
	  // Wait and verify page title
	  wait.until(ExpectedConditions.titleContains("Legoland Malaysia Day Pass"));
	  Assert.assertTrue(driver.getTitle().equals("Legoland Malaysia Day Pass"));
	  
  }
  
  @Test
  public void isCarPageOpenAfterClickImgInFeaturedCars(){
	  
	  // Wait until element to be invisibility
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='top']//div/a[@href='http://phptravels.net/cars/oman/muscat/Toyota-Camry-2015-full-option']")));
	    
	  // Find car
	  WebElement car = driver.findElement(By.xpath("//*[@id='top']//div/a[@href='http://phptravels.net/cars/oman/muscat/Toyota-Camry-2015-full-option']"));
	  
	  // Create instance of JavascriptExecutor
	  JavascriptExecutor js = (JavascriptExecutor)driver;
	  
	  // Scroll to car and click
	  js.executeScript("arguments[0].scrollIntoView(true); arguments[0].click();", car);
	  
	  // Wait and verify page title
	  wait.until(ExpectedConditions.titleContains("Toyota Camry 2015 full option"));
	  Assert.assertTrue(driver.getTitle().equals("Toyota Camry 2015 full option"));
	  
  }

  @Test
  public void isOfferPageOpenAfterClickImgInFeaturedOffers(){
	  
	  // Wait until element to be invisibility
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='foo2']/li/a[@href='http://phptravels.net/offers/Hotels-Deals']")));
	    
	  // Find offer
	  WebElement offer = driver.findElement(By.xpath("//*[@id='foo2']/li/a[@href='http://phptravels.net/offers/Hotels-Deals']"));
	  
	  // Create instance of JavascriptExecutor
	  JavascriptExecutor js = (JavascriptExecutor)driver;
	  
	  // Scroll to car and click
	  js.executeScript("arguments[0].scrollIntoView(true); arguments[0].click();", offer);
	  
	  // Wait and verify page title
	  wait.until(ExpectedConditions.titleContains("Hotels Deals"));
	  Assert.assertTrue(driver.getTitle().equals("Hotels Deals"));
	  
  }
}
