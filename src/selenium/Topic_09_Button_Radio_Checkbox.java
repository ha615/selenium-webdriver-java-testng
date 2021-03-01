package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_09_Button_Radio_Checkbox {
	WebDriver driver;
	JavascriptExecutor jsExecutor;

	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
	}

	public void TC01_Button_JavaScript_Executor() {
		driver.get("https://www.fahasa.com/customer/account/create");

		driver.findElement(By.xpath("//a[text() = 'Ä�Äƒng nháº­p']")).click();
		Assert.assertFalse(driver.findElement(By.xpath("//button[@class='fhs-btn-login']")).isEnabled());

		driver.findElement(By.xpath("//input[@id='login_username']")).sendKeys("automationtesting@yahoo.com");
		driver.findElement(By.xpath("//input[@id='login_password']")).sendKeys("0988288977");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//button[@class='fhs-btn-login']")).isEnabled());

		driver.navigate().refresh();
		driver.findElement(By.xpath("//a[text() = 'Ä�Äƒng nháº­p']")).click();
		sleepInSecond(3);

		By loginBtn = By.cssSelector(".fhs-btn-login");
		removeDisableAtributeByJS(loginBtn);
		sleepInSecond(3);

		driver.findElement(loginBtn).click();

		Assert.assertEquals(driver
				.findElement(By.xpath(
						"//label[text()='Sá»‘ Ä‘iá»‡n thoáº¡i/Email']/following-sibling::div[@class='fhs-input-alert']"))
				.getText(), "ThÃ´ng tin nÃ y khÃ´ng thá»ƒ Ä‘á»ƒ trá»‘ng");
		
		Assert.assertEquals(driver
				.findElement(By.xpath(
						"//label[text()='Máº­t kháº©u']/following-sibling::div[@class='fhs-input-alert']"))
				.getText(), "ThÃ´ng tin nÃ y khÃ´ng thá»ƒ Ä‘á»ƒ trá»‘ng");
	}
	
	@Test
	public void TC02_Default_Checkbox_Or_RadioButton() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		driver.findElement(By.xpath("//input[@type='checkbox' and @value='Anemia']")).click();
		sleepInSecond(3);
		//label[text()=' Anemia ']/preceding-sibling::input
		Assert.assertTrue(driver.findElement(By.xpath("//label[text()=' Anemia ']/preceding-sibling::input")).isSelected());
		
		driver.findElement(By.xpath("//input[@type='checkbox' and @value='Anemia']")).click();
		sleepInSecond(3);
		Assert.assertFalse(driver.findElement(By.xpath("//label[text()=' Anemia ']/preceding-sibling::input")).isSelected());

	}



	private void removeDisableAtributeByJS(By by) {
		WebElement element = driver.findElement(by);
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled')", element);
	}

	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
