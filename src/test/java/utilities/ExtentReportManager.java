package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.plaf.synth.SynthOptionPaneUI;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {
public ExtentSparkReporter sparkReporter; //UI of the report
public ExtentTest test; //populate common info on the report
public ExtentReports extent; //creating testcase entries in the report and update status of the test methods
String repName;

public void onStart(ITestContext testcontext) {
	  
	System.out.println("in report..");
	String timestamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	repName="Test-Report"+timestamp+".html";
	System.out.println(repName);
	 sparkReporter = new ExtentSparkReporter(".\\reports\\"+repName);
	
	 
	 sparkReporter.config().setDocumentTitle("Opencart Automation Report");
	 sparkReporter.config().setReportName(" Opencart Functional Testing");
	 sparkReporter.config().setTheme(Theme.DARK);
	 
	 extent=new ExtentReports();
	 extent.attachReporter(sparkReporter);
	 
	 extent.setSystemInfo("Application", "Opencart");
	 extent.setSystemInfo("Module", "Admin");
	 extent.setSystemInfo("Sub Module", "Customers");
	 extent.setSystemInfo("user Name", System.getProperty("user.name"));
	 extent.setSystemInfo("Environment", "QA");
	 System.out.println("........");
	 String os=testcontext.getCurrentXmlTest().getParameter("os");
	 extent.setSystemInfo("Operating System", os);
	 
	 String browser=testcontext.getCurrentXmlTest().getParameter("browser");
	 extent.setSystemInfo("Browser", browser);
	 List<String>includedGroups=testcontext.getCurrentXmlTest().getIncludedGroups();
	 if(!includedGroups.isEmpty())
	 {
		 extent.setSystemInfo("Groups", includedGroups.toString()); 
	 }
	  }
public void onTestSuccess(ITestResult result) {
       test=extent.createTest(result.getTestClass().getName()); //creates new entry in report
       test.assignCategory(result.getMethod().getGroups());
       test.log(Status.PASS, result.getName()+" got executed Succesfully");// update status pass/fail/skip
  }

public void onTestFailure(ITestResult result) {
	 test=extent.createTest(result.getTestClass().getName()); //creates new entry in report
     test.assignCategory(result.getMethod().getGroups());
     test.log(Status.FAIL, result.getName()+" got failed");
     test.log(Status.INFO,result.getThrowable().getMessage());
     try
     {
    	 String imgpath=new BaseClass().captureScreen(result.getName());
         	 test.addScreenCaptureFromPath(imgpath);
     }catch(IOException e1)
     {
    	 e1.printStackTrace();
     }
	  }
 public void onTestSkipped(ITestResult result) {
	 test=extent.createTest(result.getTestClass().getName()); //creates new entry in report
     test.assignCategory(result.getMethod().getGroups());
     test.log(Status.SKIP, result.getName()+"got Skipped");
     test.log(Status.INFO,result.getThrowable().getMessage());
	  }
public void onFinish(ITestContext testcontext)
{
	System.out.println("in flush");
	extent.flush();
	
	String pathofextentreport=System.getProperty("user.dir")+"\\reports\\"+repName;
	File extenetReport=new File(pathofextentreport);
	try {
		Desktop.getDesktop().browse(extenetReport.toURI());
	}
	catch(IOException e1)
    {
   	 e1.printStackTrace();
    }
	
	
}

	
}

