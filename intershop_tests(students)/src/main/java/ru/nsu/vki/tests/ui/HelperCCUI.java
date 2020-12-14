package ru.nsu.vki.tests.ui;

import ru.nsu.vki.shared.RandomDataGenerator;
import ru.nsu.vki.tests.data.CustomerRepo;

import java.util.UUID;

public class HelperCCUI {

    public static CustomerRepo creater(){
        String unique = UUID.randomUUID().toString().substring(0, 3);

        CustomerRepo customerRepo = new CustomerRepo();
        customerRepo.firstName = RandomDataGenerator.generateName();
        customerRepo.lastName = RandomDataGenerator.generateName();
        customerRepo.login = customerRepo.firstName + unique + "@gmail.com";
        customerRepo.pass = RandomDataGenerator.generatePassword();
        //customerRepo.balance = RandomDataGenerator.generateMoney();

        return customerRepo;
    }
}
