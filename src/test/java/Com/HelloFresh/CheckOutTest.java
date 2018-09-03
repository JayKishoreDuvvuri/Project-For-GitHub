package Com.HelloFresh;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CheckOutTest {
	
	WebDriver driver;
	WebDriverWait wait; 
	
	@Parameters({"browser", "url"})
	@Test(priority=0)
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

	
	 @Parameters({"existingUserEmail", "existingUserPassword", "orderheading", "ordertext", "orderurltext"})
	 @Test(priority=1)
	    public void CheckoutTest(String existingUserEmail, String existingUserPassword, String orderheading, String ordertext, String orderurltext)
	    {
	    	wait = new WebDriverWait(driver, 10);
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login"))).click();
	        driver.findElement(By.id("email")).sendKeys(existingUserEmail);
	        driver.findElement(By.id("passwd")).sendKeys(existingUserPassword);
	        driver.findElement(By.id("SubmitLogin")).click();
	        wait = new WebDriverWait(driver, 10);
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Women"))).click();
	        driver.findElement(By.xpath("//a[@title='Faded Short Sleeve T-shirts']/ancestor::li")).click();
	        driver.findElement(By.xpath("//a[@title='Faded Short Sleeve T-shirts']/ancestor::li")).click();
	        wait = new WebDriverWait(driver, 10);
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("Submit"))).click();
	        wait = new WebDriverWait(driver, 10);
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='layer_cart']//a[@class and @title='Proceed to checkout']"))).click();
	        wait = new WebDriverWait(driver, 10);
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class,'cart_navigation')]/a[@title='Proceed to checkout']"))).click();
	        wait = new WebDriverWait(driver, 10);
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("processAddress"))).click();
	        wait = new WebDriverWait(driver, 10);
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uniform-cgv"))).click();
	        driver.findElement(By.name("processCarrier")).click();
	        wait = new WebDriverWait(driver, 10);
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("bankwire"))).click();
	        wait = new WebDriverWait(driver, 10);
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='cart_navigation']/button"))).click();
	        wait = new WebDriverWait(driver, 10);
	        WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));

	        assertEquals(orderheading, heading.getText());
	        assertTrue(driver.findElement(By.xpath("//li[@class='step_done step_done_last four']")).isDisplayed());
	        assertTrue(driver.findElement(By.xpath("//li[@id='step_end' and @class='step_current last']")).isDisplayed());
	        assertTrue(driver.findElement(By.xpath("//*[@class='cheque-indent']/strong")).getText().contains(ordertext));
	        assertTrue(driver.getCurrentUrl().contains(orderurltext));
	    }

		 @Test(priority=2)
		 public void closeApplication()
		 {
			driver.close();  
		 } 
}
