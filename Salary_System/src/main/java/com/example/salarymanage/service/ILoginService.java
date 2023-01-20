package com.example.salarymanage.service;

import com.example.salarymanage.pojo.Message;
import com.example.salarymanage.pojo.Users;
import com.github.pagehelper.Page;

public interface ILoginService {
    public Message checkPassword(String userId, String userPassword);

    public Message getUserInfo(String userId);

    public Message addUser(Users admin);

    public void regAdmin(Users admin);

    public Page<Users> queryUsersInfo(String u_name, int pageNumber, int pageSize);

    public int deleteById(int id);

    // 更新
    public int updateById(Users admin);

    // 审核
    public int updateState(Integer id, Integer u_state);
}
