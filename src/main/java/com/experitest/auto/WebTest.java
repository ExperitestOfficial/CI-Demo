package com.experitest.auto;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.TestNGUtils;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.URL;


public class WebTest {

    private WebDriver driver;
    private DesiredCapabilities dc = new DesiredCapabilities();

    @BeforeTest
    @Parameters({"browserName","platformName"})
    public void setUp(@Optional("chrome") String browserName, @Optional("") String platformName) throws Exception {
        dc.setCapability(CapabilityType.BROWSER_NAME, browserName);
        dc.setCapability(CapabilityType.VERSION, "Any");
        dc.setCapability(CapabilityType.PLATFORM, platformName);
		dc.setCapability("stream", "ci.demo");
        dc.setCapability("build.number", "000"+System.getenv("BUILD_NUMBER"));
        dc.setCapability("accessKey", System.getenv("accessKey"));
        dc.setCapability("testName", "Experitest site");
        dc.setCapability("platform", browserName);
        System.out.println("Creating RemoteWebDriver for "+browserName );

        driver = new RemoteWebDriver(new URL(System.getenv("url")), dc);
    }


    @Test
    public void testExperitest() throws InterruptedException {
        System.out.println("Hitting experitest.com url");
        driver.get("https://www.experitest.com/");
        System.out.println("Waiting for #menu-item-8537 > a");
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#menu-item-8537 > a")));
        System.out.println("finished testExperitest()");
    }

    @Test
    public void testGoogleSearch() {
        System.out.println("Hitting google.com url");

        driver.get("https://www.google.com");
        System.out.println("Wait for lst-ib ");

        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.id("lst-ib")));
        WebElement searchBar = driver.findElement(By.id("lst-ib"));
        System.out.println("Click lst-ib ");

        searchBar.click();
        searchBar.sendKeys("Experitest");
        searchBar.sendKeys(Keys.ENTER);
        System.out.println("finished testGoogleSearch() ");

    }

    @AfterTest
    public void tearDown() {
        System.out.println("finished WebTest");
        driver.quit();
    }

}