package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import commonfunctions.StepExecutor;

public class GooglePage extends StepExecutor {


	public static String searchField = "//input[@name='q']";
	public static String searchKey = "//*[@id=\"tsf\"]/div[2]/div/div[3]/center/input[1]";
	
	public static boolean searchAndClickVerify(String url , String value)
	{
		boolean flag = false;
		getUrl(url);
		sendKeys(searchField, value);
		Actions a=new Actions(driver);
		a.sendKeys(Keys.ENTER).build().perform();
		//clickOnElement(searchKey);
		
		if(driver.getTitle().contains(value))
		{
			flag = true;
		}
		
		return flag;
	}
	
	
}
