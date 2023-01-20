package com.example.salarymanage.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Salarys {
    private int sid;
    private int wage;
    private int welfare;
    private int reimburse;
    private String s_time;
    private int id;
}
