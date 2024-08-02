package activities;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class Activity5 {

    AndroidDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        UiAutomator2Options caps = new UiAutomator2Options()
                .setPlatformName("android")
                .setAutomationName("UiAutomator2")
                .setAppPackage("com.google.android.apps.messaging")
                .setAppActivity(".ui.ConversationListActivity")
                .noReset();

        URL serverURL = new URL("http://localhost:4723/wd/hub");

        driver = new AndroidDriver(serverURL, caps);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @Test
    public void sendText(){
        driver.findElement(AppiumBy.accessibilityId("Start chat")).click();
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"ContactSearchField\"]")).sendKeys("7702891598");
        driver.findElement(AppiumBy.xpath("//android.view.View[@resource-id=\"ContactSuggestionList\"]/android.view.View\n")).click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(AppiumBy.id("com.google.android.apps.messaging:id/compose_message_text")));
        driver.findElement(AppiumBy.id("com.google.android.apps.messaging:id/compose_message_text")).sendKeys("Hello from Appium");
        driver.findElement(AppiumBy.accessibilityId("Send SMS")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id=\"message_list\"]/android.view.View[1]/android.view.View[2]")));
        String status = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id = \"message_text\"]")).getText();
        Assert.assertEquals(status, "Hello from Appium");
    }
}
