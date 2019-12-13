package com.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MultiSelect {
	WebDriver driver;


	public @FindBy(how=How.LINK_TEXT, using= "Select Dropdown List")
	WebElement dropdown;
	public @FindBy(how=How.ID, using= "multi-select")
	WebElement multiselect;
	public @FindBy(how=How.ID, using= "printAll")
	WebElement getallbutton;
	public @FindBy(how=How.CLASS_NAME, using= "getall-selected")
	WebElement displaytext;
	
	public MultiSelect (WebDriver driver) {
		this.driver = driver;
	}
	
	public void drop() {
		dropdown.click();
		
		Select drop = new Select(multiselect);
		drop.deselectAll();
		drop.selectByValue("Florida");
		drop.selectByValue("New York");
		getallbutton.click();
		String string = displaytext.getText();
		
		Boolean actual= string.contains("Florida,New York");
		Assert.assertTrue(actual);
		
		
	
		}
		
	}

