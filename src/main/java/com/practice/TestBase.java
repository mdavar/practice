package com.practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;


public class TestBase {
	
	public static WebDriver driver=null;
	public static FileInputStream fis; //extract files from external source (config.properties/excel)
	public static Properties prop= new Properties();
	
	@BeforeTest
	public static void setUp() throws InterruptedException, IOException {
		fis= new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/Properties/Config.properties");
		prop.load(fis); //load config property details
		String browser= prop.getProperty("browser"); //to access config property Key(browser) and value(chrome)
		
		if (browser.equalsIgnoreCase("chrome")) {
			String executable= System.getProperty("user.dir")+"\\src\\test\\resources\\executable\\chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", executable);
			driver=new ChromeDriver();
		} else if(browser.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", "\\src\\test\\resources\\executable\\IEDriverServer.exe");
			DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
			ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
			true);
			ieCapabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
			ieCapabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
			ieCapabilities.setCapability("allow-blocked-content", true);
			ieCapabilities.setCapability("allowBlockedContent", true);
			driver = new InternetExplorerDriver(ieCapabilities);
			
		} else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "\\src\\test\\resources\\executable\\geckodriver.exe file");
			DesiredCapabilities cap= DesiredCapabilities.firefox();
			cap.setCapability("marionette", true);
			driver = new FirefoxDriver(cap);
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(prop.getProperty("testurl"));
		driver.findElement(By.xpath("//a[@id='basic_example']/span")).click();
		Thread.sleep(2000);
	

		
	}
	
	
	@AfterTest
	public static void tearDown() {
		if (driver!=null) {
			driver.manage().deleteAllCookies();
			driver.quit(); //close all the browser opened by selenium test
		}
	}

}
