package com.example.salarymanage.mapper;

import com.example.salarymanage.pojo.ReimBurses;
import com.example.salarymanage.pojo.Salarys;

import java.util.List;
import java.util.Map;

public interface ReimBurseMapper {
    // 增加
    public void addreimburse(ReimBurses reimBurses);

    // 删除
    public int deleteById(int id);

    // 修改
    public int updateById(ReimBurses reimBurses);

    // 获取所有报销
    public List<ReimBurses> getAll(String rid);

    // 通过报销
    public int updateState(Integer rid, Integer state_pass);

    // 图表数据
    public List<Map<String, Object>> selecttb();

}
