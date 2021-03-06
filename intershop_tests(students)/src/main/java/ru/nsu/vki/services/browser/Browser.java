package ru.nsu.vki.services.browser;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.nsu.vki.shared.ImageUtils;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Please read: https://github.com/SeleniumHQ/selenium/wiki/Grid2
 */
public class Browser implements Closeable {
    private WebDriver webDriver;

    public Browser() {
        // create web driver
        try {
            ChromeOptions chromeOptions = new ChromeOptions();

            // for running in Docker container as 'root'.
            chromeOptions.addArguments("no-sandbox");
            chromeOptions.addArguments("disable-dev-shm-usage");
            chromeOptions.addArguments("disable-setuid-sandbox");
            chromeOptions.addArguments("disable-infobars");

            chromeOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            chromeOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);

            // we use Windows platform for development only and not for AT launch.
            // For launch AT regression, we use Linux platform.
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                System.setProperty("webdriver.chrome.driver", "./lib/chromedriver.exe");
                chromeOptions.setHeadless(Boolean.parseBoolean(System.getProperty("headless")));
                webDriver = new ChromeDriver(chromeOptions);
            } else {
                File f = new File("/usr/bin/chromedriver");
                if (f.exists()) {
                    chromeOptions.addArguments("single-process");
                    chromeOptions.addArguments("headless");
                    System.setProperty("webdriver.chrome.driver", f.getPath());
                    webDriver = new ChromeDriver(chromeOptions);
                }
            }

            if (webDriver == null) {
                throw new RuntimeException();
            }

            webDriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public Browser openPage(String url) {
        webDriver.get(url);
        return this;
    }

    public void waitForElement(By element) {
        waitForElement(element, 10);
    }

    private Browser waitForElement(final By element, int timeoutSec) {
        WebDriverWait wait = new WebDriverWait(webDriver, timeoutSec);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        return this;
    }

    public void click(By element) {
        webDriver.findElement(element).click();
    }

    public void typeText(By element, String text) {
        webDriver.findElement(element).sendKeys(text);
    }

    public WebElement getElement(By element) {
        return webDriver.findElement(element);
    }

    public String getValue(By element) {
        return getElement(element).getAttribute("value");
    }

    public List<WebElement> getElements(By element) {
        return webDriver.findElements(element);
    }
    public String getDataFromTable(int column) {
        return webDriver.findElement(By.xpath(String.format("(//table//td)[%d]\n", column))).getText();
    }
    public void clickButtonInTable() {
        String path = "(//table//td//button)\n";
        webDriver.findElement(By.xpath(path)).click();
    }
    public URI getCurrentPageUrl() {
        return URI.create(webDriver.getCurrentUrl());
    }
    public boolean isElementPresent(By element) {
        return getElements(element).size() != 0;
    }

    public byte[] makeScreenshot() {
        try {
            return ImageUtils.toByteArray(((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void close() {
        webDriver.close();
    }
}
