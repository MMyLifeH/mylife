package com.example.salarymanage.controller;

import com.example.salarymanage.page.Page;
import com.example.salarymanage.pojo.ReimBurses;
import com.example.salarymanage.pojo.Salarys;
import com.example.salarymanage.service.ReimBurseServiceImpl;
import com.example.salarymanage.service.SalaryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/reimburse")
public class ReimBurseController {
    @Autowired
    ReimBurseServiceImpl service;

    // 增加
    @RequestMapping("/addReimburse")
    @ResponseBody
    public Map<String, Object> addreimburse(@RequestBody ReimBurses vo) {
        service.regReimBurse(vo);
        Map<String, Object> map = new HashMap<>();
        map.put("flag", "添加成功");
        return map;
    }

    // 获取所有未通过报销单
    @RequestMapping(value = "/getAll")
    public Page getAll(String rid, int pageNumber, int pageSize) {
        Page<ReimBurses> data = service.getAll(rid, pageNumber, pageSize);
        return data;
    }

    // 删除
    @RequestMapping(value = "/deleteReimburse")
    public String deleteById(int id) {
        int result = service.deleteById(id);
        if (result >= 1) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }

    // 更新
    @RequestMapping(value = "/updateReimburse", method = RequestMethod.POST)
    @ResponseBody
    public String updateById(@RequestBody ReimBurses reimBurses) {
        int result = service.updateById(reimBurses);
        if (result >= 1) {
            return "修改成功";
        } else {
            return "修改失败";
        }
    }

    // 报销单通过
    @RequestMapping("/updateState")
    public Map<String, Object> updateState(Integer rid, Integer state_pass) {
        int i = service.updateState(rid, state_pass);
        Map<String, Object> map = new HashMap<>();
        if (i > 0) {
            if (state_pass == 1) {
                map.put("msg", "通过");
            } else {
                map.put("msg", "驳回");
            }
            map.put("flag", "success");
        } else {
            map.put("flag", "error");
        }
        return map;
    }

    // 图表数据
    @GetMapping(value = "/selecttb")
    public List<Map<String, Object>> selecttb() {
        return service.selecttb();

    }

}
