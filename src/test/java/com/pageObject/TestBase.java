package com.pageObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;
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
			System.setProperty("webdriver.gecko.driver", "\\src\\test\\resources\\executable\\geckodriver.exe");
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
	
	public static void takescreenshot(){
		File src= ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE); //source path
		//String timestamp= new SimpleDateFormat("yyyymmdd_HH:MM").format(Calendar.getInstance().getTime()); // timestamp
		Date d=new Date();
		String  screenshotname= d.toString().replace(":", "_").replace(" ", "_")+".png";
		File screenshot = new File(System.getProperty("user.dir")+"/test-output/screenshot/"+screenshotname); // destination path
		try {
			FileUtils.copyFile(src,screenshot);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
		} // create folder and copying the screenshot taken
		
		String filepath = screenshot.toString();
		Reporter.log("<a target=\"_blank\",  href="+filepath+"><img src="+filepath+" height=200 width=300></img></a>");
		//Reporter will add logs, message or path in the emailable report
		//addsteps tell that to add the message, path etc on the currrent step
		//inside bracket is the format to create html path for the screenshot which is added
}
	
	
	@AfterTest
	public static void tearDown() {
		if (driver!=null) {
			driver.manage().deleteAllCookies();
			//driver.quit(); //close all the browser opened by selenium test
		}
	}

}
