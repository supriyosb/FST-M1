package activities;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

public class Activity4 {

    AndroidDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        UiAutomator2Options caps = new UiAutomator2Options()
                .setPlatformName("android")
                .setAutomationName("UiAutomator2")
                .setAppPackage("com.google.android.dialer")
                .setAppActivity(".extensions.GoogleDialtactsActivity")
                .noReset();

        URL serverURL = new URL("http://localhost:4723/wd/hub");

        driver = new AndroidDriver(serverURL, caps);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @Test
    public void createNewContact(){
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"com.google.android.dialer:id/contact_name\" and @text=\"Create new contact\"]")).click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(AppiumBy.xpath("//android.widget.EditText[@text=\"First name\"]")));
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text=\"First name\"]")).sendKeys("Aaditya");
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text=\"Last name\"]")).sendKeys("Varma");
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text=\"Phone\"]")).sendKeys("999148292");
        driver.findElement(AppiumBy.id("com.google.android.contacts:id/toolbar_button")).click();
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"com.google.android.dialer:id/contact_name\" and @text=\"Create new contact\"]")));
        List<WebElement> contactNames = driver.findElements(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"com.google.android.dialer:id/contact_name\" ]"));
        Assert.assertEquals(contactNames.get(1).getText(), "Aaditya Varma");
    }

    @AfterClass
    public void tearDown() {
        // Close the app
        driver.quit();
    }
}
