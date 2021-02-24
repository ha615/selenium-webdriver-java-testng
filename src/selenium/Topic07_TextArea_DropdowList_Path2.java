package selenium;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;

public class Topic07_TextArea_DropdowList_Path2 {
	WebDriver driver;
	Random ran = new Random();
	private String fName = "";
	private String lName = "";
	private String email = "";
	
	Select select;
	private String companyName = "";
	private String password = "";
	private String confirmPassword = "";
	
	By rdGender = By.id("gender-male");
	By txtFName = By.id("FirstName");
	By txtLName = By.id("LastName");
	By txtEmail = By.id("Email");
	By drlDay = By.name("DateOfBirthDay");
	By drlMonth = By.name("DateOfBirthMonth");
	By drlYear = By.name("DateOfBirthYear");
	By txtCompany = By.name("Company");
	By ckbNewsletter = By.name("Newsletter");
	By txtPassword = By.name("Password");
	By txtConfirmPassword = By.name("ConfirmPassword");
	By btnRegister = By.name("register-button");
	
	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
		
		fName = "Baggio";
		lName = "Dang";
		email = "bobo_" + ran.nextInt(99999) +"@yahoo.com";
		companyName = "SU BO Entertaiment";
		password = "123456";
		confirmPassword = "123456";
	}
	
	//@Test
	public void TC01_Handle_Default_Dropdown_List_01() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		WebElement drlRole01 = driver.findElement(By.id("job1"));
		Select select = new Select(drlRole01 );
		Assert.assertFalse(select.isMultiple());
		select.selectByVisibleText("Mobile Testing");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Mobile Testing");
		select.selectByValue("manual");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Manual Testing");
		select.selectByIndex(9);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Functional UI Testing");
		int listValueJob1 = select.getOptions().size();
		Assert.assertEquals(listValueJob1 , 10);
		
		WebElement drlRole02 = driver.findElement(By.id("job2"));
		Select select2 = new Select(drlRole02);
		
		Assert.assertTrue(select2.isMultiple());
		ArrayList<String> arrListFirst = new ArrayList<>();
		arrListFirst.add("Automation");
		arrListFirst.add("Mobile");
		arrListFirst.add("Desktop");
		
		for(String strItem : arrListFirst)
		{
			select2.selectByVisibleText(strItem);
		}
		List<WebElement> listValueOptionJob2 = select2.getAllSelectedOptions();
		ArrayList<String> arrListValueOptionJob2 = new ArrayList<>();
		for(WebElement item: listValueOptionJob2) {
			arrListValueOptionJob2.add(item.getText());
		}
		
		Assert.assertEquals(listValueOptionJob2.size(), 3);

		Assert.assertEquals(arrListValueOptionJob2, arrListFirst);
		//select2.deselectAll();
	}
	
	//@Test
	public void TC02_Handle_Default_Dropdown_List_02() {
		driver.get("https://demo.nopcommerce.com");
		driver.findElement(By.xpath("//a[text()='Register']")).click();

		
		IsCheckedRadioOrCheckbox(rdGender );
		driver.findElement(txtFName).sendKeys(fName);
		driver.findElement(txtLName).sendKeys(lName);
		driver.findElement(txtEmail).sendKeys(email);
		
		select = new Select(driver.findElement(drlDay));
		String day = "1";
		select.selectByVisibleText(day);
		Assert.assertEquals(select.getOptions().size(), 32);
		
		select = new Select(driver.findElement(drlMonth));
		String month = "May";
		select.selectByVisibleText(month);
		Assert.assertEquals(select.getOptions().size(), 13);
		
		select = new Select(driver.findElement(drlYear));
		String year = "1990";
		select.selectByVisibleText(year);
		Assert.assertEquals(select.getOptions().size(), 112);
		
		driver.findElement(txtCompany).sendKeys(companyName);
		IsCheckedRadioOrCheckbox(ckbNewsletter);
		driver.findElement(txtPassword).sendKeys(password);
		driver.findElement(txtConfirmPassword).sendKeys(confirmPassword);
		
		driver.findElement(btnRegister).click();
		
		By resultInfo = By.xpath("//div[text()='Your registration completed']");
		Assert.assertTrue(driver.findElement(resultInfo ).isDisplayed());
		
		driver.findElement(By.xpath("//a[text()='My account']")).click();
		
		Assert.assertEquals(driver.findElement(txtFName).getAttribute("value"), fName);
		Assert.assertEquals(driver.findElement(txtLName).getAttribute("value"), lName);
		Assert.assertEquals(driver.findElement(txtEmail).getAttribute("value"), email);
		Assert.assertEquals(driver.findElement(txtCompany).getAttribute("value"), companyName);
		Assert.assertFalse(unCheckRadioOrCheckbox(ckbNewsletter));

		select = new Select(driver.findElement(drlDay));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), day);
		
		select = new Select(driver.findElement(drlMonth));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
		
		select = new Select(driver.findElement(drlYear));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
		
		
	}

	private boolean unCheckRadioOrCheckbox(By ckbNewsletter) {
		WebElement element = driver.findElement(ckbNewsletter);
		if(!element.isSelected())
			return true;
		return false;
	}

	private void IsCheckedRadioOrCheckbox(By by) {
		WebElement element = driver.findElement(by);
		if(!element.isSelected())
			element.click();	
	}

	
}
