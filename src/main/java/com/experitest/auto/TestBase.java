package com.experitest.auto;

import java.net.URL;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class TestBase {

	protected DesiredCapabilities dc;


	@BeforeSuite
	public void setUp(ITestContext testContext) throws Exception {



		dc = new DesiredCapabilities();
		dc.setCapability("stream", "CI-Run");
		dc.setCapability("build.number", System.getenv("BUILD_NUMBER"));

		dc.setCapability("accessKey", System.getenv("ACCESS_KEY"));
		dc.setCapability("testName", "Sanity - Experitest.com");

	}

}
