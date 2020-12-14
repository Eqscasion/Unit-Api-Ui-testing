package ru.nsu.vki.tests.api;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.testng.annotations.Test;
import ru.nsu.vki.services.log.Logger;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


public class CreatePlan {

    private ClientConfig clientConfig;
    private HttpAuthenticationFeature feature;
    private Client client;
    private WebTarget webTarget;
    private Invocation.Builder invocationBuilder;

    public CreatePlan(){
        clientConfig = new ClientConfig();

        feature = HttpAuthenticationFeature.basic("admin", "setup");
        clientConfig.register(feature) ;

        clientConfig.register(JacksonFeature.class);

        client = ClientBuilder.newClient( clientConfig );

        webTarget = client.target("http://localhost:8080/intershop/rest").path("plans");

        invocationBuilder =	webTarget.request(MediaType.APPLICATION_JSON);
    }
    @Test
    public void createPlan() {
        String PLAN_TEMPLATE = "{\n" +
                "\t\"name\":\"LizaPlan\",\n" +
                "    \"details\":\"Prosto kakoy to opisanie\",\n" +
                "    \"fee\":\"1330\"\n" +
                "}";

        Logger.debug("Try to make POST...");
       /* Response response = invocationBuilder.post(Entity.entity("{\n" +
                "\t\"name\":\"LizaPlan\",\n" +
                "    \"details\":\"Prosto kakoy to opisanie\",\n" +
                "    \"fee\":\"1330\"\n" +
                "}", MediaType.APPLICATION_JSON));*/
        Response response = invocationBuilder.post(Entity.entity(PLAN_TEMPLATE, MediaType.APPLICATION_JSON));
        Logger.info("Response: " + response.readEntity(String.class));
    }
    @Test
    public void createPlanWithWrongDetails() {
        String PLAN_TEMPLATE = "{\n" +
                "\t\"name\":\"LizaPlan\",\n" +
                "    \"details\":\"e\",\n" +
                "    \"fee\":\"1330\"\n" +
                "}";
        Logger.debug("Try to make POST...");
        Response response = invocationBuilder.post(Entity.entity(PLAN_TEMPLATE, MediaType.APPLICATION_JSON));
        Logger.info("Response: " + response.readEntity(String.class));
    }
    @Test
    public void createPlanWithWrongFee() {
        String PLAN_TEMPLATE = "{\n" +
                "\t\"name\":\"LizaPlan\",\n" +
                "    \"details\":\"Prosto kakoy to opisanie\",\n" +
                "    \"fee\":\"y\"\n" +
                "}";

        Logger.debug("Try to make POST...");
        Response response = invocationBuilder.post(Entity.entity(PLAN_TEMPLATE, MediaType.APPLICATION_JSON));
        Logger.info("Response: " + response.readEntity(String.class));
    }
}
