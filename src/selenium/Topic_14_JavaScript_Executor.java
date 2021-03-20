package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class Topic_14_JavaScript_Executor {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;
	
	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		jsExecutor = (JavascriptExecutor)driver;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	public void TC01_JavaScript_Executor() {
		navigateToUrlByJS("http://live.demoguru99.com/");
		String domainNameGuru99 = (String)executeForBrowser("return document.domain;");
		System.out.println("Domain Name Guru99 " + domainNameGuru99);
		Assert.assertEquals(domainNameGuru99,"live.demoguru99.com");
		
		String homePageURL = (String)executeForBrowser("return document.URL;");
		System.out.println("Home Page Guru99 " + homePageURL);
		Assert.assertEquals(homePageURL,"http://live.demoguru99.com/");
		
		highlightElement("//a[text()='Mobile']");
		clickToElementByJS("//a[text()='Mobile']");
		highlightElement("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
		clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
		Assert.assertTrue(getInnerText().contains("Samsung Galaxy was added to your shopping cart."));
		
		String customerServiceTitle = (String)executeForBrowser("return document.title;");
		System.out.println("Customer Service Title " + customerServiceTitle);
		Assert.assertEquals(customerServiceTitle,"Shopping Cart");
		
		highlightElement("//input[@id='newsletter']");
		scrollToElement("//input[@id='newsletter']");
		sendkeyToElementByJS("//input[@id='newsletter']", "baggio123@yahoo.com");
		
		highlightElement("//input[@id='newsletter']");
		clickToElementByJS("//button[@title='Subscribe']");
		Assert.assertTrue(getInnerText().contains("Thank you for your subscription."));
		
		navigateToUrlByJS("http://demo.guru99.com/v4/");
		String navigateDomainName = (String) executeForBrowser("return document.domain");
		Assert.assertEquals(navigateDomainName, "demo.guru99.com");
		
		sleepInSecond(2);
	}
	
	
	public void TC02_HTML5_Validation_Message() {
		driver.get("https://automationfc.github.io/html5/index.html");
		driver.findElement(By.xpath("//input[@name='submit-btn']")).click();
		System.out.println(getElementValidationMessage("//input[@id='fname']"));
		Assert.assertEquals(getElementValidationMessage("//input[@id='fname']"), "Please fill out this field.");
		driver.findElement(By.xpath("//input[@id='fname']")).sendKeys("baggio");
		driver.findElement(By.xpath("//input[@name='submit-btn']")).click();
		sleepInSecond(2);

	}
	
	
	public void TC03_Remove_Attribute() {
		driver.get("http://demo.guru99.com/v4");
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr314858");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("YryzetY");
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		
		removeAttributeInDOM("//input[@name='dob']", "type");
		sleepInSecond(3);

		driver.findElement(By.xpath("//input[@name='dob']")).sendKeys("17/03/2021");
		sleepInSecond(3);

	}
	
	@Test
	public void TC04_Hiden_Element() {
		driver.get("http://live.demoguru99.com/");
		clickToElementByJS("//div[@id='header-account']//a[@title='My Account']");
		sleepInSecond(3);
	}
	@AfterTest
	public void afterTest() {
		driver.quit();
	} 
	public Object executeForBrowser( String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText( String textExpected) {
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage(WebDriver driver) {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void highlightElement( String locator) {
		WebElement element = getElement( locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void sleepInSecond(long time) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(time*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void clickToElementByJS( String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement( locator));
	}

	public void scrollToElement( String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement( locator));
	}

	public void sendkeyToElementByJS( String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement( locator));
	}

	public void removeAttributeInDOM( String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement( locator));
	}

	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, 10);
		jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public String getElementValidationMessage( String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public WebElement getElement( String locator) {
		// TODO Auto-generated method stub
		return driver.findElement(By.xpath(locator));
	}

	public boolean isImageLoaded( String locator) {
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getElement( locator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}
}
