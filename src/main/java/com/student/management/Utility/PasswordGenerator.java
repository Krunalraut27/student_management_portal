package com.student.management.Utility;

import java.util.Random;

public class PasswordGenerator {

    public static String generatePassword() {

        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@#$";

        StringBuilder password = new StringBuilder();

        Random random = new Random();

        for (int i = 0; i < 8; i++)
        {
            int index = random.nextInt(characters.length());
            password.append(characters.charAt(index));
        }

        return password.toString();
    }

}
