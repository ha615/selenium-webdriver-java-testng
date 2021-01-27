package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Xpath_Css_Html_Path_1 {

	WebDriver driver;
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	}
	@Test
	public void loginForm() throws Exception {
		//Hiểu được html của 1 Element
		// Tại sao phải bắt Element
		// Bắt xong phải làm gì/thao tác như thế nào
		driver.findElement(By.id("txtFirstname")).sendKeys("Luong Khong Nho");
		driver.findElement(By.id("txtEmail")).sendKeys("lkn@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("lkn@gmail.com");
		driver.findElement(By.cssSelector("#txtPassword")).sendKeys("123456");
		// Thao tác đăng kí button
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		// driver: đại diện cho selenium webdriver - gọi thư viện ra để sử dụng
		// findElement: tìm element
		// by.id, by.xpaht, by.css: loại locator gì
		// click(): hành động
		
		Thread.sleep(5000);
		
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
