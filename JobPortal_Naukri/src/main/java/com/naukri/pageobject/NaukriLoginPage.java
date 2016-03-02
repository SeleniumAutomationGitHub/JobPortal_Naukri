package com.naukri.pageobject;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.naukri.utilities.InitializeDriver;


public class NaukriLoginPage {

	private WebDriverWait wdw = new WebDriverWait(InitializeDriver.driver, 15);
	
	@FindBy(xpath=".//*[@id='login_Layer']/span")
	private WebElement loginIcon;
	
	@FindBy(xpath=".//*[@id='eLogin']")
	private WebElement emailID;
	
	
	@FindBy(xpath=".//*[@id='pLogin']")
	private WebElement password;
	
	@FindBy(xpath=".//*[@id='lgnFrm']/div[7]/button[@value='Login']")
	private WebElement loginBtn;
	
	public void loginPage(){
		
		try{
			closeChildWindows();
			InitializeDriver.driver.manage().timeouts().setScriptTimeout(1000, TimeUnit.SECONDS);
			if(InitializeDriver.driver.getTitle() !=null)
			{
				wdw.until(ExpectedConditions.visibilityOf(loginIcon));
				Assert.assertEquals((InitializeDriver.driver.getTitle()), "Jobs - Recruitment - Job Search - Employment - Job Vacancies - Naukri.com", "Login Title validation failed");
			}else{
				System.out.println("URL title can't be blank.");					
			}
		}catch(Exception e){
			System.out.println("URL is not correct");
		}
	}
	
	public void closeChildWindows()throws Exception{
		
		String parentWindow = InitializeDriver.driver.getWindowHandle();
		Set<String> allWindows = InitializeDriver.driver.getWindowHandles();
		
		if(allWindows.size()>0){
			
			for(String eachWindow:allWindows){
				
				InitializeDriver.driver.switchTo().window(eachWindow);
				
				switch(InitializeDriver.driver.getTitle())
				{
					case "Epicor":
					{
						InitializeDriver.driver.close();
						break;
					}
				
					case "Incedo":
					{
						InitializeDriver.driver.close();
						break;
					}
				
					case "Cognizant":
					{
						InitializeDriver.driver.close();
						break;
					}
				
					case "Prokarma":
					{
						InitializeDriver.driver.close();
						break;
					}
					
					case "Amazon":
					{
						InitializeDriver.driver.close();
						break;
					}
					
					case "Alstom":
					{
						InitializeDriver.driver.close();
						break;
					}
				}
			}
			
			//Switch back to parent window
			InitializeDriver.driver.switchTo().window(parentWindow);
		}
	}
	
	public void jobSeekerLogin(String username, String passwd){
		
		try{
			loginIcon.click();
			if(username !=null && !username.equals("") && passwd !=null && !passwd.equals("")  ){
				emailID.clear();
				emailID.sendKeys(username);
				password.clear();
				password.sendKeys(passwd);
				
				if(loginBtn.isDisplayed() && loginBtn.isEnabled()){
					loginBtn.click();
					wdw.until(ExpectedConditions.visibilityOf(MyNaukriPage.myNaukriIcon));
					Assert.assertEquals(InitializeDriver.driver.getTitle(), "Mynaukri", "Failed login page");
					System.out.println("Login Successfully...!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}	
