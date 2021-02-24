package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class Topic_06_WebBrowser_WebElement_Path4 {
	WebDriver driver = new FirefoxDriver();
	By txtEmail = By.xpath("//input[@id='mail']");
	By txtEdu = By.xpath("//textarea[@id='edu']");
	By rdAge = By.xpath("//input[@value='under_18']");

	@BeforeTest
	public void initTest() {

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public boolean isElementDisplayed(By by) {
		WebElement element = driver.findElement(by);
		if (element.isDisplayed()) {
			System.out.println("Element is display");
			return true;
		} else {
			System.out.println("Element is not display");
			return false;
		}
	}

	public void sendValueToElement(String value, By by) {
		WebElement element = driver.findElement(by);
		element.sendKeys(value);
	}

	public void clickToElement(By by) {
		WebElement element = driver.findElement(by);
		element.click();
	}

	//@Test
	public void TC01_Verify_isDisplaed_Element() throws Exception {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		if (isElementDisplayed(txtEmail)) {
			Thread.sleep(5000);
			sendValueToElement("Automation Testing", txtEmail);
		}

		if (isElementDisplayed(txtEdu)) {
			Thread.sleep(5000);
			sendValueToElement("Automation Testing", txtEdu);
		}

		/*
		 * if(isElementDisplayed(rdAge)) { Thread.sleep(5000); clickToElement(rdAge); }
		 */
		Assert.assertTrue(isElementDisplayed(rdAge));

	}

	//@Test
	public void TC02_Verify_isEnable_Element() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		Assert.assertTrue(isElementEnable(txtEdu));
		Assert.assertFalse(isElementEnable(txtEmail));
	}

	private boolean isElementEnable(By by) {
		WebElement element = driver.findElement(by);
		if(element.isEnabled()){
			System.out.println("Element is Enable");
			return true;
		}
		else {
			System.out.println("Element is disnable");
			return false;
		}

	}

	@Test
	public void TC04_Register_Function_At_Mailchimp() throws InterruptedException {
		driver.get("https://login.mailchimp.com/signup/");
		WebElement txtEmail = driver.findElement(By.cssSelector("#email"));
		WebElement txtUserName = driver.findElement(By.cssSelector("#new_username"));
		WebElement txtPassword = driver.findElement(By.cssSelector("#new_password"));
		txtEmail.sendKeys("Automation Testing");
		txtUserName.sendKeys("Baggio.R");
		// lowercase character
		txtPassword.sendKeys("abc");
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.id("create-account")).isEnabled());
		// uppercase character
		txtPassword.clear();
		txtPassword.sendKeys("ABC");
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed' and text()='One uppercase character']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.id("create-account")).isEnabled());
		// number
		// special character
		// 8 characters minimum
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}
