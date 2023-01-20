package com.sdut.users.mapper;

import com.sdut.users.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    public void addUser(User user);

    public List<User> queryUsersInfo(@Param("u_name") String u_name);
//    //    获取所有
//    public List<User> queryUsers();

    //    删除
    public int deleteById (int UserId);
    //    修改
    public int updateById(User user);
}
