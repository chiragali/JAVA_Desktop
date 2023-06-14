package com.por.expert.UtilitiesRepository;

import io.appium.java_client.windows.WindowsDriver;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CustomUtilities {
    public Desktop localEnvironment;
    private final Logger log = LogManager.getLogger(CustomUtilities.class.getName());

    //region Win App Driver - Start and Stop

    //Start Windows Application Driver (WinAppDriver.exe located in C: Drive)
    public void startWinAppDriverServer() {
        if (Desktop.isDesktopSupported()) {
            String driverPath = getApplicationProperty("DRIVER_PATH");

            try {
                localEnvironment = Desktop.getDesktop();
                localEnvironment.open(new File(driverPath));
                log.debug("WinAppDriver.exe was started successfully");
            } catch (IOException e) {
                log.error("WinAppDriver.exe could not be started");
                e.printStackTrace();
            }
        } else {
            log.error("Desktop class not supported on this platform");
        }
    }

    // Stop Windows Application Driver (WinAppDriver.exe) running in background
    public void stopWinAppDriverServer() {
        try {
            Runtime.getRuntime().exec("TASKKILL /F /IM WinAppDriver.exe");
            log.debug("WinAppDriver.exe was killed");
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("WinAppDriver.exe could not be killed");
        }
    }

    //endregion

    //region Press - Down Arrow
    public void downArrow(WindowsDriver<?> driver) {
        try {
            Thread.sleep(500);
            Robot r = new Robot();
            r.keyPress(KeyEvent.VK_DOWN);
            r.keyRelease(KeyEvent.VK_DOWN);
            log.debug("Pressed Down arrow key");
        } catch (Exception e) {
            log.error("Down arrow key could not be pressed");
            e.printStackTrace();
        }
    }
    //endregion

    //region Press - Up Arrow
    public void upArrow(WindowsDriver<?> driver) {
        try {
            Thread.sleep(500);
            Robot r = new Robot();
            r.keyPress(KeyEvent.VK_UP);
            r.keyRelease(KeyEvent.VK_UP);
            log.debug("Pressed Down arrow key");
        } catch (Exception e) {
            log.error("Up arrow key could not be pressed");
            e.printStackTrace();
        }
    }

    //endregion

    //region Press - Enter
    public void enterKey(WindowsDriver<?> driver) {
        try {
            Thread.sleep(500);
            Robot r = new Robot();
            r.keyPress(KeyEvent.VK_ENTER);
            r.keyRelease(KeyEvent.VK_ENTER);
            log.debug("Pressed Enter key");
        } catch (Exception e) {
            log.error("Enter key could not be pressed");
            e.printStackTrace();
        }
    }

    //endregion

    //region Press - Tab
    public void tabKey(WindowsDriver<?> driver) {
        try {
            Thread.sleep(500);
            Robot r = new Robot();
            r.keyPress(KeyEvent.VK_TAB);
            r.keyRelease(KeyEvent.VK_TAB);
            log.debug("Pressed Tab key");
        } catch (Exception e) {
            log.error("Tab key could not be pressed");
            e.printStackTrace();
        }
    }

    //endregion

    //region Enter DEMO
    public static void robotKeys(WindowsDriver<?> driver) {
        try {
            Thread.sleep(500);
            Robot r = new Robot();
            r.keyPress(KeyEvent.VK_D);
            r.keyRelease(KeyEvent.VK_D);
            r.keyPress(KeyEvent.VK_E);
            r.keyRelease(KeyEvent.VK_E);
            r.keyPress(KeyEvent.VK_M);
            r.keyRelease(KeyEvent.VK_M);
            r.keyPress(KeyEvent.VK_O);
            r.keyRelease(KeyEvent.VK_O);
            System.out.println("Entered DEMO");
        } catch (Exception e) {
            System.out.println("Cannot enter text");
            e.printStackTrace();
        }
    }

    //endregion

    //region Retrieve data from application.properties
    public String getApplicationProperty(String propertyKey) {
        Properties props = new Properties();

        try {
            InputStream appPropFile = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/configs/application.properties");
            props.load(appPropFile);
            log.debug("The 'application.properties' file was loaded");
        } catch (IOException e) {
            log.error("*** The 'application.properties' file was not found");
            e.printStackTrace();
        }
        String result = props.getProperty(propertyKey);
        log.debug("Retrieved the value of " + propertyKey + " from 'application.properties' file");
        return String.valueOf(result);
    }

    //endregion

    //region Print Screen Utilities

    // Get Screenshot using Windows Driver
    public static String captureScreenshot(WindowsDriver driver, String fileName) throws IOException {
        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destination = new File("./test-results/screenshots/" + fileName + ".png");
        String filepath = destination.getAbsolutePath();
        FileUtils.copyFile(source, destination);
        System.out.println("--------destination path-------" + filepath);
        return filepath;
    }

    // Get Screenshot using Web Driver
    public static String takeWebScreenshot(WebDriver driver, String SSName) throws IOException {
        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destination = new File("./test-results/screenshots/" + SSName + ".png");
        String filepath = destination.getAbsolutePath();
        FileUtils.copyFile(source, destination);
        System.out.println("--------destination path-------" + filepath);
        return filepath;
    }
    
    public static void loadWaitTimer(long waitTime) throws InterruptedException {
        System.out.println("Loading in progress ...");
        Thread.sleep(waitTime);
        System.out.println("Load complete!");
    }

    // Get base64 Screenshot using Web Driver
    public static String takeBase64Screenshot(WebDriver driver) throws IOException {
        String base64Img = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        return base64Img;
    }
    
    //endregion
}
