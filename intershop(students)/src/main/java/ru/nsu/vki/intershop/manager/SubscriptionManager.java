package ru.nsu.vki.intershop.manager;

import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import ru.nsu.vki.intershop.database.IDBService;
import ru.nsu.vki.intershop.database.data.CustomerRepo;
import ru.nsu.vki.intershop.database.data.PlanRepo;
import ru.nsu.vki.intershop.database.data.SubscriptionRepo;

import java.util.List;
import java.util.UUID;

public class SubscriptionManager extends ParentManager {
    public SubscriptionManager(IDBService dbService, Logger flowLog) {
        super(dbService, flowLog);
    }

    /**
     * Метод создает подписку. Ограничения:
     * 1. Подписки с таким планом пользователь не имеет.
     * 2. Стоймость подписки не превышает текущего баланса кастомера и после покупки вычитается из его баласа.
     */
    public SubscriptionRepo createSubscription(SubscriptionRepo subscription) {
        Validate.notNull(subscription);
        Validate.notNull(subscription.getCustomerId());
        Validate.notNull(subscription.getPlanId());
        Validate.isTrue(!dbService.isSubscriptionWithCustomerIdAndPlanIdExist(subscription.getCustomerId(), subscription.getPlanId()));
        CustomerRepo customer = dbService.getCustomerById(subscription.getCustomerId());
        PlanRepo plan = dbService.getPlanById(subscription.getPlanId());
        Validate.isTrue(customer.getBalance() >= plan.getFee());
        SubscriptionRepo s = dbService.createSubscription(subscription);
        dbService.updateCustomerBalance(s.getCustomerId(), -1*plan.getFee());
        return s;
    }

    public void removeSubscription(UUID subscriptionId) {
        dbService.removeSubscription(subscriptionId);
    }

    public UUID getSubscriptionIdByCIDAndPID(UUID customerId, UUID planId) {
        return dbService.getSubscriptionByCustomerIdAndPlanId(customerId, planId);
    }



    /**
     * Возвращает список подписок указанного customer'а.
     */
    public List<SubscriptionRepo> getSubscriptionsByCID(UUID customerId) {
        return dbService.getSubscriptions();
    }


    public List<SubscriptionRepo> getSubscriptions() {
        return dbService.getSubscriptions();
    }
}
