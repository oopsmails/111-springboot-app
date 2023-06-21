package com.oopsmails.springboot.mockbackend.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class JaveCommonUnitTest {
    @Test
    void testJaveCommon_1() {
        log.info("Test");

        String testString = "aaaba000aa";
        String result = removeConsecutiveDuplicates(testString);

        log.info("result = [{}]", result);
        assertTrue(result != null);
    }

    public static String removeConsecutiveDuplicates(String input) {
        StringBuilder output = new StringBuilder();
        char previousChar = 0;
        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            if (currentChar != previousChar) {
                output.append(currentChar);
                previousChar = currentChar;
            }
        }
        return output.toString();
    }
}
