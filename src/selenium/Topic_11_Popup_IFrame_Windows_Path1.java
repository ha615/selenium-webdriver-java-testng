package selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class Topic_11_Popup_IFrame_Windows_Path1 {
	WebDriver driver;
	WebDriverWait explicitWait;
	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 10);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	
	public void TC01_Fixed_Popup() {
		driver.get("https://www.zingpoll.com/");
		
		driver.findElement(By.xpath("//a[@id='Loginform']")).click();
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='Loginform']")));
		Assert.assertTrue(driver.findElement(By.xpath("//form[@id='loginForm']")).isDisplayed());
		
		//driver.findElement(By.xpath("//button[@onclick='ResetForm()']//span[text()='×']")).click();
		driver.findElement(By.xpath("//div[@id='Login']//button[@class='close']")).click();

		
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//form[@id='loginForm']")));
		
		Assert.assertFalse(driver.findElement(By.xpath("//form[@id='loginForm']")).isDisplayed());

	}
	
	public void TC02_Fixed_Popup() {
		driver.get("https://bni.vn/");
		//doi toi khi element xuat hien
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='sgpb-inputs-container']")));
		driver.findElement(By.xpath("//input[@value='JOIN WITH US']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='sgpb-inputs-container']")).isDisplayed());
		
		// cho cho toi khi hinh nay co duoc click hay khong(load het hinh len)
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@alt='Close']")));
		driver.findElement(By.xpath("//img[@alt='Close']")).click();
		
		// check no toi khi hinh khong hien thi
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='sgpb-inputs-container']")));
		Assert.assertFalse(driver.findElement(By.xpath("//div[@class='sgpb-inputs-container']")).isDisplayed());
	}
	
	public void TC03_Random_Popup_In_DOM() {
		driver.get("https://blog.testproject.io/");
		//sleepInSeconds(7);
		if(driver.findElement(By.xpath("//div[@class='mailch-wrap']")).isDisplayed()) {
			explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='close-mailch']")));
			driver.findElement(By.xpath("//div[@class='close-mailch']")).click();
			
			explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='mailch-wrap']")));
		}
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section//input[@class='search-field']")));
		driver.findElement(By.xpath("//section//input[@class='search-field']")).sendKeys("Selenium");

		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//section//span[@class='glass']")));
		driver.findElement(By.xpath("//section//span[@class='glass']")).click();
		
		sleepInSeconds(5);
	}
	
	@Test
	public void TC04_Random_Popup_Not_In_DOM() {
		driver.get("https://shopee.vn/");
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='home_popup_banner']")));
		List<WebElement> popup = driver.findElements(By.xpath("//img[@alt='home_popup_banner']"));
		if(popup.size()>0 && popup.get(0).isDisplayed()) {
			System.out.println("Close popup");
			explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".shopee-popup__close-btn")));
			driver.findElement(By.cssSelector(".shopee-popup__close-btn")).click();
			
		}else {
			System.out.println("Popup is not display");
		}
		
	}
	public void sleepInSeconds(long times) {
		try {
			Thread.sleep(times*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@AfterTest
	public void afterTest() {
		driver.close();
	}
}
