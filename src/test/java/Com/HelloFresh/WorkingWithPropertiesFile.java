package Com.HelloFresh;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.testng.annotations.Test;

public class WorkingWithPropertiesFile {
	    @Test
	    public void getFileProperties() throws IOException {
		Properties pros=new Properties();
		FileInputStream fis = new FileInputStream("\\src\\test\\resources\\TestSuite\\Config.Properties");
		pros.load(fis);
		String URL = pros.getProperty("URL");
		System.out.println(URL);
		String user=pros.getProperty("Username");
		System.out.println(user);
	    }
	    
}

	