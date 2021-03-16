package selenium;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class Topic_13_Window_Tab {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	public void TC_01_Only_2_Windows_Or_Tabs() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		// Moi 1 Windows hay 1 Tab co 1 iD dai dien
		// Lay ra iD cuar Windows/Tab hien tai dang active
		String parentWindowID = driver.getWindowHandle();
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(4);
		SwitchToWindowTabByID(parentWindowID);
		Assert.assertTrue(driver.findElement(By.xpath("//img[@alt='Google']")).isDisplayed());
		String googleWindowID = driver.getWindowHandle();
		SwitchToWindowTabByID(googleWindowID);
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		sleepInSecond(4);

	}

	@Test
	public void TC_02_Greater_Than_2_Windows_Or_Tab() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(2);
		SwitchToWindowTabByTitle("Google");
		Assert.assertTrue(driver.findElement(By.xpath("//img[@alt='Google']")).isDisplayed());
		SwitchToWindowTabByTitle("SELENIUM WEBDRIVER FORM DEMO");
		sleepInSecond(2);

		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		sleepInSecond(2);
		SwitchToWindowTabByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		Assert.assertTrue(driver.findElement(By.xpath("//button[@data-view-id='main_search_form_button']")).isDisplayed());
		SwitchToWindowTabByTitle("SELENIUM WEBDRIVER FORM DEMO");
		sleepInSecond(2);
		
		driver.findElement(By.xpath("//a[text()='LAZADA']")).click();
		sleepInSecond(2);
		SwitchToWindowTabByTitle("Shopping online - Buy online on Lazada.vn");
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='q']")).isDisplayed());
		sleepInSecond(4);
		
		// tat tat ca cua so ngoại trừ cửa sổ cha
		closeAllWindowsExceptParent();

	}
	
	// >= 2 Windows/Tab by use title
	private void SwitchToWindowTabByTitle(String expectWindowTitle) {
		Set<String> allWindowsID = driver.getWindowHandles();
		for(String windowID : allWindowsID) {
			driver.switchTo().window(windowID);
			String actualWindowTitle = driver.getTitle();
			if(actualWindowTitle.equals(expectWindowTitle)) {
				break;
			}
		}
	}

	// Only 2 Windows/Tabs by use ID
	private void SwitchToWindowTabByID(String pWindowID) {
		Set<String> allWindowsID = driver.getWindowHandles();
		for(String windowID : allWindowsID) {
			if(!windowID.equals(pWindowID)) {
				driver.switchTo().window(windowID);
				break;
			}
		}
		
	}

	private void sleepInSecond(long i) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(i*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}





	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}