package framework;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.ITestContext;

public class ReadExcel {
	
	ExcelFunctions excelFunctions = new ExcelFunctions();
	final static String InputSheet="ForAutoMata.xlsx";
	int rowNum;
	
	
	public List<String> getAllsuites() throws IOException
	{
		String suites = null;
		List<String> suitelist = new ArrayList<>();
		int totalSuites = excelFunctions.getTotalRows(InputSheet, "Suites");
		for(int i=1;i<=totalSuites;i++)
		{
			String condition = excelFunctions.getCellDataFromHeading(InputSheet, "Suites", i, "Execute");
			
			if(condition.equalsIgnoreCase("yes"))
			{
				
				suites = excelFunctions.getCellDataFromHeading(InputSheet, "Suites", i, "Suites");
				suitelist.add(suites);
			}
		}
		
		return suitelist;
	}
	
	//get all classes from a suite marked 'Yes' in the InputSheet
	public List<String> getAllClassesFromSuite(String suiteName) throws IOException
	{
		String classes = null;
		List<String> classlist = new ArrayList<>();
		int totalSuites = excelFunctions.getTotalRows(InputSheet, suiteName);
		for(int i=1;i<=totalSuites;i++)
		{
			String condition = excelFunctions.getCellDataFromHeading(InputSheet, suiteName, i, "Execute");
			
			if(condition.equalsIgnoreCase("yes"))
			{
				classes = excelFunctions.getCellDataFromHeading(InputSheet, suiteName, i, "Classes");
				classlist.add(classes);
			}
		}
		
		return classlist;
	}
	
	
	
	//get the suite count
	
	
	
	//get the browser
	public String getBrowser(int iteration) throws IOException
	{
		
		List<Integer> suitelist = new ArrayList<>();
		int totalSuites = excelFunctions.getTotalRows(InputSheet, "Suites");
		for(int i=1;i<=totalSuites;i++)
		{
			String condition = excelFunctions.getCellDataFromHeading(InputSheet, "Suites", i, "Execute");
			
			if(condition.equalsIgnoreCase("yes"))
			{
				suitelist.add(i);
			}
		}
		
		return excelFunctions.getCellDataFromHeading(InputSheet, "Suites", suitelist.get(iteration), "Browser");
	}
	
   // set parallel condition
	public boolean setParallelCondition(String suiteName) throws IOException
	{
		boolean flag = false;
		int totalSuites = excelFunctions.getTotalRows(InputSheet, "Suites");
		for(int i=1;i<=totalSuites;i++)
		{
			String suiteN = excelFunctions.getCellDataFromHeading(InputSheet, "Suites", i, "Suites");
			
			if(suiteN.equalsIgnoreCase(suiteName))
			{
				if(excelFunctions.getCellDataFromHeading(InputSheet, "Suites", i, "Parallel").equalsIgnoreCase("Yes"))
				{
					//System.out.println(excelFunctions.getCellDataFromHeading(InputSheet, "Suites", i, "Parallel"));
					flag=true;
				}
				
				break;
			}
			
		}
		return flag;
	}
	
	//set result in excel
	public synchronized void setStatusInExcel(String suiteName , String className , String status) throws IOException 
	{
		//System.out.println("classname: "+className +" suiteName: "+suiteName);
		
		if(className!="")
		{
			int totalSuites = excelFunctions.getTotalRows(InputSheet, suiteName);
			for(int i=1;i<=totalSuites;i++)
			{
				String classN = excelFunctions.getCellDataFromHeading(InputSheet, suiteName, i, "Classes");
				
				if(classN.equalsIgnoreCase(className))
				{
					
					excelFunctions.setExcelData(InputSheet, suiteName, i, status);
					
				}
			
		   }
		}
		
		else
		{
			int totalSuites = excelFunctions.getTotalRows(InputSheet, "Suites");
			for(int i=1;i<=totalSuites;i++)
			{
				String suiteName1 = excelFunctions.getCellDataFromHeading(InputSheet, "Suites", i, "Suites");
				
				if(suiteName1.equalsIgnoreCase(suiteName))
				{
					
					excelFunctions.setExcelData(InputSheet, "Suites", i, status);
					
				}
			
		   }
			
		}
		
		

    }
	
	
	
}
