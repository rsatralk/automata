package beforeafters;

import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.IResultMap;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;

import framework.ReadExcel;
import framework.Reporter;
import selenium.CreateDriver;

public class BeforeAfterTest extends BeforeAfterSuiteClass{
	
	ReadExcel rd = new ReadExcel();
	 static int count=0;
	private String browser=null;
	public static WebDriver driver=null;
	
	@org.testng.annotations.BeforeTest
	public void setBrowserAndTest() throws IOException
	{
		System.out.println("In Before Test");
		browser=rd.getBrowser(count++);
		//System.out.println(rd.getBrowser(count++));
		driver = CreateDriver.getDriver(browser);
	}
	
	//update the excel after the test
	@AfterTest
	public void updateStatusInExcel(ITestContext context) throws IOException
	{
		
		IResultMap map = context.getFailedTests();
		IResultMap mapPassed = context.getPassedTests();
		
		if(map.size()>0)
		{
			for(ITestResult r : map.getAllResults())
			{
				rd.setStatusInExcel(context.getName(), r.getMethod().getTestClass().getRealClass().getSimpleName(), "Fail");
				
			}
			
		}
		
		
		if(mapPassed.size()>0)
		{
			for(ITestResult r : mapPassed.getAllResults())
			{
				//System.out.println(r.getMethod().getTestClass().getRealClass().getSimpleName());
				rd.setStatusInExcel(context.getName(), r.getMethod().getTestClass().getRealClass().getSimpleName(), "Pass");
				
			}
			
		}
	
		if(driver!=null)
		{
			driver.quit();
		}
		
	}
	
	
	
	
	

}
