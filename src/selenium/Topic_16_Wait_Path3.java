package selenium;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Wait_Path3 {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	String strSystem = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		
		  System.setProperty("webdriver.chrome.driver",
		  ".\\browserDrivers\\chromedriver.exe"); driver = new ChromeDriver();
		 
		//driver = new FirefoxDriver();
		
		jsExecutor = (JavascriptExecutor) driver;
		explicitWait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	
	public void TC_01_ImplicitWait() {
		driver.get(
				"https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

		String todayClick = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText();
		System.out.println("Before click on calendar: " + todayClick);

		driver.findElement(By.xpath("//button[@id='onetrust-accept-btn-handler']")).click();

		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='16']/parent::td")));
//		jsExecutor.executeScript("arguments[0].scrollIntoView(true);",
//				driver.findElement(By.xpath("//a[text()='16']/parent::td")));

		// driver.findElement(By.xpath("//a[text()='16']/parent::td")).click();
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[text()='16']/parent::td")));
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(
				"//div[@class='raDiv']/parent::div[not(@style = 'display:none;')]")));
		
		todayClick = driver
				.findElement(
						By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1' and text()='Tuesday, March 16, 2021']"))
				.getText();
		System.out.println("After click on calendar: " + todayClick);


		Assert.assertEquals(todayClick, "Tuesday, March 16, 2021");

	}
	
	
	public void TC02_Upload_File() {
		driver.get("https://www.file.io/");
		String parentURL = driver.getWindowHandle();
	//	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='file']")));
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(strSystem + "\\uploadFiles\\backgroud.jpg");
		//driver.findElement(By.xpath("//input[@type='file']")).sendKeys(strSystem + "\\uploadFiles\\derive-taipei-cody-ellingham.jpg");
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//button[@id='progress-full']")));
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("download-url")));
		driver.findElement(By.id("download-url")).click();
		SwitchToWindowTabByID(parentURL);
		Assert.assertTrue(driver.findElement(By.xpath("//button[text()='Download File']")).isDisplayed());
	}

	@Test
	public void TC03_Upload_File() {
		driver.get("https://filebin.net/");
	
		driver.findElement(By.id("fileField")).sendKeys(strSystem + "\\uploadFiles\\derive-taipei-cody-ellingham.jpg");
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='progress']")));
	
		Assert.assertEquals(driver.findElement(By.xpath("//td/a[text()='derive-taipei-cody-ellingham.jpg']")).getText(),"derive-taipei-cody-ellingham.jpg");
	}

	private void SwitchToWindowTabByID(String parentURL) {
		Set<String> allWindows = driver.getWindowHandles();
		for(String currentWindow : allWindows) {
			if(!currentWindow.equals(parentURL)) {
				driver.switchTo().window(currentWindow);
			}
		}
			
	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}