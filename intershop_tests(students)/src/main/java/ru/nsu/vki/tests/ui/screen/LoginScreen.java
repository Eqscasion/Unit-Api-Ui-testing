package ru.nsu.vki.tests.ui.screen;

import ru.nsu.vki.services.browser.Browser;

import org.openqa.selenium.By;
import org.slf4j.Logger;
import java.net.URI;


public class LoginScreen {

    private static final String url = "/intershop/login.html";
    private final By emailField;
    private final By passwordField;
    private final By loginButton;
    private final Browser browser;

    public LoginScreen(Browser browser) {
        this.browser = browser;

        emailField = By.id("email");

        this.checkUrl();

        passwordField = By.id("password");
        loginButton = By.id("login");
    }

    public static CustomersScreen enterAsAdmin(Browser browser) {
        LoginScreen loginScreen = new LoginScreen(browser);
        loginScreen.enterEmail("admin");
        loginScreen.enterPassword("setup");
        loginScreen.clickLogin();

        return new CustomersScreen(browser);
    }

    private void checkUrl() {
        URI u = browser.getCurrentPageUrl();
        String path = u.getPath();
        if (!url.equals(path)) {
            throw new RuntimeException("wrong url: " + path);
        }
    }

    public void enterEmail(String email) {
        browser.typeText(emailField, email);
    }

    public void enterPassword(String password) {
        browser.typeText(passwordField, password);
    }

    public void clickLogin() {
        browser.click(loginButton);
    }
}

