package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import utilities.Config;

public class TruliaHomePage {
	private WebDriver driver;
	
	public TruliaHomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="searchBox")
	public WebElement searchBox;
	
	@FindBy(xpath="//button[@class='css-ejw4np btn btnPrimary']")
	public WebElement searchBtn;
	
	@FindBy(xpath="//button[@class='css-aks6px btn btnLrg btnSecondary baz typeEmphasize pvs btnSelected']")
	public WebElement buyBtn;
	
	@FindBy(xpath="//button[contains(text(),'Rent')]")
	public WebElement rentBtn;
	
	@FindBy(xpath="//button[contains(text(),'Sold')]")
	public WebElement soldBtn;
	
	@FindBy(xpath="//input[contains(@aria-haspopup,'true')]")
	public WebElement autoSuggestPopedUp;
	
	@FindBy(xpath="//input[contains(@aria-haspopup,'false')]")
	public WebElement autoSuggestNotPopedUp;
	
	@FindBy(xpath="//li[@id='react-autowhatever-1--item-0']")
	public WebElement chooseFromPopup;
	
	@Test
	public void verifyBuyButton() {
		buyBtn.click();
		Assert.assertTrue(buyBtn.isDisplayed());
		Assert.assertEquals(buyBtn.getText() , "Buy"); //(Config.getProperty("tc17buyBtn")));
		//Assert.assertTrue(buyBtn.isEnabled());
	}
	
	@Test
	public void verifyBuyButtonIsSelelected() {
		Assert.assertTrue(buyBtn.isEnabled());
	}
	
	@Test
	public void verifyRentButton() {
		rentBtn.click();
		Assert.assertTrue(rentBtn.isDisplayed());
		Assert.assertEquals(rentBtn.getText() , (Config.getProperty("tc17rentBtn")));
		//Assert.assertTrue(rentBtn.isEnabled());
	}
	
	@Test
	public void verifySoldButton() {
		soldBtn.click();
		Assert.assertTrue(soldBtn.isDisplayed());
		Assert.assertEquals(soldBtn.getText() , (Config.getProperty("tc17soldBtn")));
		//Assert.assertTrue(soldBtn.isEnabled());
	}
	
	@Test
	public void verifyTitle() {
		String expected = Config.getProperty("title");
		Assert.assertEquals(driver.getTitle(), expected);
	}
	@Test
	public void searchBoxIsDisplayed() {
		Assert.assertTrue(searchBox.isDisplayed());
	}
	@Test
	public void searchButtonExist() {
		Assert.assertTrue(searchBtn.isDisplayed());
	}
	
	@Test
	public void searchSuggestions() {
		List<WebElement> suggestion = driver.findElements(By.xpath("//div[@class='typeEmphasize typeTruncate']"));
		for(WebElement each : suggestion)
			System.out.println(each.getText());
	}
	@Test
	public void printSearchSuggestions() {
		List<WebElement> suggestion = driver.findElements(By.xpath("//div[@class='typeEmphasize typeTruncate']"));
		for(WebElement each : suggestion)
		System.out.println(each.getText());
	}
	@Test
	public void selectFromSearchSuggestions(String location){
		List<WebElement> suggestions = driver.findElements(By.xpath("//div[@class='typeEmphasize typeTruncate']"));
		for(WebElement each : suggestions) {
			if(location.equals(each.getText())) {
				each.click();
				break;
			}
		}
	}
	
	@Test
	public void autoSuggestionPopup() {
		String s = autoSuggestPopedUp.getAttribute("aria-haspopup");
		Assert.assertTrue(s.equals("true"));
	
	}
	
	@Test
	public void searchSuggestionsHasAddress() {
		List<WebElement> checkAddress = driver.findElements(By.xpath("//div[@class='mediaImg mediaImgExt typeLowlight']"));
		for (WebElement a : checkAddress) {
			Assert.assertTrue(a.getText().equals(Config.getProperty("tc18address")));
			
		}
	}
	
	@Test
	public void searchSuggestionsHasNeigborhood() {
		List<WebElement> checkAddress = driver.findElements(By.xpath("//div[@class='mediaImg mediaImgExt typeLowlight']"));
		if(checkAddress.size() > 0){
		for (WebElement a : checkAddress) {
			Assert.assertEquals(a.getText() , (Config.getProperty("tc20neigborhood")));
			
		}
		}
	}
	
	
	
	
	
	
	
}
