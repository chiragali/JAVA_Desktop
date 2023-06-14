package com.por.expert.NOTEPAD;

import com.aventstack.extentreports.ExtentTest;
import com.por.expert.BaseSetup.TestBaseSetup;
import com.por.expert.HelperMethods.NotepadHelper;
import org.testng.annotations.Test;

public class Notepad extends TestBaseSetup {
	static ExtentTest extentReportLogger;

	@Test(enabled = true)
	public void Notepad() throws Exception, InterruptedException {
		System.out.println("Starting Notepad");
		
		extentReportTest = extentReportLibrary.createTest("Notepad",
				"About Us");

		// Launching EOD exe
		extentReportTest.pass("<span class=\"text-green-bold\" >Notepad</span>");
		extentReportTest.pass(" Launch Notepad ");
		launchApplicationUnderTest("APP_PATH_NOTEPAD");
		
		// Clicking on Yes button
		extentReportTest.pass("<span class=\"text-green-bold\" >Help</span>");
		extentReportTest.pass("Click on 'Help'");
		NotepadHelper.ClickonHelp();

		// Clicking on About Notepad
		extentReportTest.pass("<span class=\"text-green-bold\" >About Notepad</span>");
		extentReportTest.pass("Click on 'About Notepad'");
		NotepadHelper.ClickonAboutNotepad();
		
		// Entering ID as 'demo'
		extentReportTest.pass("<span class=\"text-green-bold\" >OK</span>");
		extentReportTest.pass("Click on 'OK'");
		NotepadHelper.ClickonOk();



	}
}
