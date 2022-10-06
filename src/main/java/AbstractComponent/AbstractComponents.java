package AbstractComponent;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Rahulshettyaacademy.pageobjectmodel.CartPage;
import Rahulshettyaacademy.pageobjectmodel.OrderPage;

public class AbstractComponents {
	
	WebDriver driver;
	
	 public AbstractComponents(WebDriver driver) {
		
		 this.driver=driver;
		 PageFactory.initElements(driver, this);
	}
	
	 @FindBy(css="[routerLink*='cart']")
	 WebElement cartHeader;
	 
	 @FindBy(css="[routerLink*='myorders']")
	 WebElement orderHeader;
	 
	public void waitForElementToAppear(By findBy)  {  
		
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
    wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	
	}
	public void waitForWebElementToAppear(WebElement findBy) throws InterruptedException  {  
		//Thread.sleep(3000);
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
	   wait.until(ExpectedConditions.visibilityOf(findBy));
		
		}
	public CartPage goToCart() {
		
		cartHeader.click();
		CartPage cartPage=new CartPage(driver);
		return cartPage;
	}
	
    public OrderPage goToOrderPage() {
		
		orderHeader.click();
		OrderPage orderPage=new OrderPage(driver);
		return orderPage;
	}
	
	 public void waitForElementToDisAppear(WebElement ele) throws InterruptedException {
        
		 Thread.sleep(3000);
		 //WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		 //wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
	 	 }
       
	



}