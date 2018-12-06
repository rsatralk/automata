package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CreateDriver {
	
	public static WebDriver driver=null;
	static ThreadLocal<WebDriver> threadLocal = new ThreadLocal<>();
	
	public synchronized static WebDriver getDriver(String browser)
	{
		switch (browser) {
		case "Chrome":
			System.setProperty("webdriver.chrome.driver", "./resources/chromedriver.exe");
			ChromeOptions options =new ChromeOptions();
			options.addArguments("--start-maximized");
			driver=new ChromeDriver(options);
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			threadLocal.set(driver);
			
			break;

		default:
			break;
		}
		
		return threadLocal.get();
	}

}
