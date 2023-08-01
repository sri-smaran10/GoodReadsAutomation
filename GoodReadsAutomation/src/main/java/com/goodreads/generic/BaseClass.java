package com.goodreads.generic;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.goodreads.pom.RecentUpdatesPage;
import com.goodreads.pom.SignInPage;
public class BaseClass {
	public WebDriver driver;
	 public FileUtility fileUtils = new FileUtility();
	 static {
		 System.setProperty("webdriver.chrome.driver", "./src/main/resources/driver/chromedriver.exe");
	 }

	@BeforeClass
	public void openBrowser() throws IOException {
		driver =  new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	@BeforeMethod
	public void login() throws IOException {
		String url = fileUtils.readDataFromPropertyFile("url");
		driver.get(url);
		SignInPage s = new SignInPage(driver);
		String email = fileUtils.readDataFromPropertyFile("mailid");
		String password = fileUtils.readDataFromPropertyFile("password");
		s.getSignInLink().click();
		s.getSignInWithEmail().click();
		s.getEmailTbx().sendKeys(email);
		s.getPwdTbx().sendKeys(password);
		s.getSignInBtn().click();
		//if application is asking for captcha
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.titleIs("Recent updates | Goodreads"));
	}
	@AfterMethod
	public void logout() {
		RecentUpdatesPage r = new RecentUpdatesPage(driver);
		r.getSignOutTab().click();
		r.getSignOutBtn().click();
	}
	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}
}
