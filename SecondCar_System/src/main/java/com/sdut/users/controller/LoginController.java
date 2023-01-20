package com.sdut.users.controller;

import com.sdut.users.page.Page;
import com.sdut.users.pojo.Message;
import com.sdut.users.pojo.User;
import com.sdut.users.service.LoginService;
import com.sdut.users.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(value = "/login")
public class LoginController {
    @Autowired
    LoginService loginService;

    // 登录，成功返回用户信息
    @RequestMapping(value = "")
    @ResponseBody
    public Message loginByName(String userName, String userPassword) {
        return loginService.checkPassword(userName, userPassword);
    }

    // 获取用户信息
    @RequestMapping(value = "getUser")
    @ResponseBody
    public Message getUserInfo(String userId) {
        return loginService.getUserInfo(userId);
    }

    @RequestMapping(value = "/adduser")
    @ResponseBody
    public Map<String, Object> addUser(@RequestBody(required = false) UserVo vo) {
        loginService.regAdmin(vo);
        Map<String, Object> map = new HashMap<>();
        map.put("flag", "添加成功");
        return map;
    }

    //  查询按content
    @RequestMapping(value = "/queryUsersInfo")
    public Page queryUsersInfo(String name, int pageNumber, int pageSize) {
        Page<User> data = loginService.queryUsersInfo(name,pageNumber,pageSize);
        return data;
    }

    // 删除
    @RequestMapping(value = "/deleteUsers")
    public String deleteById(int id) {
        int result = loginService.deleteById(id);
        if (result >= 1) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }
    //  更新
    @RequestMapping(value = "/updateUsers", method = RequestMethod.POST)
    @ResponseBody
    public String updateById(@RequestBody(required = false)  User admin) {
        int result = loginService.updateById(admin);
        if (result >= 1) {
            return "修改成功";
        } else {
            return "修改失败";
        }
    }

}

