package pages;

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
	
	
}
