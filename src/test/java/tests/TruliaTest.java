package tests;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
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
	
	/**
	 * @throws InterruptedException 
	 * 
	 */
	@Test(priority = 2)
	public void testCase001() {
		driver.get(Config.getProperty("url"));
		TruliaHomePage tc1 = new TruliaHomePage(driver);
		tc1.verifyTitle();
		tc1.searchBox.click();
		tc1.searchBox.sendKeys("Boston");
		Browser.sleep(2);
		List<WebElement> listOfCities = driver.findElements(By.xpath("//div[@class='typeEmphasize typeTruncate']"));
		for(WebElement each : listOfCities) {
			if(each.getText().equals("Boston, MA")) {
				each.click();
				break;
			}
		}
		Browser.sleep(2);
		TruliaSearchPage tc01=new TruliaSearchPage(driver);
		tc01.verifyBostonTitle();
		
		driver.findElement(By.xpath("//button[@id='homeTypeToggle']")).click();
		Browser.sleep(1);
		
		List<WebElement> listOfHomeTypes = driver.findElements(By.xpath("//span[@class='fieldItem checkbox']/label"));
		
		String homeTypes = Config.getProperty("homeTypes");
		List<String> expectedHomeTypes = Arrays.asList(homeTypes.split(", "));
		System.out.println(expectedHomeTypes);
		for(WebElement each : listOfHomeTypes) {
			System.out.print(each.getText()+" ");
			Assert.assertTrue(expectedHomeTypes.contains(each.getText()));
		}
		
		driver.findElement(By.xpath("//label[@for='homeType1']")).click();
		Browser.sleep(1);
		tc01.verifyLocationContainsBoston();
		
		Browser.sleep(2);
		
		tc01.verifyIfCondo();
		Browser.sleep(2);

	}

	
	@Test(priority=3)
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
	
	
	@Test(priority = 4)
	public void tC004() {
		driver.get(Config.getProperty("url"));
		TruliaHomePage home = new TruliaHomePage(driver);
		home.verifyTitle();
		home.searchBoxIsDisplayed();
		home.searchButtonExist();
		home.searchBox.clear();
		home.searchBox.sendKeys(Config.getProperty("tc4val") + ", N");
		Browser.sleep(2);
		home.printSearchSuggestions();
		home.selectFromSearchSuggestions(Config.getProperty("tc4value"));
		Browser.sleep(3);
		TruliaSearchPage search = new TruliaSearchPage(driver);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(search.searchBtn));
		searchButton.click();
		Browser.sleep(2);
		search.verifyTitle(Config.getProperty("tc4title"));
		search.verifyLocationContains(Config.getProperty("tc4val"));
		search.allBedsBtnIsDisplayed();
		search.allBedsBtn.click();
		search.verifyAllBedOptions();
		search.fourPlusBtn.click();
		Browser.sleep(3);
		search.verifyBeds(Config.getProperty("tc4bed4"));
	}

	//TESTCASE-5
		@Test(priority=5)
		public void tc005(){
			driver.get(Config.getProperty("url"));
			TruliaHomePage testFive = new TruliaHomePage(driver);
			TruliaSearchPage searchTestFive = new TruliaSearchPage(driver);
			
			testFive.searchBox.clear();
			testFive.verifyTitle();
			Browser.sleep(1);
			testFive.searchBoxIsDisplayed();
			testFive.searchButtonExist();
			Browser.sleep(1);
			testFive.searchBox.sendKeys(Config.getProperty("tc5value"));
			Browser.sleep(1);
			testFive.searchBtn.click();
			searchTestFive.verifyTitleContains(Config.getProperty("tc5value"));
			Browser.sleep(1);
			searchTestFive.verifyLocationContains(Config.getProperty("tc5value"));
			
		}
		
		//TESTCASE-6
		@Test(priority=6)
		public void tc006() {
			driver.get(Config.getProperty("url"));
			TruliaHomePage testSix = new TruliaHomePage(driver);
			TruliaSearchPage searchTestSix = new TruliaSearchPage(driver);
			Browser.sleep(1);
			
			testSix.searchBox.clear();
			testSix.verifyTitle();
			Browser.sleep(1);
			testSix.searchBoxIsDisplayed();
			testSix.searchButtonExist();
			Browser.sleep(1);
			testSix.searchBox.sendKeys(Config.getProperty("tc6value"));
			Browser.sleep(1);
			testSix.selectFromSearchSuggestions(Config.getProperty("tc6value1"));
			testSix.searchBtn.click();
			Browser.sleep(1);
			searchTestSix.verifyTitleContains(Config.getProperty("tc6value1"));
			Browser.sleep(1);
			searchTestSix.verifyLocationContains(Config.getProperty("tc6value1"));
		}
		
		//TESTCASE-7
				@Test(priority=7)
				public void tc007() {
					driver.get(Config.getProperty("url"));
					TruliaHomePage testSeven = new TruliaHomePage(driver);
					TruliaSearchPage searchTestSeven = new TruliaSearchPage(driver);
					
					testSeven.verifyTitle();
					testSeven.searchBoxIsDisplayed();
					testSeven.searchButtonExist();
					testSeven.searchBox.clear();
					Browser.sleep(1);
					
					testSeven.searchBox.sendKeys(Config.getProperty("tc7value"));
					testSeven.searchBox.click();
					
				}	
	
}
