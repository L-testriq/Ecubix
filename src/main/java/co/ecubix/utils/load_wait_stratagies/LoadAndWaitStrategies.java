package co.ecubix.utils.load_wait_stratagies;

import java.time.Duration;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class LoadAndWaitStrategies {

	private static final Duration TIMEOUT = Duration.ofSeconds(10);
	private static final Duration POLLING = Duration.ofSeconds(1);
	private static FluentWait<WebDriver> fluentWait;


	public static void waitTillElementIsVisible(WebDriver driver, WebElement element) {
		try {
			fluentWait = new FluentWait<>(driver).withTimeout(TIMEOUT).pollingEvery(POLLING)
					.ignoring(NoSuchElementException.class);
			fluentWait.until(ExpectedConditions.visibilityOf(element));
		} catch (NoSuchElementException | TimeoutException e) {
		}
	}

	public static void waitTillElementIsClickable(WebDriver driver, WebElement element) {
		try {
			fluentWait = new FluentWait<>(driver).withTimeout(TIMEOUT).pollingEvery(POLLING)
					.ignoring(NoSuchElementException.class).ignoring(ElementClickInterceptedException.class);
			fluentWait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (ElementClickInterceptedException | NoSuchElementException e) {
			
		}
	}


	public static void waitTillElementIsInteractable(WebDriver driver, WebElement element) {
		try {
			fluentWait = new FluentWait<>(driver).pollingEvery(POLLING).ignoring(ElementNotInteractableException.class)
					.ignoring(NoSuchElementException.class);
			fluentWait.withTimeout(TIMEOUT).until(ExpectedConditions.elementToBeClickable(element));
		} catch (ElementNotInteractableException e) {
			
		}
	}

	public static void waitTillElementIsInvisible(WebDriver driver, WebElement element) {
		try {
			fluentWait = new FluentWait<>(driver).pollingEvery(POLLING).ignoring(NoSuchElementException.class);
			fluentWait.withTimeout(TIMEOUT).until(ExpectedConditions.invisibilityOf(element));
		} catch (NoSuchElementException e) {
			
		}
	}

}
