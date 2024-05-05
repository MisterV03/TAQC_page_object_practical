package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShoppingCartEmptyPage extends TopPart{
	protected WebElement shoppingCartLabel;
	protected WebElement shoppingCartEmptyLabel;
	protected WebElement continueButton;
	
	 public ShoppingCartEmptyPage(WebDriver driver) {
	        super(driver);
	        initElements();
	    }
	 
	 private void initElements() {
		 shoppingCartLabel = driver.findElement(By.cssSelector("#content h1"));
		 shoppingCartEmptyLabel = driver.findElement(By.cssSelector("#content p"));
		 continueButton = driver.findElement(By.cssSelector("#content .float-end a.btn"));
	    }

	public WebElement getShoppingCartLabel() {
		return shoppingCartLabel;
	}

	public WebElement getShoppingCartEmptyLabel() {
		return shoppingCartEmptyLabel;
	}

	public WebElement getContinueButton() {
		return continueButton;
	}
	
	public HomePage clickContinueButton() {
		getContinueButton().click();
		return new HomePage(driver);
	}
}
