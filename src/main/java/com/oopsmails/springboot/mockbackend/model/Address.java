package com.oopsmails.springboot.mockbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address implements Comparable<Address> {
    private int addressId;
    private String street;
    private String city;
    private String state;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Address other = (Address) obj;
        return addressId == other.addressId;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(addressId);
    }

    @Override
    public int compareTo(Address other) {
        return Integer.compare(this.addressId, other.addressId);
    }
}
