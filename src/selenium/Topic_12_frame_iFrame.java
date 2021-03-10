package selenium;

import java.util.Spliterator;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class Topic_12_frame_iFrame {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_IFrame() {
		driver.get("https://automationfc.com/2020/02/18/training-online-automation-testing/");
		// switch to iFrame FB
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@title,'Facebook Social Plugin')]")));
		Assert.assertEquals(driver.findElement(By.xpath("//a[@title='Automation FC']")).getText(), "Automation FC");
		// Assert.assertEquals(driver.findElement(By.xpath("//a[@title='Automation
		// FC']/parent::div/following-sibling::div")).getText(), "2,187 likes");
		String txtNumber = driver
				.findElement(By.xpath("//a[@title='Automation FC']/parent::div/following-sibling::div")).getText();
		String likeNumber = txtNumber.split(" ")[0].replace(",", "");
		int countLike = Integer.parseInt(likeNumber);
		assertThat(countLike, greaterThan(2000));

		// switch to Top Windows
		driver.switchTo().defaultContent();
		Assert.assertEquals(driver.findElement(By.xpath("//h1[@class='post-title']")).getText(),
				"[Training Online] – Fullstack Selenium WebDriver Framework in Java (Livestream)");
		// switch to Google doc iFrame
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'docs.google.com')]")));
		Assert.assertEquals(driver.findElement(By.cssSelector(".exportFormTitle")).getText(),
				"KHÓA HỌC SELENIUM AUTOMATION TESTING");
	}

	// @Test
	public void TC_02_ValidatePageTitle() {
		// Login Page title
	}

	// @Test
	public void TC_03_LoginFormDisplayed() {
		// Login form displayed

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}