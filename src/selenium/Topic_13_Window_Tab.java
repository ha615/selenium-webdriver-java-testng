package selenium;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

	public void TC_02_Greater_Than_2_Windows_Or_Tab() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		String parentTitle = driver.getTitle();
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(2);
		SwitchToWindowTabByTitle("Google");
		Assert.assertTrue(driver.findElement(By.xpath("//img[@alt='Google']")).isDisplayed());
		SwitchToWindowTabByTitle("SELENIUM WEBDRIVER FORM DEMO");
		sleepInSecond(2);

		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		sleepInSecond(2);
		SwitchToWindowTabByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		Assert.assertTrue(
				driver.findElement(By.xpath("//button[@data-view-id='main_search_form_button']")).isDisplayed());
		SwitchToWindowTabByTitle("SELENIUM WEBDRIVER FORM DEMO");
		sleepInSecond(2);

		driver.findElement(By.xpath("//a[text()='LAZADA']")).click();
		sleepInSecond(2);
		SwitchToWindowTabByTitle("Shopping online - Buy online on Lazada.vn");
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='q']")).isDisplayed());
		sleepInSecond(4);

		// đóng tat ca cua so ngoại trừ cửa sổ cha
		closeAllExceptParentWindow(parentTitle);
		sleepInSecond(4);

	}
	@Test
	public void TC03_Windows_Or_Tab() {
		driver.get("https://kyna.vn/");
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='k-footer']//iframe")));
		String locator = "//a[text()='Kyna.vn']";
		clickToElementByJS(locator);
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//div[@id='k-footer']//img[@alt='facebook']")).click();
		driver.findElement(By.xpath("//div[@id='k-footer']//img[@alt='youtube']")).click();
		driver.findElement(By.xpath("//div[@id='k-footer']//img[@alt='zalo']")).click();
		driver.findElement(By.xpath("//div[@id='k-footer-copyright']//a[1]/img[@alt='Khóa học trực tuyến']")).click();
		driver.findElement(By.xpath("//div[@id='k-footer-copyright']//a[2]/img[@alt='Khóa học trực tuyến']")).click();
		sleepInSecond(5);
	}

	private void clickToElementByJS(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
		jsExecutor.executeScript("arguments[0].click();", element);
	}

	public void TC03_Compare_Product() {
		driver.get("http://live.demoguru99.com/index.php/");
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		String parentTitle = driver.getTitle();
		driver.findElement(By.xpath(
				"//a[@title='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']"))
				.click();
		sleepInSecond(2);

		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),
				"The product Sony Xperia has been added to comparison list.");
		sleepInSecond(5);
		driver.findElement(By.xpath(
				"//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']"))
				.click();
		sleepInSecond(2);

		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),
				"The product Samsung Galaxy has been added to comparison list.");

		driver.findElement(By.xpath("//button[@title='Compare']")).click();
		sleepInSecond(2);

		SwitchToWindowTabByTitle("Products Comparison List - Magento Commerce");
		Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");

		closeAllExceptParentWindow(parentTitle);

		SwitchToWindowTabByTitle(parentTitle);

		driver.findElement(By.xpath("//input[@id='search']")).sendKeys("iPhone");
		driver.findElement(By.xpath("//button[@title='Search']")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//a[text()='IPhone']")).getText(), "IPHONE");
	}

	private void closeAllExceptParentWindow(String parentTitle) {
		Set<String> allWindowID = driver.getWindowHandles();
		for (String windowID : allWindowID) {
			driver.switchTo().window(windowID);
			String actualWindowTitle = driver.getTitle();
			if (!actualWindowTitle.equals(parentTitle)) {
				driver.close();
				sleepInSecond(2);
			}
		}

	}

	// >= 2 Windows/Tab by use title
	private void SwitchToWindowTabByTitle(String expectWindowTitle) {
		Set<String> allWindowsID = driver.getWindowHandles();
		for (String windowID : allWindowsID) {
			driver.switchTo().window(windowID);
			String actualWindowTitle = driver.getTitle();
			if (actualWindowTitle.equals(expectWindowTitle)) {
				break;
			}
		}
	}

	// Only 2 Windows/Tabs by use ID
	private void SwitchToWindowTabByID(String pWindowID) {
		Set<String> allWindowsID = driver.getWindowHandles();
		for (String windowID : allWindowsID) {
			if (!windowID.equals(pWindowID)) {
				driver.switchTo().window(windowID);
				break;
			}
		}

	}

	private void sleepInSecond(long i) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(i * 1000);
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