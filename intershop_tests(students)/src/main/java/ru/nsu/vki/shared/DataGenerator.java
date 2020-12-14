package ru.nsu.vki.shared;

import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;
import ru.nsu.vki.services.rest.dto.CustomerDto;
import ru.nsu.vki.services.rest.dto.UserDto;

import java.util.Random;

/**
 * @author Alina Mozhina (mohinalina@gmail.com).
 */
public class DataGenerator {
    private static final String COMPANY_ADMIN_ROLE = "Company administrator";
    private static final String TECHNICAL_ADMIN_ROLE = "Technical administrator";
    private static final String BILLING_ADMIN_ROLE = "Billing administrator";
    private static final String USER_ROLE = "User";

    private static final String[] USER_ROLES = { COMPANY_ADMIN_ROLE, TECHNICAL_ADMIN_ROLE,
            BILLING_ADMIN_ROLE, USER_ROLE };

    public static CustomerDto getCustomerDto() {
        Person person = Fairy.create().person();
        Random random = new Random();

        return new CustomerDto().setFirstName(person.firstName())
                .setLastName(person.lastName())
                .setLogin(person.email())
                .setPass(person.password())
                .setMoney(random.nextInt(1000));
    }

    public static UserDto getUserDto() {
        Person person = Fairy.create().person();
        Random random = new Random();

        return new UserDto().setFirstName(person.firstName())
                .setLastName(person.lastName())
                .setLogin(person.email())
                .setPassword(person.password())
                .setUserRole(USER_ROLES[random.nextInt(4)]);
    }

    public static String getCompanyName() {
        return Fairy.create().company().name().replace(".", "");
    }
}

