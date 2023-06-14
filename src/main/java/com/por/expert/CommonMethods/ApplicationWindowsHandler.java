package com.por.expert.CommonMethods;

import com.por.expert.BaseSetup.TestBaseSetup;
import org.openqa.selenium.Keys;

import java.util.Set;

public class ApplicationWindowsHandler extends TestBaseSetup {

    //region Switch between Application Windows

    public static void getCurrentlySelectedWindow() {
        String windowTitle = windowsApplicationDriver.getTitle();
        System.out.println("Currently Selected Window: " + windowTitle);
    }

    public static void SwitchToNewWindow(String WindowName) {
        String windowTitle = windowsApplicationDriver.getTitle();
        System.out.println("Window: " + windowTitle);
        if(windowTitle != WindowName) {
            windowsApplicationDriver.switchTo().window(WindowName);
        }

        System.out.println("Switched to Window: " + windowTitle);
    }

    public static void SwitchToContainsWindow(String WindowName) {
        Set<String> windows = windowsApplicationDriver.getWindowHandles();
        java.util.Iterator<String> itr = windows.iterator();
        while (itr.hasNext()) {
            String window = itr.next();
            windowsApplicationDriver.switchTo().window(window);
            if (windowsApplicationDriver.getTitle().contains(WindowName)) {
                System.out.println(WindowName + " window selected");
            }
        }
        String windowTitle = windowsApplicationDriver.getTitle();
        System.out.println("Switched to Window: " + windowTitle);
    }

    public static void SwitchToMatchingWindow(String WindowName) {
        Set<String> windows = windowsApplicationDriver.getWindowHandles();
        java.util.Iterator<String> itr = windows.iterator();
        while (itr.hasNext()) {
            String window = itr.next();
            windowsApplicationDriver.switchTo().window(window);
            if (windowsApplicationDriver.getTitle().matches(WindowName)) {
                // Point of Rental window selected
            }
        }
        String windowTitle = windowsApplicationDriver.getTitle();
        System.out.println(windowTitle);
    }

    //endregion

    //region Enter Operator ID

    public static void EnterID(String ID) {
        // Entering ID
        windowsApplicationDriver.findElementByAccessibilityId("txtPassword").sendKeys(ID + Keys.ENTER);
        System.out.println("Entered ID" +ID);
    }

    //endregion

}
