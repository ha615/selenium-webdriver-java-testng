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

public class Topic_07_TextArea_DropdowList_Path1 {
	WebDriver driver;
	String userID = "", password = "";
	String loginUrl = "";
	
	By txtCustomerName = By.name("name");
	By rdGender = By.name("gender");
	By dtpDOB = By.name("dob");
	By txtareaAddress = By.name("addr");
	By txtCity = By.name("city");
	By txtState = By.name("state");
	By txtPIN = By.name("pinno");
	By txtPhone = By.name("telephoneno");
	By txtEmail = By.name("emailid");
	By txtPass = By.name("password");
	String customerID;
	String customerName;
	String gender;
	String dayOfBirt;
	String addr;
	String city;
	String state;
	String pin;
	String phone;
	String email;

	String editAaddr;
	String editCity;
	String editState;
	String editPin;
	String editPhone;
	String editEmail;
	
	@BeforeClass
	public void beforeClass() {
		//32771
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
		driver.get("https://www.demo.guru99.com/v4");
	}

	@Test
	public void TC01_Register() {
		loginUrl  = driver.getCurrentUrl();
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.name("emailid")).sendKeys("zinzin@gmail.com");
		driver.findElement(By.name("btnLogin")).click();
		
		// get info user/pass ra luu vao bien
		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
		
		Random ran = new Random();
		
		customerName = "Linh";
		gender = "male";
		dayOfBirt = "2000-01-02";
		addr ="Cau Ong Lanh";
		city = "HCM City";
		state = "Viet Nam";
		pin = "065897";
		phone = "0987123654";
		email = "zinzin"+ ran.nextInt(999999) +"@gmail.com";
		
		editAaddr = "Cho Cau Muoi";
		editCity = "Vung Tau";
		editState ="Viet Nam";
		editPin ="657812";
		editPhone = "0987123888";
		editEmail = "subo"+ ran.nextInt(999999) + "@yahoo.com";
	}

	@Test
	public void TC02_Login() {	
		driver.get(loginUrl);
		//set gia tri tu bien vao form dang nhap
		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(txtPass).sendKeys(password);
		driver.findElement(By.name("btnLogin")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='Manger Id : " + userID +"']")).isDisplayed());
	}

	@Test
	public void TC03_New_Customer() throws InterruptedException {
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		//input
		driver.findElement(txtCustomerName).sendKeys(customerName);
		driver.findElement(dtpDOB).sendKeys(dayOfBirt);
		driver.findElement(txtareaAddress).sendKeys(addr);
		driver.findElement(txtCity).sendKeys(city);
		driver.findElement(txtState).sendKeys(state);
		driver.findElement(txtPIN).sendKeys(pin);
		driver.findElement(txtPhone).sendKeys(phone);
		driver.findElement(txtEmail).sendKeys(email);
		driver.findElement(txtPass).sendKeys(password);
		driver.findElement(By.name("sub")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Customer Registered Successfully!!!']")).isDisplayed());
	
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), customerName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dayOfBirt);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), addr);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), phone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), email);
		
		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
	}

	@Test
	public void TC04_Edit_Customer() {
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.name("cusid")).sendKeys(customerID);
		driver.findElement(By.name("AccSubmit")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Edit Customer']")).isDisplayed());
		//verify 3 object isEnable
		Assert.assertFalse(isElementEnalble(txtCustomerName));
		Assert.assertFalse(isElementEnalble(rdGender));
		Assert.assertFalse(isElementEnalble(dtpDOB));
		
		//verify all field
		Assert.assertEquals(driver.findElement(txtCustomerName).getAttribute("value"), customerName);
		Assert.assertEquals(driver.findElement(rdGender).getAttribute("value"), gender);
		Assert.assertEquals(driver.findElement(dtpDOB).getAttribute("value"), dayOfBirt);
		Assert.assertEquals(driver.findElement(txtareaAddress).getText(), addr);
		Assert.assertEquals(driver.findElement(txtCity).getAttribute("value"), city);
		Assert.assertEquals(driver.findElement(txtState).getAttribute("value"), state);
		
		//input edit data
		driver.findElement(txtareaAddress).clear();
		driver.findElement(txtCity).clear();
		driver.findElement(txtState).clear();
		driver.findElement(txtPIN).clear();
		driver.findElement(txtPhone).clear();
		driver.findElement(txtEmail).clear();
	
		driver.findElement(txtareaAddress).sendKeys(editAaddr);
		driver.findElement(txtCity).sendKeys(editCity);
		driver.findElement(txtState).sendKeys(editState);
		driver.findElement(txtPIN).sendKeys(editPin);
		driver.findElement(txtPhone).sendKeys(editPhone);
		driver.findElement(txtEmail).sendKeys(editEmail);
		driver.findElement(By.name("sub")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Customer details updated Successfully!!!']")).isDisplayed());
		
		//output
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), customerName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText(), customerID);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), editAaddr);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), editCity);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), editState);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), editPin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), editPhone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), editEmail);
	
	}

	private boolean isElementEnalble(By by) {
		if(driver.findElement(by).isEnabled())
			return true;
		return false;
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
