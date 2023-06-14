package com.por.expert.ReportsManager;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;

public class ExtentManager {

    static ExtentSparkReporter htmlSparkReporter;
    public static ExtentReports extent;

    public static synchronized ExtentTest startTest(String testName) {
        return extent.createTest(testName);
    }

    public static ExtentReports createInstance(String fileName) {
        if (extent == null) {
            htmlSparkReporter = new ExtentSparkReporter(fileName);
            extent = new ExtentReports();
            extent.attachReporter(htmlSparkReporter);
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("OS Architecture", System.getProperty("os.arch"));
            extent.setSystemInfo("OS Version", System.getProperty("os.version"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
            extent.setSystemInfo("Java Runtime", System.getProperty("java.runtime.version"));

            htmlSparkReporter.config().setTheme(Theme.STANDARD);
            htmlSparkReporter.config().setDocumentTitle("POR QA Automation");
            htmlSparkReporter.config().setEncoding("utf-8");
            htmlSparkReporter.config().enableOfflineMode(false);
            htmlSparkReporter.config().setReportName("Rental Expert Test Results");
            htmlSparkReporter.config().thumbnailForBase64(true);
            htmlSparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
            htmlSparkReporter.config().setCss("" +
                    ".text-green-bold " +
                    "{ " +
                        "color: #008000; " +
                        "font-weight: bold; " +
                    "}");
        }
        return extent;
    }
}