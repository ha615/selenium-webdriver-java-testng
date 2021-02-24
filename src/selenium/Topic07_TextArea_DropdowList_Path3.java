package selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.bouncycastle.crypto.EphemeralKeyPair;
import org.junit.rules.ExpectedException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.ExpectedExceptions;
import org.testng.annotations.Test;

public class Topic07_TextArea_DropdowList_Path3 {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;

	String month = "February";
	String[] months = { "March", "May", "September", "November" };

	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		jsExecutor = (JavascriptExecutor) driver;
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
	}

	public void TC01_JQuery() {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");

		SelectIemByCustomDropDownList("//span[@id='speed-button']", "//ul[@id='speed-menu']//div", "Fast");
		sleepInSecond(4);
		Assert.assertEquals(
				driver.findElement(By.xpath("//span[@id='speed-button']//span[@class='ui-selectmenu-text']")).getText(),
				"Fast");

		SelectIemByCustomDropDownList("//span[@id='number-button']", "//ul[@id='number-menu']//div", "2");
		Assert.assertEquals(
				driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(),
				"2");

		SelectIemByCustomDropDownList("//span[@id='number-button']", "//ul[@id='number-menu']//div", "10");
		sleepInSecond(4);

		SelectIemByCustomDropDownList("//span[@id='number-button']", "//ul[@id='number-menu']//div", "7");
		sleepInSecond(4);

		SelectIemByCustomDropDownList("//span[@id='number-button']", "//ul[@id='number-menu']//div", "19");
		sleepInSecond(4);
	}

	public void TC02_Angular() {
		driver.get(
				"https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");

		SelectIemByCustomDropDownList("//ejs-dropdownlist[@id='games']//span[@role='listbox']",
				"//ul[@id='games_options']//li", "Basketball");
		Assert.assertEquals(getTextAngularByCustomDropdownList(), "Basketball");

		SelectIemByCustomDropDownList("//ejs-dropdownlist[@id='games']//span[@role='listbox']",
				"//ul[@id='games_options']//li", "Football");
		Assert.assertEquals(getTextAngularByCustomDropdownList(), "Football");

		SelectIemByCustomDropDownList("//ejs-dropdownlist[@id='games']//span[@role='listbox']",
				"//ul[@id='games_options']//li", "Snooker");
		Assert.assertEquals(getTextAngularByCustomDropdownList(), "Snooker");

	}

	public void TC03_ReactJS() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		SelectIemByCustomDropDownList("//div[@role='listbox']", "//div[@role='option']/span", "Jenny Hess");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(), "Jenny Hess");

		SelectIemByCustomDropDownList("//div[@role='listbox']", "//div[@role='option']/span", "Stevie Feliciano");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(), "Stevie Feliciano");

		SelectIemByCustomDropDownList("//div[@role='listbox']", "//div[@role='option']/span", "Stevie Feliciano");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(), "Stevie Feliciano");

	}

	public void TC04_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		SelectIemByCustomDropDownList("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a",
				"Second Option");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "Second Option");

		SelectIemByCustomDropDownList("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a",
				"First Option");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "First Option");

		SelectIemByCustomDropDownList("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a",
				"Third Option");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "Third Option");

	}

	public void TC05_Editable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		// SelectIemByCustomDropDownList("//div[@role='combobox']",
		// "//div[@role=\"listbox\"]//span", "Aruba");
		// Assert.assertEquals(driver.findElement(By.xpath("//div[@role=\"alert\"]")).getText(),
		// "Aruba");

		// SelectIemByCustomDropDownList("//div[@role='combobox']",
		// "//div[@role=\"listbox\"]//span", "Aland Islands");
		// Assert.assertEquals(driver.findElement(By.xpath("//div[@role=\"alert\"]")).getText(),
		// "Aland Islands");

		// SelectIemByCustomDropDownList("//div[@role='combobox']",
		// "//div[@role=\"listbox\"]//span", "Bangladesh");
		// Assert.assertEquals(driver.findElement(By.xpath("//div[@role=\"alert\"]")).getText(),
		// "Bangladesh");

		SendItemByCustomDropdownList("//input[@class='search']", "//div[@role='option']/span", "Aruba");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(), "Aruba");

		SendItemByCustomDropdownList("//input[@class='search']", "//div[@role='option']/span", "Aland Islands");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(), "Aland Islands");

		SendItemByCustomDropdownList("//input[@class='search']", "//div[@role='option']/span", "Bangladesh");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(), "Bangladesh");

	}

	public void TC06_Editable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

		SendItemByKeysTab("//input[@class='search']", "Bangladesh");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(), "Bangladesh");

		SendItemByKeysTab("//input[@class='search']", "Aruba");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(), "Aruba");

		SendItemByKeysTab("//input[@class='search']", "Albania");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(), "Albania");

	}

	@Test
	public void TC07_Multiple_Selected_DropdownList() {
		driver.get("http://multiple-select.wenzhixin.net.cn/templates/template.html?v=189&url=basic.html");
		SelectIemByMultipleCustomDropDownList("(//button[@class='ms-choice'])[1]",
				"(//div[@class='ms-drop bottom'])[1]//span", months);
		Assert.assertEquals(areItemsSelected(months), months);
	}

	private boolean areItemsSelected(String[] itemSelectedText) {
		//get list items are selected
		List<WebElement> itemsSelected = driver.findElements(By.xpath("//li[@class='selected']//span"));
		int countItemSelected = itemsSelected.size();
		
		String allItemsSelectedText = driver.findElement(By.xpath("(//button[@class='ms-choice'])[1]//span")).getText();
		System.out.println("Text is selected " + allItemsSelectedText);
		
		if(countItemSelected > 0 && countItemSelected <=3) {
			for(String item : itemSelectedText) {
				if(allItemsSelectedText.contains(item))
					break;
			}
			return true;
		}
		else 
			if(countItemSelected > 3 && countItemSelected < 12) {
				return driver.findElement(By.xpath(""))		
			}
				
		}

	return null;

	}

	private void SelectIemByMultipleCustomDropDownList(String parentXpath, String allItemsXpath,
			String[] expectedItem) {
		driver.findElement(By.xpath(parentXpath)).click();

		List<WebElement> allItems = explicitWait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemsXpath)));

		for (String strMonth : expectedItem)
			for (WebElement item : allItems) {
				if (item.getText().equals(strMonth)) {
					item.click();
					sleepInSecond(2);
					break;
				}
			}
	}

	private void SendItemByKeysTab(String parentXpath, String expectedItem) {
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath(parentXpath)));
		driver.findElement(By.xpath(parentXpath)).sendKeys(expectedItem);
		sleepInSecond(4);
		driver.findElement(By.xpath(parentXpath)).sendKeys(Keys.TAB);
		sleepInSecond(4);

	}

	private void SendItemByCustomDropdownList(String parentXpath, String allItemsXpath, String expectedItem) {
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath(parentXpath)));
		driver.findElement(By.xpath(parentXpath)).sendKeys(expectedItem);
		List<WebElement> allItems = driver.findElements(By.xpath(allItemsXpath));
		for (WebElement item : allItems) {
			if (item.getText().equals(expectedItem)) {
				item.click();
				sleepInSecond(3);
				break;
			}
		}
	}

	private String getTextAngularByCustomDropdownList() {
		// return (String) jsExecutor.executeScript("return
		// $(\"select[name='games']>option[selected]\").text");
		return (String) jsExecutor
				.executeScript("return document.querySelector(\"select[name='games']>option[selected]\").text");
	}

	private void SelectIemByCustomDropDownList(String parentXpath, String allItemsXpath, String expectedItem) {

		// click vào dropdown

		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath(parentXpath)));
		driver.findElement(By.xpath(parentXpath)).click();

		// chờ cho cac item hien thi ra het trước khi chọn
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemsXpath)));

		List<WebElement> allItems = driver.findElements(By.xpath(allItemsXpath));
		for (WebElement item : allItems)
			if (item.getText().equals(expectedItem)) {
				item.click();
				sleepInSecond(3);
				break;
			}
	}

	public void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
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
