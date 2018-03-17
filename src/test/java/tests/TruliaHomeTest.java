package tests;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import pages.TruliaHomePage;
import pages.TruliaSearchPage;
import utilities.Browser;
import utilities.Config;

public class TruliaHomeTest {
	protected WebDriver driver;
	@BeforeClass
	public void start() {
		driver = Browser.getDriver();
		driver.get(Config.getProperty("url"));
		
	}
	@AfterClass
	public void tearDown() {
		Browser.quit();
	}
	@Test(priority = 1, parameters="smoke")
	public void searchByCity() {
		TruliaHomePage home = new TruliaHomePage(driver);
		home.verifyTitle();
		home.searchBoxIsDisplayed();
		home.searchButtonExist();
		home.searchBox.clear();
		home.searchBox.sendKeys(Config.getProperty("city"));
		home.searchBtn.click();
		TruliaSearchPage search = new TruliaSearchPage(driver);
		search.verifyTitleContainsCity();
	}
	
	
	
}
