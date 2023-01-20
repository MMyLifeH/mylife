package com.example.salarymanage.service;

import com.example.salarymanage.page.Page;
import com.example.salarymanage.pojo.ReimBurses;
import com.example.salarymanage.pojo.Salarys;

import java.util.List;
import java.util.Map;

public interface IReimBurseService {
    public void regReimBurse(ReimBurses reimBurses);

    // 查询
    public Page<ReimBurses> getAll(String rid, int pageNumber, int pageSize);

    // 删除
    public int deleteById(int id);

    // 更新
    public int updateById(ReimBurses reimBurses);

    // 审核
    public int updateState(Integer rid, Integer state_pass);

    // 图表数据
    public List<Map<String, Object>> selecttb();

}
