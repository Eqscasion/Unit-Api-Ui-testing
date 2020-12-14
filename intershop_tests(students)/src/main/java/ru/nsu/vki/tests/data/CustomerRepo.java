package ru.nsu.vki.tests.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerRepo {
    @JsonProperty("firstName")
    public String firstName;

    @JsonProperty("lastName")
    public String lastName;

    @JsonProperty("login")
    public String login;

    @JsonProperty("pass")
    public String pass;

    @JsonProperty("balance")
    public int balance;
}
