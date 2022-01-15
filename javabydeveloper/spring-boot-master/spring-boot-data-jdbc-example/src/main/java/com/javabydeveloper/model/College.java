package com.javabydeveloper.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.Embedded.OnEmpty;

import lombok.Data;

@Data
public class College {
    
    @Id
    private Long collegeId;
    
    private String collegeName;
    
    @Embedded(onEmpty = OnEmpty.USE_NULL)
    private ContactAddress address;
  
}
