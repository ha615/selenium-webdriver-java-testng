package selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class Topic_16_Wait_Path2 {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		//timeout nay duoc apply duy nhat cho viec tim element(findElement/findElements), neu khong set mac dinh=   0
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	public void TC_01_FindElement() {
		driver.get("http://automationpractice.com/index.php?controller=my-account");
		driver.findElement(By.xpath("//input[@id='order_email']"));
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("zizi@yahoo.com");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	}

	@Test
	public void TC_02_FindElements() {
		driver.get("http://automationpractice.com/index.php?controller=my-account");

		// Case 1: tim khong thay element
		List<WebElement> elements = driver.findElements(By.xpath("//input[@id='order_email']"));
		// neu chua het timeout tiep tuc tim - neu thay -> pass
		// luon tim element theo chu ki 0.5s tim lai 1 lan cho den het thoi gian timeout
		// implicitWait
		// Ket qua: khong danh Fail testcase, tra ve danh sach rong
		System.out.println("Count element in Lis: " + elements.size());
		Assert.assertTrue(elements.isEmpty());
		Assert.assertEquals(elements.size(), 0);

		// Case 2: tim thay 1 phan tu duy nhat
		elements = driver.findElements(By.xpath("//input[@id='email']"));
		System.out.println("Count element is List: " + elements.size());
		Assert.assertFalse(elements.isEmpty());
		Assert.assertEquals(elements.size(), 1);

		// Case 3: tim thay hon 1 phan tu
		elements = driver.findElements(By.xpath("//button[@type='submit']"));
		System.out.println("Count List: " + elements.size());
		Assert.assertFalse(elements.isEmpty());
		Assert.assertEquals(elements.size(), 4);

	}
	
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}