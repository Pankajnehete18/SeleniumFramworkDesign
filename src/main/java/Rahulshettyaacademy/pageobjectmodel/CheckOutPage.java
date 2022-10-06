package Rahulshettyaacademy.pageobjectmodel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import AbstractComponent.AbstractComponents;

public class CheckOutPage extends AbstractComponents {

	WebDriver driver;
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	@FindBy(css=".action__submit")
	WebElement submit;
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement country;
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement selectIndia;
	
	By results=By.cssSelector(".ta-results");
	
	public void selectCountry(String countryName) {
		
	Actions a=new Actions(driver);
    a.sendKeys(country,countryName).build().perform();
    //WebElement element = driver.findElement(By.cssSelector(".action__submit"));
   // a.moveToElement(element).build().perform();
    
    waitForElementToAppear(By.cssSelector(".ta-results"));
	selectIndia.click();
	
	waitForElementToAppear(By.cssSelector(".action__submit"));
	submit.click();
		  	
	}	
		
	public ConfirmationPage submitOrder()	{
		
		submit.click();
	return new ConfirmationPage(driver);
		
		}
	
	


}