package ru.nsu.vki.services.rest;

public class RestResources {
    public static final String REST_ROOT = "http://localhost:8080/intershop/rest/";

    static final String CUSTOMER_ROOT = "customers";
    static final String CUSTOMER_LOGIN = CUSTOMER_ROOT + "/%s";
    static final String CUSTOMER_DATA = CUSTOMER_ROOT + "/%s/data";
    static final String CUSTOMER_BALANCE = CUSTOMER_ROOT + "/%s/balance";
    static final String CUSTOMER_DELETE = CUSTOMER_ROOT + "/%s";

    static final String PLAN_ROOT = "plans";
    static final String PLAN_DELETE = PLAN_ROOT + "/%s";

    static final String USER_ROOT = "users";
    static final String USER_CREATE = USER_ROOT + "/%s";
    static final String USER_LOGIN = USER_ROOT + "/%s";
    static final String USER_DELETE = USER_ROOT + "/%s";

    public static final String ID_NOT_FOUND = "Response does not contain id";
}
