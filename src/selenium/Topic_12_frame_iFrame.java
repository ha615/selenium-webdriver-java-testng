
package selenium;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	
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
				"[Training Online] â€“ Fullstack Selenium WebDriver Framework in Java (Livestream)");
		// switch to Google doc iFrame
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'docs.google.com')]")));
		Assert.assertEquals(driver.findElement(By.cssSelector(".exportFormTitle")).getText(),
				"KHÃ“A Há»ŒC SELENIUM AUTOMATION TESTING");
	}

	@Test
	public void TC_02_IFrame() {
		driver.get("https://kyna.vn/");
		driver.switchTo().frame("cs_chat_iframe");
		driver.findElement(By.xpath("//div[@class='border_overlay meshim_widget_widgets_BorderOverlay']")).click();
		driver.findElement(By.xpath("//textarea[@name='message']")).sendKeys("zin zin");
		driver.findElement(By.xpath("//textarea[@name='message']")).sendKeys(Keys.ENTER);
		sleepInSecond(5);
	
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//input[@id='live-search-bar']")).sendKeys("Excel");
		driver.findElement(By.xpath("//button[@class='search-button']")).click();
		sleepInSecond(5);
		
		//List<WebElement> courseList = driver.findElements(By.xpath("//ul[@class='k-box-card-list']/li"));
		List<WebElement> courseList = driver.findElements(By.cssSelector("div.content h4"));
		List<String> courseListName = new ArrayList<String>(); 
		for (WebElement courseElement : courseList) {
			System.out.println(courseElement.getText());
			courseListName.add(courseElement.getText());
		}
		
		for (String courseName : courseListName) {
			Assert.assertTrue(courseName.contains("Excel"));
		}
	}

	private void sleepInSecond(long i)  {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(i*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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