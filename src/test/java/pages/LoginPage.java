package pages;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	
	WebDriver driver = null;
	//locators for search box and button
	@FindBy  (id="nav-link-accountList-nav-line-1") public WebElement login_button;
	@FindBy  (id="ap_email") public WebElement username;
	@FindBy  (id="continue") public WebElement submit;
	@FindBy (id = "ap_password") public WebElement password;
	@FindBy (id = "ap_change_login_claim") public WebElement change;
	@FindBy (id = "auth-error-message-box") public WebElement error;

	//constructor to get the driver from other class
	public LoginPage (WebDriver driver) {
	this.driver=driver;
	PageFactory.initElements(driver,this); //to create web elements
	}
	
	//ACTIONS
	public void clickSignIn() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds (3)); 
		login_button.click();	
	}
	public void setTextUsername (String name) {
	username.sendKeys(name);
	}
	public void clickSubmitButton() {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds (3)); 
	submit.submit();
	submit.isDisplayed();
	}
	
	public void passwordBar(String pass) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds (3)); 
	password.sendKeys(pass);
	}
	
	public void changeButton() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds (3)); 
		change.submit();
	}
	
	public void errorBox() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds (3)); 
		//error.isDisplayed();
	}
	
	
	}

