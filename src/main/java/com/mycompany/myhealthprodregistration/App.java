package com.mycompany.myhealthprodregistration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/**
 * Hello world!
 *
 */


public class App 
{
	
	private static WebDriver driver;
	
    public static void main( String[] args )
    {
    	driver = new HtmlUnitDriver();

		//Navigate to desired web page
		driver.get("http://google.com");
		
		letTheTestsBegin();
    }

	private static void letTheTestsBegin() {
		
		String actualTitle = driver.getTitle();
		System.out.println(actualTitle);
	}
}
