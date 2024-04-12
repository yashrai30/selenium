package tests;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Listeners;

import junit.framework.TestListener;


@Listeners(ScreenshotListner.class)
public class ScreenshotListner extends AmazonTests implements ITestListener{
	
	private static WebDriver driver;
	 
	public static void setDriver(WebDriver driver)
	{
	ScreenshotListner.driver = driver;
	}
	@Override
	    public void onStart(ITestContext context) {
	    }
	 
	    @Override
	    public void onFinish(ITestContext context) {
	    }
	 
	    @Override
	    public void onTestStart(ITestResult result) {
	    }
	 
	    @Override
	    public void onTestSuccess(ITestResult result) {
	    }
	 
	    @Override
	    public void onTestFailure(ITestResult result) {
	        try
	        {
	         System.out.println("Failed Test");
	AmazonTests.getScreenshot(result.getName());
	}
	        catch (IOException e)
	        {
	// TODO Auto-generated catch block
	e.printStackTrace();
	}
	    }
	 
	    @Override
	    public void onTestSkipped(ITestResult result) {
	    }
	 
	    @Override
	    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	        // Do nothing
	    }
	
	    
	
	

}
