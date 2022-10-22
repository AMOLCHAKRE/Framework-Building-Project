package onlineShopping.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import onlineShopping.PageObjects.CartPage;
import onlineShopping.PageObjects.LoginPage;
import onlineShopping.PageObjects.ProductCatalogue;
import onlineShopping.TestComponents.BaseTest;

public class ErrorValidationTest extends BaseTest {

	// public static void main(String[] args) {

	@Test (groups= {"ErrorHandling"})

	public void loginpageerrorMessage() throws IOException, InterruptedException {
		

		LoginPage loginpage = launchingbrowser();

		loginpage.LoginApplication("chakreamol720@gmail.com", "Google@1774");
		
		loginpage.getErrorMessage();
		
		Assert.assertEquals("Incorrect email or password.", loginpage.getErrorMessage());
		

	}
	
	@Test
	
	public void ProductErrorValidation () throws InterruptedException
	{
		String Productname = "ZARA COAT 3";

		ProductCatalogue productcatalogue = loginpage.LoginApplication("amol_c015@yahoo.com", "Google@1774$");


		List<WebElement> products = productcatalogue.getProductList();
		productcatalogue.AddProductToCart(Productname);

		CartPage productdisplay = productcatalogue.GoTOCartPage();
		Boolean match = productdisplay.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
	}
		
	}


