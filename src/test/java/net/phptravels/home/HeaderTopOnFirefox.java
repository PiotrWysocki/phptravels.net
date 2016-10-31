package net.phptravels.home;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.ImagePath;
import org.sikuli.script.Screen;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HeaderTopOnFirefox {

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
	public void isPhoneNumberCorrect() {

		// Find div with phone number
		WebElement phoneNumberDIV = driver.findElement(By.xpath("//div[contains(@class,'header-phone')]"));

		// Verify if number is correct
		Assert.assertEquals(phoneNumberDIV.getText(), "+123-456-789");
	}

	@Test
	public void isEmailTextCorrect() {

		// Find div with email
		WebElement emailDIV = driver.findElement(By.xpath("//div[contains(@class,'header-email')]"));

		// Verify if email text is correct
		Assert.assertEquals(emailDIV.getText(), "email@domain.com");

	}

	@Test
	public void isEmailHrefCorrect() {

		// Find <a> tag with email
		WebElement emailA = driver
				.findElement(By.xpath("//div[contains(@class,'header-email')]/a[contains(@id,'email_footer')]"));

		// Verify if email href is correct
		Assert.assertEquals(emailA.getAttribute("href"), "mailto:email@domain.com");

	}

	@Test
	public void isUSDollarSelected() {

		// Find dropdown menu of currency
		WebElement currency_dropdown = driver.findElement(By.id("currency"));

		// Create new select object
		Select currency = new Select(currency_dropdown);

		// Get first selected option
		WebElement firstSelected = currency.getFirstSelectedOption();

		// Verify if "US Dollar" selected
		Assert.assertEquals(firstSelected.getText(), "US Dollar");

	}

	@Test
	public void selectGBPoundCurrency() {

		// Find dropdown menu of currency
		WebElement currency = driver.findElement(By.id("currency"));

		// Select "GB Pound"
		((JavascriptExecutor) driver).executeScript(
				"var currency = arguments[0]; for(var i = 0; i < currency.options.length; i++){ if(currency.options[i].text == arguments[1]){ currency.options[i].selected = true; } }",
				currency, "GB Pound");

		// Find "GB Pound" option
		WebElement gbPound = driver
				.findElement(By.xpath("//select[@id='currency']/option[contains(text(), 'GB Pound')]"));

		// Verify if "GB Pound" selected
		Assert.assertTrue(gbPound.isSelected());
	}

	@Test
	public void isEnglishLanguageSelected() {

		// Find <a> tag with selected language
		WebElement languageA = driver
				.findElement(By.xpath("//div[contains(@class, 'header-top')]//a[@class='dropdown-toggle']"));

		// Verify if "English" language selected
		Assert.assertEquals(languageA.getText(), "English");

	}

	@Test
	public void selectSpanishLanguage() {

		// Click on dropdown menu
		driver.findElement(By.xpath("//div[contains(@class, 'header-top')]//a[@class='dropdown-toggle']")).click();

		// Create a new screen object
		Screen screen = new Screen();

		// Click Spanish language
		if (screen.exists("spanish.png", 5) != null) {
			try {
				screen.click("spanish.png");

			} catch (FindFailed e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		// Wait until page is reloaded
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.urlMatches("http://phptravels.net/es"));

		// Find <a> tag with selected language
		WebElement languageA = driver
				.findElement(By.xpath("//div[contains(@class, 'header-top')]//a[@class='dropdown-toggle']"));

		// Verify if "Spanish" language selected
		Assert.assertEquals(languageA.getText(), "Spanish");

	}

}
