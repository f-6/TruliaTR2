package pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import utilities.Browser;
import utilities.Config;

public class TruliaSearchPage {
	private WebDriver driver;
	String handle;
	
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
	
	
	@FindBy(xpath="//div[@class='xsColOffset4 smlColOffset4 mdColOffset4 lrgColOffset4 miniCol12 xsCol10 smlCol10  mdCol10 lrgCol10']//li[.=' Condo']")
	public WebElement condo;
	
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
	@Test
	public void verifyBostonTitle() {
		String expected = Config.getProperty("bostonTitle");
		Assert.assertEquals(driver.getTitle(), expected);
	}
	@Test
	public void verifyLocationContainsBoston() {
		List<WebElement> listings = driver.findElements(By.xpath("//div[@id='resultsColumn']//div[@class='typeTruncate typeLowlight']"));
		for(WebElement each : listings) {
			String expectedCity = Config.getProperty("bostonCity");
			Assert.assertTrue(each.getText().contains(expectedCity));
		}
	}
	@Test
	public void switchWindow() {
		 handle = driver.getWindowHandle();
		String newWindowHandle="";
		Set<String> windowHandles = driver.getWindowHandles();
		
		// capture the handle of the tab which is not the current tab
		for (String string : windowHandles) {
			System.out.println(string);
			if (!string.equals(handle)) {
				newWindowHandle = string;
			}
		}
				// switch to new window using the handle of the new window
		driver.switchTo().window(newWindowHandle);
	}
	@Test
	public void verifyIfCondo() {
		List<String> result = new ArrayList();
		List<WebElement> listings = driver.findElements(By.xpath("//div[@class='containerFluid']//li[@class='xsCol12Landscape smlCol12 lrgCol8']"));
//		String handle = driver.getWindowHandle();
		System.out.println("Size : "+listings.size());
		for( WebElement each :listings) {
			each.click();
			switchWindow();
			WebDriverWait wait = new WebDriverWait(driver, 10);
			WebElement condoText = wait.until(ExpectedConditions.visibilityOf(condo));
			result.add(condoText.getText());
			driver.close();
			driver.switchTo().window(handle);
		}
		Assert.assertTrue(result.contains("Condo"));
	}
	
	
	
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
			if(each.getText().equals(Config.getProperty("tc4bed4"))) {
				Assert.assertTrue(each.getText().contains(beds));				
				continue;
			}
			System.out.println("This is a bug: " + each.getText());
		}
	}
	
}
