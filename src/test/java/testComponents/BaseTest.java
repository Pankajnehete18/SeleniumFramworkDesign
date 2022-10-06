package testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import Rahulshettyaacademy.pageobjectmodel.LandingPage;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landingPage;
	
	@Test
	public WebDriver driverInitializer() throws IOException {
		
	    
		Properties prop=new Properties();
		FileInputStream fls=new FileInputStream("C:\\Users\\PC\\eclipse-workspace\\SelenuiumFrameworkDesign\\src\\main\\java\\resources\\GlobalData.properties");
		prop.load(fls);
		//String browsername=prop.getProperty("browser");
		String browsername=System.getProperty("browser") !=null ? System.getProperty("browser") : prop.getProperty("browser");
	
		if(browsername.contains("chrome")) {
		ChromeOptions options=new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		if(browsername.contains("headless")) {
		options.addArguments("headless");
		}
		 driver=new ChromeDriver(options);
		 driver.manage().window().setSize(new Dimension(1440,1200));
		
	    
		}
		else if(browsername.equalsIgnoreCase("Firefox")){
	
	
		}
		else if(browsername.equalsIgnoreCase("edge")) {
			WebDriverManager.chromedriver().setup();
			driver=new EdgeDriver();
	
	}
	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	   // driver.manage().window().maximize();
	    return driver;
	}	
	
	   public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
   	 
   	 //File read to json
   	  String jsonContent= FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
   	 
  //String to Hashmap by using jackson databind
   	  ObjectMapper mapper=new ObjectMapper();
   	  List<HashMap<String,String>> data= mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
    
        return data;
	}
	  
	   public  String getScreenshot(String testCasename,WebDriver driver) throws IOException  {
	  
		   TakesScreenshot ts=  (TakesScreenshot) driver;
		   File source=ts.getScreenshotAs(OutputType.FILE);
		   File file=new File(System.getProperty("user.dir")+ "//reports"+ testCasename+".png");
		   FileUtils.copyFile(source, file);
		   return System.getProperty("user.dir")+ "//reports"+ testCasename+".png";
		   
	   
 }
		@BeforeMethod(alwaysRun=true)
        public LandingPage launchapplication() throws IOException {

        driver= driverInitializer();
        landingPage=new LandingPage(driver) ;
        landingPage.goTo();
       
         return landingPage;
       
       
       }
       
       @AfterMethod(alwaysRun=true)
       public void teardown() {
    	   
    	   driver.quit();
       }
       
}
       
       
       
       
       
       
       
          
       	
	