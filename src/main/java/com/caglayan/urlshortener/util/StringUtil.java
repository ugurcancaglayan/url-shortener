package com.caglayan.urlshortener.util;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class StringUtil {

    public static String generateCode() {
        StringBuilder generatedCode = new StringBuilder();
        SecureRandom random = new SecureRandom();
        var characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPRSTUVWXYZ1234567890".toCharArray();

        for (int i = 0; i < 5; i++) {
            int index = random.nextInt(characters.length);
            generatedCode.append(characters[index]);
        }

        return generatedCode.toString();
    }
}
