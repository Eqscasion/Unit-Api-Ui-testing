package ru.nsu.vki.tests.ui.screen;

import org.openqa.selenium.By;
import org.slf4j.Logger;
import ru.nsu.vki.services.browser.Browser;

import java.net.URI;


public class CustomersScreen {
    private static final String url = "/intershop/customers.html";
    private Browser browser;
    private By addNewCustomerButton;
    private By showPlansButton;

    public CustomersScreen(Browser browser) {
        this.browser = browser;

        browser.waitForElement(By.id("add_new_customer"));
        browser.waitForElement(By.id("customer_list_id"));
        addNewCustomerButton = By.id("add_new_customer");
        showPlansButton = By.id("plans");

        this.checkUrl();
    }

    public static String getCharacteristicElementId() {
        return "add_new_customer";
    }

    private void checkUrl() {
        URI u = browser.getCurrentPageUrl();
        String path = u.getPath();
        if (!url.equals(path)) {
            throw new RuntimeException("wrong url: " + path);
        }
    }

    public void clickAddNewCustomer() {
        browser.click(addNewCustomerButton);
    }

    public void clickShowPlans() {
        browser.click(showPlansButton);
    }

    public String getDataFromTable(int column) {
        return browser.getDataFromTable(column);
    }

    public void increaseBalance() {
        browser.clickButtonInTable();
    }
}
