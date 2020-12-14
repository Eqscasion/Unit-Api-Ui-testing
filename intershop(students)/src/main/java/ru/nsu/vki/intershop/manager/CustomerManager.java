package ru.nsu.vki.intershop.manager;

import org.apache.commons.lang.NotImplementedException;
import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import ru.nsu.vki.intershop.database.IDBService;
import ru.nsu.vki.intershop.database.data.CustomerRepo;

import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerManager extends ParentManager {
    public CustomerManager(IDBService dbService, Logger flowLog) {
        super(dbService, flowLog);
    }
    private static final String passSize = "length should be more or equal to 6 symbols and less or equal to 12 symbols.";
    private static final String symbolsSize = "length should be more or equal to 2 symbols and less or equal to 12 symbols.";
    private static final String logSize = "length should be more or equal to 6 symbols and less or equal to 30 symbols.";

    /**
     * Метод создает новый объект типа Customer. Ограничения:
     * Аргумент 'customerData' - не null;
     * firstName - нет пробелов, длина от 2 до 12 символов включительно, начинается с заглавной буквы, остальные символы строчные, нет цифр и других символов;
     * lastName - нет пробелов, длина от 2 до 12 символов включительно, начинается с заглавной буквы, остальные символы строчные, нет цифр и других символов;
     * login - указывается в виде email, проверить email на корректность, проверить что нет customer с таким же email;
     * pass - длина от 6 до 12 символов включительно, не должен быть простым (123qwe или 1q2w3e), не должен содержать части login, firstName, lastName
     * money - должно быть равно 0.
     */
    public CustomerRepo createCustomer(CustomerRepo customer) {
        //Validate.notNull(customer, "Argument 'customerData' is null.");
        //Validate.notNull(customer.getFirstName(),"FirstName is null.");
        //Validate.notNull(customer.getLastName(),"LastName is null.");
        //Validate.notNull(customer.getPass(),"Password is null.");
        //Validate.notNull(customer.getLogin(),"Login is null.");
        //Validate.isTrue(customer.getFirstName().length() >= 2 && customer.getFirstName().length() < 13, String.format("FirstName's %s ",this.symbolsSize));
        //Validate.isTrue(customer.getLastName().length() >= 2 && customer.getLastName().length() < 13, String.format("LastName's length should be more or equal 2 symbols and less or equal 12 symbols. %s ",this.symbolsSize));
        //Validate.isTrue(customer.getLogin().length() >= 6 && customer.getLogin().length() < 30, String.format("The login length must be greater than or equal to 6 characters and less than or equal to 30 characters. %s ",this.logSize));
        //Validate.isTrue(customer.getPass().length() >= 6 && customer.getPass().length() < 13, String.format("Password's %s ",this.passSize));
        //Validate.isTrue(!customer.getPass().equalsIgnoreCase("123qwe"), "Password is easy.");

        Validate.notNull(customer, "Argument 'customerData' is null.");

        Validate.notNull(customer.getFirstName(),"FirstName is null");
        Validate.isTrue(customer.getFirstName().length() >= 2 && customer.getFirstName().length() < 13, String.format("FirstName's length should be more or equal 2 symbols and less or equal 12 symbols. %s ",this.symbolsSize));
        Validate.notNull(customer.getPass(),"Password is null");
        Validate.isTrue(customer.getPass().length() >= 6 && customer.getPass().length() < 13, String.format("Password's length should be more or equal 6 symbols and less or equal 12 symbols. %s ",this.passSize));
        Validate.isTrue(!customer.getPass().equalsIgnoreCase("123qwe"), "Password is easy.");

        // TODO: необходимо дописать дополнительные проверки
        Validate.notNull(customer.getLastName(),"LastName is null");
        Validate.isTrue(customer.getLastName().length() >= 2 && customer.getLastName().length() < 13, String.format("LastName's length should be more or equal 2 symbols and less or equal 12 symbols. %s ",this.symbolsSize));


        Validate.notNull(customer.getLogin(),"Login is null");
        Validate.isTrue(customer.getLogin().length() >= 6 && customer.getLogin().length() < 31, String.format("The login length must be greater than or equal to 6 characters and less than or equal to 30 characters. %s ",this.logSize));



        // TODO: необходимо дописать дополнительные проверки

        /*
         * a digit must occur at least once
         * a lower case letter must occur at least once
         * an upper case letter must occur at least once
         * a special character must occur at least once
         * no whitespace allowed in the entire string
         * anything, at least six places though
         */
        Validate.isTrue(customer.getPass().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$"), "Password should have lowercase, uppercase letters, numbers & special characters and not whitespace.");
        // Пароль не должен совпадать с логином, именем и фамилией.
        Validate.isTrue(!customer.getPass().contains(customer.getLogin()), "Password must not match Login.");
        Validate.isTrue(!customer.getPass().contains(customer.getFirstName()), "Password must not match FirstName.");
        Validate.isTrue(!customer.getPass().contains(customer.getLastName()), "Password must not match LastName.");
        // Login должен быть email
        Validate.isTrue(validateEmail(customer.getLogin()), "Login must be a valid email address.");
        // FirstName и LastName начинается с заглавной буквы, остальные символы строчные, нет цифр и других символов;
        Validate.isTrue(!customer.getFirstName().matches("([A-Z][a-z]*){2,}"), "FirstName must start with uppercase letters and the rest characters are lowercase letters.");
        Validate.isTrue(!customer.getLastName().matches("([A-Z][a-z]*){2,}"), "LastName must start with uppercase letters and the rest characters are lowercase letters.");

        return dbService.createCustomer(customer);
    }

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    /**
     * Метод проверяет email на правильность
     */
    public static boolean isValidEmail(String email)
    {
        if (email != null)
        {
            Pattern p = Pattern.compile("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$");
            Matcher m = p.matcher(email);
            return m.find();
        }
        return false;
    }


    /**
     * Метод возвращает список объектов типа customer.
     */
    public List<CustomerRepo> getCustomers() {
        return dbService.getCustomers();
    }

    public UUID getCustomerIdByLogin(String login) {
        Validate.notNull(login, "Login is null.");
        return dbService.getCustomerIdByLogin(login);
    }
    /**
     * Метод обновляет объект типа Customer.
     * Можно обновить только firstName и lastName.
     */
    public CustomerRepo updateCustomer(CustomerRepo customer) {
        Validate.notNull(customer, "Argument 'customerData' is null");


        CustomerRepo original = null;
        try {
            original = this.dbService.getCustomerById(customer.getId());
        } catch (IllegalArgumentException ignored) { }

        // Money should not be checked, because they can potentially change

        return this.dbService.updateCustomer(customer);
    }

    public void removeCustomer(UUID id) {
        throw new NotImplementedException("Please implement the method.");
    }

    /**
     * Метод добавляет к текущему баласу amount.
     * amount - должен быть строго больше нуля.
     */
    public CustomerRepo topUpBalance(UUID customerId, int amount) {
        throw new NotImplementedException("Please implement the method.");
    }
}
