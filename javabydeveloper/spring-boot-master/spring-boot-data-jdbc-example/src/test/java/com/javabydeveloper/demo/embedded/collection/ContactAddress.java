package com.javabydeveloper.demo.embedded.collection;

import org.springframework.data.relational.core.mapping.Column;

import com.javabydeveloper.util.AddressType;

import lombok.Data;

@Data
public class ContactAddress {
    
    private String street;
    private String state;
    private String city;
    @Column("ZIP_CODE")
    private String areaCode;
    @Column("ADDR_TYPE")
    private AddressType addressType;
}
