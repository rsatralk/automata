package beforeafters;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.testng.ITestContext;
import org.testng.ITestMethodFinder;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BeforeAndAfterMethodClass extends BeforeAndAfterClass{

	
	 static Map<Integer , String> map=new ConcurrentHashMap<>();
	  
	
	@BeforeMethod
	public void getTestMethod(ITestResult result)
	{
		System.out.println("in bmethod");
		//System.out.println("m name: "+result.getMethod().getMethodName());
	   map.put(1, result.getMethod().getQualifiedName());
	   
		//context.getCurrentXmlTest().
	}
	
	@AfterMethod
	public void clearMap()
	{
		map.clear();
	}
	
	public Map<Integer , String> returnMap()
	{
		return map;
	}
}
