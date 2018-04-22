package utils;

import java.util.Random;

public class StringGenerator {

    public static String randomString (int length) {
        String SALTCHARS = "abcdefghijklmnopqrstqwxyz";
        StringBuilder salt = new StringBuilder();
        Random random = new Random();
        while (salt.length() < length) {
            int index = (int) (random.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }

}
