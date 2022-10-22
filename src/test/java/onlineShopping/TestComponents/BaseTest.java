package onlineShopping.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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

			public List<HashMap<String,String>> getJasonDataToMap (String filepath) throws IOException
			{
				//Read jason to String
				
				String jasondata = FileUtils.readFileToString(new File(System.getProperty("user.dir") +"\\src\\test\\java\\onlineShopping\\Data\\PurchaseOrder.jason"), StandardCharsets.UTF_8);

				// Convert String to HashMap - Jakson databind - dependency
				
				ObjectMapper mapper = new ObjectMapper();
				List <HashMap <String, String>> data = mapper.readValue(jasondata, new TypeReference<List<HashMap<String, String>>>() {
				});
				return data;
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


