package tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Rahulshettyaacademy.pageobjectmodel.CartPage;
import Rahulshettyaacademy.pageobjectmodel.CheckOutPage;
import Rahulshettyaacademy.pageobjectmodel.ConfirmationPage;
import Rahulshettyaacademy.pageobjectmodel.OrderPage;
import Rahulshettyaacademy.pageobjectmodel.ProductCataloude;
import testComponents.BaseTest;

public class SubmitOrderTest extends BaseTest {

	String productName = "ZARA COAT 3";

	@Test(dataProvider="getData",groups= {"Purchase"})
	public void submitOrder(HashMap<String,String>input) throws IOException, InterruptedException {

		ProductCataloude productcataloude = landingPage.LoginApplication(input.get("email"), input.get("password"));

		List<WebElement> products = productcataloude.getProductList();
		productcataloude.addProductToCart(input.get("product"));
		productcataloude.goToCart();
		
		CartPage cartPage = productcataloude.goToCart();
         Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
		 Assert.assertTrue(match);

		CheckOutPage checkoutpage = cartPage.goToCheckOut();
		checkoutpage.selectCountry("india");
		ConfirmationPage confirmationpage = checkoutpage.submitOrder();

		String confirmMessage = confirmationpage.getConfirmationMessage();
		 Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		 driver.close();

	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistoryTest() {

		ProductCataloude productcataloude = landingPage.LoginApplication("abcpvn@gmail.com", "Pankaj@18");
		OrderPage orderPage = productcataloude.goToOrderPage();
		//orderPage.VerifyOrderDisplay(productName);
		Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
	}

	
		
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
       
		List<HashMap<String,String>>data=getJsonDataToMap("C:\\Users\\PC\\eclipse-workspace\\SelenuiumFrameworkDesign\\src\\test\\java\\data\\SubmitOrder.json") ;
		return new Object[][] {{data.get(0)}, {data.get(1)} };
		

	//@DataProvider
	//public Object[][] getData() {
		
		//return new Object[][] {{"abcpvn@gmail.com","Pankaj@18","ZARA COAT 3"}, {"abcpvn@gmail.com","Pankaj@18","ADIDAS ORIGINAL"} };

		//HashMap<String ,String> map=new HashMap<String ,String>();
				//map.put("email", "abcpvn@gmail.com");
				//map.put("password", "Pankaj@18");
				//map.put("product", "ZARA COAT 3");
				
				//HashMap<String ,String> map1=new HashMap<String ,String>();
				//map1.put("email", "abcpvn@gmail.com");
				//map1.put("password", "Pankaj@18");
				//map1.put("product", "ADIDAS ORIGINAL");
		//List<HashMap<String,String>>data=getJsonDataToMap("C:\\Users\\PC\\eclipse-workspace\\SelenuiumFrameworkDesign\\src\\test\\java\\data\\SubmitOrder.json");
	}
       

       //Project successful//
}
