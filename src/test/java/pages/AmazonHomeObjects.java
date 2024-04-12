package pages;

import java.time.Duration;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AmazonHomeObjects {
	
		WebDriver driver = null;		
		//locators for search box and button
		@FindBy  (id="twotabsearchtextbox") public WebElement textbox_search;
		@FindBy  (id="nav-search-submit-button") public  WebElement search_button;
				
		//constructor to get the driver from other class
		public AmazonHomeObjects (WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this); //to create web elements
		}
		//ACTIONS
		public void setTextInsearchBox (String product) {
		textbox_search.sendKeys(product);
		}
		public void clickSearchButton() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds (3)); 
		 search_button.sendKeys(Keys.RETURN);
		}
		
	
}

