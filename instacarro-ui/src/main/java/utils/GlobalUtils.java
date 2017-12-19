package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class GlobalUtils {
	private static WebDriver driver;
	
	public GlobalUtils(){
		
	}
	
	public static boolean containedInUrl(String url, String words){		
		words= words.toLowerCase();
		words= words.replace("'", "-");
		words= words.replace(" ", "-");	
		return url.contains(words);
	}
	
	private static WebDriver getChromeDriver(){	
		System.setProperty("webdriver.chrome.driver", ".\\src\\test\\resources\\drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		return driver;
	}
	
	public static WebDriver getDriver(){
		if(driver==null){
			driver = getChromeDriver();
		}
		return driver;
	}
	
	public static void quitDriver(){
		driver.quit();
		driver=null;
	}
	
	
	public static void scrollToElement(WebElement element){
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

}
