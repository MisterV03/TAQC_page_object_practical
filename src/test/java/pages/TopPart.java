package pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class TopPart {
	protected final String TAG_ATTRIBUTE_VALUE = "value";
	private WebElement currensyForm;
	private List<WebElement> topNavPanel;
	private WebElement logoLink;
	private WebElement searchField;
	private WebElement ShoppingCartBlackButton;
	private List<WebElement> goodsTypesNavPanel;
	private List<WebElement> goodsTypesNavPanelLinks;
	protected WebDriver driver;
	private WebElement searchButton;

	public TopPart(WebDriver driver) {
		this.driver = driver;
		initElements();
	}

	private void initElements() {
		currensyForm = driver.findElement(By.id("form-currency"));
		topNavPanel = driver.findElements(By.cssSelector("#top .nav.float-end li.list-inline-item"));
		logoLink = driver.findElement(By.cssSelector("#logo a"));
		searchField = driver.findElement(By.cssSelector("input[name=\"search\"]"));
		ShoppingCartBlackButton = driver.findElement(By.cssSelector("#header-cart button"));
		goodsTypesNavPanel = driver.findElements(By.cssSelector("#menu li.nav-item"));
		goodsTypesNavPanelLinks = driver.findElements(By.cssSelector("#menu li.nav-item>a"));
		searchButton = driver.findElement(By.cssSelector("#search button"));		
	}

	public WebElement getSearchButton() {
		return searchButton;
	}
	
	public WebElement getCurrencyForm() {
		return currensyForm;
	}

	public WebElement getCurrensyForm() {
		return currensyForm;
	}

	public List<WebElement> getTopNavPanel() {
		return topNavPanel;
	}

	public WebElement getLogoLink() {
		return logoLink;
	}

	public WebElement getSearchField() {
		return searchField;
	}

	public WebElement getShoppingCartBlackButton() {
		return ShoppingCartBlackButton;
	}

	public List<WebElement> getGoodsTypesNavPanel() {
		return goodsTypesNavPanel;
	}
	
	public List<WebElement> getGoodsTypesNavPanelLinks() {
		return goodsTypesNavPanelLinks;
	}
	
	public String getSearchTopFieldText() {
		return getSearchField().getAttribute(TAG_ATTRIBUTE_VALUE);
	}
	
	public String getPageTitle() {
		return driver.getTitle();
	}
	
	public String getPageURL() {
		return driver.getCurrentUrl();
	}
	
	
	public void clickSearchButton() {
		getSearchButton().click();
	}

	public void clickLogo() {
		getLogoLink().click();
	}
	
    public void clickSearchField() {
        getSearchField().click();
    }

    public TopPart clickOnTheTopNavElement(String name) {
    	WebElement temp = null;
    	for(WebElement in : getTopNavPanel()) {
    		temp = in.findElement(By.tagName("span"));
    		if(temp.getText().contains(name)) {
    			in.findElement(By.tagName("a")).click();
    			return createPageByName(name);
    		}
    	}
		return null;
    }
    
    private TopPart createPageByName(String name) {
    	if (name.contains("Wishlist")) {
    		//return new WishListPage; //class isn`t implemented
    	}
        if (name.contains("Shopping Cart")) {
    		return new ShoppingCartEmptyPage(driver);
        }
      	if(name.contains("checkout")) {
    		//return new CheckoutPage; //class isn`t implemented
      	}
    	return null;
    }
    
    
	public void clearSearchField() {
		getSearchField().clear();
	}

	 public void searchWithSearchField(String searchText) {
	        clickSearchField();
	        clearSearchField();
	        setSearchTopField(searchText);
	        clickSearchButton();
	    }
	 
	
	 public void setSearchTopField(String text) {
	        getSearchField().sendKeys(text);
	  }
	 
	 public void clickOnTheDropdownMenu(String menuLabel) {
		 for(WebElement in : getGoodsTypesNavPanelLinks()) {
			 if(in.getText().equals(menuLabel)) {
				 in.click();
				 return;
			 }
		 }	 
	 }
	 
	 public List<WebElement> getDropdownOptions(String dropdownLabel){
		 int targetIndex;
		 List<WebElement> res = null;
		 for(targetIndex = 0; targetIndex<goodsTypesNavPanelLinks.size(); targetIndex++) {
			 if(getGoodsTypesNavPanelLinks().get(targetIndex).getText().equals(dropdownLabel)) {
				 break;
			 }
		 }
		 try {
			 res = getGoodsTypesNavPanel().get(targetIndex).findElements(By.cssSelector("#menu li.nav-item .dropdown-menu li a"));
			 return res;
		 }catch(Exception ex){
			 return null;
		 }
	 }
	 
	protected void scrollToElement(WebElement webElement, boolean useJavascript) {
		if (useJavascript) {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
		} else {
			Actions action = new Actions(driver);
			action.moveToElement(webElement).perform();
		}

	}
}
