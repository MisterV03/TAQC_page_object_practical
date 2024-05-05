package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends TopPart{
	private WebElement slideshow0;
	
	public HomePage(WebDriver driver) {
		super(driver);
		initElements();
		// TODO Auto-generated constructor stub
	}
	
	private void initElements() {
		slideshow0 = driver.findElement(By.cssSelector("div#carousel-banner-0 div.carousel-inner"));
	}
	
}
