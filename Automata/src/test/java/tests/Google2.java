package tests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import beforeafters.BeforeAfterTest;
import dataprovider.DataProviderClass;
import pages.GooglePage;

public class Google2 extends GooglePage {
	
	@Test(dataProviderClass=DataProviderClass.class , dataProvider="dynamicDataProvider" , priority=2)
	public void testOne(String url , String searchKey)
	{
		System.out.println(" In google2");
		//assertTrue(GooglePage.searchAndClickVerify("https://www.google.co.in/","batman"));
		System.out.println(" In testOne");
	
		assertTrue(GooglePage.searchAndClickVerify(url,searchKey));
		//System.out.println(System.currentTimeMillis());
		//assertTrue(false);
	}

}
