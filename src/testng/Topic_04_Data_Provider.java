package testng;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Topic_04_Data_Provider {
	WebDriver driver;
	By emailTextbox = By.id("email");
	By passTextbox = By.id("pass");
	By loginButton = By.id("send2");
	
	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}
	
	
	@Test(dataProvider = "email_pass")
	public void TC_01_Login_To_System(String email, String password) {
		driver.get("http://live.demoguru99.com/index.php/customer/account/login");
		
		driver.findElement(emailTextbox).sendKeys(email);
		driver.findElement(passTextbox).sendKeys(password);
		driver.findElement(loginButton).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains(email));		
		
		driver.findElement(By.xpath("//div[@class='page-header-container']//span[text()='Account']")).click();
		
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
		
		//driver.get("http://live.demoguru99.com/index.php/customer/account/login");

	}
	
	//@Test(dataProvider = "register")
	public void TC_02_Registed(String email, String password) {
		// mngr316362
		// teqEsEd
		driver.get("http://live.demoguru99.com/index.php/customer/account/login");
		
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		
		driver.findElement(By.id("firstname")).sendKeys("Baggio");
		driver.findElement(By.id("middlename")).sendKeys("R.");
		driver.findElement(By.id("lastname")).sendKeys("Mr");
		driver.findElement(By.id("email_address")).sendKeys(email);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys(password);
		
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		
		driver.findElement(By.xpath("//div[@class='page-header-container']//span[text()='Account']")).click();
		
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
	}
	
	@DataProvider(name="email_pass")
	public String[][] EmailAndPassWordData(){
		return new String[][] {
			{"zinzin4925@yahoo.com","111111"},
			{"zinzin5801@yahoo.com","111111"},
			{"zinzin5049@yahoo.com","111111"}
		};
	}
	
	@DataProvider(name="register")
	public String [][] Register_Data(){
		return new String[][]{
			{"zinzin"+ getRandomNumber() + "@yahoo.com","111111"},
			{"zinzin"+ getRandomNumber() + "@yahoo.com","111111"},
			{"zinzin"+ getRandomNumber() + "@yahoo.com","111111"},
			{"zinzin"+ getRandomNumber() + "@yahoo.com","111111"}
		};
	}
	
	public int getRandomNumber() {
		int random = new Random().nextInt(9999);
		return random;
	}
	

}
