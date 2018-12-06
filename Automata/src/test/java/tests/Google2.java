package tests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import beforeafters.BeforeAfterTest;
import pages.GooglePage;

public class Google2 extends GooglePage {
	
	@Test
	public void testOne()
	{
		System.out.println(" In google2");
		assertTrue(GooglePage.searchAndClickVerify("https://www.google.co.in/","batman"));

		//System.out.println(System.currentTimeMillis());
		//assertTrue(false);
	}

}
