package framework;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.ITestListener;
import org.testng.ITestNGListener;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import org.testng.xml.XmlSuite.ParallelMode;

public class CreateXml {

	ReadExcel readExcel = new ReadExcel();
	TestNG testng =  new TestNG();
	
	public CreateXml(TestNG testng)
	{
		this.testng = testng;
	}
	
	
	public void  createAndRun() throws IOException
	{
	
		List<XmlSuite> mySuites = new ArrayList<XmlSuite>();
		List<Class<? extends ITestNGListener>> listenerClasses = new ArrayList<> ();
		listenerClasses.add(TestListener.class);
		
		for(String suiteName : readExcel.getAllsuites())
		{
			
			XmlSuite xmlSuite = new XmlSuite();
			xmlSuite.setName(suiteName);
		
			
			
			XmlTest myTest = new XmlTest(xmlSuite);
			myTest.setName(suiteName);
			
			List<XmlClass> myClasses = new ArrayList<XmlClass> ();
			
			
			for(String classname : readExcel.getAllClassesFromSuite(suiteName))
			{
				String finalclassname = appender(classname);
				 myClasses.add(new XmlClass(finalclassname));
			}
		    
			
			//set parallel mode
			
			if(readExcel.setParallelCondition(suiteName))
			{
				System.out.println("running parallely");
				xmlSuite.setParallel(ParallelMode.TESTS);
				//int threadCount = myClasses.size();
				xmlSuite.setThreadCount(5);
			}
			
			myTest.setXmlClasses(myClasses);
			
			List<XmlTest> myTests = new ArrayList<XmlTest>();
		    myTests.add(myTest);
		    
		    xmlSuite.setTests(myTests);
		   
		    mySuites.add(xmlSuite);
		   
		    
		}
		
		testng.setXmlSuites(mySuites);
		
		testng.setListenerClasses(listenerClasses);
		
		testng.run();
		
	}
	
	public String appender(String cname)
	{
		return "tests."+cname;
	}
	
}
