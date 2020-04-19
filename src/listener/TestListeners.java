package listener;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.google.common.io.Files;

import testCases.HybridExecuteTest;

public class TestListeners implements ITestListener {

	private WebDriver webdriver;
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		try
		{
//			this.webdriver = ((HybridExecuteTest)result.getInstance()).webdriver; // getting instance of WebDriver
			// screenshot code.
			File src = ((TakesScreenshot)webdriver).getScreenshotAs(OutputType.FILE);

			// for unique name of screenshot.
			String file_name = result.getMethod().getMethodName() + new SimpleDateFormat("MM-dd-yyyy_HH-ss").format(new GregorianCalendar().getTime());
			
			String path = System.getProperty("user.dir") + "\\screenshots" + file_name + ".PNG";
			
			File dest = new File(path);
			
			Files.copy(src, dest);
		}
		catch(Exception ex)
		{
			System.out.println("Error from Test Listener onTestFailure screenshot method = " + ex.getMessage());
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
