package com.practice;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class Testone extends TestBase {
	
	@Test
	public void test1() {
		Window w = PageFactory.initElements(driver, Window.class);
		w.win();
	}

}
