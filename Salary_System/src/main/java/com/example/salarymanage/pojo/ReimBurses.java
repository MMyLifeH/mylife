package com.example.salarymanage.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReimBurses {
    public int rid;
    public int office;
    public int room;
    public int eat;
    public int gasoline;
    private int state_pass;
    private String r_time;
    public int sid;
}
