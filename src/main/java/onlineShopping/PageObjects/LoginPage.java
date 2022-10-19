package onlineShopping.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import onlineShopping.AbstractComponent.AbstractComponent;

public class LoginPage extends AbstractComponent {

	WebDriver driver;
	
	public LoginPage (WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
		
		/*
		 * WebElement Emailid = driver.findElement(By.id("userEmail")); WebElement
		 * WebElement getpassword = driver.findElement(By.id("userPassword")); WebElement submit =
		 * WebElement submit = driver.findElement(By.id("login"));
		 */
	
	//PageFactory method implementation 
	
	@FindBy (id= "userEmail")
	WebElement Emailid;
	
	@FindBy (id = "userPassword")
	WebElement getpassword;
	
	@FindBy (id = "login")
	WebElement submit;
	
	@FindBy (css="[class*='flyInOut']")
	WebElement errorMessage;
	
	public ProductCatalogue LoginApplication ( String Email, String Password) {
		
		Emailid.sendKeys(Email);
		getpassword.sendKeys(Password);
		submit.click();	
		ProductCatalogue productcatalogue = new ProductCatalogue(driver);
		return productcatalogue;
	}
	
	public void GoTo () {
		
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrorMessage ()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	
	}

