package com.experitest.auto;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class AndroidTest {

	private String testName = "Demo Android Phone";
	protected AndroidDriver<AndroidElement> driver = null;
	protected DesiredCapabilities dc = new DesiredCapabilities();

    @BeforeTest
    @Parameters("deviceQuery")
    public void setUp(@Optional("@os='android'") String deviceQuery) throws MalformedURLException {
        dc.setCapability("testName", testName);
		dc.setCapability("deviceQuery", deviceQuery);
		dc.setCapability("reportDirectory", "reports");
		dc.setCapability(MobileCapabilityType.ORIENTATION, "PORTRAIT");
		dc.setCapability("reportFormat", "xml");
		dc.setCapability("build.number", "000"+System.getenv("BUILD_NUMBER"));
		dc.setCapability("accessKey", System.getenv("accessKey")); 
        dc.setCapability(MobileCapabilityType.APP, "cloud:com.experitest.eribank/com.experitest.ExperiBank.LoginActivity");
        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.experitest.eribank");
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.experitest.ExperiBank.LoginActivity");
//	    dc.setCapability("instrumentApp", true);
        dc.setCapability("stream", "ci.demo");
        dc.setCapability("platform", "Android");
        System.out.println("Creating AndroidDriver");
        driver = new AndroidDriver<AndroidElement>(new URL(System.getenv("url")), dc);
    }

    @Test
    public void test() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Find username field");
        driver.findElement(By.xpath("//*[@id='usernameTextField']")).sendKeys("company");
        driver.hideKeyboard();
        System.out.println("CLick login");

        driver.findElement(By.xpath("//*[@id='passwordTextField']")).sendKeys("company");
        driver.findElement(By.xpath("//*[@id='loginButton']")).click();

    }

    @AfterTest
    public void tearDown(){
        System.out.println("Finished AndroidTest");

        driver.quit();
    }
}