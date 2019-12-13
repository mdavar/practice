package com.testcase;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.pageObject.Simpleform;
import com.pageObject.TestBase;

public class tc_1 extends TestBase {

	@Test
	public void testcase1() throws InterruptedException, IOException {
	
		Simpleform first = PageFactory.initElements(driver, Simpleform.class);
		
		try
		{
		first.openbasicexample();
		first.namedisplay();
		}
		catch(Throwable t) {
			takescreenshot();
			Reporter.log(t.getMessage());
	}
}
}