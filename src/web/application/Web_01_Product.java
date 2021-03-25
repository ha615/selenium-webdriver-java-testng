package web.application;

import org.testng.annotations.Test;

public class Web_01_Product {
	
	 @Test(groups = "web")
	  public void TC_01_New_Product() {
		  System.out.println("Test case 01");
	  }
	  
	  @Test(groups = "web")
	  public void TC_02_View_Product() {
		  System.out.println("Test case 02");
	  }
	  
	  @Test(groups = "web")
	  public void TC_03_Delete_Product() {
		  System.out.println("Test case 03");
	  }
}
