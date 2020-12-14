package ru.nsu.vki.services.rest.dto;


/**
 * @author Alina Mozhina (mohinalina@gmail.com).
 */
public class CustomerDto extends DtoBase {
    private String firstName;
    private String lastName;
    private String login;
    private String pass;
    private int balance;

    public String getFirstName() {
        return firstName;
    }

    public CustomerDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public CustomerDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public CustomerDto setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPass() {
        return pass;
    }

    public CustomerDto setPass(String pass) {
        this.pass = pass;
        return this;
    }

    public int getMoney() {
        return balance;
    }

    public CustomerDto setMoney(int balance) {
        this.balance = balance;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (!(obj instanceof CustomerDto))
            return false;

        CustomerDto dto = (CustomerDto) obj;

        return (firstName != null ? firstName.equals(dto.firstName) : dto.firstName == null) &&
                (lastName != null ? lastName.equals(dto.lastName) : dto.lastName == null) &&
                (login != null ? login.equals(dto.login) : dto.login == null) &&
                (pass != null ? pass.equals(dto.pass) : dto.pass == null) &&
                balance == dto.balance;
    }

    @Override
    public String toString() {
        return String.format("%s %s, %s:%s, %d$", firstName, lastName, login, pass, balance);
    }
}
