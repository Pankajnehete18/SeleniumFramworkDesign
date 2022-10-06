package tests;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import Rahulshettyaacademy.pageobjectmodel.LandingPage;

public class EndToEndFlowOfEcommerceApp {
	
	
	@Test
	
	public void addtoCart() throws InterruptedException {
		
		String productName="ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    driver.get("https://rahulshettyacademy.com/client");
	    driver.manage().window().setSize(new Dimension(1440,900));
	    LandingPage landingpage=new LandingPage(driver) ;
	    driver.findElement(By.id("userEmail")).sendKeys("abcpvn@gmail.com");
	    driver.findElement(By.id("userPassword")).sendKeys("Pankaj@18");
	    driver.findElement(By.id("login")).click();
	    WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));         //
	    List<WebElement>list= driver.findElements(By.cssSelector(".mb-3"));                          //
	    Thread.sleep(3000);
	   WebElement prod=list.stream().filter(product-> product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3"))      //
			   .findFirst().orElse(null);
	  prod.findElement(By.xpath("(//button[contains(@class,'btn w-10 rounded')])[1]")).click();         //
	  
	  
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));      //
	  wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));      //
	 
	  driver.findElement(By.cssSelector("[routerLink*='cart']")).click(); //
	  Thread.sleep(3000);
	  List<WebElement>cartproducts=driver.findElements(By.cssSelector(".cartSection h3"));     //
	  
	  boolean match=cartproducts.stream().anyMatch(cartproduct-> cartproduct.getText().equalsIgnoreCase(productName));
	  Assert.assertTrue(match);
	  driver.findElement(By.xpath("(//button[starts-with(@class,'btn')])[8]")).click();            //
	  
	  Actions a=new Actions(driver);
	  a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "India").build().perform();       //
	  
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));        //
	  wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("(//button[contains(@class,'ta-item')])[2]")))); 
	  Thread.sleep(3000);
	  //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@class,'btnn action__submit ng-star-inserted')]")));
	  //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@class,'btnn action__submit ng-star-inserted')]"))).click();
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".action__submit"))).click();
	  String confirmationmessage=driver.findElement(By.cssSelector(".hero-primary")).getText();
	  System.out.println(confirmationmessage);
	    
	   }
	
	
	
}

