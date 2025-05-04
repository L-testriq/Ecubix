package co.ecubix.site.core;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import co.ecubix.utils.load_wait_stratagies.LoadAndWaitStrategies;

public class PageObjectFacilitator {

	private WebDriver driver;

	public PageObjectFacilitator(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	protected void setTextInField(By element, String text) {
		LoadAndWaitStrategies.waitTillElementIsInteractable(driver, driver.findElement(element));
		removeTextFromField(driver, element);
		driver.findElement(element).sendKeys(text);
	}
	

	protected void setTextInFieldWithoutRemovalOfPreExistingData(By element, String text) {
		LoadAndWaitStrategies.waitTillElementIsInteractable(driver, driver.findElement(element));
		driver.findElement(element).sendKeys(text);
	}

	protected void uploadFile(By element, String filePath) {
		driver.findElement(element).sendKeys(filePath);
	}

	protected void scrollToTheElement(By element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(element));
	}

	protected String getTextFromField(By element) {
		LoadAndWaitStrategies.waitTillElementIsVisible(driver, driver.findElement(element));
		return driver.findElement(element).getText();
	}

	protected void clickButton(WebDriver driver, By buttonElement) {
		LoadAndWaitStrategies.waitTillElementIsClickable(driver, driver.findElement(buttonElement));
		try {
			driver.findElement(buttonElement).click();
		} catch (ElementClickInterceptedException clickInterceptedException) {
		}
	}
	
	protected Boolean checkPresenceOfElement(WebDriver driver, By elementToBeChecked) {
        LoadAndWaitStrategies.waitTillElementIsVisible(driver, driver.findElement(elementToBeChecked));
        try {
            return driver.findElement(elementToBeChecked).isDisplayed();
        } catch (NoSuchElementException noSuchElementException) {
            return false;
        }
    }
    protected Boolean checkButtonIsEnabled(WebDriver driver, By elementToBeCheckedLocator) {
    	 LoadAndWaitStrategies.waitTillElementIsVisible(driver, driver.findElement(elementToBeCheckedLocator));
         try {
             return driver.findElement(elementToBeCheckedLocator).isEnabled();
         } catch (NoSuchElementException noSuchElementException) {
             return false;
         }
    }
    
    public void scrollToWindowTop() {
    	JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0);");
    }

	protected void removeTextFromField(WebDriver driver, By element) {
		WebElement el = driver.findElement(element);
		el.clear();
		el.sendKeys(Keys.CONTROL + "a");
		el.sendKeys(Keys.DELETE);
	}

}
