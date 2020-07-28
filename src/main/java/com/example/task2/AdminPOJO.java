package com.example.task2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminPOJO
{
    private Long Id;
    private String empUsername;
    private String empPassword;
    private String empDepartment;
    private String empName;
    private String empEmail;
    private Long empPhoneNumber;
    private String adminStatus;

}
