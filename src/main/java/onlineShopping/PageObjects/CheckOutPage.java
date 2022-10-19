package onlineShopping.PageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import onlineShopping.AbstractComponent.AbstractComponent;

public class CheckOutPage extends AbstractComponent {
	
	WebDriver driver;
	
	public CheckOutPage(WebDriver driver) {
		super(driver);
		
		this.driver = driver;
		PageFactory.initElements(driver, this );
	}

    @FindBy (css = ".form-group input.txt.text-validated")
    WebElement country;
    
    @FindBy (css = ".ta-item:nth-of-type(2)")
    WebElement SelectCountry;
    
    @FindBy (css = ".btnn.action__submit")
    
    WebElement submit;


public void SelectCountry (String countryName) {
	
	Actions action = new Actions (driver);
	action.sendKeys(country, countryName, Keys.ENTER).build().perform();
	SelectCountry.click();
	
}

public SubmitOrder Confirmationpage () {
	
	submit.click();	
	return new SubmitOrder(driver);
}
}
