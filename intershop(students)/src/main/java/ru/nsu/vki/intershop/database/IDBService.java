package ru.nsu.vki.intershop.database;

import ru.nsu.vki.intershop.database.data.CustomerRepo;
import ru.nsu.vki.intershop.database.data.PlanRepo;
import ru.nsu.vki.intershop.database.data.SubscriptionRepo;

import java.util.List;
import java.util.UUID;

public interface IDBService {
    CustomerRepo createCustomer(CustomerRepo customerData);
    CustomerRepo updateCustomer(CustomerRepo customerData);
    CustomerRepo getCustomerById(UUID customerId);
    List<CustomerRepo> getCustomers();
    void updateCustomerBalance(UUID customerId, int amount);
    UUID getCustomerIdByLogin(String customerLogin);

    //Plan
    PlanRepo createPlan(PlanRepo planRepo);
    PlanRepo updatePlan(PlanRepo planRepo);
    void removePlan(UUID id);
    UUID getPlanIdByName(String name);
    PlanRepo getPlanById(UUID planId);
    List <PlanRepo> getPlans();
    Boolean isPlanWithNameExist(String name);

    //Subs
    SubscriptionRepo createSubscription(SubscriptionRepo subscription);
    boolean isSubscriptionWithCustomerIdAndPlanIdExist(UUID customerId, UUID planId);
    List<SubscriptionRepo> getSubscriptionsByCid(UUID customerId);
    List<SubscriptionRepo> getSubscriptions();
    void removeSubscription(UUID id);
    UUID getSubscriptionByCustomerIdAndPlanId(UUID customerId, UUID planId);
}
