package testng;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Topic_05_Parameter {
	WebDriver driver;
	By emailTextbox = By.id("email");
	By passTextbox = By.id("pass");
	By loginButton = By.id("send2");

	@Parameters({"browser", "url"})
	@BeforeClass
	//public void beforeClass( String browserName, @Optional("http://live.demoguru99.com")String urlValue) {
	public void beforeClass( String browserName,String urlValue) {
		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browserName.equals("opera")) {
			System.setProperty("webdriver.opera.driver", ".\\browserDrivers\\operadriver.exe");
			driver = new OperaDriver();
		} else {
			throw new RuntimeException("Please choose other browser");
		}
		//driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get(urlValue);
	}

	@Test(dataProvider = "register")
	public void TC_02_Registed(String email, String password) {
		// mngr316362
		// teqEsEd
		

		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		
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

	@DataProvider(name = "register")
	public String[][] Register_Data() {
		return new String[][] { { "zinzin" + getRandomNumber() + "@yahoo.com", "111111" },
				{ "zinzin" + getRandomNumber() + "@yahoo.com", "111111" },
				{ "zinzin" + getRandomNumber() + "@yahoo.com", "111111" },
				{ "zinzin" + getRandomNumber() + "@yahoo.com", "111111" } };
	}

	public int getRandomNumber() {
		int random = new Random().nextInt(9999);
		return random;
	}

}
