package appiumLearning;

import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileBy;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

@SuppressWarnings("deprecation")
public class MobileApp {

	//Driver declaration
	public static AndroidDriver driver;
	
	public static void main(String[] args) {
		try {
			//Start the App on Main Activity page
			OpenApp();
			
			//Click on WebView for scrolling
			driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Webview\"]")).click();
			Thread.sleep(2000);
			scrollToBottom();
			
			//Click on Swipe for swiping
			driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Swipe\"]")).click();
			Thread.sleep(2000);
			swipeRightToLeft(5);
			
			System.out.println("Testing for the checkout");
		}catch(Exception ex) {
			System.out.println(ex);
		}
	}
	
	//Open the App with specific activity page
	public static void OpenApp() {
		try {
			//Add desired capabilities
			DesiredCapabilities capability = new DesiredCapabilities();
			capability.setCapability("deviceName", "emulator-5554");
			capability.setCapability("udid", "emulator-5554");
			capability.setCapability("appPackage", "com.wdiodemoapp");
			capability.setCapability("noReset", "true");
			capability.setCapability("autoGrantPermission", "true");
			capability.setCapability("platformName", "Android");
			capability.setCapability("appActivity", "com.wdiodemoapp.MainActivity");
			
			URL url = new URL("http://localhost:4723/wd/hub");
			driver = new AndroidDriver(url, capability);
			System.out.println("Application started...");
		}catch(Exception ex) {
			System.out.println(ex);
		}
	}
	
	//Vertical Scrolling
	public static void scrollToBottom() {
		try {
			System.out.println("Opearation 1: Scrolling - Start");
			WebElement lastElement = (WebElement) driver.findElement(MobileBy.AndroidUIAutomator(
					"new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().text(\"GitHub\"));"));
			System.out.println("Opearation 1: Scrolling - End");
			Thread.sleep(1000);
		}catch(Exception ex) {
			System.out.println(ex);
		}
	}
	
	//Vertical Scrolling
	public static void swipeRightToLeft(int swipeCount) {
		try {
			System.out.println("Opearation 2: Swiping - Start");
			while(swipeCount > 0) {
				//Do the action of swipe
				new TouchAction((PerformsTouchActions) driver)
				.press(PointOption.point(900, 1600))
				.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
				.moveTo(PointOption.point(150, 1600))
				.release().perform();
				swipeCount--;
			}
			System.out.println("Opearation 2: Swiping - End");
		}catch(Exception ex) {
			System.out.println(ex);
		}
	}
}
