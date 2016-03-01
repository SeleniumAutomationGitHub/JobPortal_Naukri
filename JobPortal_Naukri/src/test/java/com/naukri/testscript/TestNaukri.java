package com.naukri.testscript;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import com.naukri.pageobject.NaukriLoginPage;
import com.naukri.pageobject.MyNaukriPage;
import com.naukri.utilities.DriverScriptExcel;
import com.naukri.utilities.InitializeDriver;
import com.naukri.utilities.InitializeURL;


public class TestNaukri {
	
	
	NaukriLoginPage nlp;
	MyNaukriPage mnp;
	
  
  @Parameters("browser")
  @BeforeTest
  public void beforeTest(String browser) {
	  
	  InitializeDriver.driver = InitializeDriver.launchBrowser(browser);
	  new InitializeURL().lunchURL("http://www.naukri.com/");
	  
	  nlp = PageFactory.initElements(InitializeDriver.driver, NaukriLoginPage.class);
	  mnp = PageFactory.initElements(InitializeDriver.driver, MyNaukriPage.class);
  }
  
  @DataProvider(name="Authentication")
  public Object[][] credentials() throws Exception{
	  return DriverScriptExcel.getTestData("D:\\Projects\\Automation\\Workspace\\JobPortal_Naukri\\src\\test\\resources\\NaukriTestData.xls", "login");
  }
  
  
  @Test(dataProvider="Authentication", description="Verify the Naukri login page", priority=1, enabled=true)
  public void verifyLoginPage(String username, String passwd) {
	  nlp.loginPage();
	  nlp.jobSeekerLogin(username, passwd);
  }
  
  @Test(priority=2, description="Verify My Naukri Page", enabled=true)
  public void verifyAttachedResume() {
	  mnp.attachedResumeClick();
  }
  
  @Test(priority=3, description="Verify uploading resume", enabled=true)
  public void verifyUploadResume() {
	  mnp.uploadResume();
	  
  }
  
  @Test(priority=4, description="Verify logout from naukri", enabled=true)
  public void verifyLogOut() {
	  mnp.logOut();
  }
  
  @Test(priority=5, description="Verify login again right click", enabled=true)
  public void verifyloginAgain() {
	  mnp.loginAgain();
  }
  
}
