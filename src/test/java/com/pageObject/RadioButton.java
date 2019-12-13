package com.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RadioButton {

	WebDriver driver;


	public @FindBy(how=How.LINK_TEXT, using= "Radio Buttons Demo")
	WebElement radiobutton;
	public @FindBy(how=How.CSS, using= "[value='Female'][name='gender']")
	WebElement scndradio;
	public @FindBy(how=How.CSS, using= "[value='5 - 15'][name='ageGroup']")
	WebElement thrdradio;
	public @FindBy(how=How.CSS, using= "[onclick='getValues();']")
	WebElement gvalue;
	public @FindBy(how=How.XPATH, using= "//div[2]/div[2]/p[2]")
	WebElement text;
	
	public RadioButton (WebDriver driver) {
		this.driver = driver;
	}
	
	public void radioButton() throws InterruptedException {
		radiobutton.click();
		Thread.sleep(2000);

	}

	public void groupButton() {
		Boolean a = scndradio.isSelected();
		System.out.println(a);
		if (a == false) {
			scndradio.click();
		}

		Boolean b = thrdradio.isEnabled();
		System.out.println(b);

		if (b == true) {
			thrdradio.click();
		}
		gvalue.click();
		String actual = text.getText();
		// substring - Female, and substring 5 - 15
		String expected = "Sex : Female"+"\r\n"+"Age group: 5 - 15";

		Assert.assertEquals(actual, expected);

	}
}