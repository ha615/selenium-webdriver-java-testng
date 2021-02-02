package selenium;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Ignore;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Xpath_Css_Html_Path_3 {

	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://live.demoguru99.com/index");
	}
	@Ignore
	public void TC01_Login_With_Empty_Email_And_Password() {
		driver.get("http://live.demoguru99.com");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("");
		driver.findElement(By.name("login[password]")).sendKeys("");
		driver.findElement(By.id("send2")).click();	
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText(),"This is a required field.");
	}
	
	@Ignore
	public void TC02_Login_With_Invalid_Email() {
		driver.get("http://live.demoguru99.com");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("dsab@45363");
		driver.findElement(By.name("login[password]")).sendKeys("123123");
		driver.findElement(By.id("send2")).click();	
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
	}
	@Ignore
	public void TC03_Login_With_Password_Less_6_Character() {
		driver.get("http://live.demoguru99.com");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("dsab@gmail.com");
		driver.findElement(By.name("login[password]")).sendKeys("123");
		driver.findElement(By.id("send2")).click();	
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
	}
	
	@Ignore
	public void TC04_Login_With_Incorrect_Email_Password() {
		driver.get("http://live.demoguru99.com");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("ha615@outlook.com");
		driver.findElement(By.name("login[password]")).sendKeys("123123123");
		driver.findElement(By.id("send2")).click();	
		Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Invalid login or password.']")).getText(), "Invalid login or password.");
	}
	public void sleepThread(long time) {
		try {
			Thread.sleep(time*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Ignore
	public void TC05_Create_A_New_Account() {
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.xpath("//a[@class='button']")).click();
		driver.findElement(By.id("firstname")).sendKeys("Zing");
		driver.findElement(By.name("middlename")).sendKeys("Zin");
		driver.findElement(By.xpath("//input[@title='Last Name']")).sendKeys("Zi");
		int number = new Random().nextInt(11);
		String email = "tuntun" + Integer.toString(number) + "@yahoo.com";
		driver.findElement(By.id("email_address")).sendKeys(email);
		driver.findElement(By.id("password")).sendKeys("123456789");
		driver.findElement(By.id("confirmation")).sendKeys("123456789");
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		
		sleepThread(5);
		
		
	}
	
	@Test
	//tuntun10@yahoo.com
	public void TC06_Login_With_Valid_Email_Password() {
		driver.get("http://live.demoguru99.com");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("tuntun10@yahoo.com");
		driver.findElement(By.name("login[password]")).sendKeys("123456789");
		driver.findElement(By.id("send2")).click();	
		sleepThread(5);
		Assert.assertEquals(driver.findElement(By.xpath("//h1[text()='My Dashboard']")).getText(), "MY DASHBOARD");
		Assert.assertEquals(driver.findElement(By.xpath("//strong[text()='Hello, Zing Zin Zi!']")).getText(), "Hello, Zing Zin Zi!");

	} 
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
