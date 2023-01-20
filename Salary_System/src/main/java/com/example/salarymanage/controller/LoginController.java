package com.example.salarymanage.controller;

import com.example.salarymanage.page.Page;
import com.example.salarymanage.pojo.Message;
import com.example.salarymanage.pojo.Users;
import com.example.salarymanage.service.LoginServiceImpl;
import com.example.salarymanage.vo.UsersVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(value = "/login")
public class LoginController {
    @Autowired
    LoginServiceImpl loginServiceImpl;

    // 登录，成功返回用户信息
    @RequestMapping(value = "")
    @ResponseBody
    public Message loginByName(String userName, String userPassword) {
        return loginServiceImpl.checkPassword(userName, userPassword);
    }

    // 获取用户信息
    @RequestMapping(value = "getUser")
    @ResponseBody
    public Message getUserInfo(String userId) {
        return loginServiceImpl.getUserInfo(userId);
    }

    @RequestMapping(value = "/adduser")
    @ResponseBody
    public Map<String, Object> addUser(@RequestBody(required = false) UsersVo vo) {
        loginServiceImpl.regAdmin(vo);
        Map<String, Object> map = new HashMap<>();
        map.put("flag", "添加成功");
        return map;
    }

    // 查询按content
    @RequestMapping(value = "/queryUsersInfo")
    public Page queryUsersInfo(String u_name, int pageNumber, int pageSize) {
        Page<Users> data = loginServiceImpl.queryUsersInfo(u_name, pageNumber, pageSize);
        return data;
    }

    // 删除
    @RequestMapping(value = "/deleteUsers")
    public String deleteById(int id) {
        int result = loginServiceImpl.deleteById(id);
        if (result >= 1) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }

    // 更新
    @RequestMapping(value = "/updateUsers", method = RequestMethod.POST)
    @ResponseBody
    public String updateById(@RequestBody(required = false) Users admin) {
        int result = loginServiceImpl.updateById(admin);
        if (result >= 1) {
            return "修改成功";
        } else {
            return "修改失败";
        }
    }

    // 申诉
    @RequestMapping("/updateState")
    public Map<String, Object> updateState(Integer id, Integer u_state) {
        int i = loginServiceImpl.updateState(id, u_state);
        Map<String, Object> map = new HashMap<>();
        if (i > 0) {
            if (u_state == 0) {
                map.put("msg", "已申诉");
            } else {
                map.put("msg", "取消申诉");
            }
            map.put("flag", "success");
        } else {
            map.put("flag", "error");
        }
        return map;
    }
}
