package onlineShopping.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import onlineShopping.AbstractComponent.AbstractComponent;

public class SubmitOrder extends AbstractComponent {

	WebDriver driver;
	public SubmitOrder(WebDriver driver) {

		super (driver);
		
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
		
	}
	
	
	@FindBy (css = ".hero-primary")
	WebElement Thanksfororder;
	
	/*
	 * @FindBy (css = ".btn.btn-primary") WebElement downloadOrderReceipt;
	 * 
	 * By OrderReceipt = By.cssSelector(".toast-bottom-right");
	 */

	
	public String placeoutfinalorder () {
		
		/*
		 * waitForElementToAppear(OrderReceipt);
		 *  downloadOrderReceipt.click();
		 */
		return Thanksfororder.getText();
	}

}
