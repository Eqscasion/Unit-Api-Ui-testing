package ru.nsu.vki.services.utils;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.jackson.JacksonFeature;
import ru.nsu.vki.services.rest.dto.DtoBase;
import ru.nsu.vki.shared.JsonMapper;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import java.io.IOException;

import static ru.nsu.vki.services.rest.RestResources.REST_ROOT;


public class WebClientService {
    public static WebTarget getWebTarget(String login, String password, String path) {
        ClientConfig clientConfig = new ClientConfig();

        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic(login, password);
        clientConfig.register(feature);

        clientConfig.register(JacksonFeature.class);

        Client client = ClientBuilder.newClient(clientConfig);

        return client.target(REST_ROOT).path(path);
    }

    public static Response postDto(WebTarget webTarget, DtoBase dto) {
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

        String dtoJson;
        try {
            dtoJson = JsonMapper.toJson(dto, false);
        } catch (IOException e) {
            dtoJson = "";
        }
        return invocationBuilder.post(Entity.entity(dtoJson, MediaType.APPLICATION_JSON));
    }

    public static Response postFormData(WebTarget webTarget, MultivaluedMap<String, String> formData) {
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_FORM_URLENCODED_TYPE);

        return invocationBuilder.post(Entity.form(formData));
    }

    public static Response get(WebTarget webTarget) {
        Invocation.Builder invocationBuilder = webTarget.request();

        return invocationBuilder.get();
    }

    public static Response delete(WebTarget webTarget) {
        Invocation.Builder invocationBuilder = webTarget.request();

        return invocationBuilder.delete();
    }
}
