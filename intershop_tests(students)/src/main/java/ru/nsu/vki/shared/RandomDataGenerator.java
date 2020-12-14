package ru.nsu.vki.shared;

import java.util.Random;
import java.util.regex.Pattern;


public class RandomDataGenerator
{
    private static char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};


    /* нет пробелов, длина от 2 до 12 символов включительно, начинается с заглавной буквы, остальные символы строчные, нет цифр и других символов */
    public final static int MIN_FN_LEN = 2;
    public final static int MAX_FN_LEN = 12;
    public static String generateName()
    {
        Random random = new Random();
        char firstLetter = Character.toUpperCase(alphabet[random.nextInt(alphabet.length)]);
        int nameLength = Math.abs(random.nextInt(MAX_FN_LEN - MIN_FN_LEN) + MIN_FN_LEN + 1);
        String result = "" + firstLetter;
        for (int i = 1; i < nameLength; i++)
        {
            result += alphabet[random.nextInt(alphabet.length)];
        }
        return result;
    }

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private String login;

    public static String generateLogin()
    {
        Random random = new Random();
        int nameLength = Math.abs(random.nextInt(MAX_FN_LEN - MIN_FN_LEN) + MIN_FN_LEN + 1);
        String result = "";
        for (int i = 1; i < nameLength; i++)
        {
            result += alphabet[random.nextInt(alphabet.length)];
        }
        result += '@';
        nameLength = Math.abs(random.nextInt(MAX_FN_LEN - MIN_FN_LEN) + MIN_FN_LEN + 1);
        for (int i = 1; i < nameLength; i++)
        {
            result += alphabet[random.nextInt(alphabet.length)];
        }
        return result;
    }

    /* длина от 6 до 12 символов включительно, недолжен быть простым, не должен содержать части login, firstName, lastName */
    public final static int MIN_PW_LEN = 6;
    public final static int MAX_PW_LEN = 12;
    private final static String SPEC_SYMS = "!@#$%^&*_+=-";
    public static String generatePassword()
    {
        Random random = new Random();
        String result = "";
        int nameLength = Math.abs(random.nextInt(MAX_PW_LEN - MIN_PW_LEN) + MIN_PW_LEN + 1);
        for (int i = 0; i < nameLength - 4; i++)
        {
            if (random.nextBoolean())
                result += alphabet[random.nextInt(alphabet.length)];
            else
                result += Character.toUpperCase(alphabet[random.nextInt(alphabet.length)]);
        }
        result += alphabet[random.nextInt(alphabet.length)];
        result += Character.toUpperCase(alphabet[random.nextInt(alphabet.length)]);
        result += random.nextInt(10);
        result += SPEC_SYMS.charAt(random.nextInt(SPEC_SYMS.length()));
        return result;
    }



    /* счет не может быть отрицательным */
    public static int generateMoney()
    {
        return new Random().nextInt(1000);
    }


}
