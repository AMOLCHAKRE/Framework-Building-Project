package onlineShopping.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import onlineShopping.AbstractComponent.AbstractComponent;

public class MyOrders extends AbstractComponent {
	
	WebDriver driver;
	
	public MyOrders(WebDriver driver) {
		super(driver);
		this.driver = driver;
		
		PageFactory.initElements(driver, this );
		
	}
	
	@FindBy (css="tr td:nth-child(3)")
	private List<WebElement> ProductsName;

	
	public Boolean VerifyMyOrdersList (String ProductsNames)
	{
		Boolean match = ProductsName.stream().anyMatch(productlist -> productlist.getText().equalsIgnoreCase(ProductsNames));
		
		return match;
		
		
	}

}
