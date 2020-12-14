package ru.nsu.vki.services.rest;


import javax.ws.rs.client.*;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;
import org.glassfish.jersey.internal.util.collection.MultivaluedStringMap;
import ru.nsu.vki.services.rest.dto.CustomerDto;
import ru.nsu.vki.services.rest.dto.IdDto;
import ru.nsu.vki.services.utils.WebClientService;
import ru.nsu.vki.shared.JsonMapper;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.UUID;

import static ru.nsu.vki.services.rest.RestResources.*;


public class CustomerService {
    public static final String ADMIN_LOGIN = "admin";
    public static final String ADMIN_PASSWORD = "setup";

    public static Response createCustomerHttp(CustomerDto customerDto) {
        WebTarget webTarget = WebClientService.getWebTarget(ADMIN_LOGIN, ADMIN_PASSWORD, CUSTOMER_ROOT);

        return WebClientService.postDto(webTarget, customerDto);
    }

    @Step("Creating customer {0}")
    public static void createCustomer(CustomerDto customerDto) {
        Response response = createCustomerHttp(customerDto);
        if (response.getStatus() != HttpStatus.SC_OK)
            throw new RuntimeException(response.readEntity(String.class));
    }

    public static Response loginHttp(String login, String password) {
        WebTarget webTarget = WebClientService.getWebTarget(login, password,
                String.format(CUSTOMER_LOGIN, login));

        return WebClientService.get(webTarget);
    }

    @Step("Login as customer {0}")
    public static String login(String login, String password) {
        Response response = loginHttp(login, password);
        String content = response.readEntity(String.class);
        if (response.getStatus() != HttpStatus.SC_OK)
            throw new RuntimeException(content);
        IdDto idDto = JsonMapper.fromJson(content, IdDto.class);
        if (idDto.getId() == null)
            throw new RuntimeException(ID_NOT_FOUND);
        return idDto.getId();
    }

    @Step("Check existence of customer {0}")
    public static boolean checkCustomerExists(String login, String password) {
        try {
            String id = login(login, password);
            //noinspection ResultOfMethodCallIgnored
            UUID.fromString(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static Response getCustomerDataHttp(String login, String password, String customerId) {
        WebTarget webTarget = WebClientService.getWebTarget(login, password,
                String.format(CUSTOMER_DATA, customerId));

        return WebClientService.get(webTarget);
    }

    public static Response topUpBalanceHttp(String login, String password, String customerId, int value) {
        WebTarget webTarget = WebClientService.getWebTarget(login, password,
                String.format(CUSTOMER_BALANCE, customerId));

        MultivaluedMap<String, String> formData = new MultivaluedStringMap();
        formData.add("sum", String.valueOf(value));
        return WebClientService.postFormData(webTarget, formData);
    }

    public static Response deleteCustomerHttp(String customerId) {
        WebTarget webTarget = WebClientService.getWebTarget(ADMIN_LOGIN,
                ADMIN_PASSWORD,
                String.format(CUSTOMER_DELETE, customerId));

        return WebClientService.delete(webTarget);
    }

    @Step("Deleting customer with id {0}")
    public static void deleteCustomer(String customerId) {
        Response response = deleteCustomerHttp(customerId);
        if (response.getStatus() != HttpStatus.SC_OK)
            throw new RuntimeException(response.readEntity(String.class));
    }
}
