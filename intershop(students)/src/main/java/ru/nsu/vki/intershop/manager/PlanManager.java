package ru.nsu.vki.intershop.manager;

import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import ru.nsu.vki.intershop.database.IDBService;
import ru.nsu.vki.intershop.database.data.PlanRepo;

import java.util.List;
import java.util.UUID;

public class PlanManager extends ParentManager {
    public PlanManager(IDBService dbService, Logger flowLog) {
        super(dbService, flowLog);
    }
  //  private static Pattern namePattern = Pattern.compile("^[A-Za-z]+");
    /**
     * Метод создает новый объект типа Plan. Ограничения:
     * name - длина не больше 128 символов и не меньше 2 включительно не содержит спец символов. Имена не пересекаются друг с другом;
    /* details - длина не больше 1024 символов и не меньше 1 включительно;
    /* fee - больше либо равно 0 но меньше либо равно 999999.
     */
    public PlanRepo createPlan(PlanRepo plan) {
        Validate.notNull(plan, "Argument 'planData' is null.");

        Validate.notNull(plan.getName());
        Validate.isTrue(plan.getName().length() >= 2 && plan.getName().length() <= 128, "Plan name length must be from 2 to 128");

        Validate.notNull(plan.getDetails());
        Validate.isTrue(plan.getDetails().length() >= 1 && plan.getName().length() <= 1024, "Plan details length must be from 1 to 1024");

        Validate.isTrue(plan.getFee() >= 0 && plan.getFee() <= 999999, "Plan fee must be from 0 to 999999");

        return dbService.createPlan(plan);
    }

    public PlanRepo updatePlan(PlanRepo plan)
    {
        ///Надо дописать методы (студенты)
        return dbService.updatePlan(plan);
    }

    public void removePlan(UUID id) { dbService.removePlan(id);  }

    /**
     * Метод возвращает список планов доступных для покупки.
     */
    public List<PlanRepo> getPlans() {
        return dbService.getPlans();
    }

    public UUID getPlanIdByName(String name) {
        return dbService.getPlanIdByName(name);
    }


}
