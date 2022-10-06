package Rahulshettyaacademy.pageobjectmodel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponent.AbstractComponents;

public class LandingPage extends AbstractComponents {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		 this.driver=driver;
		 PageFactory.initElements(driver, this);
	 }
	
	
	//WebElement userEmail=driver.findElement(By.id("userEmail"));
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement passwordEle;
	
	@FindBy(id="login")
	WebElement Submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	public ProductCataloude LoginApplication(String email,String password) {
		
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		Submit.click();
		ProductCataloude productcataloude=new ProductCataloude(driver);
		return productcataloude;
	}
	
	public String getErrorMessge() throws InterruptedException {
		
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	public void goTo() {
		
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	
	
	
	
	
	
	
	

}
