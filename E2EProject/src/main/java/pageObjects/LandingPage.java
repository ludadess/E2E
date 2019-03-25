package pageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LandingPage {
	public WebDriver driver;
	
	
	By login = By.xpath("//span[contains(text(),'Login')]");
	By contactMenu = By.cssSelector("a[href*='contac']");
	By title = By.xpath("//h2[contains(text(),'Featured Courses')]");
	By navbar = By.xpath("//ul[@class='nav navbar-nav navbar-right']");
	By popup = By.xpath("//button[contains(text(),'NO THANKS')]");
	
	
	public LandingPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}

	public WebElement getLoging() {
		return driver.findElement(login);
	}
	public WebElement getTitle() {
		return driver.findElement(title);
	}
	public WebElement getContactMenu() {
		return driver.findElement(contactMenu);
	}
	public WebElement getNavBar() {
		return driver.findElement(navbar);
	}
	public int getPopUpSize() {
		return driver.findElements(popup).size();
	}
	public WebElement getPopUp() {
		return driver.findElement(popup);
	}
}
