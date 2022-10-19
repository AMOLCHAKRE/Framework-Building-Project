package onlineShopping.TestComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import onlineShopping.PageObjects.LoginPage;

public class BaseTest {

	public WebDriver driver;
	public LoginPage loginpage;
	//String prop = System.getProperty("user.dir")+"src\\main\\java\\onlineShopping\\Resources\\GlobalData.properties";
	
	public WebDriver intializebrowser () throws IOException {
		
		Properties property = new Properties();
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\onlineShopping\\Resources\\GlobalData.properties");
		property.load(file);
		String browserName = property.getProperty("browser");
		
		
		
		if (browserName.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		
		else if  (browserName.equalsIgnoreCase("firefox"))
		
		{
			driver = new FirefoxDriver();
		}
		
		else if  (browserName.equalsIgnoreCase("Edge"))
		
		{
			driver = new EdgeDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	
	@BeforeTest
		public LoginPage launchingbrowser () throws IOException
		{
			
			driver = intializebrowser();
			loginpage = new LoginPage(driver);
			loginpage.GoTo();
			return loginpage;
			
		}
	
	@AfterTest
	
	public void teardown ()
	{
		driver.close();
	}

	}


