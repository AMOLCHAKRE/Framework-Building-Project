package onlineShopping.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import onlineShopping.PageObjects.CartPage;
import onlineShopping.PageObjects.CheckOutPage;
import onlineShopping.PageObjects.LoginPage;
import onlineShopping.PageObjects.MyOrders;
import onlineShopping.PageObjects.ProductCatalogue;
import onlineShopping.PageObjects.SubmitOrder;
import onlineShopping.TestComponents.BaseTest;

public class LoginOrderTest extends BaseTest {

	// public static void main(String[] args) {
	String Productname = "ZARA COAT 3";

	@Test (priority = -1)

	public void submitorder() throws IOException, InterruptedException {
		
		String countryName = "India";

		LoginPage loginpage = launchingbrowser();

		ProductCatalogue productcatalogue = loginpage.LoginApplication("chakreamol720@gmail.com", "Google@1774$");


		List<WebElement> products = productcatalogue.getProductList();
		productcatalogue.AddProductToCart(Productname);

		CartPage productdisplay = productcatalogue.GoTOCartPage();
		Boolean match = productdisplay.VerifyProductDisplay(Productname);
		Assert.assertTrue(match);

		CheckOutPage placeordr = productdisplay.GoToCheckOutPage();

		placeordr.SelectCountry(countryName);
		SubmitOrder confirm = placeordr.Confirmationpage();

		String ConfirmMessage = confirm.placeoutfinalorder();
		Assert.assertTrue(ConfirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		// Assert.assertEquals(ConfirmMessage, "THANKYOU FOR THE ORDEr.");
	}
	
	@Test (dependsOnMethods= {"submitorder"})
	//@Test
	public void OrderHistoryTest () throws IOException
	{
	// TO verify product is displaying in order list
	
		//MyOrders order = new MyOrders(driver);
		//Assert.assertEquals("zara coat 3", order.VerifyMyOrders());
		LoginPage loginpage = launchingbrowser();
		
		ProductCatalogue productcatalogue = loginpage.LoginApplication("chakreamol720@gmail.com", "Google@1774$");
		
		
		MyOrders order = productcatalogue.VerifyMyOrders();		
		Assert.assertTrue(order.VerifyMyOrdersList(Productname));
		
	}
		
	}


