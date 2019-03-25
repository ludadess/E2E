package academy;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import common.BaseFunctions;
import junit.framework.Assert;
import pageObjects.LandingPage;
import pageObjects.LoginPage;

public class SanityTest extends BaseFunctions{
	
	//private static Logger Log = LogManager.getLogger(base.class.getName());
	private static Logger Log = LogManager.getLogger(SanityTest.class.getName());
	@BeforeClass
	public void initilize () throws IOException {
		driver= initializeDriver();
		Log.info("driver initialized successfully");
		
		driver.get(readPropData("url"));
		Log.info("navigated to home page");
	}
		
	@Test
	public void navigateHomePage() throws IOException {
		
		// LandingPage **********************
		LandingPage landPg = new LandingPage(driver);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//Assert.assertEquals(expected, actual);
		Assert.assertEquals("FEATURED COURSES", landPg.getTitle().getText());
		Log.info("successfully validated title");
		//Assert.assertTrue(condition);
		Assert.assertTrue(landPg.getContactMenu().isDisplayed());
		Log.info("successfully validated Contact menu title");
		landPg.getLoging().click();
		// LoginPage *****************************
		LoginPage loginPg = new LoginPage(driver);
		loginPg.getEmailAddress().sendKeys(readPropData("emailaddress"));
		loginPg.getPassword().sendKeys(readPropData("password"));
		Log.info("successfully entered user credentials data ");
		loginPg.getLoginButton().click();
	}		

	@Test
	public void storeData() throws IOException {
		String projectpath = System.getProperty("user.dir");
		
		String fPath = projectpath+"\\src\\main\\resources\\SetUserData.properties";
		FileOutputStream outStream = new FileOutputStream (fPath);
		Properties p = new Properties();
		p.setProperty("happy", "moon");
		/*p.setProperty("Address", "111 Steels Ave2");
		p.setProperty("City", "Toronto2");
		p.setProperty("Province", "ON2");
		p.setProperty("FirstName", "Boris");*/
		p.store(outStream, null);
		System.out.println("all data are stored");
		
		/*addPropData("two", "test");
		addPropData("happy", "face");*/
		
		System.out.println("new data added");
		
	}
	@AfterClass
	public void closeBroser() {
		driver.close();	
		driver.quit();
		driver = null;
	}
}
