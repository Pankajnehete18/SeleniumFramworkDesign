package Rahulshettyaacademy.pageobjectmodel;

import java.util.List;
import java.util.function.Predicate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponent.AbstractComponents;

public class CartPage extends AbstractComponents {
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		
		super(driver);
		this.driver= driver;
		 PageFactory.initElements(driver, this);
	 }
	
	@FindBy(css=".totalRow button")
	WebElement checkoutEle ;
	
	
	@FindBy(css=".cartSection h3")
	private List<WebElement> cartProducts ;
	
	public Boolean VerifyProductDisplay(String productName) {
		boolean match=cartProducts.stream().anyMatch(cartproduct-> cartproduct.getText().equalsIgnoreCase(productName));
	    return match;
	}
	
	public CheckOutPage goToCheckOut() {
		
	   checkoutEle.click();
	   return new CheckOutPage(driver);
		
	
	}
	
	
}
	
	
	
	
	


