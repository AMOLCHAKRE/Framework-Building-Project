package onlineShopping.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import onlineShopping.AbstractComponent.AbstractComponent;

public class CartPage extends AbstractComponent {
	
	WebDriver driver;
	public CartPage (WebDriver driver) {
		
		super (driver);	
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy (css = ".cartSection h3")
	
	List<WebElement> listofcartItems;
	
	@FindBy (css= ".totalRow button")
	WebElement checkout;
	
	
	
	
	public Boolean VerifyProductDisplay (String Productname)
	{
		Boolean match = listofcartItems.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(Productname));
		return match;
	}
	
	public CheckOutPage GoToCheckOutPage () {
		
		checkout.click();
		return new CheckOutPage(driver);
		
		
	}

}
