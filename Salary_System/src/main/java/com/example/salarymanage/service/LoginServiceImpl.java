package com.example.salarymanage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.salarymanage.mapper.UserMapper;
import com.example.salarymanage.page.Page;
import com.example.salarymanage.pojo.Message;
import com.example.salarymanage.pojo.Users;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class LoginServiceImpl {
    @Autowired
    private UserMapper dao;

    public Message checkPassword(String userName, String userPassword) {
        Users user = dao.getUserInfo(userName);
        if (null == userPassword)
            return new Message(201, "登录失败", new Users());
        if (null == user)
            return new Message(202, "登录失败", new Users());
        if (!userPassword.equals(user.getPassword()))
            return new Message(203, "登录失败", new Users());
        return new Message(200, "登录成功", user);
    }

    public Message getUserInfo(String userId) {
        Users user = dao.getUserInfo(userId);
        if (null == user)
            return new Message(201, "获取用户信息失败", new Users());
        return new Message(200, "获取用户信息成功", user);
    }

    public void regAdmin(Users admin) {
        dao.addUser(admin);
    }

    public Page<Users> queryUsersInfo(String u_name, int pageNumber, int pageSize) {

        // 使用MyBatis分页插件完成分页
        PageHelper.startPage(pageNumber, pageSize);
        List<Users> reviews = dao.queryUsersInfo(u_name);

        PageInfo<Users> info = new PageInfo<>(reviews);
        Page<Users> page = new Page<>();

        page.setRows(reviews);
        page.setTotal((int)info.getTotal());
        page.setPageNumber(info.getPageNum());
        page.setPageSize(info.getPageSize());
        return page;
    }

    // 删除
    public int deleteById(int id) {
        return dao.deleteById(id);
    }

    // 更新
    public int updateById(Users admin) {
        return dao.updateById(admin);
    }

    // 申诉

    public int updateState(Integer id, Integer u_state) {
        return dao.updateState(id, u_state);
    }
}
