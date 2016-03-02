package com.naukri.pageobject;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.naukri.utilities.InitializeDriver;


public class MyNaukriPage {
	
	Actions actObj = new Actions(InitializeDriver.driver);
	
	
	//My Naukri Icon
	@FindBy(xpath=".//*[@id='mainHeader']/div/div/ul[2]/li[2]/a/div[2][@class='mTxt']")
	public static WebElement myNaukriIcon;
	
	//Attached Resume Link
	@FindBy(xpath=".//*[@id='leftNav_updateProfile']/ul/li/a[4]")
	private WebElement attachedResume;
	
	@FindBy(how = How.XPATH, using=".//*[@id='uploadLink']")
	private WebElement uploadResume;
	
	@FindBy(how = How.XPATH, using=".//*[@id='attachCV']")
	private WebElement browseBtn;
	
	@FindBy(xpath=".//*[@id='attachCVMsg']")
	private WebElement successMsg;
	
	@FindBy(xpath=".//*[@id='editForm']/div[7]/button")
	private WebElement saveBtn;
	
	@FindBy(xpath=".//*[@id='confirmMessage']")
	private WebElement profileUpdatedMsg;
	
	@FindBy(xpath=".//*[@id='mainHeader']/div/div/ul[2]/li[2]/div/ul/li[5]/a[@class='logout']")
	private WebElement logOut;
	
	@FindBy(xpath="html/body/div[4]/div[1]/div[1]/div[2]/span")
	private WebElement logOutMsg;
	
	@FindBy(xpath="html/body/div[4]/div[1]/div[1]/div[2]/span/a")
	private WebElement loginAgain;
	
	
	public void attachedResumeClick(){
		try{
			
			if(attachedResume.isDisplayed() && attachedResume.isEnabled()){
				
				//JavascriptExecutor js = (JavascriptExecutor)InitializeDriver.driver;
				//js.executeScript("arguments[0].click", attachedResume);
				
				attachedResume.click();
				InitializeDriver.driver.manage().timeouts().pageLoadTimeout(1000, TimeUnit.SECONDS);
				Assert.assertEquals(InitializeDriver.driver.getTitle(), "Update Profile|Mynaukri", "Not in update page" );
			}
			
			}catch(Exception e){
				System.out.println("Attached Resume link is not available");
				e.printStackTrace();
			}
		}
	
	public void uploadResume(){
		
		WebDriverWait wait = new WebDriverWait(InitializeDriver.driver, 10);
		
		try{
			
			if(uploadResume.isDisplayed() && uploadResume.isEnabled()){
				
				//click on upload new resume link
				uploadResume.click();
				InitializeDriver.driver.manage().timeouts().pageLoadTimeout(1000, TimeUnit.SECONDS);
				
				//click on browse button
				browseBtn.click();
				InitializeDriver.driver.manage().timeouts().pageLoadTimeout(1000, TimeUnit.SECONDS);
				
				Runtime.getRuntime().exec(".\\AutoIT\\selectResume.exe");
				
				wait.until(ExpectedConditions.visibilityOf(successMsg));
				
				Assert.assertEquals(successMsg.getText(), "File uploaded successfully.", "File not uploaded");
				String color = successMsg.getCssValue("color");
				System.out.println("Text color: " + color);
				saveBtn.click();
				InitializeDriver.driver.manage().timeouts().pageLoadTimeout(1000, TimeUnit.SECONDS);
				Assert.assertEquals(profileUpdatedMsg.getText(), "Your naukri profile has been updated with your uploaded resume", "Profile not updated successfully.");
				System.out.println("File uploaded successfully");
			}
			
			}catch(Exception e){
				System.out.println("Upload Resume link is not available");
				e.printStackTrace();
			}
		}
	
	public void logOut(){
		try{
			
			actObj.moveToElement(myNaukriIcon).build().perform();
			Thread.sleep(1000);
			if(logOut.isDisplayed() && logOut.isEnabled()){
				logOut.click();				
				Assert.assertEquals(logOutMsg.getText(), "You have successfully Logged out of naukri.com Login Again", "You have not loggout of naukri.com.");
				System.out.println("Logout Successfully...!");
			}
			
			}catch(Exception e){
				System.out.println("No logout button available");
				e.printStackTrace();
			}
		}
	
	public void loginAgain(){
		try{
			InitializeDriver.driver.manage().timeouts().setScriptTimeout(1000, TimeUnit.SECONDS);
			if(loginAgain.isDisplayed() && loginAgain.isEnabled()){
				//Right click
				actObj.contextClick(loginAgain).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
				
				//Single click
				actObj.click(loginAgain).build().perform();
				System.out.println("Opened in another tab");
				
				//Double click
				//actObj.doubleClick(loginAgain).build().perform();
				//System.out.println("Opened in another tab");
			}
			
			}catch(Exception e){
				System.out.println("Not opened in another tab");
				e.printStackTrace();
			}
		}
		
		
		

}
