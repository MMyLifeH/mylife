package com.example.salarymanage.controller;

import com.example.salarymanage.page.Page;
import com.example.salarymanage.pojo.Salarys;
import com.example.salarymanage.service.SalaryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/salary")
public class SalaryController {
    @Autowired
    SalaryServiceImpl service;

    // 增加
    @RequestMapping("/addSalary")
    @ResponseBody
    public Map<String, Object> addSalary(@RequestBody Salarys vo) {
        service.regSalary(vo);
        Map<String, Object> map = new HashMap<>();
        map.put("flag", "添加成功");
        return map;
    }

    // 查询按content
    @RequestMapping(value = "/querySalaryInfo")
    public Page querySalaryInfo(String id, int pageNumber, int pageSize) {
        Page<Salarys> data = service.querySalaryInfo(id, pageNumber, pageSize);
        return data;
    }

    // 删除
    @RequestMapping(value = "/deleteSalary")
    public String deleteById(int id) {
        int result = service.deleteById(id);
        if (result >= 1) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }

    // 更新
    @RequestMapping(value = "/updateSalary", method = RequestMethod.POST)
    @ResponseBody
    public String updateById(@RequestBody Salarys re) {
        int result = service.updateById(re);
        if (result >= 1) {
            return "修改成功";
        } else {
            return "修改失败";
        }
    }

    // 图表数据
    @GetMapping(value = "/selecttb2")
    public List<Map<String, Object>> selecttb2() {
        return service.selecttb2();

    }

}
