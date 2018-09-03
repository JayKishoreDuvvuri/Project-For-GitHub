package Com.HelloFresh;

    import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.ie.InternetExplorerDriver;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.Select;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.testng.annotations.AfterTest;
	import org.testng.annotations.BeforeTest;
	import org.testng.annotations.Parameters;
	import org.testng.annotations.Test;
    import io.codearte.jfairy.Fairy;
    import io.codearte.jfairy.producer.person.Person;
    import static org.testng.Assert.assertEquals;
	import static org.testng.Assert.assertTrue;
	import java.util.concurrent.TimeUnit;
	
	public class JFairySignInTest {

	WebDriver driver;
	WebDriverWait wait; 

	@Parameters({"browser", "url"})
	@BeforeTest
	 public void setUp(String browser, String url ){
	    
		    if(browser.equalsIgnoreCase("Chrome")) 
		       {
		 	       System.setProperty("webdriver.chrome.driver", "C:/chromedriver/chromedriver.exe");
		           driver = new ChromeDriver();
		        }  
		    else if (browser.equalsIgnoreCase("Firefox")) 
		        { 
		    	    System.setProperty("webdriver.gecko.driver","C:/geckodriver/geckodriver.exe");
					driver = new FirefoxDriver();
		        }
		    else if (browser.equalsIgnoreCase("IE")) 
	            { 
	    	         System.setProperty("webdriver.ie.driver","C:/IEDriver/IEDriver.exe");
				     driver = new InternetExplorerDriver();
	            }
	             driver.get(url);
	             driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	             driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	             driver.manage().window().maximize();  
	    }

	@Parameters({"account", "text", "urltext"} )
	@Test
	 public void signInTest(String account, String text, String urltext){
		
		       //Generate random data using  JFAIRY API
	             Fairy fairy = Fairy.create();
	             Person person = fairy.person();
	     
	             String name = person.getFirstName();
	             String surname = person.getLastName();
	             String email = person.getEmail();
	             String Homephone = person.getTelephoneNumber();
	             String Mobilephone = person.getTelephoneNumber();
	             String password = person.getPassword();
	             String company = person.getCompany().getName();
	             
	             String address1 = person.getAddress().getAddressLine1();
	             String address2 = person.getAddress().getStreet();
	             String Address1 = (address1 + address2);
	             
	             String address3 = person.getAddress().getStreetNumber();
	             String address4 = person.getAddress().getAddressLine2();
	             String Address2 = (address3 + "  " + address4);
	             String City = person.getAddress().getCity();
	             String postcode = person.getAddress().getPostalCode();
	     
	      
	             wait = new WebDriverWait(driver, 10);
	             wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login"))).click();
	            //String timestamp = String.valueOf(new Date().getTime());
	            //String email = "hf_challenge_" + timestamp + "@hf" + timestamp.substring(7) + ".com";
	            driver.findElement(By.id("email_create")).sendKeys(email);
	            driver.findElement(By.id("SubmitCreate")).click();
	            wait = new WebDriverWait(driver, 10);
	            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_gender2"))).click();
	            driver.findElement(By.id("customer_firstname")).sendKeys(name);
	            driver.findElement(By.id("customer_lastname")).sendKeys(surname);
	            driver.findElement(By.id("passwd")).sendKeys(password);
	            Select select = new Select(driver.findElement(By.id("days")));
	            select.selectByValue("1");
	            select = new Select(driver.findElement(By.id("months")));
	            select.selectByValue("7");
	            select = new Select(driver.findElement(By.id("years")));
	            select.selectByValue("1980");
	            driver.findElement(By.id("company")).sendKeys(company);
	            driver.findElement(By.id("address1")).sendKeys(Address1);
	            driver.findElement(By.id("address2")).sendKeys(Address2);
	            driver.findElement(By.id("city")).sendKeys(City);
	            select = new Select(driver.findElement(By.id("id_state")));
	            select.selectByVisibleText("Colorado");
	            driver.findElement(By.id("postcode")).sendKeys(postcode);
	            driver.findElement(By.id("other")).sendKeys("This is a sample text entered for test");
	            driver.findElement(By.id("phone")).sendKeys(Homephone);
	            driver.findElement(By.id("phone_mobile")).sendKeys(Mobilephone);
	            driver.findElement(By.id("alias")).sendKeys(Address2);
	            driver.findElement(By.id("submitAccount")).click();
	            wait = new WebDriverWait(driver, 10);
	            WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='page-heading']")));
	            
	           assertEquals(heading.getText(), account);
	           assertEquals(driver.findElement(By.className("account")).getText(), name + " " + surname); 
	           assertTrue(driver.findElement(By.className("info-account")).getText().contains(text));
	           assertTrue(driver.findElement(By.className("logout")).isDisplayed());
	           assertTrue(driver.getCurrentUrl().contains(urltext));
	     }

	@AfterTest
	  public void closeApplication()  
	   {
		 driver.close();
	   }


}
