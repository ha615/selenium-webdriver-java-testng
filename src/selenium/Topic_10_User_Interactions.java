package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_10_User_Interactions {
	WebDriver driver;
	Actions actions;
	WebDriverWait explicitWait;

	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		actions = new Actions(driver);
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public void TC01_Hover_To_Element_Tooltip() {
		driver.get("https://jqueryui.com/resources/demos/tooltip/default.html");
		actions.moveToElement(driver.findElement(By.xpath("//input[@id='age']"))).perform();
		sleepSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).getText(),
				"Hover the field to see the tooltip.");
	}

	@Test
	public void TC02_Hover_To_Element() {
		driver.get("https://www.myntra.com/");
		actions.moveToElement(driver.findElement(By.xpath("//div[@class='desktop-navLink']/a[text()='Kids']"))).perform();
		sleepSecond(4);
		driver.findElement(By.xpath("//a[text()='Home & Bath']")).click();
		sleepSecond(6);
	}

	private void sleepSecond(long i) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(i * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}
