package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.TruliaHomePage;
import pages.TruliaSearchPage;
import utilities.Browser;
import utilities.Config;

public class TruliaTest {
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
		search.verifyTitleContains(Config.getProperty("city"));
	}
	
	@Test(priority=2)
	public void searchByZip() {
		driver.get(Config.getProperty("url"));
		TruliaHomePage searchByZip = new TruliaHomePage(driver);
		searchByZip.verifyTitle();
		searchByZip.searchBoxIsDisplayed();
		searchByZip.searchButtonExist();
		searchByZip.searchBox.clear();
		searchByZip.searchBox.sendKeys(Config.getProperty("tc2value"));
		Browser.sleep(1);
		searchByZip.printSearchSuggestions();
		searchByZip.searchBtn.click();
		TruliaSearchPage homeType = new TruliaSearchPage(driver);
		Browser.sleep(1);
		homeType.verifyTitleContains(Config.getProperty("tc2value"));
		homeType.homeTypeToggle.click();
		Browser.sleep(1);
		homeType.homeTypeLand.click();
		Browser.sleep(1);
		homeType.verifyLocationContains(Config.getProperty("tc2value"));
	}
	
	
	@Test(priority = 3)
	public void tC004() {
		driver.get(Config.getProperty("url"));
		TruliaHomePage home = new TruliaHomePage(driver);
		home.verifyTitle();
		home.searchBoxIsDisplayed();
		home.searchButtonExist();
		home.searchBox.clear();
		home.searchBox.sendKeys("Park Place");
		Browser.sleep(1);
		home.printSearchSuggestions();
		home.selectFromSearchSuggestions(Config.getProperty("tc4value"));
		Browser.sleep(3);
		TruliaSearchPage search = new TruliaSearchPage(driver);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement searchButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dropdownBtn")));
		searchButton.click();
		Browser.sleep(1);
		search.verifyTitle(Config.getProperty("tc4title"));
		search.verifyLocationContains("Park Place");
		
	}
	
}
