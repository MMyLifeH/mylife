package com.sdut.users.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Auth {
    private String authToken;
    private Timestamp authTime;
    private String authIp;
    private String authAddress;
    private Integer authState;
    private long userId;

}
