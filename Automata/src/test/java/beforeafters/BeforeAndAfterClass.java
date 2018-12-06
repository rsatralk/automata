package beforeafters;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.xml.XmlTest;

import framework.Reporter;

public class BeforeAndAfterClass extends BeforeAfterTest {
	
	
	 static Map<Integer , String> map=new ConcurrentHashMap<>();
	
	@BeforeClass
	public void getClassName()
	{
		String className = this.getClass().getName();
		//System.out.println(context.);
		map.put(1, className);
		
		
	}

	@AfterClass
	public void clearMap()
	{
		map.clear();
	}
	
	public Map<Integer , String> returnMap()
	{
		return map;
	}
}
