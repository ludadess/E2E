package stepsDefinition;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.And;
import cucumber.api.junit.Cucumber;
import pageObjects.CourseSearch;
import pageObjects.LandingPage;
import pageObjects.LoginPage;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.runner.RunWith;

import common.BaseFunctions;

@RunWith(Cucumber.class)
public class MyStepsDefinition extends BaseFunctions {

	 @Given("^Initilize browser$")
	    public void initilize_browser() throws Throwable {
		 driver= initializeDriver();
	    }

	 @And("^Navigate to url \"([^\"]*)\"$")
	    public void navigate_to_url_something(String strArg1) throws Throwable {
	    	driver.get(strArg1);
	    }

	  @And("^Click Login link$")
	    public void click_login_link() throws Throwable {
	    	LandingPage landPg = new LandingPage(driver);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			if (landPg.getPopUpSize()>0) {
				landPg.getPopUp().click();
			}
			landPg.getLoging().click();		
	    }
   
    @Then("^Enter (.+) and (.+) and log in$")
    public void enter_and_and_log_in(String username, String password) throws Throwable {
    	LoginPage loginPg = new LoginPage(driver);
		loginPg.getEmailAddress().sendKeys(username);
		loginPg.getPassword().sendKeys(password);
		loginPg.getLoginButton().click();
    }

    @When("^Verify User successfully logged In$")
    public void verify_user_successfully_logged_in() throws Throwable {
    	CourseSearch coursePg = new CourseSearch(driver);
    	Assert.assertTrue(coursePg.getSearchBox().isDisplayed()); 
    }
    @And("^Close Browser$")
    public void close_browser() throws Throwable {
        driver.close();
    }
}
