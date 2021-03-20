package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class Topic_15_Wait_Path1 {
	WebDriver driver;
	WebDriverWait explicitWait;
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	
	public void TC_01_Display_Visible() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		//wait cho element display/visible in 10s
		//Element display on UI and DOM(HTML)
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		
		//Fail trong 10s
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("create_account_error")));
	}

	
	public void TC_02_UndisplayInvisible_In_DOM() {
		//wait until element undisplay/invisible
		//Undisplay on UI, nguoi dung khong thao tac duoc
		//Case 1: Element in DOM
		// Firt find element: Tim thay ngay va ap dieu kien (invisiblity) luon
		// pass dieu kien luon k can cho het timeout
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("create_account_error")));
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("email")));
	}
	
	
	public void TC_03_UndisplayInvisible_Out_DOM() {
		//wait until element undisplay/invisible
		//Undisplay on UI, nguoi dung khong thao tac duoc
		//Case 2: Element not in DOM
		//First find element: Khong tim thay -> tim di tim lai nhieu lan den khi het timeout cua implicitWait ->10s
		//Moi apply dieu kien invisiblity  -> pass
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("create_account_error1")));

	}
	
	
	public void TC04_Presence() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");

		//Wait element bat buoc phai co trong DOM
		//Case 1: Element co trong DOM + display on GUI
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
		//Case 2: Element co trong DOM + unDisplay on GUI
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.id("create_account_error")));
		
		//explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.id("create_account_error1")));

	}
	
	
	public void TC05_Clickable_Element_Enable() {
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.id("SubmitLogin")));
		
		driver.get("https://login.mailchimp.com/signup/");
		
		driver.findElement(By.id("email")).sendKeys("zinzin@yahoo.com");
		driver.findElement(By.id("new_username")).sendKeys("zin zin");
		driver.findElement(By.id("new_password")).sendKeys("Pass@!123ten");
		
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.id("create-account")));
	}
	
	@Test
	public void TC06_Staleness() {
		driver.get("https://shopee.vn/");
		//wait cho 1 element Staleness
		//Step1: Thao tac voi 1 element -> Error message xuat hien(*) - hien thi 
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='home_popup_banner']")));
		WebElement popup = driver.findElement(By.xpath("//img[@alt='home_popup_banner']"));
		
		//Step2: Thao tac voi .... ->(*) khong hien thi
		driver.findElement(By.xpath("//div[@class='shopee-popup__close-btn']")).click();
		
		//Step3: cho cho element khong hien thi trong DOM luon
		explicitWait.until(ExpectedConditions.stalenessOf(popup));
	}

	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}