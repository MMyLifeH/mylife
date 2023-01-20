package com.sdut.users.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sdut.users.mapper.UserDao;
import com.sdut.users.mapper.UserMapper;
import com.sdut.users.pojo.Message;
import com.sdut.users.page.Page;
import com.sdut.users.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserMapper dao;

    public Message checkPassword(String userName, String userPassword) {
        User user = userDao.getUserInfo(userName);
        if (null == userPassword)
            return new Message(201, "登录失败", new User());
        if (null == user)
            return new Message(202, "登录失败", new User());
        if (!userPassword.equals(user.getPassword()))
            return new Message(203, "登录失败", new User());
        return new Message(200, "登录成功", user);
    }

    public Message getUserInfo(String userId) {
        User user = userDao.getUserInfo(userId);
        if (null == user)
            return new Message(201, "获取用户信息失败", new User());
        return new Message(200, "获取用户信息成功", user);
    }
    public void regAdmin(User admin) {
        dao.addUser(admin);
    }

    public Page<User> queryUsersInfo(String u_name, int pageNumber, int pageSize) {

        //使用MyBatis分页插件完成分页
        PageHelper.startPage(pageNumber,pageSize);
        List<User> reviews = dao.queryUsersInfo(u_name);

        PageInfo<User> info = new PageInfo<>(reviews);
        Page<User> page = new Page<>();

        page.setRows(reviews);
        page.setTotal((int)info.getTotal());
        page.setPageNumber(info.getPageNum());
        page.setPageSize(info.getPageSize());
        return page;
    }

    //删除
    public int deleteById(int id) {

        return dao.deleteById(id);
    }
    //   更新

    public int updateById (User admin){

        return dao.updateById(admin);
    }
}

