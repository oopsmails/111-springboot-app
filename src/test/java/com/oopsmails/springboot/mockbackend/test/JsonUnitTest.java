package com.oopsmails.springboot.mockbackend.test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oopsmails.springboot.mockbackend.model.Address;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class JsonUnitTest {
    @Test
    void testJason_1() {
        String jsonString = "{\"addressId\": 1,\"street\":\"123 Main St\",\"city\":\"Example City\",\"state\":\"Example State\"}";
        List<Address> result = getAddressesFromJson(jsonString);
        log.info("1: result = [{}]", result);

        assertTrue(result.size() == 1);

        String jsonArrayString = "[{\"addressId\": 1,\"street\":\"123 Main St\",\"city\":\"Example City\",\"state\":\"Example State\"}, " +
                "{\"addressId\": 2,\"street\":\"456 Elm St\",\"city\":\"Example City\",\"state\":\"Example State\"}," +
                "{\"addressId\": 3,\"street\":\"789 Oak St\",\"city\":\"Example City\",\"state\":\"Example State\"}]";
        result = getAddressesFromJson(jsonArrayString);
        log.info("2: result = [{}]", result);
        assertTrue(result.size() > 1);

        String jsonArrayStringSet = "[{\"addressId\": 1,\"street\":\"123 Main St\",\"city\":\"Example City\",\"state\":\"Example State\"}, " +
                "{\"addressId\": 2,\"street\":\"456 Elm St\",\"city\":\"Example City\",\"state\":\"Example State\"}," +
                "{\"addressId\": 1,\"street\":\"789 Oak St\",\"city\":\"Example City\",\"state\":\"Example State\"}]";
        TreeSet<Address> resultTreeSet = getAddressesFromJsonTreeSet(jsonArrayStringSet);
        log.info("3: result = [{}]", result);
        assertTrue(resultTreeSet.size() == 2);
    }

    private List<Address> getAddressesFromJson(String jsonString) {
        List<Address> result = new ArrayList<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            // Convert to single object (Address)
            Address address = objectMapper.readValue(jsonString, Address.class);
            result.add(address);
        } catch (Exception e) {
            log.info("Passed in jsonString is a List:  {}", jsonString);
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                result = objectMapper.readValue(jsonString, new TypeReference<List<Address>>() {
                });

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        log.info("List of objects (List<Address>):  [{}]", result);
        return result;
    }

    private TreeSet<Address> getAddressesFromJsonTreeSet(String jsonString) {
        TreeSet<Address> result = new TreeSet<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            // Convert to single object (Address)
            Address address = objectMapper.readValue(jsonString, Address.class);
            result.add(address);
        } catch (Exception e) {
            log.info("Passed in jsonString is a List:  {}", jsonString);
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                // !!! need Address class to implement Comparable, otherwise, Exception !!!
                result = objectMapper.readValue(jsonString, new TypeReference<TreeSet<Address>>() {
                });

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        log.info("TreeSet of objects (TreeSet<Address>):  [{}]", result);
        return result;
    }
}
