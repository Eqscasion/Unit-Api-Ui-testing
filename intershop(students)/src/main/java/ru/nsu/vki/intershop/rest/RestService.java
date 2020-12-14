package ru.nsu.vki.intershop.rest;

import org.apache.commons.lang.exception.ExceptionUtils;

import org.apache.commons.lang.Validate;
import ru.nsu.vki.intershop.MainFactory;
import ru.nsu.vki.intershop.database.data.*;
import ru.nsu.vki.intershop.shared.Globals;
import ru.nsu.vki.intershop.shared.JsonMapper;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Path("")
public class RestService {
    //private static final Logger logger = MainFactory.getInstance().getLogger();
    private static final String CUSTOMER_PATH = "/customer";
    private static final String PLAN_PATH = "/plan";
    private static final String SUBSCRIPTION_PATH = "/subscription";

    @RolesAllowed({ Globals.UNKNOWN_ROLE, Globals.ADMIN_ROLE })
    @GET
    @Path("/health_check")
    public Response healthCheck() {
        try {
            return Response.ok().entity(JsonMapper.toJson(new HealthCheckRepo(), true)).build();
        } catch (IOException ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage() + "\n" + ExceptionUtils.getFullStackTrace(ex)).build();
        }
    }

    @RolesAllowed({ Globals.UNKNOWN_ROLE , Globals.ADMIN_ROLE })
    @GET
    @Path("/role")
    public Response getRole(@Context ContainerRequestContext crc) {
        RoleRepo roleRepo = new RoleRepo();
        roleRepo.role = crc.getProperty("ROLE").toString();

        try {
            return Response.ok().entity(JsonMapper.toJson(roleRepo, true)).build();
        } catch (IOException ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage() + "\n" + ExceptionUtils.getFullStackTrace(ex)).build();
        }
    }

    // Example request: ../customers?login='john_wick@gmail.com'
    @RolesAllowed(Globals.ADMIN_ROLE)
    @GET
    @Path("/customers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomers(@DefaultValue("") @QueryParam("login") String customerLogin) {
        try {
            List<CustomerRepo> customers = MainFactory.getInstance()
                    .getCustomerManager()
                    .getCustomers().stream()
                    .filter(x -> customerLogin.isEmpty() || x.getLogin().equals(customerLogin))
                    .collect(Collectors.toList());

            return Response.ok().entity(JsonMapper.toJson(customers, true)).build();
        } catch (IllegalArgumentException | IOException ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage() + "\n" + ExceptionUtils.getFullStackTrace(ex)).build();
        }
    }

    @RolesAllowed(Globals.ADMIN_ROLE)
    @POST
    @Path("/customers")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCustomer(String customerDataJson) {
        try {
            // convert json to object
            CustomerRepo customerData = JsonMapper.fromJson(customerDataJson, CustomerRepo.class);

            // create new customer
            CustomerRepo customer = MainFactory.getInstance().getCustomerManager().createCustomer(customerData);

            // send the answer
            return Response.ok().entity(JsonMapper.toJson(customer, true)).build();
        } catch (IllegalArgumentException | IOException ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage() + "\n" + ExceptionUtils.getFullStackTrace(ex)).build();
        }
    }

    @RolesAllowed(Globals.ADMIN_ROLE)
    @GET
    @Path("/get_customer_id/{customer_login}")
    public Response getCustomerId(@PathParam("customer_login") String customerLogin) {
        try {
            Loggers.info(String.format("getCustomerId customerLogin=%s", customerLogin));

            UUID customerId = MainFactory.getInstance().getCustomerManager().getCustomerIdByLogin(customerLogin);

            Loggers.info(String.format("getCustomerId customerId=%s", customerLogin));

            return Response.ok().entity(JsonMapper.toJson(customerId, true)).build();
        } catch (IllegalArgumentException | IOException ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage() + "\n" + ExceptionUtils.getFullStackTrace(ex)).build();
        }
    }

    @RolesAllowed(Globals.UNKNOWN_ROLE)
    @POST
    @Path("update_customer_profile/{customer_login}")
    public Response updateCustomerProfile(@PathParam("customer_login") String customerLogin, @QueryParam("first_name") String firstName, @QueryParam("last_name") String lastName) {
        try {
            Loggers.info(String.format("updateCustomerProfile customerLogin=%s firstName=%s lastName=%s", customerLogin, firstName, lastName));

            UUID customerId = MainFactory.getInstance().getCustomerManager().getCustomerIdByLogin(customerLogin);
            Validate.notNull(customerId);
            CustomerRepo customer = MainFactory.getInstance().getCustomerManager().updateCustomer(new CustomerRepo().setId(customerId).setFirstName(firstName).setLastName(lastName));

            Loggers.info(String.format("updateCustomProfile customer=%s", customer));

            return Response.ok().entity(JsonMapper.toJson(customer, true)).build();
        } catch (IllegalArgumentException | IOException ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage() + "\n" + ExceptionUtils.getFullStackTrace(ex)).build();
        }
    }

    ////////////////
    ///PLANS///
    ///////////////

    @RolesAllowed(Globals.ADMIN_ROLE)
    @POST
    @Path("/plans")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPlan(String planDataJson) {
        try {
            // convert json to object
            PlanRepo planData = JsonMapper.fromJson(planDataJson, PlanRepo.class);

            //create new plan
            PlanRepo plan = MainFactory.getInstance().getPlanManager().createPlan(planData);

            // send the answer
            return Response.ok().entity(JsonMapper.toJson(plan, true)).build();
        } catch (IllegalArgumentException | IOException ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage() + "\n" + ExceptionUtils.getFullStackTrace(ex)).build();
        }
    }

    @RolesAllowed(Globals.ADMIN_ROLE)
    @GET
    @Path("/get_plan_id/{plan_name}")
    public Response getPlanId(@PathParam("plan_name") String planName) {
        try {
            Loggers.info(String.format("getPlanId planName=%s", planName));
            UUID planId = MainFactory.getInstance().getPlanManager().getPlanIdByName(planName);
            Loggers.info(String.format("getPlanId planId=%s", planId));
            return Response.ok().entity(JsonMapper.toJson(planId, true)).build();
        } catch (IllegalArgumentException | IOException ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage() + "\n" + ExceptionUtils.getFullStackTrace(ex)).build();
        }
    }
    @RolesAllowed(Globals.ADMIN_ROLE)
    @GET
    @Path(PLAN_PATH)
    public Response getPlans() {
        try {
            Loggers.info("getPlans");

            List<PlanRepo> plans = MainFactory.getInstance().getPlanManager().getPlans();

            Loggers.info(String.format("getPlans plans=%s", plans));

            return Response.ok().entity(JsonMapper.toJson(plans, true)).build();
        } catch (IllegalArgumentException | IOException ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage() + "\n" + ExceptionUtils.getFullStackTrace(ex)).build();
        }
    }


    ////////////////
    ///TopBalance///
    ///////////////
    @RolesAllowed(Globals.UNKNOWN_ROLE)
    @POST
    @Path("top_up_balance/{customer_login}")
    public Response topUpBalance(@PathParam("customer_login") String customerLogin, @QueryParam("amount") int amount) {
        try {
            Loggers.info(String.format("topUpBalance customerLogin=%s amount=%s", customerLogin, amount));

            UUID customerId = MainFactory.getInstance().getCustomerManager().getCustomerIdByLogin(customerLogin);
            MainFactory.getInstance().getCustomerManager().topUpBalance(customerId, amount);

            return Response.ok().build();
        } catch (IllegalArgumentException ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage() + "\n" + ExceptionUtils.getFullStackTrace(ex)).build();
        }
    }

    @RolesAllowed(Globals.UNKNOWN_ROLE)
    @GET
    @Path(SUBSCRIPTION_PATH)
    public Response getSubscriptions() {
        try {
            Loggers.info("getSubscriptions");

            List<SubscriptionRepo> subscriptions = MainFactory.getInstance().getSubscriptionManager().getSubscriptions();

            Loggers.info(String.format("getSubscriptions subscriptions=%s", subscriptions));

            return Response.ok().entity(JsonMapper.toJson(subscriptions, true)).build();
        } catch (IllegalArgumentException | IOException ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage() + "\n" + ExceptionUtils.getFullStackTrace(ex)).build();
        }
    }

    @RolesAllowed(Globals.UNKNOWN_ROLE)
    @GET
    @Path("get_customer_subscriptions/{customer_login}")
    public Response getCustomerSubscriptions(@PathParam("customer_login") String customerLogin) {
        try {
            Loggers.info(String.format("getCustomerSubscriptions customerLogin=%s", customerLogin));

            UUID customerId = MainFactory.getInstance().getCustomerManager().getCustomerIdByLogin(customerLogin);
            Validate.notNull(customerId);
            List<SubscriptionRepo> subscriptions = MainFactory.getInstance().getSubscriptionManager().getSubscriptionsByCID(customerId);

            Loggers.info(String.format("getCustomerSubscriptions subscriptions=%s", subscriptions));

            return Response.ok().entity(JsonMapper.toJson(subscriptions, true)).build();
        } catch (IllegalArgumentException | IOException ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage() + "\n" + ExceptionUtils.getFullStackTrace(ex)).build();
        }
    }

    @RolesAllowed(Globals.UNKNOWN_ROLE)
    @POST
    @Path(SUBSCRIPTION_PATH)
    public Response createSubscription(String subscriptionDataJson) {
        try {
            Loggers.info(String.format("createSubscription subscriptionDataJson=%s", subscriptionDataJson));

            SubscriptionRepo subscriptionData = JsonMapper.fromJson(subscriptionDataJson, SubscriptionRepo.class);
            SubscriptionRepo subscription = MainFactory.getInstance().getSubscriptionManager().createSubscription(subscriptionData);

            Loggers.info(String.format("createSubscription subscription=%s", subscription));

            return Response.ok().entity(JsonMapper.toJson(subscription, true)).build();
        } catch (IllegalArgumentException | IOException ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage() + "\n" + ExceptionUtils.getFullStackTrace(ex)).build();
        }
    }

    @RolesAllowed(Globals.UNKNOWN_ROLE)
    @DELETE
    @Path(SUBSCRIPTION_PATH)
    public Response deleteSubscription(@QueryParam("customerLogin") String customerLogin, @QueryParam("planName") String planName) {
        try {
            Loggers.info(String.format("deleteSubscription customerLogin=%s planName=%s", customerLogin, planName));

            UUID customerId = MainFactory.getInstance().getCustomerManager().getCustomerIdByLogin(customerLogin);
            Validate.notNull(customerId);
            UUID planId = MainFactory.getInstance().getPlanManager().getPlanIdByName(planName);
            Validate.notNull(planId);
            UUID subscriptionId = MainFactory.getInstance().getSubscriptionManager().getSubscriptionIdByCIDAndPID(customerId, planId);
            Validate.notNull(subscriptionId);
            MainFactory.getInstance().getSubscriptionManager().removeSubscription(subscriptionId);

            return Response.ok().build();
        } catch (IllegalArgumentException ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage() + "\n" + ExceptionUtils.getFullStackTrace(ex)).build();
        }
    }


}