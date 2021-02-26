package selenium;

import static org.junit.Assert.assertArrayEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_09_Button_Radio_Checkbox {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;
	Alert alert;
	By result = By.xpath("//p[@id='result']");
	
	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
	}

	public void TC01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");

		driver.findElement(By.xpath("//a[text() = 'Đăng nhập']")).click();
		Assert.assertFalse(driver.findElement(By.xpath("//button[@class='fhs-btn-login']")).isEnabled());

		driver.findElement(By.xpath("//input[@id='login_username']")).sendKeys("automationtesting@yahoo.com");
		driver.findElement(By.xpath("//input[@id='login_password']")).sendKeys("0988288977");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//button[@class='fhs-btn-login']")).isEnabled());

		driver.navigate().refresh();
		driver.findElement(By.xpath("//a[text() = 'Đăng nhập']")).click();
		sleepInSecond(3);

		By loginBtn = By.cssSelector(".fhs-btn-login");
		removeDisableAtributeByJS(loginBtn);
		sleepInSecond(3);

		driver.findElement(loginBtn).click();

		Assert.assertEquals(driver
				.findElement(By.xpath(
						"//label[text()='Số điện thoại/Email']/following-sibling::div[@class='fhs-input-alert']"))
				.getText(), "Thông tin này không thể để trống");

		Assert.assertEquals(driver
				.findElement(By.xpath("//label[text()='Mật khẩu']/following-sibling::div[@class='fhs-input-alert']"))
				.getText(), "Thông tin này không thể để trống");
	}

	public void TC02_Default_Radio_Checkbox() {
		driver.get("https://automationfc.github.io/multiple-fields/");

		/*
		 * List<WebElement> elements =
		 * driver.findElements(By.xpath("//input[@type='checkbox']")); for(WebElement
		 * item:elements) item.click();
		 */

		driver.findElement(By.xpath("//input[@value='Digestive Problems']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//input[@value='Digestive Problems']")).isSelected());
		sleepInSecond(3);
		driver.findElement(By.xpath("//input[@value='Digestive Problems']")).click();

		driver.findElement(By.xpath("//input[@value='I have a strict diet']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//input[@value='I have a strict diet']")).isSelected());
	}

	public void TC03_Custom_Radio_Checkbox() {
		driver.get("https://material.angular.io/components/radio/examples");
		// 1 The input khong click duoc nhung verify duoc
		// isSelected() chi kiem tra duoc cho the input, khong kiem tra duoc cho the
		// span, label
		// driver.findElement(By.xpath("//input[@value='Winter']")).click();
		// Assert.assertTrue(driver.findElement(By.xpath("//input[@value='Winter']")).isSelected());

		// 2- The span dung de click duoc nhung khong verify duoc
		/*
		 * driver.findElement( By.xpath(
		 * "//input[@value='Winter']/preceding-sibling::span[@class='mat-radio-outer-circle']"
		 * )).click();
		 * 
		 * Assert.assertTrue(driver .findElement( By.xpath(
		 * "//input[@value='Winter']/preceding-sibling::span[@class='mat-radio-outer-circle']"
		 * )) .isSelected());
		 */

		// 3- ket hop ca 2: (khi lam du an 1element phai design voi 2locator khac nhau
		// -> so luong code tang
		// len/bao tri mat thoi gian)
		// driver.findElement(
		// By.xpath("//input[@value='Winter']/preceding-sibling::span[@class='mat-radio-outer-circle']")).click();
		// Assert.assertTrue(driver.findElement(By.xpath("//input[@value='Winter']")).isSelected());

		// 4- Dung JavaScript vua click vua verify luon voi the input, khong quan tam
		// element hidden/visible
		By radioBtn = By.xpath("//input[@value='Winter']");
		clickByJS(radioBtn);
		Assert.assertTrue(driver.findElement(radioBtn).isSelected());
		sleepInSecond(4);
	}
	
	public void TC04_Custom_Radio_Checkbox() {
		driver.get(
				"https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@data-value='Cần Thơ' and @aria-checked='false']")).isDisplayed());
		driver.findElement(By.xpath("//div[@data-value='Cần Thơ' and @aria-checked='false']")).click();
		sleepInSecond(4);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@data-value='Cần Thơ' and @aria-checked='true']")).isDisplayed());
	}
	
	@Test
	public void TC05_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		explicitWait.until(ExpectedConditions.alertIsPresent());
		alert = driver.switchTo().alert();
		//alert.dismiss();
		Assert.assertEquals(alert.getText(),"I am a JS Alert");
		//alert.sendKeys("zin zin");
		sleepInSecond(4);
		alert.accept();
		sleepInSecond(4);
		Assert.assertEquals(driver.findElement(result).getText(), "You clicked an alert successfully");
		
	}
	private void clickByJS(By by) {
		WebElement element = driver.findElement(by);
		jsExecutor.executeScript("arguments[0].click()", element);
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
