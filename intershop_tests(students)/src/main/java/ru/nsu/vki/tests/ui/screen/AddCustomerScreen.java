package ru.nsu.vki.tests.ui.screen;

import org.openqa.selenium.By;
import ru.nsu.vki.services.browser.Browser;

import java.net.URI;

public class AddCustomerScreen {
    private static final String url = "/endpoint/add_customer.html";
    private final Browser browser;
    private By firstNameField;
    private By lastNameField;
    private By emailField;
    private By passwordField;
    private By createCustomerButton;

    public AddCustomerScreen(Browser browser) {
        this.browser = browser;
        this.checkUrl();

        firstNameField = By.id("first_name_id");
        lastNameField = By.id("last_name_id");
        emailField = By.id("email_id");
        passwordField = By.id("password_id");
        createCustomerButton = By.id("create_customer_id");
    }

    private void checkUrl() {
        URI u = browser.getCurrentPageUrl();
        String path = u.getPath();
        if (!url.equals(path)) {
            throw new RuntimeException("wrong url: " + path);
        }
    }

    public void enterFirstName(String firstName) {
        browser.typeText(firstNameField, firstName);
    }

    public void enterLastName(String lastName) {
        browser.typeText(lastNameField, lastName);
    }

    public void enterEmail(String email) {
        browser.typeText(emailField, email);
    }

    public void enterPassword(String password) {
        browser.typeText(passwordField, password);
    }

    public void clickCreateCustomer() {
        browser.click(createCustomerButton);
    }
}
