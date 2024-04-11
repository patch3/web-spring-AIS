package org.example.ais.config.security;

import java.util.Random;

public final class SecretKeys {

    public static final String REMEMBER_ME = generateRandomKey();

    public static final int TIME_REMEMBER = 86400;

    private static String generateRandomKey() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 32; i++) {
            sb.append(random.nextInt(31));
        }
        return sb.toString();
    }
}
