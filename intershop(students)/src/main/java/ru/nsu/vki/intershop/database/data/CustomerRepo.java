package ru.nsu.vki.intershop.database.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerRepo {
    @JsonProperty("id")
    private UUID id;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("login")
    private String login;

    @JsonProperty("pass")
    private String pass;

    @JsonProperty("balance")
    private int balance;

    public UUID getId() {
        return id;
    }

    public CustomerRepo setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

    public int getBalance() {
        return balance;
    }

    public CustomerRepo setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public CustomerRepo setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public CustomerRepo setLogin(String login) {
        this.login = login;
        return this;
    }

    public CustomerRepo setPass(String pass) {
        this.pass = pass;
        return this;
    }

    public CustomerRepo setBalance(int balance) {
        this.balance = balance;
        return this;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", pass='" + pass + '\'' +
                ", balance=" + balance +
                '}';
    }

    @Override
    public CustomerRepo clone() {
        return new CustomerRepo()
                .setId(id)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setLogin(login)
                .setPass(pass)
                .setBalance(balance);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerRepo customer = (CustomerRepo) o;

        return id != null ? id.equals(customer.id) : customer.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
