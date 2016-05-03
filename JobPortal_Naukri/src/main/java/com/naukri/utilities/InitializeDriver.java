package com.naukri.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class InitializeDriver {
	
public static WebDriver driver;
	
	public static WebDriver launchBrowser(String browser){
		if (browser!=null){
			if("firefox".equalsIgnoreCase(browser)){
				driver = new FirefoxDriver();
			}
			else if("ie".equalsIgnoreCase(browser)){
				System.setProperty("webdriver.ie.driver", ".\\lib\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			}
			else if("CHROME".equalsIgnoreCase(browser)){
				System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
				driver = new ChromeDriver();
			}
			else{
				System.out.println("No broswer specified. Please enter the browser");
				//driver = new FirefoxDriver();
			}
		}
			return driver;
		}
	
}
