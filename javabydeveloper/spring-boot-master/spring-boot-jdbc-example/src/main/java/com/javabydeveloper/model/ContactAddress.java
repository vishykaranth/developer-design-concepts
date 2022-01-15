package com.javabydeveloper.model;

import lombok.Data;

@Data //lombok
public class ContactAddress {
    
    private String streetAddress;
    private String state;
    private String city;
    private String areaCode;
}
