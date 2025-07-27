package com.learning.shortened_url.utils;

import java.util.Objects;
import java.util.Optional;

public class EncodeUtility {

    private static final String BASE62_ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public static Optional<String> encode(Long value){
        if(Objects.isNull(value)){
            return Optional.empty();
        }
        StringBuilder sb = new StringBuilder();
        while (value > 0) {
            int remainder = (int) (value % 62);
            sb.append(BASE62_ALPHABET.charAt(remainder));
            value /= 62;
        }
        return Optional.of(sb.reverse().toString());
    }

    public static Optional<Long> decode(String encodedUrl){
        if(Objects.isNull(encodedUrl)){
            return Optional.empty();
        }
        long number = 0;
        for (char character : encodedUrl.toCharArray()) {
            number = number * 62 + BASE62_ALPHABET.indexOf(character);
        }
        return Optional.of(number);
    }
}
