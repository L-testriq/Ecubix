package co.ecubix.utils.read_files;

import org.openqa.selenium.By;

public class CreateLocator extends ReadLocators{ 
	
	private By locator;
	private String locatorType;
	private String locatorValue;
	
	public By getLocator(String pageName, String locatorName) {
		locatorType = getType(pageName, locatorName);
		locatorValue = getValue(pageName, locatorName);
		switch(locatorType) {
		case "id":
			locator = By.id(locatorValue);
			break;
		case "xpath":
			locator = By.xpath(locatorValue);
			break;
		case "cssSelector":
			locator = By.cssSelector(locatorValue);
			break;
		case "tagName":
			locator = By.tagName(locatorValue);
			break;
		case "className":
			locator = By.className(locatorValue);
			break;
		case "linkText":
			locator = By.linkText(locatorValue);
			break;
		case "partialLinkText":
			locator = By.partialLinkText(locatorValue);
			break;
		case "name":
			locator = By.name(locatorValue);
			break;
		default:
			locator = null;
		}
		return locator;
	}

}
