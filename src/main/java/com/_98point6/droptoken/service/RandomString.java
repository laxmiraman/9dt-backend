package com._98point6.droptoken.service;

import java.util.UUID;

public class RandomString {
    public static String generateString() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replace('-', '0');
    }
}