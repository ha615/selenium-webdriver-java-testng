package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Xpath_Css_Html {

	WebDriver driver;
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	}
	@Test
	public void loginForm() {
		driver.findElement(By.id("txtFirstname")).sendKeys("Luong Khong Nho");
		driver.findElement(By.id("txtEmail")).sendKeys("lkn@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("lkn@gmail.com");
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
