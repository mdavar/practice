package com.testcase;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.pageObject.MultiSelect;
import com.pageObject.Simpleform;
import com.pageObject.TestBase;

public class tc_2 extends TestBase {

	@Test
	public void testcase2() throws InterruptedException {
	
		MultiSelect select = PageFactory.initElements(driver, MultiSelect.class);
		select.drop();
	}
}
