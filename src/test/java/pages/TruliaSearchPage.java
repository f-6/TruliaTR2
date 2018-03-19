package pages;

import java.util.Arrays;
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
	
	@FindBy(xpath = "//div[@id='locationField']//button")
	public WebElement searchBtn;
	
	@FindBy(id = "bedroomsToggle")
	public WebElement allBedsBtn;
	
	@FindBy(xpath = "//div[@id='bedroomsButtonGroup']//button[.='4+']")
	public WebElement fourPlusBtn;
	
	@Test
	public void verifyTitle(String title) {
		Browser.sleep(1);
		String expectedTitle = title;
		Assert.assertEquals(driver.getTitle(), expectedTitle);
	}
	
	@Test
	public void verifyTitleContains(String location) {
		Browser.sleep(1);
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
	@Test
	public void allBedsBtnIsDisplayed() {
		allBedsBtn.isDisplayed();
	}
	@Test
	public void verifyAllBedOptions() {
		List<WebElement> bedsOPtion = driver.findElements(By.xpath("//div[@id='bedroomsButtonGroup']//button"));
		List<String> expected = Arrays.asList(Config.getProperty("tc4allBeds").split(", "));
		for(WebElement each : bedsOPtion)
			Assert.assertTrue(expected.contains(each.getText()));
	}
	@Test
	public void verifyBeds(String beds) {
		List<WebElement> listings = driver.findElements(By.xpath("//li[@data-auto-test='beds']"));
		for(WebElement each : listings) {
			if(each.getText()!=Config.getProperty("tc4bed4")) {
				System.out.println("This is a bug: " + each.getText());
				continue;
			}
			Assert.assertTrue(each.getText().contains(beds));
		}
	}
	
}
