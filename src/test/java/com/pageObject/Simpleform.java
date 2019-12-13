package com.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Simpleform {
	
	WebDriver driver;
	
	public @FindBy(how=How.CSS, using= "#basic .list-group-item:nth-child(1)")
	WebElement simpform;
	public @FindBy(how=How.ID, using = "user-message")
	WebElement enter;
	public @FindBy(how=How.CSS, using = "[onclick='showInput();']")
	WebElement showmessage;
	public @FindBy(how=How.ID, using= "display")
	WebElement displaytext;
	
	public Simpleform (WebDriver driver) {
		this.driver = driver;
	}
	
	public void openbasicexample() throws InterruptedException {
		
		simpform.click();
	}
	
	public void namedisplay() {
		
		enter.sendKeys("Mrunali");
		showmessage.click();
		String actual = displaytext.getText();

		Assert.assertEquals(actual, "xts");
	}
	
	}

