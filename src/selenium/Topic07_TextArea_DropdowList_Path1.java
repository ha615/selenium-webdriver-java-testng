package selenium;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import org.testng.Assert;

public class Topic07_TextArea_DropdowList_Path1 {
	WebDriver driver;
	String loginPageUrl;
	String userID;
	String password;
	String name, dob, address, city, state, pin, phone, email;
	private By txtName = By.xpath("//input[@name='name']");
	private By inputDOB = By.name("dob");
	private By txtareaAddress = By.name("addr");
	private By txtCity = By.name("city");
	private By txtState = By.name("state");
	private By txtPin = By.name("pinno");
	private By txtTelephone = By.name("telephoneno");
	private By txtEmail = By.name("emailid");
	private By txtPas = By.name("password");

	private By btnSubmit = By.name("sub");
	private By btnReset = By.name("res");
	@BeforeClass
	public void beforeClass() {
		//32771
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
		driver.get("https://www.demo.guru99.com/v4/");
		name = "brian adam";
		dob = "01-01-1990";
		address = "Lang Kenh Phu Bai Nam Dinh";
		city = "Nam Dinh";
		state = "Quat Lam Tu";
		pin = "021531";
		phone = "0987256351";
		email = "brianadam@yahoo.com";
		password = "0987256351";
	}

	@Test
	public void TC01_Register() {
		loginPageUrl = driver.getCurrentUrl();
		Random ran = new Random();
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.name("emailid")).sendKeys("zinzin" + ran.nextInt(999999) + "@gmail.com");
		driver.findElement(By.name("btnLogin")).click();
		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
	}

	@Test
	public void TC02_Login() {
		driver.get(loginPageUrl);
		loginPageUrl = driver.getCurrentUrl();
		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("btnLogin")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='Manger Id : " + userID + "']")).isDisplayed());
	}

	@Test
	public void TC03_New_Customer() {
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		
		driver.findElement(txtName).sendKeys(name);
		driver.findElement(inputDOB).sendKeys(dob);
		driver.findElement(txtareaAddress).sendKeys(address);
		driver.findElement(txtCity).sendKeys(city);
		driver.findElement(txtState).sendKeys(state);
		driver.findElement(txtPin).sendKeys(pin);
		driver.findElement(txtTelephone).sendKeys(phone);
		driver.findElement(txtEmail).sendKeys(email);
		driver.findElement(txtPas).sendKeys(password);

		driver.findElement(btnSubmit).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Customer Registered Successfully!!!']")).isDisplayed());
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dob);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), address);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), phone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), email);
		
	}

	@Test
	public void TC04_Edit_Customer() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
