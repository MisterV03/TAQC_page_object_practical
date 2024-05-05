package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import pages.HomePage;
import pages.ShoppingCartEmptyPage;
import pages.TopPart;

public class emptyShoppingCartTest extends TestRunner{
	
	
	
	@Test
	public void emptyCartTest() {
		HomePage homePage =  TestRunner.loadApplication();
		TopPart resultPage = homePage.clickOnTheTopNavElement("Shopping Cart");
		ShoppingCartEmptyPage emptyCartPage = null;
		if(resultPage instanceof ShoppingCartEmptyPage) {
			 emptyCartPage = (ShoppingCartEmptyPage) resultPage;
		}else {
			logger.error("received page isn`t empty shopping cart page");
			isTestSuccessful = false;
			return;
		}		
		if(!emptyCartPage.getShoppingCartLabel().getText().contains("Shopping Cart")) {
			logger.error("Page title doesn`t contains \"Shopping cart\"");
			isTestSuccessful = false;
		}		
		if (!emptyCartPage.getShoppingCartEmptyLabel().getText().contains("Your shopping cart is empty")) {
			isTestSuccessful = false;
			logger.error("Page doesn`t contains \"Your shopping cart is empty!\"");
		}
		homePage = emptyCartPage.clickContinueButton();
		if(!homePage.getPageTitle().contains("Your Store")){
			logger.error("Button \"Continue\" doesn`t return the home page");
			isTestSuccessful = false;
		}
	}
	
   
}
