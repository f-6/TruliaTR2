package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import utilities.Browser;
import utilities.Config;

public class TruliaSearchPage {
private WebDriver driver;
	
	public TruliaSearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="homeTypeToggle")
	public WebElement homeTypeToggle;
	@FindBy(id="homeType4")
	public WebElement homeTypeLand;

	@Test
	public void verifyTitleContains(String location) {
		Browser.sleep(1);
		String expectedCity = location;
		Assert.assertTrue(driver.getTitle().contains(location));
	}
	@Test
	public void verifyLocationContains(String location) {
		List<WebElement> listings = driver.findElements(By.xpath("//div[@class='typeTruncate typeLowlight']"));
		for(WebElement each : listings) {
			String expectedCity = location;
			Assert.assertTrue(each.getText().contains(expectedCity));
		}	
		}
	@Test
	public void searchSuggestions() {
		List<WebElement> suggestion = driver.findElements(By.xpath("//div[@class='typeEmphasize typeTruncate']"));
		for(WebElement each : suggestion)
		System.out.println(each.getText());
	}
}
