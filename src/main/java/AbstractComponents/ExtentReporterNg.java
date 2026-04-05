package AbstractComponents;
import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNg {

	public static ExtentReports getReportObject() {
		
		String path = System.getProperty("user.dir")+"/reports/index.html";
		
		// Create reports folder if it doesn't exist
		File reportsDir = new File(System.getProperty("user.dir") + "/reports");
		reportsDir.mkdirs();
		
		ExtentSparkReporter reporter =new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Shiva Reddy");
		return extent;
	}
	
}
