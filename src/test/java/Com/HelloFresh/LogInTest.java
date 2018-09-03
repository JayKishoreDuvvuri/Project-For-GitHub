package Com.HelloFresh;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LogInTest {
	
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
	    else if(browser.equalsIgnoreCase("Firefox")) 
	        { 
	    	    System.setProperty("webdriver.gecko.driver","C:/geckodriver/geckodriver.exe");
				driver = new FirefoxDriver();
	        }
	    else if(browser.equalsIgnoreCase("IE")) 
          { 
  	         System.setProperty("webdriver.ie.driver","C:/IEDriver/IEDriver.exe");
			     driver = new InternetExplorerDriver();
          }
	             driver.get(url);
	             driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	             driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	             driver.manage().window().maximize();
	    }


	 
	 @Parameters({"existingUserEmail", "existingUserPassword", "fullName", "account", "text", "urltext"})
	 @Test(priority=1)
	 public void logInTest(String existingUserEmail, String existingUserPassword, String fullName, String account, String text, String urltext) 
	 {
		    wait = new WebDriverWait(driver, 10);
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login"))).click();
	        driver.findElement(By.id("email")).sendKeys(existingUserEmail);
	        driver.findElement(By.id("passwd")).sendKeys(existingUserPassword);
	        driver.findElement(By.id("SubmitLogin")).click();
	        wait = new WebDriverWait(driver, 10);
	        WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='page-heading']")));

	        Assert.assertEquals(account, heading.getText());
	        Assert.assertEquals(fullName, driver.findElement(By.className("account")).getText());
	        Assert.assertTrue(driver.findElement(By.className("info-account")).getText().contains(text));
	        Assert.assertTrue(driver.findElement(By.className("logout")).isDisplayed());
	        Assert.assertTrue(driver.getCurrentUrl().contains(urltext));
	 }
	 
	 @Test(priority=2)
	 public void LogOut()  
	 {
		driver.findElement(By.className("logout")).click();
		driver.close();
	 } 
}
