package framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 
 * @author Rohit Satralkar
 * This class provides the necessary excel based functions
 */
public class ExcelFunctions {

	final static String InputSheet="../resources/ForAutoMata.xlsx";
	FileInputStream fio;
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	XSSFRow row;
	XSSFCell cell;
	Map<String, Integer> firstMap = new ConcurrentHashMap<>();
	Map<Integer, String> secondMap = new HashMap<>();
	
	public XSSFSheet getMainSheet(String sheetName) throws IOException
	{
		fio=new FileInputStream(new File(InputSheet));
		workbook=new XSSFWorkbook(fio);
		sheet=workbook.getSheet(sheetName);
		return sheet;
		
	}
	
	
	public XSSFSheet getAnySheet(String fileName, String sheetName) throws IOException
	{
		String inputsheet="../resources/"+fileName;
		fio=new FileInputStream(new File(inputsheet));
		workbook=new XSSFWorkbook(fio);
		sheet=workbook.getSheet(sheetName);
		return sheet;
		
	}
	
	
	public int getTotalRows(String fileName, String sheetName) throws IOException
	{
		String inputsheet="./resources/"+fileName;
		fio=new FileInputStream(new File(inputsheet));
		workbook=new XSSFWorkbook(fio);
		sheet=workbook.getSheet(sheetName);
		int rowNumber=sheet.getLastRowNum();
		
		return rowNumber;
	}
	
	//return cell value based on row and column number
	public String getCellData(String fileName, String sheetName, int rowNum , int col) throws IOException
	{
		String inputsheet="../resources/"+fileName;
		fio=new FileInputStream(new File(inputsheet));
		workbook=new XSSFWorkbook(fio);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rowNum);
		String cellValue = row.getCell(col).getStringCellValue();
		return cellValue;
	}
	
	//return cell value based on row and column heading
	public synchronized String getCellDataFromHeading(String fileName, String sheetName , int rowNum , String header) throws IOException
	{
		String inputsheet="./resources/"+fileName;
		fio=new FileInputStream(new File(inputsheet));
		workbook=new XSSFWorkbook(fio);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(0);
		for(int i=0;i<row.getLastCellNum();i++)
		{
			if(row.getCell(i).getStringCellValue().equals(header))
			{
				cell=row.getCell(i);
				firstMap.put(header, cell.getColumnIndex());
			}
		}
		
		row=sheet.getRow(rowNum);
		String cellData = row.getCell(firstMap.get(header)).getStringCellValue();
		
		return cellData;
	}
	
	
	public Map<String, Integer> setHashMap(String fileName, String sheetName , String header) throws IOException
	{
		String inputsheet="../resources/"+fileName;
		fio=new FileInputStream(new File(inputsheet));
		workbook=new XSSFWorkbook(fio);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(0);
		
		for(int i=0;i<row.getLastCellNum();i++)
		{
			if(row.getCell(i).getStringCellValue().equals(header))
			{
				cell=row.getCell(i);
				firstMap.put(header, cell.getColumnIndex());
			}
		}
		
		return firstMap;
	}
	
	//function to set excel data
	public synchronized void setExcelData(String fileName, String sheetName, int row1 , String data) throws IOException
	{
		
		String inputsheet="./resources/"+fileName;
		fio=new FileInputStream(new File(inputsheet));
		workbook=new XSSFWorkbook(fio);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(row1);
		
			if(sheetName=="Suites")
			{
				cell=row.createCell(5);
			}
			else
			{
				cell=row.createCell(2);
			}
		
		cell.setCellValue(data);
		
		FileOutputStream fio=new FileOutputStream(new File(inputsheet));
		workbook.write(fio);
		workbook.close();
		fio.close();
	}
	
	
	//set test data
	
	public Object[][] setTestDataFromSheet(String fileName, String methodName , String className) throws IOException
	{
		
        
		String inputsheet=fileName;
		fio=new FileInputStream(new File(inputsheet));
		workbook=new XSSFWorkbook(fio);
		sheet=workbook.getSheet(className);
		XSSFRow row=sheet.getRow(0);
		XSSFCell cell;
		int columnIndex=0;
		int columnLimit=0;
		int rowCount=0;
		int l=0,k=0;
		
		for(int i=0;i<row.getLastCellNum();i++)
		{
			if(row.getCell(i).getStringCellValue().equalsIgnoreCase(methodName))
			{
				columnLimit++;
				
				if(columnLimit==1)
				{
					cell=row.getCell(i);
					columnIndex=cell.getColumnIndex();
					
				}
			}
		}
		
		
		
		
		for(int i=2;i<=sheet.getLastRowNum();i++)
		{
			row=sheet.getRow(i);
			
			for(int j=columnIndex;j<(columnIndex+1);j++)
			{
				if(row.getCell(j)!=null)
				{
					if(row.getCell(j).getStringCellValue().equalsIgnoreCase("Yes"))
					{
						rowCount++;
					}
				}
				else
				{
					continue;
				}
				
			}
		}
		
		
		String array[][]=new String[rowCount][columnLimit-1];
		
		
		
		for(int i=2;i<=sheet.getLastRowNum();i++)
		{
			row=sheet.getRow(i);
			
			try {
				
				if(row.getCell(columnIndex)!=null)
				{
					if(row.getCell(columnIndex).getStringCellValue().equalsIgnoreCase("Yes"))
					{
					for(int j=(columnIndex+1);j<(columnIndex+columnLimit);j++)
					{
						
						if(!row.getCell(j).getStringCellValue().equals("Yes"))
						{
							array[l][k]=row.getCell(j).getStringCellValue();
							k++;
						}
						
							
					
					}
					}
					
					
				}
				
			
				
				
			}
			catch(Exception e)
			{
				continue;
			}
			
			
			l++;
			k=0;
		}
		
		
		
		return array;
	}
	
}
