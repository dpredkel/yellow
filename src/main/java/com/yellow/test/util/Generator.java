package com.yellow.test.util;

import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.NoArgGenerator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Generator {
    private static final NoArgGenerator UUID_GENERATOR = Generators.timeBasedGenerator();

    public static UUID uuid() {
        return UUID_GENERATOR.generate();
    }

    public static String uuidAsString() {
        return uuid().toString();
    }

    public static String randomString() {
        return randomString(10);
    }

    /**
     * The results are not cryptographically secure!!!
     * This method is just to get test random strings.
     */
    public static String randomString(int length) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        StringBuilder buffer = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomLimitedInt = leftLimit + (int) (ThreadLocalRandom.current().nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

}