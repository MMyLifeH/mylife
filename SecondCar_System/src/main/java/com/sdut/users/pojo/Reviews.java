package com.sdut.users.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reviews {

    private int  rv_id;
    private  String rv_content;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private  Timestamp re_date;
    
}
