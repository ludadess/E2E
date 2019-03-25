package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;



import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BaseFunctions {
	
	public static WebDriver driver;
	public static final String USERNAME = "Valdess";
	public static final String ACCESS_KEY = "bcc2229e-853b-4290-a1b4-3392eba941df";
	public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";
	public static String projectpath = System.getProperty("user.dir");
	
	public String readPropData (String keyName) throws IOException {
		Properties p = new Properties();
		String projectpath = System.getProperty("user.dir");
		//Read object repository file
		String fPath = projectpath+"\\src\\main\\resources\\UserData.properties";
		FileInputStream stream = new FileInputStream (fPath);
		//FileInputStream stream = new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\TestNg_New\\UserData.properties"));
		//load all objects
		p.load(stream);
		String keyValue = p.getProperty(keyName);
		return keyValue;
	}

	public void addPropData(String keyName, String keyValue ) throws IOException {
		
		String fPath = projectpath +"\\src\\main\\resources\\SetUserData.properties";
		//1. Convert properties data to HashMap ***********************
		Map mapProp = new HashMap();
		// create file input stream object, to make file as readable by machine
		FileInputStream fis = new FileInputStream(fPath);
		// create properties class object to access all non-static methods
		Properties prop = new Properties();
		// load the required .properties file
		prop.load(fis);
		// close the file as Properties class object have all the details
		fis.close();
		// Properties work like HashTable, so we have to handle like hash table only
		Enumeration enumKeys = prop.keys();
		// iterate till the enumKeys has keys
		while (enumKeys.hasMoreElements()) {
			// move from null to first element(key), by default it will not point to first element
			String key = (String) enumKeys.nextElement();
			// fetch the property for the key
			String value = prop.getProperty(key);
			// store the key and value in map
			mapProp.put(key, value);
		}
		
		mapProp.put(keyName, keyValue);
		System.out.println("Value stored to Map are : "+mapProp);
		
		
		//2. Write Hashmap to property file *************************
		//String fPath1 ="C:\\Work\\test3.txt";
		File file = new File(fPath);
		FileOutputStream outStream = new FileOutputStream (file);
		Properties prop1 = new Properties();
		
		Iterator keys = (Iterator) mapProp.keySet().iterator();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			String value = (String) mapProp.get(key);
			prop1.setProperty(key, value);
		}
		prop1.store(outStream, null);
	}
		
		
		
		
	
	
	public void writePropData(String keyName, String keyValue  ) throws IOException {
		
		String fPath = "C:\\Work\\eclipse-workspace\\E2EProject\\src\\main\\resources\\SetUserData.properties";
		FileOutputStream outStream = new FileOutputStream (fPath);
		Properties p = new Properties();
		p.setProperty(keyName, keyValue);
		p.store(outStream, null);
		
		
	}
	public WebDriver initializeDriver() throws IOException {
		String browserSession = readPropData("browser");
		if (browserSession.equals("chrome")) {
			 System.setProperty("webdriver.chrome.driver", "C:\\Work\\chromedriver.exe");
			 driver = new ChromeDriver();
		}
		else if (browserSession.equals("firefox")){
			System.setProperty("webdriver.gecko.driver", "C:\\Work\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if (browserSession.equals("IE")){
			System.setProperty("webdriver.ie.driver", "C:\\Work\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		
		else if (browserSession.equals("cloud")){
			DesiredCapabilities caps = DesiredCapabilities.chrome();
		    caps.setCapability("platform", "Windows 10");
		    caps.setCapability("version", "latest");
		     driver = new RemoteWebDriver(new URL(URL), caps);
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	public void getScreenshot(String testName) throws IOException {
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("C:\\Work\\Test\\"+testName+"screenshot.jpg"));	
	}
}
