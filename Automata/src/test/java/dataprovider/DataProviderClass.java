package dataprovider;

/**
 * author=rsatralk
 */
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;

import beforeafters.BeforeAndAfterClass;
import beforeafters.BeforeAndAfterMethodClass;
import framework.ExcelFunctions;

public class DataProviderClass {

	ExcelFunctions ex=new ExcelFunctions();
	final static String DataSheet="./resources/DataSheet.xlsx";
	BeforeAndAfterMethodClass mname= new BeforeAndAfterMethodClass();
	BeforeAndAfterClass cls = new BeforeAndAfterClass();
	 List<XmlClass> list=new ArrayList<>();
	  List<Method> listm=new ArrayList<>();
	  List<String> listv=new ArrayList<>();
	
	static volatile int counter=0;
	
	@DataProvider(name="dynamicDataProvider")
	public synchronized Object[][] testData(ITestContext context) throws IOException, ClassNotFoundException
	{
		
		
		String className = cls.returnMap().get(1).substring(6);
		String methodName=getMethodName(context,className).get(counter);
		Object[][] arr=ex.setTestDataFromSheet(DataSheet, methodName, className);
		counter++;
		
		for(int i=0;i<arr.length;i++)
		{
			for(int j=0;j<arr[i].length;j++)
			{
				System.out.println(i+" "+j+" "+arr[i][j]);
			}
		}
		return arr;
	}
	
	public synchronized List<String> getMethodName(ITestContext context , String className) throws ClassNotFoundException
	{
		list=context.getCurrentXmlTest().getClasses();
		Method[] m = null;
		Class cl;
		
		 cl=Class.forName("tests."+className);
	     m=cl.getMethods();
	   
		listm=Arrays.asList(m);
		
		for(Method m1:listm)
		{
			if(m1.getDeclaringClass().getSimpleName().equals(className))
			{
				if(!listv.contains(m1.getName()))
				{
					listv.add(m1.getName());
				}
				//System.out.println(m1.getName());
			}
		}
		
		return listv;
		
	}
}
