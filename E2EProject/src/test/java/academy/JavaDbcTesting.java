package academy;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;


import common.BaseFunctions;

public class JavaDbcTesting extends BaseFunctions{
	private static Logger log = LogManager.getLogger(JavaDbcTesting.class.getName());
	
	@BeforeClass
	public void initilize () throws IOException {
		driver= initializeDriver();
		log.info("driver initialized successfully");
		driver.get(readPropData("url2"));
		log.info("navigated to home page");
	}
	@AfterClass
	private void closeBroser() {
		// TODO Auto-generated method stub
		driver.close();
		driver.quit();
	}
	@Test
	private void testJdbcConnection() throws SQLException {
		// TODO Auto-generated method stub
		String host="localhost";
		String port= "3306";
		String dbName = "qadbt";
		String url1 = "jdbc:mysql://" + host + ":" + port + "/"+ dbName;

		//DriverManager.getConnection(url, UserName, Password);
		Connection con = (Connection) DriverManager.getConnection(url1, "root", "Fantasy1");
		Statement st = (Statement) con.createStatement();
		ResultSet rs = st.executeQuery("select * from Employeenfo where location = 'NorthYork'");
		while(rs.next()) {			
			//System.setProperty("webdriver.chrome.driver", "C:\\Work\\chromedriver.exe");
			//WebDriver driver = new ChromeDriver();
			//driver.get("https://login.salesforce.com");
			driver.findElement(By.xpath(".//*[@id='username']")).sendKeys(rs.getString("name"));
			driver.findElement(By.xpath(".//*[@id='password']")).sendKeys(rs.getString("id"));
		System.out.println(rs.getString("name"));
		System.out.println(rs.getInt("id"));
		}
	}

	
}