package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class UserRegistersAndDeletesAccount {
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
  public void testUserRegistersAndDeletesAccount() throws Exception {
    driver.get(baseUrl + "/");
    // ERROR: Caught exception [ERROR: Unsupported command [getEval |  | ]]
    // ERROR: Caught exception [ERROR: Unsupported command [getEval |  | ]]
    driver.findElement(By.cssSelector("span.signup-button")).click();
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys(randomUsername);
    driver.findElement(By.name("name")).clear();
    driver.findElement(By.name("name")).sendKeys("fake fullname");
    driver.findElement(By.name("email")).clear();
    driver.findElement(By.name("email")).sendKeys(randomEmail);
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("fakepassword");
    driver.findElement(By.id("tos")).click();
    driver.findElement(By.id("submit_signup")).click();
    driver.findElement(By.xpath("//header[@id='header-nav']/div/ul[4]/li[7]/a")).click();
    driver.findElement(By.linkText("Edit Your Profile")).click();
    driver.findElement(By.linkText("Delete Account")).click();
    assertTrue(closeAlertAndGetItsText().matches("^Are you sure[\\s\\S][\\s\\S] All of your projects will be deleted and you will not be able to access any of your data ever again\\.$"));
    assertEquals("Your user account has been deleted. Thanks for using Splice.", closeAlertAndGetItsText());
    driver.findElement(By.linkText("Log In")).click();
    driver.findElement(By.xpath("//div[@id='registerModal']/div/div/div/div/div/div/div/div/form/div/div/div/input")).clear();
    driver.findElement(By.xpath("//div[@id='registerModal']/div/div/div/div/div/div/div/div/form/div/div/div/input")).sendKeys(randomUsername);
    driver.findElement(By.xpath("//input[@type='password']")).clear();
    driver.findElement(By.xpath("//input[@type='password']")).sendKeys("fakepassword");
    driver.findElement(By.id("submit_signin")).click();
    // Warning: assertTextPresent may require manual changes
    assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*$"));
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
