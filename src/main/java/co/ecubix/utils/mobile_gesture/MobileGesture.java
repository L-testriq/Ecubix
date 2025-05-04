package co.ecubix.utils.mobile_gesture;

import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;

public class MobileGesture {

	AppiumDriver appium_driver;

	public MobileGesture(AppiumDriver appium_driver) {
		this.appium_driver = appium_driver;
		PageFactory.initElements(appium_driver, this);
	}

	public void tap(int x, int y) {
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		org.openqa.selenium.interactions.Sequence tap = new org.openqa.selenium.interactions.Sequence(finger, 1);
		tap.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), x, y));
		tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		appium_driver.perform(Arrays.asList(tap));
	}

}
