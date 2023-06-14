package com.por.expert.NOTEPAD;

import java.net.URL;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import io.appium.java_client.windows.WindowsDriver;
import io.appium.java_client.windows.WindowsElement;

public class NotepadAutomation {

	public static void main(String[] args) throws Exception {


		String random = RandomStringUtils.randomAlphabetic(5);

		System.out.println("Random Number: " + random);

		WindowsDriver driver = null;

		// Set the capabilities
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("app", "C:\\Windows\\System32\\notepad.exe");
		//capabilities.setCapability("deviceName", "");
		capabilities.setCapability("platformName", "Windows");

		// Create a new WinApp driver instance
		driver = new WindowsDriver<WindowsElement>(new URL("http://127.0.0.1:4723/"), capabilities);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		// Wait for the Notepad application to launch
		Thread.sleep(2000);

		driver.findElementByName("Help").click();
		driver.findElementByName("About Notepad").click();
		driver.findElementByName("OK").click();
		// Find the text field element and enter some text
		// WindowsElement textField = driver.findElementByName("Text Editor");
		// textField.sendKeys("Hello World!");

		// Save the file
//        driver.findElementByName("File").click();
//        Thread.sleep(1000);
//        driver.findElementByName("Save As...").click();
//        Thread.sleep(2000);
//        driver.findElementByName("File name:").sendKeys("test.txt");
//        driver.findElementByName("Save").click();

		// Close the Notepad application
		driver.findElementByName("Close").click();
		driver.quit();
	}
}
