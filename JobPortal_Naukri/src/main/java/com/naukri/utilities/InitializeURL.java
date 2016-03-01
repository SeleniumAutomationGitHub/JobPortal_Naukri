package com.naukri.utilities;


public class InitializeURL {

	public void lunchURL(String url){
		InitializeDriver.driver.manage().window().maximize();
		InitializeDriver.driver.get(url);
	}
}
