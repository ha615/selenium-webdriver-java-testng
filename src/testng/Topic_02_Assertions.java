package testng;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_02_Assertions {

	@Test
	public void TC_01() {
		String zinzin = "Hello world";
		
		String empty = null;
		
		Assert.assertEquals(zinzin, "Hello world");
		
		Assert.assertTrue(zinzin.contains("Hello"));
		
		Assert.assertFalse(zinzin.contains("Hello word"));
		
		Assert.assertFalse(zinzin.equals("Helo"));
		
		Assert.assertNotNull(zinzin);
		
		Assert.assertNull(empty);
	}
}
