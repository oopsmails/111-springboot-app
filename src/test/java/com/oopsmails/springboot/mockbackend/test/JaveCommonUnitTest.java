package com.oopsmails.springboot.mockbackend.test;

import com.oopsmails.springboot.mockbackend.model.Address;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

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

    @Test
    void testJaveCommon_2_TreeSet() {
        Comparator<Address> addressIdComparator = Comparator.comparingInt(Address::getAddressId);

        // Create a TreeSet with the custom comparator
        Set<Address> result = new TreeSet<>(addressIdComparator);

        // Add Address objects to the TreeSet
        result.add(new Address(1, "123 Main St", "Example City", "Example State"));
        result.add(new Address(2, "456 Elm St", "Example City", "Example State"));
        result.add(new Address(3, "789 Oak St", "Example City", "Example State"));

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
