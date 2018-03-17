package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
	
	@Test
	public void verifyTitleContainsCity() {
		Browser.sleep(1);
		String expectedCity = Config.getProperty("city");
		Assert.assertTrue(driver.getTitle().contains(expectedCity));
	}
	@Test
	public void verifyLocationContainsCity() {
		List<WebElement> listings = driver.findElements(By.xpath("//div[@class='typeTruncate typeLowlight']"));
		for(WebElement each : listings) {
			String expectedCity = Config.getProperty("city");
			Assert.assertTrue(each.getText().contains(expectedCity));
		}
	}
}
