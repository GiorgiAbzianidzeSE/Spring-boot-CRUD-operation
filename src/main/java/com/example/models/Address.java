package com.example.models;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@ToString
@EqualsAndHashCode
public class Address {
    
    private String streetName;

    private Integer streetNumber;

    private Integer zipCode;

    private Boolean isApartment;

    public Address(final String streetName , final Integer streetNumber, 
    final Integer zipCode , final Boolean isApartment){
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.zipCode = zipCode;
        this.isApartment = isApartment;
    }

    public Address(){

    }
}
