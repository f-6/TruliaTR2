package utilities;

import java.nio.channels.NetworkChannel;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Browser {
	private static WebDriver driver;
	
	public static WebDriver getDriver() {
		int iWait = Integer.parseInt(Config.getProperty("implicitlyWait"));
	
		if(driver==null) {
			switch(Config.getProperty("browser").toLowerCase()) {
			case "chrome":
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				driver.manage().timeouts().implicitlyWait(iWait, TimeUnit.SECONDS);
			
				return driver;
			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				driver.manage().timeouts().implicitlyWait(iWait, TimeUnit.SECONDS);
				return driver;
			default:
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				driver.manage().timeouts().implicitlyWait(iWait, TimeUnit.SECONDS);
				return driver;
			}
			
			
		}
		
		return driver;
	}
	
	public static void sleep(int seconds) {
		try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void quit() {
		sleep(5);
		driver.quit();
	}
}
