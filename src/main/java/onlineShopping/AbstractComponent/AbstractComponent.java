package onlineShopping.AbstractComponent;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import onlineShopping.PageObjects.CartPage;
import onlineShopping.PageObjects.MyOrders;

public class AbstractComponent {
	
	 WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this );
	}
	
	@FindBy (css = "[routerlink*='cart']")
	WebElement header;
	
	@FindBy (css="[routerlink*='myorders']")
	WebElement orderHeader;

	public void waitForElementToAppear(By FindBy) {
	
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(FindBy));
	
	}
	
	public void waitForWebElementToAppear (WebElement FindBy)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(FindBy));
	}
	
	public void waitForElementToDisAppear (WebElement element) throws InterruptedException {
		
		Thread.sleep(1000); // speedup code for finding invisible elements
		
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		//wait.until(ExpectedConditions.invisibilityOf(element));
		
		
	}
	
	public CartPage GoTOCartPage ()
	{
		header.click();
		
		CartPage productdisplay = new CartPage(driver);
		return productdisplay;
	}
	
	public MyOrders VerifyMyOrders ()
	{
		orderHeader.click();
		waitForWebElementToAppear(orderHeader);
		MyOrders order = new MyOrders(driver);
		orderHeader.click();
		return order;
	}
}

