package tests;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import Rahulshettyaacademy.pageobjectmodel.CartPage;
import Rahulshettyaacademy.pageobjectmodel.ProductCataloude;
import testComponents.BaseTest;
import testComponents.Retry;

public class LoginErrorValidationTest extends BaseTest {

	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	
	public void addToCart() throws IOException, InterruptedException {

		
		//String productName = "ZARA COAT 3";
		landingPage.LoginApplication("abcp@gmail.com", "Pankaj@18");
		//landingPage.getErrorMessge();

		Assert.assertEquals("Incorrect email or pass**word.", landingPage.getErrorMessge());

	}

	@Test
	public void productPageValidation() throws IOException, InterruptedException {

		String productName = "ZARA COAT 3";
		ProductCataloude productcataloude = landingPage.LoginApplication("abcpvn@gmail.com", "Pankaj@18");
		List<WebElement> products = productcataloude.getProductList();
		productcataloude.addProductToCart(productName);
		CartPage cartPage = productcataloude.goToCart();

		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
		 Assert.assertFalse(match);

	}
}
