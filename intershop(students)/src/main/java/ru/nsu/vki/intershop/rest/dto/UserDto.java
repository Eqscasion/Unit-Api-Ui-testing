package ru.nsu.vki.intershop.rest.dto;


public class UserDto extends DtoBase {
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private String userRole;

    public String getFirstName() {
        return firstName;
    }

    public UserDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public UserDto setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getUserRole() {
        return userRole;
    }

    public UserDto setUserRole(String userRole) {
        this.userRole = userRole;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (!(obj instanceof UserDto))
            return false;

        UserDto dto = (UserDto) obj;

        return (firstName != null ? firstName.equals(dto.firstName) : dto.firstName == null) &&
                (lastName != null ? lastName.equals(dto.lastName) : dto.lastName == null) &&
                (login != null ? login.equals(dto.login) : dto.login == null) &&
                (password != null ? password.equals(dto.password) : dto.password == null) &&
                (userRole != null ? userRole.equals(dto.userRole) : dto.userRole == null);
    }

}

