package Com.HelloFresh;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

	public class ScreenshotTest { 
		
		WebDriver driver;
		WebDriverWait wait; 
		
		@Parameters({"browser", "url"})
		@Test(priority=0)
		 public void setUp(String browser, String url)
		{
			   if (browser.equalsIgnoreCase("IE")) 
                { 
    	            System.setProperty("webdriver.ie.driver","C:/IEDriver/IEDriver.exe");
			        driver = new InternetExplorerDriver();
                }
		             driver.get(url);
		             driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		             driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		             driver.manage().window().maximize();
		 }

		 @Parameters({"account", "text", "urltext"}) 
		 @Test(priority=1)
		 public void logInTest(String account, String text, String urltext) throws IOException
		 {   
			   // Getting the data from the Properties file
			   Properties pros=new Properties();
	           FileInputStream fis = new FileInputStream("E:\\PropertiesFile\\Config.Properties.txt");
	           pros.load(fis);
	           String fullName = pros.getProperty("fullName");
	           System.out.println(fullName);
	           String existingUserEmail = pros.getProperty("existingUserEmail");
	           System.out.println(existingUserEmail);
	           String existingUserPassword = pros.getProperty("existingUserPassword");
	           System.out.println(existingUserPassword);
		
			   wait = new WebDriverWait(driver, 30);
		       wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login"))).click();
		       driver.findElement(By.id("email")).sendKeys(existingUserEmail);
		       driver.findElement(By.id("passwd")).sendKeys(existingUserPassword);
		       driver.findElement(By.id("SubmitLogin")).click();
		//       WebElement heading = driver.findElement(By.cssSelector("h1"));
		//       heading = driver.findElement(By.cssSelector("h2"));
		       File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		  
		            try {
		            	 Assert.fail(account); 
		            	 FileHandler.copy(srcFile, new File("src/test/java/com/HelloFresh/FailedTest.png"));
						 
					} catch (IOException e1) {
						e1.printStackTrace();
					}
		            
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
