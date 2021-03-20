package selenium;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Wait_Path3 {
	WebDriver driver;
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 1);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	public void TC_01_ImplicitWait() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.xpath("//div[@id='start']/button")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']/h4")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");

	}

	public void TC_02_ExplicitWait_Visible() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.xpath("//div[@id='start']/button")).click();
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='finish']/h4")));
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']/h4")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");

	}

	public void TC_03_ExplicitWait_Invisible() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.xpath("//div[@id='start']/button")).click();
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='loading']")));
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']/h4")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");

	}

	@Test
	public void TC04_ExplicitWait_UploadFile() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(
				"D:\\AutomationTest\\02.Selenium-API\\selenium-webdriver-java-testng\\uploadFiles\\Untitled.png");
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(
				"D:\\AutomationTest\\02.Selenium-API\\selenium-webdriver-java-testng\\uploadFiles\\backgroud.jpg");

		explicitWait.until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//div[@class='progress-bar progress-bar-success']")));

		explicitWait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary start']")));

		List<WebElement> elements = driver
				.findElements(By.xpath("//button[@class='btn btn-primary start']//span[text()='Start']"));
		
		for(WebElement item:elements) {
			item.click();
		}

		Assert.assertTrue(	driver.findElement(By.xpath("//a[@title='backgroud.jpg']")).isDisplayed());

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}