package tests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import beforeafters.BeforeAfterTest;
import dataprovider.DataProviderClass;
import pages.GooglePage;

public class Google extends GooglePage {
	
	@Test(dataProviderClass=DataProviderClass.class , dataProvider="dynamicDataProvider" , priority=1)
	public void testOne(String url , String searchKey)
	{
		
		System.out.println(" In google");
		assertTrue(GooglePage.searchAndClickVerify(url,searchKey));
		//System.out.println(System.currentTimeMillis());
		//assertTrue(false);
		
	}
	
	@Test(dataProviderClass=DataProviderClass.class , dataProvider="dynamicDataProvider" , priority=2)
	public void testTwo(String url , String searchKey , String testy)
	{
		
		System.out.println(" In testTwo");
		System.out.println(testy);
		assertTrue(GooglePage.searchAndClickVerify(url,searchKey));
		//System.out.println(System.currentTimeMillis());
		//assertTrue(false);
		
	}

}
