package testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Topic_03_Priority_Disable {
	/*
	 * @Test(priority = 1) public void TC_Create_User() {
	 * System.out.println("Test case 01"); }
	 * 
	 * @Test(priority = 2) public void TC_View_User() {
	 * System.out.println("Test case 02"); }
	 * 
	 * @Test(priority = 4) public void TC_Update_User() {
	 * System.out.println("Test case 03"); }
	 * 
	 * @Test(priority = 3) public void TC_Move_User() {
	 * System.out.println("Test case 04"); }
	 * 
	 * @Test(priority = 5) public void TC_Delete_User() {
	 * System.out.println("Test case 05"); }
	 */
	//sap xep theo thu tu Alphabet: 0-9, A-Z
	//Ten TC: Ten chuc nang - So thu tu - Ten testcase
	@Test
	  public void Login_01_Empty_Mail_And_Password() {
		  System.out.println("Test case 01");
	  }
	  
	  @Test(enabled = false)
	  public void Login_02_Empty_Mail_And_Invalid_Password() {
		  System.out.println("Test case 02");
	  }
	  
	  @Test(description = "Link with jira: http://jira.com/5356")
	  public void Login_04_Invalid_Email_And_Valid_Password() {
		  System.out.println("Test case 03");
	  }
	  
	  @Test(enabled = true)
	  public void Login_03_Valid_Email_And_Valid_Password() {
		  System.out.println("Test case 04");
	  }
	  
	  @Test
	  public void Login_05_Invalid_Email_And_Invalid_Password() {
		  System.out.println("Test case 05");
	  }
}
