package com.javabydeveloper.model;

import org.springframework.data.relational.core.mapping.Column;

import lombok.Data;

@Data //lombok
public class ContactAddress {
    
    @Column("STREET")
    private String streetAddress;
    private String state;
    private String city;
    @Column("ZIP_CODE")
    private String areaCode;
}
