package selenium;

import static org.testng.Assert.assertEquals;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Upload_File {
	WebDriver driver;
	WebDriverWait explicitWait;
	String userPath = System.getProperty("user.dir");

	String fileNameDaNang = "danang.jpg";
	String fileNameHaNoi = "hanoi.jpg";
	String fileNameSaiGon = "saigon.jpg";

	String filePathDaNang = userPath + "\\uploadFiles\\" + fileNameDaNang;
	String filePathHaNoi = userPath + "\\uploadFiles\\" + fileNameHaNoi;
	String filePathSaiGon = userPath + "\\uploadFiles\\" + fileNameSaiGon;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", userPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 6);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	
	public void TC_01_Send_Key_One_File_Per_Time() {
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		driver.findElement(By.xpath("//input[@name='files[]']")).sendKeys(filePathDaNang);
		driver.findElement(By.xpath("//input[@name='files[]']")).sendKeys(filePathHaNoi);
		driver.findElement(By.xpath("//input[@name='files[]']")).sendKeys(filePathSaiGon);

		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + fileNameDaNang + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + fileNameHaNoi + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + fileNameSaiGon + "']")).isDisplayed());

		List<WebElement> startNodes = driver
				.findElements(By.xpath("//tbody[@class='files']//button[@class='btn btn-primary start']"));
		for (WebElement startNode : startNodes) {
			startNode.click();
			sleepSecond(1);
		}

		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + fileNameDaNang + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + fileNameHaNoi + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + fileNameSaiGon + "']")).isDisplayed());
	}

	
	public void TC_02_Send_Key_Multiple_File_Per_Time() {
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");

		driver.findElement(By.xpath("//input[@name='files[]']"))
				.sendKeys(filePathDaNang + "\n" + filePathHaNoi + "\n" + filePathSaiGon);

		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + fileNameDaNang + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + fileNameHaNoi + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + fileNameSaiGon + "']")).isDisplayed());

		List<WebElement> startNodes = driver
				.findElements(By.xpath("//tbody[@class='files']//button[@class='btn btn-primary start']"));
		for (WebElement startNode : startNodes) {
			startNode.click();
			sleepSecond(1);
		}

		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + fileNameDaNang + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + fileNameHaNoi + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + fileNameSaiGon + "']")).isDisplayed());
	}
	
	@Test
	public void TC03_Upload_File_With_Robot() {
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		
		driver.findElement(By.xpath("//span[@class='btn btn-success fileinput-button']")).click();
		uploadFile(filePathDaNang);
		
//		driver.findElement(By.xpath("//span[@class='btn btn-success fileinput-button']")).click();
//		uploadFile(filePathHaNoi);
//		
//		driver.findElement(By.xpath("//span[@class='btn btn-success fileinput-button']")).click();
//		uploadFile(filePathSaiGon);

	}
	private static void setClipboardData(String pathFile) {
		// StringSelection is a class that can be used for copy and paste operations.
		StringSelection stringSelection = new StringSelection(pathFile);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	}

	public static void uploadFile(String fileLocation) {
		try {
			// Setting clip board with file location
			setClipboardData(fileLocation);
			// native key strokes for CTRL, V and ENTER keys
			Robot robot = new Robot();

			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			Thread.sleep(3000);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}
	private void sleepSecond(long i) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(1000 * i);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void TC_02_() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}