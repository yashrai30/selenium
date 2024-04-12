package tests;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import pages.AmazonHomeObjects;
import pages.LoginPage;
import org.apache.logging.log4j.*;

public class AmazonTests  {	
	static WebDriver driver=null;	
	
	ExtentSparkReporter htmlReporter;
	ExtentReports extent;
	ExtentTest test;

	@BeforeTest
	@Parameters ("browser")
	public void setUpTest(String browser) {

		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\yashrai\\OneDrive - Nagarro\\Desktop\\Java\\Assignment3\\drivers\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
			driver= new ChromeDriver();
		}
		else 		if (browser.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", "C:\\Users\\yashrai\\OneDrive - Nagarro\\Desktop\\Java\\Assignment3\\drivers\\IEDriverServer_x64_4.14.0\\IEDriverServer.exe");
			driver= new InternetExplorerDriver();
		}
//		else 		if (browser.equalsIgnoreCase("firefox")) {
//			System.setProperty("webdriver.gecko.driver", "C:\\Users\\yashrai\\OneDrive - Nagarro\\Desktop\\Java\\Assignment3\\drivers\\geckodriver-v0.34.0-win-aarch64\\geckodriver.exe");
//			driver= new FirefoxDriver();
//		}
				
		// Initialize ExtentReports and ExtentSparkReporter
	    htmlReporter = new ExtentSparkReporter("ExtentAmazonReport.html");
	    extent = new ExtentReports();
	    extent.attachReporter(htmlReporter);

	    Logger log = LogManager.getLogger("setUpTest");
	    // Test methods
	    amazonSearchTest();
	    log.info("Searched for product on Amazon page");
	    loginTest();
	    log.info("Logged in with true username values");
	    loginTest2();
	    log.error("Error logging providing false username values");
	}

	@Test
	public  void amazonSearchTest() {
		test = extent.createTest("Searchbar test");
	        test.pass("Step 1 passed");
	        extent.flush();
		driver.get("https://www.amazon.in/");
		test.log(Status.INFO, "Amazon page opened.");
		driver.get("https://www.amazon.in/");
		AmazonHomeObjects seachPageObj=new AmazonHomeObjects (driver);
		seachPageObj.setTextInsearchBox ("boAt Stone 620 Bluetooth Speaker");
		seachPageObj.clickSearchButton();
		test.log(Status.INFO, "Searched for product.");
	}

	@Test
	public  void loginTest() {
		test = extent.createTest("Click login button and enter credentials test");
		LoginPage loginPageObj=new LoginPage (driver);
		loginPageObj.clickSignIn();
		test.log(Status.INFO, "Clicked sigin button.");
		loginPageObj.setTextUsername("8989450481");
		test.log(Status.INFO, "Entered Username.");
		loginPageObj.clickSubmitButton();
		loginPageObj.change.click();
		test.log(Status.INFO,"Clicked login button");
	}
	
	@Test
	public  void loginTest2() {
		test = extent.createTest("Clear username box and enter new credentials test");
		LoginPage loginPageObj2=new LoginPage (driver);
		 Logger log = LogManager.getLogger("setUpTest");
		loginPageObj2.setTextUsername("8989450481abc");
		loginPageObj2.clickSubmitButton();
		test.log(Status.INFO,"Clicked Submit button.");
		try
        {
            //Assert.fail("Deliberate failure to capture screenshot");
			loginPageObj2.error.isDisplayed();
			log.info ("Logged in");
            test.log(Status.INFO, "Logged in ");
        }
        
        catch (AssertionError e)
        {
         String screenshotFileName = "Failed"; // Adjust filename as needed
         log.info ("Login Failed");
            String screenshotPath = "C:/Users/yashrai/OneDrive - Nagarro/Desktop/Java/Assignment3/test-output/Screenshots/" + screenshotFileName + ".png"; // Full path to the screenshot file
            try
            {
                getScreenshot(screenshotPath);
test.fail("Test failed", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
                log.error("Test failed");
            }
            catch (IOException ioException)
            {
                ioException.printStackTrace();
            }
        }
	}

	@AfterMethod
	public void getTestResult(ITestResult result) {
		if(result.getStatus()==ITestResult.FAILURE) {
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + "FAILED", ExtentColor.RED));
		}
		else if(result.getStatus()==ITestResult.SUCCESS) {
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + "SUCCESS", ExtentColor.GREEN));
		}
		else if(result.getStatus()==ITestResult.SKIP) {
			test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + "SKIPPED", ExtentColor.BLACK));
		}	
	}
	
	@AfterMethod
	public static void getScreenshot(String screenshotPath) throws IOException
    {
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile,new File( "/Users/yashrai/OneDrive - Nagarro/Desktop/Java/Assignment3/test-output/Screenshots"));
		
    }
	
	@AfterTest
	public void tearDown() {	
		driver.close();
		driver.quit();
		test.log(Status.INFO,"Exit test");
	}
}




