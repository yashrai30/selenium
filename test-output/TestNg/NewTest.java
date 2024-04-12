package TestNg;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.AmazonHomeObjects;
import pages.LoginPage;
import pages.initializeDriver;

public class NewTest {
	@Test
	public void f() {
		static WebDriver driver=null;	

		@BeforeTest
		public void setUpTest() {
			initializeDriver driverObj = new initializeDriver();
			driver=driverObj.initializeDriver();
			amazonSearchTest();
			loginTest();  
		}

		@Test
		public  void amazonSearchTest() {		
			driver.get("https://www.amazon.in/");
			Reporter.log("Amazon page opened.");
			AmazonHomeObjects seachPageObj=new AmazonHomeObjects (driver);
			seachPageObj.setTextInsearchBox ("boAt Stone 620 Bluetooth Speaker");
			seachPageObj.clickSearchButton();
			Reporter.log("Searched for product.");
		}

		@Test
		public  void loginTest() {
			LoginPage loginPageObj=new LoginPage (driver);
			loginPageObj.clickSignIn();
			Reporter.log("Clicked sigin button.");
			loginPageObj.setTextUsername("8989450481");
			Reporter.log("Entered Username.");
			loginPageObj.clickSubmitButton();
			Reporter.log("Clicked Submit button.");
			Assert.assertTrue(loginPageObj.password.isDisplayed());

		}
		//@Test
		//		public  void loginTest() {
		//		    LoginPage loginPageObj=new LoginPage (driver);
		//		    loginPageObj.clickSignIn();
		//			loginPageObj.setTextUsername("8989450481");
		//			loginPageObj.clickSubmitButton();
		//	
		//			}
		@AfterTest
		public void tearDown2() {
			//driver.close();
			driver.quit();
			Reporter.log("Quit Browser");
			System.out.println("Login test incomplete");
			//			System.out.println("Login test2 complete");
		}
	}
}
