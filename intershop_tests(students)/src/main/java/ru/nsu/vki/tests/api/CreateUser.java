package ru.nsu.vki.tests.api;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.testng.annotations.Test;
import ru.nsu.vki.services.log.Logger;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ru.nsu.vki.services.rest.dto.CustomerDto;

public class CreateUser {
    protected CustomerDto customer;

    @Test(description = "Create customer via API.")
    public void createCustomer() {
        String CUSTOMER_TEMPLATE = "{\n" +
                "\t\"firstName\":\"Moon\",\n" +
                "    \"lastName\":\"Fomeo\",\n" +
                "    \"login\":\"m.fomeo@login.com\",\n" +
                "    \"pass\":\"tHI9sa@ss142\",\n" +
                "    \"balance\":\"1330\"\n" +
                "}";

        ClientConfig clientConfig = new ClientConfig();

        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("admin", "setup");
        clientConfig.register(feature) ;

        clientConfig.register(JacksonFeature.class);

        Client client = ClientBuilder.newClient( clientConfig );

        WebTarget webTarget = client.target("http://localhost:8080/intershop/rest").path("customers");

        Invocation.Builder invocationBuilder =	webTarget.request(MediaType.APPLICATION_JSON);
        Logger.debug("Try to make POST...");
        Response response = invocationBuilder.post(Entity.entity(CUSTOMER_TEMPLATE, MediaType.APPLICATION_JSON));
        Logger.info("Response: " + response.readEntity(String.class));
    }

    @Test(description = "Create customer via API with wrong first name")
    public void setCustomerWithWrongFirstName(){
        String CUSTOMER_TEMPLATE = "{\n" +
                "\t\"firstName\":\"YOfgaFg\",\n" +
                "    \"lastName\":\"Fomeo\",\n" +
                "    \"login\":\"m.fomeo@login.com\",\n" +
                "    \"pass\":\"tHI9sa@ss142\",\n" +
                "    \"balance\":\"1330\"\n" +
                "}";

        ClientConfig clientConfig = new ClientConfig();

        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("admin", "setup");
        clientConfig.register(feature) ;

        clientConfig.register(JacksonFeature.class);

        Client client = ClientBuilder.newClient( clientConfig );

        WebTarget webTarget = client.target("http://localhost:8080/intershop/rest").path("customers");

        Invocation.Builder invocationBuilder =	webTarget.request(MediaType.APPLICATION_JSON);
        Logger.debug("Try to make POST...");
        Response response = invocationBuilder.post(Entity.entity(CUSTOMER_TEMPLATE, MediaType.APPLICATION_JSON));
        Logger.info("Response: " + response.readEntity(String.class));
    }

    @Test(description = "Create customer via API with wrong last name")
    public void setCustomerWithWrongLastName(){
        String CUSTOMER_TEMPLATE = "{\n" +
                "\t\"firstName\":\"Moon\",\n" +
                "    \"lastName\":\"FoASmeo\",\n" +
                "    \"login\":\"m.fomeo@login.com\",\n" +
                "    \"pass\":\"tHI9sa@ss142\",\n" +
                "    \"balance\":\"1330\"\n" +
                "}";

        ClientConfig clientConfig = new ClientConfig();

        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("admin", "setup");
        clientConfig.register(feature) ;

        clientConfig.register(JacksonFeature.class);

        Client client = ClientBuilder.newClient( clientConfig );

        WebTarget webTarget = client.target("http://localhost:8080/intershop/rest").path("customers");

        Invocation.Builder invocationBuilder =	webTarget.request(MediaType.APPLICATION_JSON);
        Logger.debug("Try to make POST...");
        Response response = invocationBuilder.post(Entity.entity(CUSTOMER_TEMPLATE, MediaType.APPLICATION_JSON));
        Logger.info("Response: " + response.readEntity(String.class));
    }

    @Test(description = "Create customer via API with wrong login")
    public void setCustomerWithWrongLogin(){
        String CUSTOMER_TEMPLATE = "{\n" +
                "\t\"firstName\":\"Moon\",\n" +
                "    \"lastName\":\"Fomeo\",\n" +
                "    \"login\":\"m.fomeo@logincom\",\n" +
                "    \"pass\":\"tHI9sa@ss142\",\n" +
                "    \"balance\":\"1330\"\n" +
                "}";

        ClientConfig clientConfig = new ClientConfig();

        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("admin", "setup");
        clientConfig.register(feature) ;

        clientConfig.register(JacksonFeature.class);

        Client client = ClientBuilder.newClient( clientConfig );

        WebTarget webTarget = client.target("http://localhost:8080/intershop/rest").path("customers");

        Invocation.Builder invocationBuilder =	webTarget.request(MediaType.APPLICATION_JSON);
        Logger.debug("Try to make POST...");
        Response response = invocationBuilder.post(Entity.entity(CUSTOMER_TEMPLATE, MediaType.APPLICATION_JSON));
        Logger.info("Response: " + response.readEntity(String.class));
    }

    @Test(description = "Create customer via API with password without numbers")
    public void setCustomerWithPasswordWithoutNumbers(){
        String CUSTOMER_TEMPLATE = "{\n" +
                "\t\"firstName\":\"Moon\",\n" +
                "    \"lastName\":\"Fomeo\",\n" +
                "    \"login\":\"m.fomeo@login.com\",\n" +
                "    \"pass\":\"tHIsa@ss\",\n" +
                "    \"balance\":\"0\"\n" +
                "}";

        ClientConfig clientConfig = new ClientConfig();

        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("admin", "setup");
        clientConfig.register(feature) ;

        clientConfig.register(JacksonFeature.class);

        Client client = ClientBuilder.newClient( clientConfig );

        WebTarget webTarget = client.target("http://localhost:8080/intershop/rest").path("customers");

        Invocation.Builder invocationBuilder =	webTarget.request(MediaType.APPLICATION_JSON);
        Logger.debug("Try to make POST...");
        Response response = invocationBuilder.post(Entity.entity(CUSTOMER_TEMPLATE, MediaType.APPLICATION_JSON));
        Logger.info("Response: " + response.readEntity(String.class));
    }

    @Test(description = "Create customer via API with password without upper case letters")
    public void setCustomerWithPasswordWithoutUpperCaseLetters(){
        String CUSTOMER_TEMPLATE = "{\n" +
                "\t\"firstName\":\"Moon\",\n" +
                "    \"lastName\":\"Fomeo\",\n" +
                "    \"login\":\"m.fomeo@login.com\",\n" +
                "    \"pass\":\"thi9sa@ss142\",\n" +
                "    \"balance\":\"0\"\n" +
                "}";

        ClientConfig clientConfig = new ClientConfig();

        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("admin", "setup");
        clientConfig.register(feature) ;

        clientConfig.register(JacksonFeature.class);

        Client client = ClientBuilder.newClient( clientConfig );

        WebTarget webTarget = client.target("http://localhost:8080/intershop/rest").path("customers");

        Invocation.Builder invocationBuilder =	webTarget.request(MediaType.APPLICATION_JSON);
        Logger.debug("Try to make POST...");
        Response response = invocationBuilder.post(Entity.entity(CUSTOMER_TEMPLATE, MediaType.APPLICATION_JSON));
        Logger.info("Response: " + response.readEntity(String.class));
    }

    @Test(description = "Create customer via API with password without lower case letters")
    public void setCustomerWithPasswordWithoutLowerCaseLetters(){
        String CUSTOMER_TEMPLATE = "{\n" +
                "\t\"firstName\":\"Moon\",\n" +
                "    \"lastName\":\"Fomeo\",\n" +
                "    \"login\":\"m.fomeo@login.com\",\n" +
                "    \"pass\":\"THI9SA@SS142\",\n" +
                "    \"balance\":\"0\"\n" +
                "}";

        ClientConfig clientConfig = new ClientConfig();

        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("admin", "setup");
        clientConfig.register(feature) ;

        clientConfig.register(JacksonFeature.class);

        Client client = ClientBuilder.newClient( clientConfig );

        WebTarget webTarget = client.target("http://localhost:8080/intershop/rest").path("customers");

        Invocation.Builder invocationBuilder =	webTarget.request(MediaType.APPLICATION_JSON);
        Logger.debug("Try to make POST...");
        Response response = invocationBuilder.post(Entity.entity(CUSTOMER_TEMPLATE, MediaType.APPLICATION_JSON));
        Logger.info("Response: " + response.readEntity(String.class));
    }

    @Test(description = "Create customer via API with password without special characters")
    public void setCustomerWithPasswordWithoutSpecialCharacters(){
        String CUSTOMER_TEMPLATE = "{\n" +
                "\t\"firstName\":\"Moon\",\n" +
                "    \"lastName\":\"Fomeo\",\n" +
                "    \"login\":\"m.fomeo@login.com\",\n" +
                "    \"pass\":\"tHI9sass142\",\n" +
                "    \"balance\":\"0\"\n" +
                "}";

        ClientConfig clientConfig = new ClientConfig();

        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("admin", "setup");
        clientConfig.register(feature) ;

        clientConfig.register(JacksonFeature.class);

        Client client = ClientBuilder.newClient( clientConfig );

        WebTarget webTarget = client.target("http://localhost:8080/intershop/rest").path("customers");

        Invocation.Builder invocationBuilder =	webTarget.request(MediaType.APPLICATION_JSON);
        Logger.debug("Try to make POST...");
        Response response = invocationBuilder.post(Entity.entity(CUSTOMER_TEMPLATE, MediaType.APPLICATION_JSON));
        Logger.info("Response: " + response.readEntity(String.class));
    }

    @Test(description = "Create customer via API with password contains login")
    public void setCustomerWithPasswordContainsLogin(){
        String CUSTOMER_TEMPLATE = "{\n" +
                "\t\"firstName\":\"Moon\",\n" +
                "    \"lastName\":\"Fomeo\",\n" +
                "    \"login\":\"eo@la.com\",\n" +
                "    \"pass\":\"H5teo@la.com\",\n" +
                "    \"balance\":\"0\"\n" +
                "}";

        ClientConfig clientConfig = new ClientConfig();

        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("admin", "setup");
        clientConfig.register(feature) ;

        clientConfig.register(JacksonFeature.class);

        Client client = ClientBuilder.newClient( clientConfig );

        WebTarget webTarget = client.target("http://localhost:8080/intershop/rest").path("customers");

        Invocation.Builder invocationBuilder =	webTarget.request(MediaType.APPLICATION_JSON);
        Logger.debug("Try to make POST...");
        Response response = invocationBuilder.post(Entity.entity(CUSTOMER_TEMPLATE, MediaType.APPLICATION_JSON));
        Logger.info("Response: " + response.readEntity(String.class));
    }

    @Test(description = "Create customer via API with password contains first name")
    public void setCustomerWithPasswordContainsFirstName(){
        String CUSTOMER_TEMPLATE = "{\n" +
                "\t\"firstName\":\"Moon\",\n" +
                "    \"lastName\":\"Fomeo\",\n" +
                "    \"login\":\"m.fomeo@login.com\",\n" +
                "    \"pass\":\"H5tMoon@12\",\n" +
                "    \"balance\":\"0\"\n" +
                "}";

        ClientConfig clientConfig = new ClientConfig();
        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("admin", "setup");
        clientConfig.register(feature) ;
        clientConfig.register(JacksonFeature.class);
        Client client = ClientBuilder.newClient( clientConfig );

        WebTarget webTarget = client.target("http://localhost:8080/intershop/rest").path("customers");

        Invocation.Builder invocationBuilder =	webTarget.request(MediaType.APPLICATION_JSON);
        Logger.debug("Try to make POST...");
        Response response = invocationBuilder.post(Entity.entity(CUSTOMER_TEMPLATE, MediaType.APPLICATION_JSON));
        Logger.info("Response: " + response.readEntity(String.class));
    }

    @Test(description = "Create customer via API with password contains last name")
    public void setCustomerWithPasswordContainsLastName(){
        String CUSTOMER_TEMPLATE = "{\n" +
                "\t\"firstName\":\"Moon\",\n" +
                "    \"lastName\":\"Fomeo\",\n" +
                "    \"login\":\"m.fomeo@login.com\",\n" +
                "    \"pass\":\"H5tFomeo@12\",\n" +
                "    \"balance\":\"0\"\n" +
                "}";

        ClientConfig clientConfig = new ClientConfig();
        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("admin", "setup");
        clientConfig.register(feature) ;
        clientConfig.register(JacksonFeature.class);
        Client client = ClientBuilder.newClient( clientConfig );

        WebTarget webTarget = client.target("http://localhost:8080/intershop/rest").path("customers");

        Invocation.Builder invocationBuilder =	webTarget.request(MediaType.APPLICATION_JSON);
        Logger.debug("Try to make POST...");
        Response response = invocationBuilder.post(Entity.entity(CUSTOMER_TEMPLATE, MediaType.APPLICATION_JSON));
        Logger.info("Response: " + response.readEntity(String.class));
    }

    @Test(description = "Create customer via API with password contains whitespace")
    public void setCustomerWithPasswordContainsWhitespace(){
        String CUSTOMER_TEMPLATE = "{\n" +
                "\t\"firstName\":\"Moon\",\n" +
                "    \"lastName\":\"Fomeo\",\n" +
                "    \"login\":\"m.fomeo@login.com\",\n" +
                "    \"pass\":\"H5tF meo@12\",\n" +
                "    \"balance\":\"0\"\n" +
                "}";

        ClientConfig clientConfig = new ClientConfig();
        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("admin", "setup");
        clientConfig.register(feature) ;
        clientConfig.register(JacksonFeature.class);
        Client client = ClientBuilder.newClient( clientConfig );

        WebTarget webTarget = client.target("http://localhost:8080/intershop/rest").path("customers");

        Invocation.Builder invocationBuilder =	webTarget.request(MediaType.APPLICATION_JSON);
        Logger.debug("Try to make POST...");
        Response response = invocationBuilder.post(Entity.entity(CUSTOMER_TEMPLATE, MediaType.APPLICATION_JSON));
        Logger.info("Response: " + response.readEntity(String.class));
    }

    ////Создание пустого пароля
    @Test(description = "Create customer via API.")
    public void creatingAnEmptyPassword() {
        String CUSTOMER_TEMPLATE = "{\n" +
                "\t\"firstName\":\"Moon\",\n" +
                "    \"lastName\":\"Fomeo\",\n" +
                "    \"login\":\"m.fomeo@login.com\",\n" +
                //"    \"pass\":\"\",\n" +
                "    \"balance\":\"1330\"\n" +
                "}";
        ClientConfig clientConfig = new ClientConfig();

        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("admin", "setup");
        clientConfig.register( feature) ;

        clientConfig.register(JacksonFeature.class);

        Client client = ClientBuilder.newClient( clientConfig );

        WebTarget webTarget = client.target("http://localhost:8080/intershop/rest").path("customers");

        Invocation.Builder invocationBuilder =	webTarget.request(MediaType.APPLICATION_JSON);
        Logger.debug("Try to make POST...");
        Response response = invocationBuilder.post(Entity.entity(CUSTOMER_TEMPLATE, MediaType.APPLICATION_JSON));
        Logger.info("Response: " + response.readEntity(String.class));

    }

    ////Создание пустого логина
    @Test(description = "Create customer via API.")
    public void creatingAnEmptyLogin() {
        String CUSTOMER_TEMPLATE = "{\n" +
                "\t\"firstName\":\"Moon\",\n" +
                "    \"lastName\":\"Fomeo\",\n" +
                //"    \"login\":\"\",\n" +
                "    \"pass\":\"thi9sa@%sS\",\n" +
                "    \"balance\":\"1330\"\n" +
                "}";
        ClientConfig clientConfig = new ClientConfig();

        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("admin", "setup");
        clientConfig.register( feature) ;

        clientConfig.register(JacksonFeature.class);

        Client client = ClientBuilder.newClient( clientConfig );

        WebTarget webTarget = client.target("http://localhost:8080/intershop/rest").path("customers");

        Invocation.Builder invocationBuilder =	webTarget.request(MediaType.APPLICATION_JSON);
        Logger.debug("Try to make POST...");
        Response response = invocationBuilder.post(Entity.entity(CUSTOMER_TEMPLATE, MediaType.APPLICATION_JSON));
        Logger.info("Response: " + response.readEntity(String.class));

    }

    ///Создание короткой фамилии
    @Test(description = "Create customer via API.")
    public void creatShortLastName() {
        String CUSTOMER_TEMPLATE = "{\n" +
                "\t\"firstName\":\"Moon\",\n" +
                "    \"lastName\":\"\",\n" +
                "    \"login\":\"m.fomeo@login.com\",\n" +
                "    \"pass\":\"thi9sa@%sS\",\n" +
                "    \"balance\":\"1330\"\n" +
                "}";
        ClientConfig clientConfig = new ClientConfig();

        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("admin", "setup");
        clientConfig.register( feature) ;

        clientConfig.register(JacksonFeature.class);

        Client client = ClientBuilder.newClient( clientConfig );

        WebTarget webTarget = client.target("http://localhost:8080/intershop/rest").path("customers");

        Invocation.Builder invocationBuilder =	webTarget.request(MediaType.APPLICATION_JSON);
        Logger.debug("Try to make POST...");
        Response response = invocationBuilder.post(Entity.entity(CUSTOMER_TEMPLATE, MediaType.APPLICATION_JSON));
        Logger.info("Response: " + response.readEntity(String.class));

    }

    ///Создание логина
    @Test(description = "Create customer via API.")
    public void creatShortLogin() {
        String CUSTOMER_TEMPLATE = "{\n" +
                "\t\"firstName\":\"Moon\",\n" +
                "    \"lastName\":\"Fomeo\",\n" +
                "    \"login\":\"m.fom\",\n" +
                "    \"pass\":\"thi9sa@%sS\",\n" +
                "    \"balance\":\"1330\"\n" +
                "}";
        ClientConfig clientConfig = new ClientConfig();

        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("admin", "setup");
        clientConfig.register( feature) ;

        clientConfig.register(JacksonFeature.class);

        Client client = ClientBuilder.newClient( clientConfig );

        WebTarget webTarget = client.target("http://localhost:8080/intershop/rest").path("customers");

        Invocation.Builder invocationBuilder =	webTarget.request(MediaType.APPLICATION_JSON);
        Logger.debug("Try to make POST...");
        Response response = invocationBuilder.post(Entity.entity(CUSTOMER_TEMPLATE, MediaType.APPLICATION_JSON));
        Logger.info("Response: " + response.readEntity(String.class));

    }

    ///Создание короткой имени
    @Test(description = "Create customer via API.")
    public void creatShortFirstName() {
        String CUSTOMER_TEMPLATE = "{\n" +
                "\t\"firstName\":\"M\",\n" +
                "    \"lastName\":\"Fomeo\",\n" +
                "    \"login\":\"m.fomeo@login.com\",\n" +
                "    \"pass\":\"thi9sa@%sS\",\n" +
                "    \"balance\":\"1330\"\n" +
                "}";
        ClientConfig clientConfig = new ClientConfig();

        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("admin", "setup");
        clientConfig.register( feature) ;

        clientConfig.register(JacksonFeature.class);

        Client client = ClientBuilder.newClient( clientConfig );

        WebTarget webTarget = client.target("http://localhost:8080/intershop/rest").path("customers");

        Invocation.Builder invocationBuilder =	webTarget.request(MediaType.APPLICATION_JSON);
        Logger.debug("Try to make POST...");
        Response response = invocationBuilder.post(Entity.entity(CUSTOMER_TEMPLATE, MediaType.APPLICATION_JSON));
        Logger.info("Response: " + response.readEntity(String.class));

    }

    ///Создание слишком длинный логин
    @Test(description = "Create customer via API.")
    public void creatLongLogin() {
        String CUSTOMER_TEMPLATE = "{\n" +
                "\t\"firstName\":\"Mfsdsd\",\n" +
                "    \"lastName\":\"Fomeo\",\n" +
                "    \"login\":\"m.fkfg234fdbvJHHGmeo@lsjdksdjkasdogin.com\",\n" +
                "    \"pass\":\"thi9sa@%sS\",\n" +
                "    \"balance\":\"1330\"\n" +
                "}";
        ClientConfig clientConfig = new ClientConfig();

        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("admin", "setup");
        clientConfig.register( feature) ;

        clientConfig.register(JacksonFeature.class);

        Client client = ClientBuilder.newClient( clientConfig );

        WebTarget webTarget = client.target("http://localhost:8080/intershop/rest").path("customers");

        Invocation.Builder invocationBuilder =	webTarget.request(MediaType.APPLICATION_JSON);
        Logger.debug("Try to make POST...");
        Response response = invocationBuilder.post(Entity.entity(CUSTOMER_TEMPLATE, MediaType.APPLICATION_JSON));
        Logger.info("Response: " + response.readEntity(String.class));

    }


    ///Создание пустой фамилии
    @Test(description = "Create customer via API.")
    public void creatingAnEmptyLastNamett() {
        String CUSTOMER_TEMPLATE = "{\n" +
                "\t\"firstName\":\"Moon\",\n" +
                //"    \"lastName\":\"\",\n" +
                "    \"login\":\"m.fomeo@login.com\",\n" +
                "    \"pass\":\"thi9sa@%sS\",\n" +
                "    \"balance\":\"1330\"\n" +
                "}";
        ClientConfig clientConfig = new ClientConfig();

        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("admin", "setup");
        clientConfig.register( feature) ;

        clientConfig.register(JacksonFeature.class);

        Client client = ClientBuilder.newClient( clientConfig );

        WebTarget webTarget = client.target("http://localhost:8080/intershop/rest").path("customers");

        Invocation.Builder invocationBuilder =	webTarget.request(MediaType.APPLICATION_JSON);
        Logger.debug("Try to make POST...");
        Response response = invocationBuilder.post(Entity.entity(CUSTOMER_TEMPLATE, MediaType.APPLICATION_JSON));
        Logger.info("Response: " + response.readEntity(String.class));

    }

    ///Создание пустого имени
    @Test(description = "Create customer via API.")
    public void creatingAnEmptyFirstNamee() {
        String CUSTOMER_TEMPLATE = "{\n" +
                //"\t\"firstName\":\"M\",\n" +
                "    \"lastName\":\"Fomeo\",\n" +
                "    \"login\":\"m.fomeo@login.com\",\n" +
                "    \"pass\":\"thi9sa@%sS\",\n" +
                "    \"balance\":\"1330\"\n" +
                "}";
        ClientConfig clientConfig = new ClientConfig();

        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("admin", "setup");
        clientConfig.register( feature) ;

        clientConfig.register(JacksonFeature.class);

        Client client = ClientBuilder.newClient( clientConfig );

        WebTarget webTarget = client.target("http://localhost:8080/intershop/rest").path("customers");

        Invocation.Builder invocationBuilder =	webTarget.request(MediaType.APPLICATION_JSON);
        Logger.debug("Try to make POST...");
        Response response = invocationBuilder.post(Entity.entity(CUSTOMER_TEMPLATE, MediaType.APPLICATION_JSON));
        Logger.info("Response: " + response.readEntity(String.class));

    }
}
