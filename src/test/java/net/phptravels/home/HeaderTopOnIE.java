package net.phptravels.home;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.ImagePath;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HeaderTopOnIE {

	WebDriver driver;

	@BeforeMethod
	public void beforeMethod() {

		// Launch IE browser
		driver = new InternetExplorerDriver();

		// Maximize the browser window
		driver.manage().window().maximize();

		// Navigate to http://phptravels.net
		driver.get("http://phptravels.net/");

		// Set implicitly timeout to 10 seconds
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@AfterMethod
	public void afterMethod() {

		// Close IE browser
		driver.quit();

	}

	@BeforeClass
	public void beforeClass() {

		// Set IE driver property
		System.setProperty("webdriver.ie.driver", "./src/test/resources/drivers/IEDriverServer_2.53.1.exe");

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

		// Create new select object
		Select currency = new Select(driver.findElement(By.id("currency")));

		// Select "GB Pound"
		currency.selectByVisibleText("GB Pound");

		// Find "GB Pound"
		WebElement gbPound = driver.findElement(By.xpath("//select[@id='currency']/option[@value='3']"));

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
		WebElement languageA = driver
				.findElement(By.xpath("//div[contains(@class, 'header-top')]//a[@class='dropdown-toggle']"));

		((JavascriptExecutor) driver).executeScript("var lang = arguments[0]; lang.click()", languageA);

		// Click on Spanish language
		List<WebElement> languageList = driver.findElements(By.cssSelector(".dropdown-menu>li>a.changelang"));
		for (WebElement l : languageList) {
			if (l.getText().equals("Spanish")) {
				((JavascriptExecutor) driver).executeScript("var l = arguments[0]; l.click()", l);
				break;
			}
		}

		// Wait until page is reloaded
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.urlMatches("http://phptravels.net/es"));

		// Find <a> tag with selected language
		languageA = driver.findElement(By.xpath("//div[contains(@class, 'header-top')]//a[@class='dropdown-toggle']"));

		// Verify if "Spanish" language selected
		Assert.assertEquals(languageA.getText(), "Spanish");
	}

}
