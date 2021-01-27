package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Xpath_Css_Html_Path_2 {
	WebDriver driver;
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}
	public void TC_01_ID() throws InterruptedException {
		driver.findElement(By.id("email")).sendKeys("sdet615@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123456");
		Thread.sleep(3000);
	}
	

	public void TC_02_Class() throws InterruptedException {
		driver.findElement(By.className("//input[@class='textType']")).sendKeys("zingzing@outlook.com");
		Thread.sleep(3000);
	}

	public void TC_03_Name() throws InterruptedException {

		Thread.sleep(3000);
	}

	public void TC_08_TagName() throws InterruptedException {
		int sizeof = driver.findElements(By.tagName("a")).size();
		System.out.println("so luong name a " + sizeof);
		Thread.sleep(3000);
	}

	public void TC_06_LinkText() throws InterruptedException {
		driver.findElement(By.linkText("Forgot Your Password?")).click();
		Thread.sleep(4000);
	}

	public void TC_07_Partial_LinkText() {
		
	}
	@Test
	public void TC_04_Xpath() throws InterruptedException {
		driver.get("https://login.ubuntu.com/");
		driver.findElement(By.xpath("//form[@id='login-form']//input[@id='id_email']")).sendKeys("lalamail");
		sleepInSecond(3);
		
		driver.get("https://login.ubuntu.com/");
		driver.findElement(By.xpath("//label[@for='id_email']/following-sibling::input")).sendKeys("tintin");
		sleepInSecond(3);
	}

	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void TC_05_Css() throws InterruptedException {
		driver.get("https://login.ubuntu.com/");
		driver.findElement(By.cssSelector("input[id='id_email']")).sendKeys("ducducgo@gmail.com");
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("input[id='id_email']")).clear();
		
		driver.findElement(By.cssSelector("input[placeholder='Your email address']")).sendKeys("zingzing@gmail.com");
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("input[placeholder='Your email address']")).clear();
		
		driver.findElement(By.cssSelector("input[name='email']")).sendKeys("tintin@outlook.com");
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("input[name='email']")).clear();
	}


	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
