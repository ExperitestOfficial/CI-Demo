package com.experitest.auto;

import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;


public class IOSTest  {

	private String testName = "Demo iOS";
    protected IOSDriver<IOSElement> driver = null;
    protected DesiredCapabilities dc = new DesiredCapabilities();

	@BeforeTest
	@Parameters("deviceQuery")
	public void setUp(@Optional("@os='ios'") String deviceQuery) throws Exception{
        dc.setCapability("testName", testName);
		dc.setCapability("deviceQuery", deviceQuery);
		dc.setCapability("reportDirectory", "reports");
		dc.setCapability("reportFormat", "xml");
		dc.setCapability("build.number", System.getenv("BUILD_NUMBER"));
		dc.setCapability("accessKey", System.getenv("accessKey")); 
	    dc.setCapability(MobileCapabilityType.APP, "cloud:com.experitest.ExperiBank");
	    dc.setCapability("appVersion", "2441");
	    dc.setCapability(IOSMobileCapabilityType.BUNDLE_ID, "com.experitest.ExperiBank");
	    dc.setCapability("instrumentApp", true);
        driver = new IOSDriver<IOSElement>(new URL(System.getenv("url")), dc);
	}

	@Test
	public void test(){
		driver.findElement(in.Repo.obj("login_page_ios.Username")).sendKeys("company");
        driver.hideKeyboard();
        driver.findElement(in.Repo.obj("login_page_ios.Password")).sendKeys("company");
        driver.findElement(in.Repo.obj("login_page_ios.Login")).click();
        new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(in.Repo.obj("Eribank_options.Advanced_Actions")));
        driver.findElement(in.Repo.obj("Eribank_options.Advanced_Actions")).click();
        new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(in.Repo.obj("Advanced.Eribank_Website")));
        driver.findElement(in.Repo.obj("Advanced.Eribank_Website")).click();
        driver.context("WEBVIEW_1");
        new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(in.Repo.obj("Website.free_trial")));
        driver.findElement(in.Repo.obj("Website.free_trial")).click();
	}
	
	@AfterTest
	public void tearDown(){
		driver.quit();
	}
}
