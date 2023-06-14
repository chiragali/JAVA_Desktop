package com.por.expert.HelperMethods;

import com.por.expert.CommonMethods.ApplicationWindowsHandler;
import com.por.expert.UtilitiesRepository.CustomUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotepadHelper extends ApplicationWindowsHandler {

	static CustomUtilities getUtility = new CustomUtilities();

	// This method is used to wait till yes button is clickable
	public static void ClickonHelp() {
		waitTillElementPresentByName("Help");
		clickByName("Help", "Clicking on Help");
	}

	public static void ClickonAboutNotepad() {
		waitTillElementPresentByName("About Notepad");
		clickByName("About Notepad", "Clicking on About Notepad");
	}

	public static void ClickonOk() {
		waitTillElementPresentByName("OK");
		clickByName("OK", "Clicking on OK");
	}

}