package onlineShopping.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import onlineShopping.PageObjects.LoginPage;

public class EcommerceTest {

	public static WebDriver driver;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String Productname = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		LoginPage LP = new LoginPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("chakreamol720@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Google@1774$");
		driver.findElement(By.id("login")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

		WebElement prod = products.stream()
				.filter(product -> product.findElement((By.cssSelector("b"))).getText().equals(Productname)).findFirst()
				.orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

		List<WebElement> listofcartItems = driver.findElements(By.cssSelector(".cartSection h3"));

		Boolean match = listofcartItems.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(Productname));

		Assert.assertTrue(match);

		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		Actions action = new Actions (driver);
		
		// .form-group input.txt.text-validated
		
		action.sendKeys(driver.findElement(By.cssSelector(".form-group input.txt.text-validated")), "INDIA", Keys.ENTER).build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();

		driver.findElement(By.cssSelector(".btnn.action__submit")).click();
		
		String ConfirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		ConfirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER.");
		
		Assert.assertEquals(ConfirmMessage, "THANKYOU FOR THE ORDER.");
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".toast-bottom-right")));
		driver.findElement(By.cssSelector(".btn.btn-primary")).click();
	}
	

}
