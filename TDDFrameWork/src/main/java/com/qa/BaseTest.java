package com.qa;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.qa.utils.TestUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.FindsByAndroidUIAutomator;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;

public class BaseTest 
{
	protected static AppiumDriver driver;
	protected static Properties props;
	protected static HashMap<String,String>strings=new HashMap<String,String>();
	protected static String platform;
	protected static String dateTime;
	InputStream inputStream;
	InputStream stringsis;
	TestUtils utils;
	public BaseTest() {
		PageFactory.initElements(new AppiumFieldDecorator(driver),this);
	}
  @Parameters({"emulator","platformName","deviceName","platformVersion","udid"})
  @BeforeTest
  public void beforeTest(String emulator,String platformName,String deviceName,String udid, String platformVersion) throws Exception {
	  utils= new TestUtils();
	  URL url;
	  platform=platformName;
	  dateTime=utils.getDateTime();
	  
	  try {
		 
		  props=new Properties();
		  String propFileName="config.properties";
		  String xmlFileName="strings/strings.xml";
		  inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		  props.load(inputStream);
		  stringsis=getClass().getClassLoader().getResourceAsStream(xmlFileName);
		  
		  
		  strings=utils.parseStringXML(stringsis);
		  
		    DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
			desiredCapabilities.setCapability("platformName",platformName);
	        desiredCapabilities.setCapability("deviceName", deviceName);
			
			switch(platform) {
			case "Android":
				desiredCapabilities.setCapability("automationName", props.getProperty("androidAutomationName"));
		        //desiredCapabilities.setCapability("UDID",props.getProperty("androidUDID"));
				desiredCapabilities.setCapability("appPackage",props.getProperty("androidAppPackage"));
		        desiredCapabilities.setCapability("appActivity",props.getProperty("androidAppActivity"));
		        if(emulator.equalsIgnoreCase("true")) {
		        	desiredCapabilities.setCapability("platformVersion", platformVersion);
		        	desiredCapabilities.setCapability("avd",deviceName);
		        	
		        }else {
		        	//desiredCapabilities.setCapability("udid",udid);
		        }
		        
		        String androidAppUrl=System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
						+ File.separator + "resources" + File.separator + "app" + File.separator + "Android.SauceLabs.Mobile.Sample.app.2.7.1.apk";
		        System.out.println("appUrl is"+androidAppUrl);
		        desiredCapabilities.setCapability("app",androidAppUrl);
				url = new URL(props.getProperty("appiumURL"));
				driver = new AndroidDriver(url,desiredCapabilities);
				String sessionId = driver.getSessionId().toString();
				break;
			case "IOS":
				desiredCapabilities.setCapability("automationName",props.getProperty("iosAutomationName"));
				desiredCapabilities.setCapability("platformVersion",platformVersion);
		        URL iosAppUrl=getClass().getClassLoader().getResource(props.getProperty("iosAppLocation"));
		        System.out.println("appUrl is"+ iosAppUrl);
		        //desiredCapabilities.setCapability("app",iosAppUrl);
		        desiredCapabilities.setCapability("bundelId",props.getProperty("iOSBundledId"));
		      
				url = new URL(props.getProperty("appiumURL"));
				driver = new IOSDriver(url, desiredCapabilities);
				break;
				
			default:
				throw new Exception("Invalid platform!-"+platformName);
						}
				        
						 
				  }catch(Exception e) {
					  e.printStackTrace();
					  throw e;
				  }finally {
					  if(inputStream !=null) {
						  inputStream.close();
					  }
					  if(stringsis !=null) {
						  stringsis.close();
					  }
				  }
				  
			  }
			 
  public AppiumDriver getDriver()
  {
	  return driver;
  }	 
  public String getDateTime()
  {
	  return dateTime;
  }
  public void clear(MobileElement e)
  {
	  waitForVisibility(e);
	  e.clear();
  }
  public void waitForVisibility(MobileElement e)
  {
	  WebDriverWait wait=new WebDriverWait(driver,TestUtils.WAIT);
	  wait.until(ExpectedConditions.visibilityOf(e));
	  
  }
  public void click(MobileElement e) 
  {
	  waitForVisibility(e);
	  e.click();  
  }
  public void sendKeys(MobileElement e,String txt) 
  {
	  waitForVisibility(e);
	  e.sendKeys(txt);  
  }
  public String getAttribute(MobileElement e,String attribute) 
  {
	  waitForVisibility(e);
	  return e.getAttribute(attribute);  
  }
  public String getText(MobileElement e)
  {
	  switch(platform)
	  
	  {
	      case "Android":
		  return getAttribute(e,"text");
	      case "ios":
	      return getAttribute(e,"label");  
	    	  
	  }
	    return null;
  }
  
  public void closeApp() 
	{
		((InteractsWithApps) driver).closeApp();
	}
	public void launchApp() {
		((InteractsWithApps) driver).launchApp();
	}
	public MobileElement scrollToElement() 
	{	  
		return (MobileElement) ((FindsByAndroidUIAutomator) driver).findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector()" + ".scrollable(true)).scrollIntoView("
						+ "new UiSelector().description(\"test-Price\"));");
  }
  
  public void iOSScrollToElement()	
  
  {
	  RemoteWebElement element=(RemoteWebElement)driver.findElement(By.className("XCUIElementTypeTable"));
	  String elementId=element.getId();
	  HashMap<String,String>scrollObject=new HashMap<String,String>();
	  scrollObject.put("element", elementId);
	  //scrollObject.put("direction","down");
	  //driver.executeScript("predicateString","label=='elementLabel");
	  scrollObject.put("toVisible","ADD TO CART");
	  driver.executeScript("mobile:scroll",scrollObject);
  }
 

  @AfterClass
  public void afterClass() 
  {
	  driver.quit();
  }


}
