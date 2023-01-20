package com.example.salarymanage.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    private int id;
    private String u_name;
    private String password;
    private String sex;
    private String salary;
    private String position;
    private String department;
    private int u_state;
    private int role;
}
