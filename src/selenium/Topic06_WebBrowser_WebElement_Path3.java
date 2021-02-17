package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic06_WebBrowser_WebElement_Path3 {
	WebDriver driver = new FirefoxDriver();

	@BeforeTest
	public void initTest() {

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public boolean isElementDisplayed(WebElement element) {
		if (element.isDisplayed()) {
			System.out.println("Element is display");
			return true;
		} else {
			System.out.println("Element is not display");
			return false;
		}
	}

	public void sendValueToElement(String value, WebElement element) {
		element.sendKeys(value);
	}

	public void clickToElement(WebElement element) {
		element.click();
	}
	@Test
	public void TC01_Verify_isDisplaed_Element() throws Exception {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		WebElement txtEmail = driver.findElement(By.xpath("//input[@id='mail']"));
		WebElement txtAge = driver.findElement(By.xpath("//input[@value='under_18']"));
		WebElement txtEdu = driver.findElement(By.xpath("//textarea[@id='edu']"));
		if(isElementDisplayed(txtEmail)) {
			Thread.sleep(5000);
			sendValueToElement("Automation Testing", txtEmail);
		}
		
		if(isElementDisplayed(txtEdu)) {
			Thread.sleep(5000);
			sendValueToElement("Automation Testing", txtEdu);
		}

		
		if(isElementDisplayed(txtAge)) {
			Thread.sleep(5000);
			clickToElement(txtAge);
		}
	}

	@Test
	public void TC02_Verify_isEnable_Element() {
		WebElement txtEmail = driver.findElement(By.cssSelector("#mail"));
		if (txtEmail.isEnabled())
			System.out.println("Element is enable");
		else
			System.out.println("Element " + txtEmail.getTagName() + " is not enable");

		if (driver.findElement(By.cssSelector("#radio-disabled")).isEnabled())
			System.out.println("Element is enable");
		else
			System.out.println("Element is not enable");

	}

	// @Test
	public void TC04_Register_Function_At_Mailchimp() {
		driver.get("https://login.mailchimp.com/signup/");
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("zinzin@gmail.com");
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("bobosusu");
		WebElement txtPassword = driver.findElement(By.xpath("//input[@id='new_password']"));
		txtPassword.sendKeys("123455");
		Boolean bool = driver.findElement(By.cssSelector("#create-account")).isEnabled();
		if (bool)
			System.out.println("Password valid");
		else
			System.out.println("Password invalid");
		txtPassword.clear();
		txtPassword.sendKeys("abcdqwe");
		bool = driver.findElement(By.cssSelector("#create-account")).isEnabled();
		if (bool)
			System.out.println("Password valid");
		else
			System.out.println("Password invalid");
		driver.findElement(By.xpath("//input[@id='new_password']")).sendKeys("ASDFSDSD");
		bool = driver.findElement(By.cssSelector("#create-account")).isEnabled();
		if (bool)
			System.out.println("Password valid");
		else
			System.out.println("Password invalid");

		txtPassword.clear();
		txtPassword.sendKeys("!@#$%^");
		bool = driver.findElement(By.cssSelector("#create-account")).isEnabled();
		if (bool)
			System.out.println("Password valid");
		else
			System.out.println("Password invalid");

		txtPassword.clear();
		txtPassword.sendKeys("abcdqwe12A!@#");
		bool = driver.findElement(By.cssSelector("#create-account")).isEnabled();
		if (bool)
			System.out.println("Password valid");
		else
			System.out.println("Password invalid");
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}
