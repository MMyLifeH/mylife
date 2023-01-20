package com.sdut.users.mapper;

import com.sdut.users.pojo.User;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface UserDao {
    @Select("select u_pwd from tb_user where u_name = #{u_name}")
    String getUserPassword(String userName);

    @Select("SELECT * FROM tb_user WHERE u_name = #{u_name}")
    @Results({
            @Result(property = "id", column = "u_id"),
            @Result(property = "name", column = "u_name"),
            @Result(property = "password", column = "u_pwd"),
            @Result(property = "phone", column = "u_phone"),
            @Result(property = "email", column = "u_email"),
            @Result(property = "address", column = "u_address"),
            @Result(property = "avatar", column = "u_avatar"),
            @Result(property = "role", column = "u_role"),
    })
    User getUserInfo(String userName);
}

