package commonfunctions;

import org.openqa.selenium.By;

import beforeafters.BeforeAfterTest;
import beforeafters.BeforeAndAfterMethodClass;

public class StepExecutor extends BeforeAndAfterMethodClass {

	public static void sendKeys(String locator , String keysToSend)
	{
		driver.findElement(By.xpath(locator)).sendKeys(keysToSend);
	}
	
	public static void clickOnElement(String locator)
	{
		driver.findElement(By.xpath(locator)).click();
	}
	
	public static void getUrl(String url)
	{
		driver.get(url);
	}
}
