package com.elearning.controllers;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;

public class CoursControllerTest {
	
	@Test
	public void testmethod() {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\simob\\Downloads\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.youtube.com/watch?v=4KdH5mas22Q");
	}
}
