package com.mycompany.myhealthprodregistration;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MPMTest {
	
	  private WebDriver driver;
	   
	    WebDriverWait wait;
	    
	    Calendar calendar = Calendar.getInstance();
	    java.util.Date now = calendar.getTime();
	    java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());

	    boolean changeShowing = true;
	    boolean messageCenterShowing;
	    String personalUN = "";
	    String personalPW = "";
	    
	    String tcUser="";
	    String tcPass="";
	     
	    String screenshotsPathJenkins = "C:\\Jenkins\\workspace\\MyHealth Prod Registration\\MyHealthProdRegistration\\target\\surefire-reports\\screenshots\\";
	    String screenshotsPathMyMachine = "C:\\Screenshots\\";
	    String chromeBrowserPropertyJenkins = "C:\\Selenium\\chromedriver.exe";
	    String chromeBrowserPropertyMyMachine = "C:\\Selenium-Standalone\\chromedriver.exe";



	    String screenshotsPath = screenshotsPathJenkins;
	    String chromeBrowserProperty = chromeBrowserPropertyJenkins;


	@Before
	public void setUp() throws Exception {
		setupFirefoxBrowser();
        //setBrowserSize();
		  //System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");
		// WebDriver driver = new ChromeDriver();
		//WebDriver driver = new OperaDriver();
		//driver.navigate().to("http://opera.com/");
        driver.get("https://www.myprivacymatters.com/");
		
	}

	@Test
	public void startMPMTest() throws Exception {
		
        
         WebElement username = findElement(By.id("txtMemberId"), "MPM user");
        username.sendKeys(personalUN);
        WebElement password = findElement(By.id("txtZipCode"), "MPM pass");
        password.sendKeys(personalPW);
        WebElement loginBtn = findElement(By.id("LoginButton"), "MPM login");
        loginBtn.click();
        
        
        Thread.sleep(500);
        driver.get("https://www.myprivacymatters.com/TrueCreditPost.aspx?ActionIndicator=VME");
        
        WebElement tcUsername = findElement(By.id("processLogin_loginForm_userName"), "TC User");
        tcUsername.sendKeys(tcUser);
        WebElement tcPassword = findElement(By.id("processLogin_loginForm_password"), "TC Pass");
        tcPassword.sendKeys(tcPass);
        WebElement tcLoginBtn = findElement(By.id("processLogin_processLogin"), "TC Pass");
        tcLoginBtn.click();
        Thread.sleep(5000);
        driver.get("https://benefits.truecredit.com/MSPFlex/flexapp/creditcenter/subsequentOrder.do");
        //processSubsequentReport_processSubsequentReport
        
        WebElement agreeNewReportRadio = findElement(By.name("doubleAcceptForm.iagree"), "TC Pass");
        agreeNewReportRadio.click();
        WebElement agreeNewReportBtn = findElement(By.id("processSubsequentReport_processSubsequentReport"), "TC Pass");
        agreeNewReportBtn.click();
        //WebElement creditMenuOption = findElement(By.cssSelector("css=css=ul li:nth-child(2) ul li a"), "Trying to match ul -> 2nd li -> ul -> li-> a");
        
        //creditMenuOption.click();
		
	}
	
	   public WebElement findElement(final By locator, String desc){
	        try{
	            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	        }catch(Exception e){
	            
	        }
	        try{           
	            WebElement tempName = driver.findElement(locator);           
	        }catch (Exception e){
	     
	            System.out.println("                 ");
	            System.out.println("-------------- The element: \"" + desc + "\" could not be found. ------------");
	            System.out.println("                 ");
	        }
	        WebElement tempName = driver.findElement(locator);
	        return tempName;
	    }
public void setupFirefoxBrowser() throws Exception{
        
        FirefoxProfile profile = new FirefoxProfile();
        profile.setAcceptUntrustedCertificates(true);
        profile.setAssumeUntrustedCertificateIssuer(false); 
        driver = new FirefoxDriver(profile);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 25);
        setBrowserSize();
    }
    
    public void setupChromeBrowser() throws Exception{

        System.setProperty("webdriver.chrome.driver", chromeBrowserProperty);
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type");
        capabilities.setCapability("chrome.binary",chromeBrowserProperty);
        capabilities.setCapability(ChromeOptions.CAPABILITY, options); 
        driver = new ChromeDriver(capabilities);
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 25);
        setBrowserSize();
    }
    
    public void setBrowserSize(){
        driver.manage().window().setSize(new Dimension(1200,1000));
    }

}
