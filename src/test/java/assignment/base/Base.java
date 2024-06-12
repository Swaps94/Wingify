package assignment.base;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import assignment.utility.ReadData;
import io.github.bonigarcia.wdm.WebDriverManager;



public class Base {
	
	
	public static WebDriver driver;
	
	public void initialization() throws IOException
	{
		String browser=ReadData.readPropertyFile("browser");
		if(browser.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", 
					"C:\\Users\\swapn\\Downloads\\chromedriver-win64 (2)\\chromedriver-win64\\chromedriver.exe");
			ChromeOptions options=new ChromeOptions();
			options.setBinary("C:\\Users\\swapn\\Downloads\\chrome-win64 (1)\\chrome-win64\\chrome.exe");
			driver=new ChromeDriver();
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else if(browser.equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		
		else if(browser.equals("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}	
	
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(ReadData.readPropertyFile("url"));	
		driver.getCurrentUrl();
	
	}
}
