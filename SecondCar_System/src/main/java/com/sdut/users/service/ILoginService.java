package com.sdut.users.service;

import com.github.pagehelper.Page;
import com.sdut.users.pojo.Message;
import com.sdut.users.pojo.User;

public interface ILoginService {
    public Message checkPassword(String userId, String userPassword);

    public Message getUserInfo(String userId);


    public Message addUser(User admin);

    public void regAdmin(User admin);

    public Page<User> queryUsersInfo(String  u_name, int pageNumber, int pageSize);

    public int deleteById(int id);
    //   更新
    public int updateById (User admin);
}
