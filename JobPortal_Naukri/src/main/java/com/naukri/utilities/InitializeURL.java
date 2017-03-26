package com.naukri.utilities;


public class InitializeURL {

	public void lunchURL(String url){
		if (url!= null){
			InitializeDriver.driver.manage().window().maximize();
			InitializeDriver.driver.get(url);
		}
	}
	
	public void lunchURL(String url, int abc){
		if (url!= null){
			InitializeDriver.driver.manage().window().maximize();
			InitializeDriver.driver.get(url);
		}
	}
}
