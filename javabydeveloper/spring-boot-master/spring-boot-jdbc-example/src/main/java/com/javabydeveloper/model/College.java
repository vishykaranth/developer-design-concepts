package com.javabydeveloper.model;

import lombok.Data;

@Data
public class College {
    
    private Long collegeId;
    private String collegeName;
    private ContactAddress address;
  
}
