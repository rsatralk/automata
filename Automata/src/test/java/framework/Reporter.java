package framework;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class Reporter {

	private static ExtentReports extent;
	private static ExtentTest test;
	String suiteName;
	public synchronized static ExtentReports getReporter(String suiteName)
	{
		
		if(extent==null)
		{
			System.out.println("new instance");
			extent = new ExtentReports("./ExtentReports/"+suiteName+".html");
			
		}
		
		return extent;
	}
	
	public synchronized static ExtentTest startTest(String testName , String description ,String suiteName)
	{
		
	   test=getReporter(suiteName).startTest(testName, description);
		
		
		return test;
	}
	
	
	public synchronized static void extentNull()
	{
		extent = null;
	}
}
