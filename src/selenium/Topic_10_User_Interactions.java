package selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_10_User_Interactions {
	WebDriver driver;
	Actions action;
	WebDriverWait explicitWait;

	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		action = new Actions(driver);
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public void TC01_Hover_To_Element_Tooltip() {
		driver.get("https://jqueryui.com/resources/demos/tooltip/default.html");
		action.moveToElement(driver.findElement(By.xpath("//input[@id='age']"))).perform();
		sleepSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).getText(),
				"Hover the field to see the tooltip.");
	}

	public void TC02_Hover_To_Element() {
		driver.get("https://www.myntra.com/");
		driver.findElement(By.xpath("//div[@class='desktop-navLink']/a[text()='Kids']")).click();
		//action.moveToElement(element).perform();
		//sleepSecond(4);
		//driver.findElement(By.xpath("//a[text()='Home & Bath']")).click();
		sleepSecond(6);
	}
	
	@Test
	public void TC03_Hover_To_Element() {
		driver.get("https://hn.telio.vn/");
		driver.findElement(By.xpath("//div[@class='desktop-navLink']/a[text()='Kids']")).click();
		//action.moveToElement(element).perform();
		//sleepSecond(4);
		//driver.findElement(By.xpath("//a[text()='Home & Bath']")).click();
		sleepSecond(4);
		action.moveToElement(driver.findElement(By.xpath("//div[@class='desktop-navLink']/a[text()='Kids']")))
				.perform();
		sleepSecond(4);
		driver.findElement(By.xpath("//a[text()='Home & Bath']")).click();
		sleepSecond(3);
	}


	public void TC04_Click_And_Hold_Element() {
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		List<WebElement> lstElement = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		System.out.println("All Element =: " + lstElement.size());

		action.clickAndHold(lstElement.get(0)).moveToElement(lstElement.get(10)).release().perform();

		List<WebElement> lstElementSelected = driver
				.findElements(By.xpath("//ol[@id='selectable']/li[@class='ui-state-default ui-selectee ui-selected']"));

		Assert.assertEquals(lstElementSelected.size(), 9);

		sleepSecond(3);
	}

	public void TC05_Click_And_Hold_Element() {
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		List<WebElement> lstElement = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		System.out.println("All Element =: " + lstElement.size());
		action.keyDown(Keys.CONTROL).perform();

		action.click(lstElement.get(0)).click(lstElement.get(4)).click(lstElement.get(7)).click(lstElement.get(9))
				.click(lstElement.get(2)).perform();
		sleepSecond(3);

		action.keyUp(Keys.CONTROL).perform();

		List<WebElement> lstElementSelected = driver
				.findElements(By.xpath("//ol[@id='selectable']/li[@class='ui-state-default ui-selectee ui-selected']"));

		Assert.assertEquals(lstElementSelected.size(), 5);
		sleepSecond(3);

	}

	public void TC06_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		sleepSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='demo']")).getText(), "Hello Automation Guys!");

	}

	@Test
	public void TC07_Right_Click_To_Element() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
		sleepSecond(3);
		action.moveToElement(driver
				.findElement(By.xpath("//li[@class='context-menu-item context-menu-icon context-menu-icon-quit']")))
				.perform();
		sleepSecond(3);
		System.out.println(driver
				.findElement(By.xpath("//li[@class='context-menu-item context-menu-icon context-menu-icon-quit']/span"))
				.getText());

	}

	private void sleepSecond(long i) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(i * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}
