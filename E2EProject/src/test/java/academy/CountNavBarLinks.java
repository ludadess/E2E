package academy;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import common.BaseFunctions;
import pageObjects.LandingPage;


public class CountNavBarLinks extends BaseFunctions {
	private static Logger Log = LogManager.getLogger(CountNavBarLinks.class.getName());
	
	@BeforeClass
	public void initilize () throws IOException {
		driver= initializeDriver();
		Log.info("test");
		Log.info("driver initialized successfully");
		driver.get(readPropData("url"));
		Log.info("navigated to home page");
		
	}
	@Test
	public void countLinks() throws IOException {
		// LandingPage **********************
		LandingPage landPg = new LandingPage(driver);
		int count = landPg.getNavBar().findElements(By.tagName("a")).size();
		Log.info("number of links on navigation bar = "+count);
		//System.out.println("number of links on navigation bar = "+count);
		for(int i=1; i<count; i++) {
			String keysSet = Keys.chord(Keys.CONTROL,Keys.ENTER);
			landPg.getNavBar().findElements(By.tagName("a")).get(i).sendKeys(keysSet);
			}
		Set<String> wds = driver.getWindowHandles();
		Iterator<String> it = wds.iterator();
	
		while (it.hasNext()) {
			driver.switchTo().window(it.next());
			Log.info(driver.getTitle());
			System.out.println(driver.getTitle());	
		}	
	}
	@AfterClass
	public void closeBroser() {
		driver.close();	
		driver.quit();
		
	}

}
