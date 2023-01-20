package com.example.salarymanage.mapper;

import com.example.salarymanage.pojo.Users;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    @Select("select u_pwd from users where u_id = #{id}")
    String getUserPassword(String userName);

    @Select("SELECT * FROM users WHERE u_id = #{id}")
    @Results({@Result(property = "id", column = "u_id"), @Result(property = "u_name", column = "u_name"),
        @Result(property = "password", column = "u_pwd"), @Result(property = "sex", column = "u_sex"),
        @Result(property = "salary", column = "u_salary"), @Result(property = "position", column = "u_position"),
        @Result(property = "department", column = "u_depart"), @Result(property = "u_state", column = "u_state"),
        @Result(property = "role", column = "u_role"),})
    Users getUserInfo(String userName);

    public void addUser(Users user);

    public List<Users> queryUsersInfo(@Param("u_name") String u_name);

    // 获取所有
    public List<Users> queryUsers();

    // 删除
    public int deleteById(int UserId);

    // 修改
    public int updateById(Users user);

    // 申诉
    public int updateState(Integer id, Integer u_state);
}
