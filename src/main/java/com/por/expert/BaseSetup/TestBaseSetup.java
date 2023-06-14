package com.por.expert.BaseSetup;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.por.expert.ReportsManager.ExtentManager;
import com.por.expert.UtilitiesRepository.CustomUtilities;

import io.appium.java_client.windows.WindowsDriver;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

public class TestBaseSetup {
    @SuppressWarnings("rawtypes")
    public static WindowsDriver windowsApplicationDriver;
    public static DesiredCapabilities driverCapabilities;
    CustomUtilities getUtility = new CustomUtilities();
    protected static ExtentReports extentReportLibrary;
    private static ThreadLocal test = new ThreadLocal();
    public static ExtentTest extentReportTest;

    //region Call WinAppDriver ON/OFF

    public void launchWinAppDriver() {
        System.out.println("Starting WinAppDriver.exe server");
        getUtility.startWinAppDriverServer();
    }

    public void closeWinAppDriver() {
        System.out.println("Starting WinAppDriver.exe server");
        getUtility.stopWinAppDriverServer();
    }

    //endregion

    //region Launch Application Under Test
    /***
     * Launch the Application Under Test
     * @throws MalformedURLException - Thrown when URL is invalid
     * @throws InterruptedException - Thrown when a thread is waiting, sleeping, or otherwise occupied, and the thread is interrupted
     */
    @SuppressWarnings("rawtypes")
    public void launchApplicationUnderTest(String applicationFolderPath) throws InterruptedException, MalformedURLException {
        String folderPath = getUtility.getApplicationProperty(applicationFolderPath);
        String applicationName = applicationFolderPath.replace("APP_PATH_","").replace("_"," ");
        driverCapabilities = new DesiredCapabilities();
        driverCapabilities.setCapability("app", folderPath);
        windowsApplicationDriver = new WindowsDriver(new URL(getUtility.getApplicationProperty("DRIVER_URL")), driverCapabilities);
        windowsApplicationDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Driver configurations initialized!");
        System.out.println(applicationName + " launched from " + folderPath + "!");
    }
    
    @SuppressWarnings("rawtypes")
    public void launchApplicationUnderTestLongWait(String applicationFolderPath) throws MalformedURLException, InterruptedException {
        String folderPath = getUtility.getApplicationProperty(applicationFolderPath);
        String applicationName = applicationFolderPath.replace("APP_PATH_","").replace("_"," ");
        driverCapabilities = new DesiredCapabilities();
        driverCapabilities.setCapability("app", folderPath);
        driverCapabilities.setCapability("ms:waitForAppLaunch", "20");
		windowsApplicationDriver = new WindowsDriver(new URL(getUtility.getApplicationProperty("DRIVER_URL")), driverCapabilities);
		windowsApplicationDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(5000);
        System.out.println("Driver configurations initialized!");
        System.out.println(applicationName + " launched from " + folderPath + "!");
    }

    //endregion

    //region WinAppDriver Element Locator Strategy - By Accessibility ID

    // Find and click element by AccessibilityID. Print element name in console
    public static void clickByAccessibilityID(String Name, String description) {
        try {
        	//waitTillElementIsClickable();
            windowsApplicationDriver.findElementByAccessibilityId(Name).click();
            System.out.println("Clicked on: " + description);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("*** Fail - " + description);
            Assert.fail();
        }
    }

    // Find element and enter data by AccessibilityID. Print element name in console
    public static void clickAndSendKeysByAccessibilityID(String AccessibilityID, String KeysToSend, String description) {
        try {
            //waitAndClick.waitTillElementIsClickable(name);
            windowsApplicationDriver.findElementByAccessibilityId(AccessibilityID).sendKeys(KeysToSend);
            System.out.println("Clicked on: " + description);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Send Keys Failed on " + AccessibilityID + " - " + description);
            Assert.fail();
        }
    }


    
    //endregion

    //region WinAppDriver Element Locator Strategy - By Class Name

    // Find and click element by ClassName. Print element name in console
    public void clickByClassName(String className, String description) {
        try {
            windowsApplicationDriver.findElementByClassName(className).click();
            System.out.println("Clicked on: " + description);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("*** Fail - " + description);
            Assert.fail();
        }
    }

    //Find element by Class Name and enter data. Print element name in console
    public void enterTextByClassName(String className, String keyboardInputData, String description) {
        try {
            windowsApplicationDriver.findElementByClassName(className).sendKeys(keyboardInputData);
            System.out.println(description);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("*** Fail - Error while entering text");
            Assert.fail();
        }
    }

    //endregion

    //region WinAppDriver Element Locator Strategy - By ID

    // Find element by Class Name and enter data. Print element name in console
    public void enterTextByID(String className, String keyboardInputData, String description) {
        try {
            windowsApplicationDriver.findElementById(className).sendKeys(keyboardInputData);
            System.out.println(description);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed when entering " + keyboardInputData + "  to element " + className);
            Assert.fail();
        }
    }

    //endregion

    //region WinAppDriver Element Locator Strategy - By Name

    //Find and click element by Name. Print element name in console
    public static void clickByName(String Name, String description) {
        try {
//            waitTillElementIsClickable(Name);
            windowsApplicationDriver.findElementByName(Name).click();
            Thread.sleep(3000);
            System.out.println("Clicked on: " + description);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("*** Fail - " + description);
            Assert.fail();
        }
    }

    // Find element by Name and enter data
    public void clickAndSendKeysByName(String Name, String KeysToSend, String description) {
        try {
            //waitTillElementIsClickable(Name);
            windowsApplicationDriver.findElementByName(Name).sendKeys(KeysToSend);
            System.out.println("Clicked on: " + description);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("*** Fail - " + description);
            Assert.fail();
        }
    }

    //endregion

    //region WinAppDriver Element Locator Strategy - By XPath

    // Find and click element by XPath
    public void clickByXPath(String elementXPath, String description) {
        try {
            waitTillElementIsClickable(elementXPath);
            windowsApplicationDriver.findElement(By.xpath(elementXPath)).click();
            System.out.println("Clicked on: " + description);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("*** Fail - " + description);
            Assert.fail();
        }
    }

    // Find element by Name and enter data
    public void enterTextByXPath(String name, String keyboardInputData, String description) {
        try {
            windowsApplicationDriver.findElement(By.xpath(name)).clear();
            windowsApplicationDriver.findElement(By.xpath(name)).sendKeys(keyboardInputData);
            System.out.println(description);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("*** Fail - Error while entering text");
            Assert.fail();
        }
    }

    // Verify if an element exists by XPath
    public boolean existsWithMessage(String elementXPath, String description) {
        try {
            waitTillElementPresent(elementXPath);
            Boolean status = windowsApplicationDriver.findElement(By.xpath(elementXPath)).isDisplayed();
            if (status) {
                System.out.println("Validated that " + description + " is displayed");
                return true;
            } else {
                System.out.println(description + " is not Displayed");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("*** Fail - Error while checking existence of element " + description);
            return false;
        }
    }

    // Check if an element exists by XPath
    public boolean exists(String elementXPath) {
        try {
            waitTillElementPresent(elementXPath);
            Boolean status = windowsApplicationDriver.findElement(By.xpath(elementXPath)).isDisplayed();
            if (status) {
//                System.out.println("Validated that " + description + " is displayed");
                return true;
            } else {
//                System.out.println(description + " is not Displayed");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Not found: " + elementXPath);
            return false;
        }
    }

    //endregion

    //region Locate Element with Wait - By XPath

    // Wait till element becomes clickable
    public static void waitTillElementIsClickable(String elementXPath) {
        try {
            WebDriverWait wait = new WebDriverWait(windowsApplicationDriver, 3);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementXPath)));
            System.out.println("Waited for clickability of: " + elementXPath);            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("*** Fail - Element was not found to be clickable");
        }
    }

    // Wait till element is available
    public static void waitTillElementPresent(String elementXPath) {
        try {
            WebDriverWait wait = new WebDriverWait(windowsApplicationDriver, 10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXPath)));
            System.out.println("Waited for visibility of: " + elementXPath);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("*** Fail - Element was not present");
        }
    }
    
    //endregion

    //region Call Other Utilities

    // Robot library - Press Down Arrow
    public void pressDownArrow() {
        System.out.println("Pressing Down arrow key");
        getUtility.downArrow(windowsApplicationDriver);
    }

    // Robot library - Press Up Arrow
    public void pressUpArrow() {
        System.out.println("Pressing Up arrow key");
        getUtility.upArrow(windowsApplicationDriver);
    }

    // Robot library - Press Tab
    public void pressTabKey() {
        System.out.println("Pressing Tab key");
        getUtility.tabKey(windowsApplicationDriver);
    }

    // Robot library - Press Enter
    public void pressEnterKey() {
        System.out.println("Pressing Enter key");
        getUtility.enterKey(windowsApplicationDriver);
    }

    // Get key:value data from test/resources/configs/application.properties file
    public String getProperty(String property_key) {
        System.out.println("Getting application property value: " + property_key);
        return getUtility.getApplicationProperty(property_key);
    }

    //endregion
    

    //region Screenshot Helpers

    public void testPassScreenshot(WindowsDriver driver, String testStepMessage, String filename) throws IOException {
        extentReportTest.log(Status.PASS, testStepMessage,
                MediaEntityBuilder.createScreenCaptureFromPath(CustomUtilities.takeWebScreenshot(driver, filename)).build());
//        extentReportTest.log(Status.PASS, testStepMessage,
//                MediaEntityBuilder.createScreenCaptureFromBase64String(CustomUtilities.takeBase64Screenshot(driver)).build());
    }

    public void testFailScreenshot(WindowsDriver driver, String testStepMessage, String filename) throws IOException {
//        extentReportTest.log(Status.FAIL, testStepMessage,
//                MediaEntityBuilder.createScreenCaptureFromPath(CustomUtilities.takeWebScreenshot(driver, filename)).build());
        extentReportTest.log(Status.FAIL, testStepMessage,
        		MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshotbase64()).build());
    }

    //endregion

    //region TestNG - Before Annotations

    @BeforeSuite(alwaysRun = true)
    public void setup() throws MalformedURLException, InterruptedException {
        extentReportLibrary = ExtentManager.createInstance("./test-results/sparkReports/Expert Test Results.html");
        launchWinAppDriver();
    }

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
    	System.out.println(""); 
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(Method method) {
  //      ExtentTest childExtentTest = ((ExtentTest) test.get()).createNode(method.getName());
  //      extentReportTest.createNode(method.getName());
        System.out.println("");
    }

    //endregion

    //region TestNG - After Annotations

    @AfterMethod
    public void AfterMethod(ITestResult result) throws IOException {

        if (result.getStatus() == ITestResult.FAILURE) {
        	//String exceptionErrorMessage = result.getThrowable().getMessage();
            // String exceptionErrorLine1 = exceptionErrorMessage.substring(0,exceptionErrorMessage.indexOf("("));
        	extentReportTest.log(Status.FAIL,
                    MarkupHelper.createLabel(result.getName()
                            + " Test case FAILED due to below issues:",
                            ExtentColor.RED));
        	extentReportTest.log(Status.FAIL,MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshotbase64()).build());
        	extentReportTest.fail(result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
        	extentReportTest.log(
                    Status.PASS,
                    MarkupHelper.createLabel(result.getName()
                            + " Test Case PASSED", ExtentColor.GREEN));
        } else {
        	extentReportTest.log(
                    Status.SKIP,
                    MarkupHelper.createLabel(result.getName()
                            + " Test Case SKIPPED", ExtentColor.ORANGE));
        	extentReportTest.skip(result.getThrowable());
        }
    }

    @AfterTest
    public void AfterTest() {

    	extentReportLibrary.flush();

    }
 //   
    
    @AfterSuite(alwaysRun = true)
    public void tearDown() throws IOException {
        System.out.println("Stopping WinAppDriver.exe server");
        windowsApplicationDriver.quit();
        getUtility.stopWinAppDriverServer();
        extentReportLibrary.flush();
    }

    //endregion

    
    // Wait till element is available
    public static void waitTillElementPresentByName(String elementName) {
        try {
            WebDriverWait wait = new WebDriverWait(windowsApplicationDriver, 10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(elementName)));
            System.out.println("Waited for visibility of: " + elementName);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("*** Fail - Element was not present");
        }
    }
    
    // This method is used to clear texts in text box
    public static void ClearTexts(String AccessibilityID) {
    	windowsApplicationDriver.findElementByAccessibilityId(AccessibilityID).clear();
    }
    
    // Get Screenshot using Web Driver
    public static String takeScreenshotbase64() throws IOException {
    	TakesScreenshot takesScreenshot = ((TakesScreenshot)windowsApplicationDriver);
    	File srcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
    	String path = System.getProperty("user.dir") + "\\test-results\\sparkReports\\image.png";
    	File descFile = new File(path);
    	FileUtils.copyFile(srcFile, descFile);
    	InputStream is = new FileInputStream(path);
    	byte[] ssBytes = IOUtils.toByteArray(is);
    	String base64 = Base64.getEncoder().encodeToString(ssBytes);
    	//extentReportTest.addScreenCaptureFromBase64String(base64);
		return base64;
    }
}
