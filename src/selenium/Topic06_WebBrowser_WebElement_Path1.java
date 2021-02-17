package selenium;



import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic06_WebBrowser_WebElement_Path1 {
	WebDriver driver = new FirefoxDriver();

	
	@BeforeTest
	public void beforeTest() {
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
		driver.get("http://live.demoguru99.com/");
	}

	@Test
	public void TC01_VerifyUrl() {
		driver.get("http://live.demoguru99.com/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		String strUrlLP = driver.getCurrentUrl();
		Assert.assertEquals(strUrlLP, "http://live.demoguru99.com/index.php/customer/account/login/");
		
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		String strUrlRP = driver.getCurrentUrl();
		Assert.assertEquals(strUrlRP, "http://live.demoguru99.com/index.php/customer/account/create/");
		
	}
	@Test
	public void TC03_NavigateFunction() {
		driver.get("http://live.demoguru99.com/");
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		String strUrlRG = driver.getCurrentUrl();
		Assert.assertEquals(strUrlRG, "http://live.demoguru99.com/index.php/customer/account/create/");
		driver.navigate().back();
		String strUrlLP = driver.getCurrentUrl();
		Assert.assertEquals(strUrlLP, "http://live.demoguru99.com/index.php/customer/account/login/");
		driver.navigate().forward();
		String strUrlFW = driver.getTitle();
		Assert.assertEquals(strUrlFW, "Create New Customer Account");
	}
	@Test
	public void TC02_VerifyTitle() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		String strTitle1 = driver.getTitle();
		Assert.assertEquals(strTitle1, "Customer Login");
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		String strTitle = driver.getTitle();
		Assert.assertEquals(strTitle, "Create New Customer Account");

	}
	@Test
	public void TC04_GetPageSourceCode() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
	}
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}
