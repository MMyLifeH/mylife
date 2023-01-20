package com.sdut.users.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
    private  int or_id;
    private String tradetime;
    private float ordermoney;
    private String ordernote;
    private int vehicleid;
    private float vehicleprice;




}
