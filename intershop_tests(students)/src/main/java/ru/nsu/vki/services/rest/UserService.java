package ru.nsu.vki.services.rest;


import ru.nsu.vki.services.rest.dto.UserDto;
import ru.nsu.vki.services.utils.WebClientService;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import static ru.nsu.vki.services.rest.RestResources.USER_CREATE;
import static ru.nsu.vki.services.rest.RestResources.USER_DELETE;
import static ru.nsu.vki.services.rest.RestResources.USER_LOGIN;


public class UserService {
    public static Response createUser(String login, String password, String customerId, UserDto userDto) {
        WebTarget webTarget = WebClientService.getWebTarget(login, password,
                String.format(USER_CREATE, customerId));

        return WebClientService.postDto(webTarget, userDto);
    }

    public static Response login(String login, String password) {
        WebTarget webTarget = WebClientService.getWebTarget(login, password,
                String.format(USER_LOGIN, login));

        return WebClientService.get(webTarget);
    }

    public static Response deleteUser(String login, String password, String userLogin) {
        WebTarget webTarget = WebClientService.getWebTarget(login, password,
                String.format(USER_DELETE, userLogin));

        return WebClientService.delete(webTarget);
    }
}

