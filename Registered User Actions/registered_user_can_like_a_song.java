package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import java.security.SecureRandom;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.support.ui.WebDriverWait;




public class user_can_like_a_track { 
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "https://tardis.splice.com/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}		

	@Test
	public void testUserCanLikeATrack() throws Exception {
		
		String randomUserName = RandomStringUtils.randomAlphabetic(8);
		String randomEmailBase = RandomStringUtils.randomAlphabetic(8);
		String randomEmail = randomEmailBase + "@gmail.com";
		driver.get(baseUrl);
		driver.findElement(By.cssSelector("a.studio.js-home-tab")).click();
		driver.findElement(By.cssSelector("a.community")).click();
		driver.findElement(By.linkText("Most Popular")).click();
		driver.findElement(By.linkText("Rebound")).click();
		String likeCount = driver.findElement(By.xpath("//*[@id=\"dna-player\"]/div[2]/div/div[2]/div[4]/div[2]/a[1]/span[2]")).getText();
		System.out.println(likeCount);
		driver.get(baseUrl + "/");
		driver.findElement(By.cssSelector("span.signup-button")).click();
		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("username")).sendKeys(randomUserName);
		driver.findElement(By.name("name")).clear();
		driver.findElement(By.name("name")).sendKeys("fake fullname");
		driver.findElement(By.name("email")).clear();
		driver.findElement(By.name("email")).sendKeys(randomEmail);
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("fakepassword");
		driver.findElement(By.id("tos")).click();
		driver.findElement(By.id("submit_signup")).click();
		driver.findElement(By.xpath("//*[@id='inviteModal']/div/div/div/div/div[1]/div[1]")).click();
		driver.findElement(By.xpath("/html/body/div[1]/div[1]/header/div/ul[1]/li[2]/a")).click();
		driver.findElement(By.linkText("Most Popular")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Rebound')]")).click();
		driver.findElement(By.xpath("//*[@id=\"dna-player\"]/div[2]/div/div[2]/div[4]/div[2]/a[1]/span[2]")).click();
		String likeCountAfterClick = driver.findElement(By.xpath("//*[@id=\"dna-player\"]/div[2]/div/div[2]/div[4]/div[2]/a[1]/span[2]")).getText();
		System.out.println(likeCountAfterClick);
		assertTrue(Integer.parseInt(likeCount) < Integer.parseInt(likeCountAfterClick));		
	}

	@After
	public void tearDown() throws Exception {
	  driver.quit();
	  String verificationErrorString = verificationErrors.toString();
	  if (!"".equals(verificationErrorString)) {
	    fail(verificationErrorString);
	  }
	}

	private boolean isElementPresent(By by) {
	  try {
	    driver.findElement(by);
	    return true;
	  } catch (NoSuchElementException e) {
	    return false;
	  }
	}
	
	private boolean isAlertPresent() {
	  try {
	    driver.switchTo().alert();
	    return true;
	  } catch (NoAlertPresentException e) {
	    return false;
	  }
	}
	
	private String closeAlertAndGetItsText() {
	  try {
	    Alert alert = driver.switchTo().alert();
	    String alertText = alert.getText();
	    if (acceptNextAlert) {
	      alert.accept();
	    } else {
	      alert.dismiss();
	    }
	    return alertText;
	  } finally {
	    acceptNextAlert = true;
	  }
	}
}