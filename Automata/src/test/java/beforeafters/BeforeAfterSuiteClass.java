package beforeafters;

import java.io.IOException;

import org.testng.IResultMap;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import framework.ReadExcel;
import framework.Reporter;

public class BeforeAfterSuiteClass {

	ReadExcel rd = new ReadExcel();
	@BeforeSuite
	public void startReport(ITestContext context)
	{
		System.out.println(context.getSuite().getName()+" started.....");
		//Reporter.getReporter(context.getSuite().getName());
	}
	
	@AfterSuite
	public void updateMainSheetResult(ITestContext context) throws IOException
	{
		IResultMap map = context.getFailedTests();
		String suiteName = context.getSuite().getName().toString();
		if(map.size()>0)
		{
			rd.setStatusInExcel(suiteName, "", "Fail");
		}
		else
		{
			rd.setStatusInExcel(suiteName, "", "Pass");
		}
		
		Reporter.getReporter(context.getSuite().getName()).flush();
		Reporter.extentNull();
		System.out.println(context.getSuite().getName()+" ended.....");
	}
}
