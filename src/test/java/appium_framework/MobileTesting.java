package appium_framework;

import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.MobileBy;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

@SuppressWarnings("deprecation")
public class MobileTesting {
	
	//Driver declaration
	public static DesiredCapabilities capability;
	public static AndroidDriver driver;
	public static ExtentReports report;
	public static ExtentTest test;
	
	@BeforeClass
	public void beforeClass() {
		try {
			//Start the extent report objects
			report = new ExtentReports(System.getProperty("user.dir")+"\\ExtentReport\\MobileTesting_ExtentReportResults.html");
			test = report.startTest("Mobile Test Automation");
			
			//Add desired capabilities
			capability = new DesiredCapabilities();
			capability.setCapability("deviceName", "emulator-5554");
			capability.setCapability("udid", "emulator-5554");
			capability.setCapability("appPackage", "com.wdiodemoapp");
			capability.setCapability("noReset", "true");
			capability.setCapability("autoGrantPermission", "true");
			capability.setCapability("platformName", "Android");
			capability.setCapability("appActivity", "com.wdiodemoapp.MainActivity");
			test.log(LogStatus.PASS, "Loaded the desired capabilities.");
		}catch(Exception ex) {
			test.log(LogStatus.FAIL, "BeforeClass failure: " + ex);
		}
	}
	
	@BeforeMethod
	public void beforeMethod() {
		try {
			URL url = new URL("http://localhost:4723/wd/hub");
			driver = new AndroidDriver(url, capability);
			test.log(LogStatus.PASS, "Application started");
		}catch(Exception ex) {
			test.log(LogStatus.FAIL, "BeforeMethod failure: " + ex);
		}
	}
		
	@Test
	public void scrollToBottom() {
		try {
			driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Webview\"]")).click();
			Thread.sleep(2000);
			WebElement lastElement = (WebElement) driver.findElement(MobileBy.AndroidUIAutomator(
					"new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().text(\"GitHub\"));"));
			test.log(LogStatus.PASS, "scrollToBottom: Successful");
		}catch(Exception ex) {
			test.log(LogStatus.FAIL, "scrollToBottom failure: " + ex);
		}
	}
	
	@Test
	public void swipeRightToLeft() {
		try {
			driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Swipe\"]")).click();
			Thread.sleep(2000);
			int swipeCount = 5;
			while(swipeCount > 0) {
				//Do the action of swipe
				new TouchAction((PerformsTouchActions) driver)
				.press(PointOption.point(900, 1600))
				.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
				.moveTo(PointOption.point(150, 1600))
				.release().perform();
				swipeCount--;
			}
			test.log(LogStatus.PASS, "swipeRightToLeft: Successful");
		}catch(Exception ex) {
			test.log(LogStatus.FAIL, "swipeRightToLeft failure: " + ex);
		}
	}
	
	@AfterMethod
	public void afterMethod() {
		try {
			driver.quit();
			test.log(LogStatus.PASS, "Application stopped.");
		}catch(Exception ex) {
			test.log(LogStatus.FAIL, "afterMethod failure: " + ex);
		}
	}
	
	@AfterClass
	public void afterClass() {
		try {
			test.log(LogStatus.PASS, "All the operations are completed.");
			report.endTest(test);
			report.flush();
		}catch(Exception ex) {
			test.log(LogStatus.FAIL, "afterClass failure: " + ex);
		}
	}

}
