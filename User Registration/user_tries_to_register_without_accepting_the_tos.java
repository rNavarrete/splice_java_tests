package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class UserTriesToRegisterWithoutAcceptingTheTos {
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
  public void testUserTriesToRegisterWithoutAcceptingTheTos() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.cssSelector("span.signup-button")).click();
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("Jonny Cage");
    driver.findElement(By.name("name")).clear();
    driver.findElement(By.name("name")).sendKeys("hollywood danny");
    driver.findElement(By.name("email")).clear();
    driver.findElement(By.name("email")).sendKeys("thisemail@isavalidone.com");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("thisemail@isagoodone.com");
    driver.findElement(By.id("submit_signup")).click();
    assertEquals("Splice - Music Made Better | Splice", driver.getTitle());
    assertEquals("Terms must be accepted", driver.findElement(By.xpath("//*[@id=\"registerModal\"]/div/div/div/div/div/div/div[1]/form/div[1]")).getText());
    assertThat("Dashboard | Splice", is(not(driver.getTitle())));
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
