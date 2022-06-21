package appiumLearning;

import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;

public class MobileApp {

	//Driver declaration
	public static AppiumDriver driver;
	
	public static void main(String[] args) {
		try {
			OpenApp();
		}catch(Exception ex) {
			System.out.println(ex);
		}
	}
	
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
			driver = new AppiumDriver(url, capability);
			
			System.out.println("Application started...");
		}catch(Exception ex) {
			System.out.println(ex);
		}
	}

}
