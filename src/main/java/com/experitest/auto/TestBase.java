package com.experitest.auto;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.annotations.BeforeSuite;

public class TestBase {

	protected DesiredCapabilities dc;


	@BeforeSuite
	public void setUp(ITestContext testContext) throws Exception {

		dc = new DesiredCapabilities();
		dc.setCapability("stream", "CI-Run");
		dc.setCapability("build.number", 100);

		dc.setCapability("accessKey", System.getenv("ACCESS_KEY"));
		dc.setCapability("testName", "Sanity - Experitest.com");

	}

}
