package com.sdut.users.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;





@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicles {
    private  int vehicleid;
    private String vehiclename;
    private String vehicleprice;
    private String vehiclecon;
    private int collectstate;
    private int shoppingstate;
    private String pic;

}
