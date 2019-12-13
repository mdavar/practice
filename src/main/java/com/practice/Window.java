package com.practice;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class Window {
	WebDriver driver;
	
	public @FindBy(how=How.PARTIAL_LINK_TEXT, using="Window") // create object
	WebElement window;
	
	public @FindBy(how=How.PARTIAL_LINK_TEXT, using="Follow")
	WebElement fol;
	
	
	public Window(WebDriver driver) { //constructor used to initiatize the driver object from Testone
		this.driver= driver;
	}
	
	
	public void win() {
		window.click();
		String parentBrowser = driver.getWindowHandle(); //parent window details
		fol.click();
//		driver.findElement(By.partialLinkText("Follow ")).click();
		
		Set<String> set = driver.getWindowHandles(); //parent and child browsers
		
		for (String child :set) { //values of set will go in loop
			if (!child.equalsIgnoreCase(parentBrowser)) {
				driver.switchTo().window(child);
				driver.close();
			}
			
		}
		
		driver.switchTo().window(parentBrowser);

	}

}

