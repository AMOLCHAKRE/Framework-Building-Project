package onlineShopping.Tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
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
	String countryName = "India";

	@Test (dataProvider = "getData", groups= {"Purchase"})

	public void submitorder(HashMap<String, String> input ) throws IOException, InterruptedException {

		LoginPage loginpage = launchingbrowser();

		ProductCatalogue productcatalogue = loginpage.LoginApplication(input.get("email"), input.get("password"));


		List<WebElement> products = productcatalogue.getProductList();
		productcatalogue.AddProductToCart(input.get("product"));

		CartPage productdisplay = productcatalogue.GoTOCartPage();
		Boolean match = productdisplay.VerifyProductDisplay(input.get("product"));
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
	
	public String TakeScreenShot (String Testcasename) throws IOException
	{
		TakesScreenshot screenshot = (TakesScreenshot)driver;
		File source = screenshot.getScreenshotAs(OutputType.FILE);
		File file = new File (System.getProperty("user.dir") +"//Reports//" + Testcasename +".png");
		FileUtils.copyFile(source, file);
		return (System.getProperty("user.dir") + "//Reports//" + Testcasename +".png");
	}
	
	@DataProvider
	
	public Object[][] getData() throws IOException
	{
		List<HashMap<String, String>> data = getJasonDataToMap(System.getProperty("user.dir") +"\\src\\test\\java\\onlineShopping\\Data\\PurchaseOrder.jason");
		return new Object [] [] { {data.get(0)},{data.get(1)} };
	}
	
	// HashMap method way
	/*
	 * HashMap <String,String> map = new HashMap <String,String> ();
	 * map.put("email", "chakreamol720@gmail.com"); map.put("password",
	 * "Google@1774$"); map.put("product", "ZARA COAT 3");
	 * 
	 * HashMap <String,String> map1 = new HashMap <String,String> ();
	 * map1.put("email", "amol_c015@yahoo.com"); map1.put("password",
	 * "Google@1774$"); map1.put("product", "ZARA COAT 3");
	 */
	   //Normal data provider method
	/*
	 * @DataProvider public Object[][] getData() { return new Object [] [] {
	 * {"chakreamol720@gmail.com","Google@1774$","ZARA COAT 3"},{
	 * "amol_c015@yahoo.com","Google@1774$","ZARA COAT 3"} }; }
	 */
}

