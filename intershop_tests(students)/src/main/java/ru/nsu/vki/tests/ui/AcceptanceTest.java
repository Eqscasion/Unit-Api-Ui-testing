package ru.nsu.vki.tests.ui;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.*;
import org.testng.internal.annotations.IBeforeTest;
import ru.nsu.vki.services.browser.Browser;
import ru.nsu.vki.services.browser.BrowserService;
import ru.nsu.vki.services.log.Logger;
import ru.nsu.vki.services.utils.JsonMapper;
import ru.nsu.vki.tests.data.CustomerRepo;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Title;
import ru.yandex.qatools.allure.model.SeverityLevel;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AcceptanceTest {
    private Browser browser = null;
    private static WebDriver driver;
    private static String customerPojoStr, timeStamp;

    private CustomerRepo customerRepo;

    @Test
    @Title("Create customer")
    @Description("Create customer via UI API")
    @Severity(SeverityLevel.BLOCKER)
    @Features("Customer feature")
    public void createCustomer() {
        beforeTest();
        afterTest();
    }

    @Test
    @Title("Create customer")
    @Description("Create customer via UI API with wrong first name")
    @Severity(SeverityLevel.BLOCKER)
    @Features("Customer feature")
    public void createCustomerWithWrongFirstName() {
        beforeTest();
        this.customerRepo.firstName = "YOfgaFg";
        afterTest();
    }

    @Test
    @Title("Create customer")
    @Description("Create customer via UI API with wrong last name")
    @Severity(SeverityLevel.BLOCKER)
    @Features("Customer feature")
    public void createCustomerWithWrongLastName() {
        beforeTest();
        this.customerRepo.lastName = "BolOkov";
        afterTest();
    }

    @Test
    @Title("Create customer")
    @Description("Create customer via UI API with wrong login")
    @Severity(SeverityLevel.BLOCKER)
    @Features("Customer feature")
    public void createCustomerWithWrongLogin() {
        beforeTest();
        this.customerRepo.login = "asmn@kovcom";
        afterTest();
    }

    @Test
    @Title("Create customer")
    @Description("Create customer via API with password without numbers")
    @Severity(SeverityLevel.BLOCKER)
    @Features("Customer feature")
    public void createCustomerWithPasswordWithoutNumbers() {
        beforeTest();
        this.customerRepo.pass = "aSmn%kBom";
        afterTest();
    }

    @Test
    @Title("Create customer")
    @Description("Create customer via API with password without uppercase letters")
    @Severity(SeverityLevel.BLOCKER)
    @Features("Customer feature")
    public void createCustomerWithPasswordWithoutUppercaseLetters() {
        beforeTest();
        this.customerRepo.pass = "as77mn%kbom";
        afterTest();
    }

    @Test
    @Title("Create customer")
    @Description("Create customer via API with password without lowercase letters")
    @Severity(SeverityLevel.BLOCKER)
    @Features("Customer feature")
    public void createCustomerWithPasswordWithoutLowercaseLetters() {
        beforeTest();
        this.customerRepo.pass = "AS77MN%KBOM";
        afterTest();
    }

    @Test
    @Title("Create customer")
    @Description("Create customer via API with password without special characters")
    @Severity(SeverityLevel.BLOCKER)
    @Features("Customer feature")
    public void createCustomerWithPasswordWithoutSpecialCharacters() {
        beforeTest();
        this.customerRepo.pass = "an6knsFS30gd";
        afterTest();
    }

    @Test
    @Title("Create customer")
    @Description("Create customer via API with password contains login")
    @Severity(SeverityLevel.BLOCKER)
    @Features("Customer feature")
    public void createCustomerWithPasswordContainsLogin() {
        beforeTest();
        this.customerRepo.login = "mao@co.com";
        this.customerRepo.pass = "an6mao@co.com30gd";
        afterTest();
    }

    @Test
    @Title("Create customer")
    @Description("Create customer via API with password contains FirstName")
    @Severity(SeverityLevel.BLOCKER)
    @Features("Customer feature")
    public void createCustomerWithPasswordContainsFirstName() {
        beforeTest();
        this.customerRepo.firstName = "Ank";
        this.customerRepo.pass = "Ank6kn&sFS30gd";
        afterTest();
    }

    @Test
    @Title("Create customer")
    @Description("Create customer via API with password contains LastName")
    @Severity(SeverityLevel.BLOCKER)
    @Features("Customer feature")
    public void createCustomerWithPasswordContainsLastName() {
        beforeTest();
        this.customerRepo.lastName = "Ank";
        this.customerRepo.pass = "Ank6kn&sFS30gd";
        afterTest();
    }

    @Test
    @Title("Create customer")
    @Description("Create customer via API with password contains whitespace")
    @Severity(SeverityLevel.BLOCKER)
    @Features("Customer feature")
    public void createCustomerWithPasswordContainsWhitespace() {
        beforeTest();
        this.customerRepo.pass = "an6kn sFS&30gd";
        afterTest();
    }



    public void beforeTest(){
        browser = BrowserService.openNewBrowser();
        this.customerRepo = new CustomerRepo();
        this.customerRepo = HelperCCUI.creater();
        browser.openPage("http://localhost:8080/intershop");
        browser.waitForElement(By.id("email"));
        browser.getElement(By.id("email")).sendKeys("admin");
        browser.getElement(By.id("password")).sendKeys("setup");
        browser.getElement(By.id("login")).click();
        browser.waitForElement(By.id("add_new_customer"));
        browser.getElement(By.id("add_new_customer")).click();
    }

    public void afterTest() {
        browser.waitForElement(By.id("first_name_id"));
        browser.getElement(By.id("first_name_id")).sendKeys(this.customerRepo.firstName);
        browser.getElement(By.id("last_name_id")).sendKeys(this.customerRepo.lastName);
        browser.getElement(By.id("email_id")).sendKeys(this.customerRepo.login);
        browser.getElement(By.id("password_id")).sendKeys(this.customerRepo.pass);
        browser.getElement(By.id("create_customer_id")).click();
        customerPojoStr = JsonMapper.toJson(this.customerRepo, true);
        Logger.info("Customer: " + customerPojoStr);
        if (browser != null)
            browser.close();
    }

    private static void takeScreenshot() throws Exception {

        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

        timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss.SSS").format(Calendar.getInstance().getTime());

        File screenShotName = new File("D:/NSU/WORK/testing/qaAuto/new/intershop_tests/test-output/" + timeStamp+".png");
        File screenShotNameNew = new File(timeStamp+".png");
        FileUtils.copyFile(scrFile, screenShotName);

        String newPath = "<img src=\"" + "./"+ screenShotNameNew.toString() + "\" style=\"width:600px\"/>";
        Reporter.log(newPath);
    }
}
