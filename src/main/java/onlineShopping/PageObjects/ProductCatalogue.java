package onlineShopping.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import onlineShopping.AbstractComponent.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
	
	WebDriver driver;
	
	public ProductCatalogue (WebDriver driver) {
		
		super (driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	//List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	
	@FindBy (css= ".mb-3")
	List <WebElement> products;
	
	@FindBy (css = ".ng-animating")
	WebElement spinner;
	
	
	
	By productsBy = (By.cssSelector(".mb-3"));
	By AddTOCart = By.cssSelector(".card-body button:last-of-type");
	By Toastpopup = By.cssSelector("#toast-container");
	
	
	public List<WebElement> getProductList () {
		
		waitForElementToAppear (productsBy);
		return products;
		
	}
	
	public WebElement getProductByName (String Productname) {
		
		WebElement prod = getProductList().stream()
				.filter(product -> product.findElement((By.cssSelector("b"))).getText().equals(Productname)).findFirst()
				.orElse(null);
		return prod;
	}

	public void AddProductToCart (String Productname) throws InterruptedException {
		
		WebElement prod = getProductByName(Productname);
						  prod.findElement(AddTOCart).click();
		waitForElementToAppear(Toastpopup);
		waitForElementToDisAppear(spinner);
		
		
	}

	

}
