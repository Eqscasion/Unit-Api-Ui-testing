package ru.nsu.vki.intershop.database;

import jersey.repackaged.com.google.common.collect.Lists;
import org.slf4j.Logger;
import ru.nsu.vki.intershop.database.data.CustomerRepo;
import ru.nsu.vki.intershop.database.data.PlanRepo;
import ru.nsu.vki.intershop.database.data.SubscriptionRepo;

import java.sql.*;
import java.util.List;
import java.util.UUID;

public class DBService implements IDBService{
    // Constants
    private static final String INSERT_CUSTOMER = "INSERT INTO CUSTOMER(id, first_name, last_name, login, pass, balance) values ('%s', '%s', '%s', '%s', '%s', %s)";
    private static final String INSERT_SUBSCRIPTION = "INSERT INTO SUBSCRIPTION(id, customer_id, plan_id) values ('%s', '%s', '%s')";
    private static final String INSERT_PLAN = "INSERT INTO PLAN(id, name, details, fee) values ('%s', '%s', '%s', %s)";

    private static final String SELECT_CUSTOMER_BY_ID = "SELECT 1 FROM CUSTOMER WHERE id=%s";
    private static final String SELECT_CUSTOMER_BY_LOGIN = "SELECT id FROM CUSTOMER WHERE login='%s'";
    private static final String SELECT_CUSTOMERS = "SELECT * FROM CUSTOMER";
    private static final String REMOVE_CUSTOMER = "DELETE FROM CUSTOMER WHERE id='%s'";
    private static final String UPDATE_CUSTOMER_BY_ID = "UPDATE CUSTOMER SET first_name = '%s', last_name = '%s', login = '%s', pass = '%s' WHERE id='%s'";
    private static final String UPDATE_CUSTOMER_BALANCE = "UPDATE CUSTOMER SET balance = balance + %s WHERE id='%s'";

    private static final String SELECT_SUBSCRIPTIONS = "SELECT * FROM SUBSCRIPTION";
    private static final String SELECT_SUBSCRIPTIONS_BY_CID = "SELECT * FROM SUBSCRIPTION WHERE customer_id=%s";
    private static final String SELECT_SUBSCRIPTION_BY_CID_PID = "SELECT id FROM SUBSCRIPTION WHERE customer_id=%s AND plan_id=%s";
    private static final String REMOVE_SUBSCRIPTION = "DELETE FROM SUBSCRIPTION WHERE id='%s'";

    private static final String SELECT_PLANS = "SELECT * FROM PLAN";
    private static final String SELECT_PLAN_BY_NAME = "SELECT id FROM PLAN WHERE name='%s'";
    private static final String SELECT_PLAN_BY_ID = "SELECT 1 FROM PLAN WHERE id=%s";
    private static final String REMOVE_PLAN = "DELETE FROM PLAN WHERE id='%s'";
    private static final String UPDATE_PLAN_BY_ID = "UPDATE PLAN SET name = '%s', details = '%s', fee = '%s' WHERE id='%s'";


    private Logger logger;
    private static final Object generalMutex = new Object();
    private Connection connection;

    public DBService(Logger logger) {
        this.logger = logger;
        init();
    }

    public CustomerRepo createCustomer(CustomerRepo customerData) {
        synchronized (generalMutex) {
            logger.info(String.format("Method 'createCustomer' was called with data: '%s'", customerData));
            customerData.setId(UUID.randomUUID());
            try {
                Statement statement = connection.createStatement();
                statement.executeUpdate(
                        String.format(
                                INSERT_CUSTOMER,
                                customerData.getId(),
                                customerData.getFirstName(),
                                customerData.getLastName(),
                                customerData.getLogin(),
                                customerData.getPass(),
                                customerData.getBalance()));
                return customerData;
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
                throw new RuntimeException(ex);
            }
        }
    }

    public List<CustomerRepo> getCustomers() {
        synchronized (generalMutex) {
            logger.info("Method 'getCustomers' was called.");

            try {
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(SELECT_CUSTOMERS);
                List<CustomerRepo> result = Lists.newArrayList();
                while (rs.next()) {
                    CustomerRepo customerData = new CustomerRepo()
                            .setFirstName(rs.getString(2))
                            .setLastName(rs.getString(3))
                            .setLogin(rs.getString(4))
                            .setPass(rs.getString(5))
                            .setBalance(rs.getInt(6));

                    result.add(customerData);
                }
                return result;
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
                throw new RuntimeException(ex);
            }
        }
    }

    public CustomerRepo getCustomerById(UUID customerId) {
        synchronized (generalMutex) {
            logger.info(String.format("Method 'getCustomerById' was called with customerId '%s'.", customerId));

            try {
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(
                        String.format(
                                SELECT_CUSTOMER_BY_ID,
                                customerId));
                if (rs.next()) {
                    return new CustomerRepo()
                            .setFirstName(rs.getString(2))
                            .setLastName(rs.getString(3))
                            .setLogin(rs.getString(4))
                            .setPass(rs.getString(5))
                            .setBalance(rs.getInt(6));
                } else {
                    throw new IllegalArgumentException("Customer with id '" + customerId + " was not found");
                }
            } catch (SQLException ex) {
                logger.debug(ex.getMessage(), ex);
                throw new RuntimeException(ex);
            }
        }
    }
    public UUID getCustomerIdByLogin(String customerLogin) {
        synchronized (generalMutex) {
            logger.info(String.format("Method 'getCustomerIdByLogin' was called with data '%s'.", customerLogin));

            try {
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(
                        String.format(
                                SELECT_CUSTOMER_BY_LOGIN,
                                customerLogin));
                if (rs.next()) {
                    return UUID.fromString(rs.getString(1));
                } else {
                    throw new IllegalArgumentException("Customer with login '" + customerLogin + " was not found");
                }
            } catch (SQLException ex) {
                logger.debug(ex.getMessage(), ex);
                throw new RuntimeException(ex);
            }
        }
    }
    public CustomerRepo updateCustomer(CustomerRepo customerData) {
        synchronized (generalMutex) {
            logger.info(String.format("Method 'updateCustomerData' was called with data: '%s'", customerData));

            try {
                Statement statement = this.connection.createStatement();
                statement.executeUpdate(
                        String.format(
                                UPDATE_CUSTOMER_BY_ID,
                                customerData.getFirstName(),
                                customerData.getLastName(),
                                customerData.getLogin(),
                                customerData.getPass(),
                                customerData.getId()));
                return customerData;
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
                throw new RuntimeException(ex);
            }
        }
    }

    public void updateCustomerBalance(UUID customerId, int amount) {
        synchronized (generalMutex) {
            logger.info(String.format("Method 'updateCustomerBalance' was called with id: '%s', amount: %s", customerId, amount));

            try {
                Statement statement = connection.createStatement();
                statement.executeUpdate(
                        String.format(
                                UPDATE_CUSTOMER_BALANCE,
                                amount,
                                customerId.toString()));
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
                throw new RuntimeException(ex);
            }
        }
    }
    public void removeCustomer(UUID id) {
        synchronized (generalMutex) {
            logger.info(String.format("Method 'removeCustomer' was called with id: '%s'", id.toString()));

            try {
                Statement statement = connection.createStatement();
                statement.executeUpdate(
                        String.format(
                                REMOVE_CUSTOMER,
                                id.toString()));
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
                throw new RuntimeException(ex);
            }
        }
    }

    //////////////////
    ///PLANS/////////
    ////////////////
    public PlanRepo createPlan(PlanRepo planData) {
        synchronized (generalMutex) {
            logger.info(String.format("Method 'createPlan' was called with data '%s'.", planData));
            planData.setId(UUID.randomUUID());
            try {
                Statement statement = connection.createStatement();
                statement.executeUpdate(
                        String.format(
                                INSERT_PLAN,
                                planData.getId(),
                                planData.getName(),
                                planData.getDetails(),
                                planData.getFee()));
                return planData;
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
                throw new RuntimeException(ex);
            }
        }
    }
    public PlanRepo getPlanById(UUID planId) {
        synchronized (generalMutex) {
            logger.info(String.format("Method 'getPlanById' was called with planId '%s'.", planId));

            try {
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(
                        String.format(
                                SELECT_PLAN_BY_ID,
                                planId));
                if (rs.next()) {
                    return new PlanRepo()
                            .setName(rs.getString(2))
                            .setDetails(rs.getString(3))
                            .setFee(rs.getInt(4));
                } else {
                    throw new IllegalArgumentException("Plan with id '" + planId + " was not found");
                }
            } catch (SQLException ex) {
                logger.debug(ex.getMessage(), ex);
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public UUID getPlanIdByName(String planName) {
        synchronized (generalMutex) {
            logger.info(String.format("Method 'getPlanIdByName' was called with data '%s'.", planName));

            try {
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(
                        String.format(
                                SELECT_PLAN_BY_NAME,
                                planName));
                if (rs.next()) {
                    return UUID.fromString(rs.getString(1));
                } else {
                    throw new IllegalArgumentException("Plan with name '" + planName + " was not found");
                }
            } catch (SQLException ex) {
                logger.debug(ex.getMessage(), ex);
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public List<PlanRepo> getPlans() {
        synchronized (generalMutex) {
            logger.info("Method 'getPlans' was called.");

            try {
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(SELECT_PLANS);
                List<PlanRepo> result = Lists.newArrayList();
                while (rs.next()) {
                    PlanRepo planData = new PlanRepo()
                            .setName(rs.getString(2))
                            .setDetails(rs.getString(3))
                            .setFee(rs.getInt(4));

                    result.add(planData);
                }
                return result;
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
                throw new RuntimeException(ex);
            }
        }
    }
    @Override
    public PlanRepo updatePlan(PlanRepo planRepo) {
        synchronized (generalMutex) {
            logger.info(String.format("Method 'updatePlan' was called with data: '%s'", planRepo));
            try {
                Statement statement = connection.createStatement();
                statement.executeUpdate(
                        String.format(
                                UPDATE_PLAN_BY_ID,
                                planRepo.getName().replace("'", "\\'"),
                                planRepo.getDetails().replace("'", "\\'"),
                                planRepo.getFee(),
                                planRepo.getId()));
                return planRepo;
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
                throw new RuntimeException(ex);
            }
        }
    }

    public void removePlan(UUID id) {
        synchronized (generalMutex) {
            logger.info(String.format("Method 'removePlan' was called with id: '%s'", id.toString()));

            try {
                Statement statement = connection.createStatement();
                statement.executeUpdate(
                        String.format(
                                REMOVE_PLAN,
                                id.toString()));
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
                throw new RuntimeException(ex);
            }
        }
    }


    @Override
    public Boolean isPlanWithNameExist(String planName) {
        logger.info(String.format("Method 'isPlanWithNameExist' was called with data '%s'.", planName));
        try {
            UUID uuid = this.getPlanIdByName(planName);
            return uuid != null;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public SubscriptionRepo createSubscription(SubscriptionRepo subscriptionData) {
        synchronized (generalMutex) {
            logger.info(String.format("Method 'createSubscription' was called with data: '%s'", subscriptionData));

            subscriptionData.setId(UUID.randomUUID());
            try {
                Statement statement = connection.createStatement();
                statement.executeUpdate(
                        String.format(
                                INSERT_SUBSCRIPTION,
                                subscriptionData.getId(),
                                subscriptionData.getCustomerId(),
                                subscriptionData.getPlanId()));
                return subscriptionData;
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
                throw new RuntimeException(ex);
            }
        }
    }
    public void removeSubscription(UUID id) {
        synchronized (generalMutex) {
            logger.info(String.format("Method 'removeSubscription' was called with id: '%s'", id.toString()));

            try {
                Statement statement = connection.createStatement();
                statement.executeUpdate(
                        String.format(
                                REMOVE_SUBSCRIPTION,
                                id.toString()));
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
                throw new RuntimeException(ex);
            }
        }
    }
    public List<SubscriptionRepo> getSubscriptions() {
        synchronized (generalMutex) {
            logger.info("Method 'getSubscriptions' was called.");

            try {
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(SELECT_SUBSCRIPTIONS);
                List<SubscriptionRepo> result = Lists.newArrayList();
                while (rs.next()) {
                    SubscriptionRepo subscriptionData = new SubscriptionRepo()
                            .setCustomerId(UUID.fromString(rs.getString(2)))
                            .setPlanId(UUID.fromString(rs.getString(3)));
                    result.add(subscriptionData);
                }
                return result;
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
                throw new RuntimeException(ex);
            }
        }
    }

    public List<SubscriptionRepo> getSubscriptionsByCid(UUID customerId) {
        synchronized (generalMutex) {
            logger.info("Method 'getSubscriptionsByCid' was called.");

            try {
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(String.format(SELECT_SUBSCRIPTIONS_BY_CID, customerId));
                List<SubscriptionRepo> result = Lists.newArrayList();
                while (rs.next()) {
                    SubscriptionRepo subscriptionData = new SubscriptionRepo()
                            .setCustomerId(UUID.fromString(rs.getString(2)))
                            .setPlanId(UUID.fromString(rs.getString(3)));
                    result.add(subscriptionData);
                }
                return result;
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
                throw new RuntimeException(ex);
            }
        }
    }

    public UUID getSubscriptionByCustomerIdAndPlanId(UUID customerId, UUID planId) {
        synchronized (generalMutex) {
            logger.info(String.format("Method 'getSubscriptionByCustomerIdAndPlanId' was called with customerId %s and planId %s.", customerId, planId));

            try {
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(
                        String.format(
                                SELECT_SUBSCRIPTION_BY_CID_PID,
                                customerId,
                                planId));
                if (rs.next()) {
                    return UUID.fromString(rs.getString(1));
                } else {
                    throw new IllegalArgumentException("Subscription with customerId: " + customerId +  "and planId: " + planId + " was not found");
                }
            } catch (SQLException ex) {
                logger.debug(ex.getMessage(), ex);
                throw new RuntimeException(ex);
            }
        }
    }

    public boolean isSubscriptionWithCustomerIdAndPlanIdExist(UUID customerId, UUID planId) {

        logger.info(String.format("Method 'isSubscriptionWithCustomerIdAndPlanIdExist' was called with customerId %s and planId %s.", customerId, planId));

        try {
            UUID uuid = this.getSubscriptionByCustomerIdAndPlanId(customerId, planId);
            return uuid != null;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private void init() {
        logger.debug("-------- MySQL JDBC Connection Testing ------------");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            logger.debug("Where is your MySQL JDBC Driver?", ex);
            throw new RuntimeException(ex);
        }

        logger.debug("MySQL JDBC Driver Registered!");

        try {
            connection = DriverManager
                    .getConnection(
                            "jdbc:mysql://localhost:3306/testmethods?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false",
                            "user",
                            "user");
        } catch (SQLException ex) {
            logger.error("Connection Failed! Check output console", ex);
            throw new RuntimeException(ex);
        }

        if (connection != null) {
            logger.debug("You made it, take control your database now!");
        } else {
            logger.error("Failed to make connection!");
        }
    }
}
