package Com.HelloFresh;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class ReadDataFromFile {

   	WebDriver driver;
    WebDriverWait wait; 
		
		@Test(priority=0)
		public void setUp()
		   {
		 	     System.setProperty("webdriver.chrome.driver", "C:/chromedriver/chromedriver.exe");
		         driver = new ChromeDriver();
		         driver.get("http://automationpractice.com/index.php");
		         driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		         driver.manage().window().maximize();
		    }
		 @Test(priority=1)
		 public void CheckoutTest()
		    {
		
		    	wait = new WebDriverWait(driver, 10);
		        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login"))).click();
		        driver.findElement(By.id("email")).sendKeys("hf_challenge_123456@hf12345.com");
		        driver.findElement(By.id("passwd")).sendKeys("12345678");
		        driver.findElement(By.id("SubmitLogin")).click();
		    } 
		 
        @Test(priority=2)
		   public void GetDataFromExcel() throws IOException 
		   {        String NameofDress = "Faded Short Sleeve T-shirts";
        	        String filepath = "E:\\Test_Data.xlsx";
					FileInputStream File = new FileInputStream(filepath);
					// Create a workbook
					XSSFWorkbook Workbook=new XSSFWorkbook(File);
					//Create and Navigate to the required sheet, Sheet1 - 0, Sheet2 - 1, Sheet3 - 2 so on..
					XSSFSheet ws=Workbook.getSheetAt(0);
					//Get the row count
					int rowcount=ws.getLastRowNum()+1;
					System.out.println("Total number of rows are:" +rowcount);
					int columncount=ws.getRow(0).getLastCellNum();
					System.out.println("Total number of columns are :" +columncount);
					for(int i=0;i<rowcount;i++)
					{
						XSSFRow row=ws.getRow(i);
						for(int j=0;j<columncount;j++)
						{
							XSSFCell column=row.getCell(j);
							String Value=column.getStringCellValue();
							System.out.println("Value from Excel:" +Value);
							
					
							if(Value.equalsIgnoreCase(NameofDress))
							{
						      System.out.println("Name of Dress is :" + column.getStringCellValue()); 
						      System.out.println("The Test is Successful"); 
							}
						}   
							
							
					 }
					    Workbook.close();
					    wait = new WebDriverWait(driver, 10);
				        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Women"))).click();
				        driver.findElement(By.xpath("//a[@title='Faded Short Sleeve T-shirts']/ancestor::li")).click();
				        driver.findElement(By.xpath("//a[@title='Faded Short Sleeve T-shirts']/ancestor::li")).click();
				       
				        //Added code for Selection of Size, Color and Quantity of the Item/Dress
				        driver.findElement(By.id("quantity_wanted")).clear();
				        driver.findElement(By.id("quantity_wanted")).sendKeys("3");
				        Select sizedropdown = new Select(driver.findElement(By.id("group_1")));
			            sizedropdown.selectByValue("2");
				        driver.findElement(By.id("color_14")).click();
				        
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
				        
				        driver.close();
		      }       	 


}

