package delta.main;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

import com.relevantcodes.extentreports.LogStatus;

import generics.Excel;
import generics.Property;
import generics.Utility;

public class DeltaDriver extends BaseDriver{
	
	public String browser;
	public String hubURL;
	@BeforeMethod
	public void launchApp(XmlTest xmltest) throws MalformedURLException
	{
		 browser=xmltest.getParameter("browser");
		 hubURL=xmltest.getParameter("hubURL");
		 DesiredCapabilities dc= new DesiredCapabilities();
		 dc.setBrowserName(browser);
		 dc.setPlatform(Platform.ANY);
		 driver= new RemoteWebDriver(new URL(hubURL), dc);
		
			
		String appURL= Property.getPropertyValue(configPptPath, "URL");
		String TimeOut = Property.getPropertyValue(configPptPath, "TimeOut");
		
		
		driver.get(appURL);
		driver.manage().timeouts().implicitlyWait(Long.parseLong(TimeOut),TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	
	@Test(dataProvider="getScenario")
	public void testScenarios(String scenarioSheet, String executionStatus) throws InterruptedException
	{
		
		testReport = eReport.startTest(browser+"_"+scenarioSheet);
		if(executionStatus.equalsIgnoreCase("yes"))
		{
		int stepCount = Excel.getRowCount(scenariosPath, scenarioSheet);
		for (int i = 1; i <= stepCount; i++) {
						
			String description=Excel.getCellValue(scenariosPath, scenarioSheet, i, 0);
			String action=Excel.getCellValue(scenariosPath, scenarioSheet, i, 1);
			String input1=Excel.getCellValue(scenariosPath, scenarioSheet, i, 2);
			String input2=Excel.getCellValue(scenariosPath, scenarioSheet, i, 3);
			String msg="Description: "+description+" Action: "+action+" Input1: "+input1+" Input2: "+input2;
			System.out.println(description+action+input1+input2);
			testReport.log(LogStatus.INFO, msg);
			Keyword.executeKeyWord(driver, action, input1, input2);
			
		
		}
		}
		else
		{
			testReport.log(LogStatus.SKIP, "Execution Status is 'No'");
			throw new SkipException("Skipping this scenario because the executionStatus in No");
			
		}
		
			
	}
	
	@AfterMethod
	public void quitApp(ITestResult test)
	{
		if(test.getStatus()==2)
			//(test.getStatus() ==ITestResult.FAILURE)
		{
			String pImage=Utility.getPageScreenShot(driver, imageFolderPath);
			String p = testReport.addScreenCapture("."+pImage);
			testReport.log(LogStatus.FAIL, "Page screenshot: "+p);
		}
		driver.close();
		eReport.endTest(testReport);
	}
	
	

}
