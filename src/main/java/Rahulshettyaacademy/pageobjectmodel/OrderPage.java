package Rahulshettyaacademy.pageobjectmodel;

import java.util.List;
import java.util.function.Predicate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponent.AbstractComponents;

public class OrderPage extends AbstractComponents {
	
	WebDriver driver;
	
	
	@FindBy(css=".totalRow button")
	WebElement checkoutEle ;
	
	
	@FindBy(css="tr td:nth-child(3)")
	private List<WebElement> productsNames ;
	
        public OrderPage(WebDriver driver) {
		
		super(driver);
		this.driver= driver;
		 PageFactory.initElements(driver, this);
	 }
	
	public Boolean VerifyOrderDisplay(String productName) {
		boolean match=productsNames.stream().anyMatch(cartproduct-> cartproduct.getText().equalsIgnoreCase(productName));
	    return match;
	}
	
	
}
	
	
	
	
	


