package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class UserCanClickOnLinksOnSharePopup {
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
  public void testUserCanClickOnLinksOnSharePopup() throws Exception {
    driver.get(baseUrl + "/");
    String randomUserName = RandomStringUtils.randomAlphabetic(8);
	String randomEmailBase = RandomStringUtils.randomAlphabetic(8);
	String randomEmail = randomEmailBase + "@gmail.com";
    driver.findElement(By.cssSelector("span.signup-button")).click();
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys(randomUsername);
    driver.findElement(By.name("name")).clear();
    driver.findElement(By.name("name")).sendKeys("John Fake Name");
    driver.findElement(By.name("email")).clear();
    driver.findElement(By.name("email")).sendKeys(randomEmail);
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("fakepassword1");
    driver.findElement(By.id("tos")).click();
    driver.findElement(By.id("submit_signup")).click();
    // ERROR: Pause/ Select Window]]
    assertTrue(isElementPresent(By.xpath("//*[@id=\"invitecode\"]")));
    driver.findElement(By.cssSelector("li.email")).click();
    assertTrue(isElementPresent(By.xpath("//*[@id=\"invite-email\"]")));
    driver.findElement(By.cssSelector("li.twitter")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [waitForPopUp | Share | 30000]]
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=Share | ]]
    // Warning: assertTextPresent may require manual changes
    assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Splice is the future of music creation, collaboration and sharing\\. Sign up using my invite code![\\s\\S]*$"));
    driver.close();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | null | ]]
    driver.findElement(By.cssSelector("li.facebook")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=Share | ]]
    // ERROR: Caught exception [ERROR: Unsupported command [waitForPopUp |  | ]]
    // Warning: assertTextPresent may require manual changes
    assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Facebook[\\s\\S]*$"));
    driver.close();
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
