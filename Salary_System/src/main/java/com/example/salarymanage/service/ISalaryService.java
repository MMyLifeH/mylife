package com.example.salarymanage.service;

import com.example.salarymanage.page.Page;
import com.example.salarymanage.pojo.Salarys;

import java.util.List;
import java.util.Map;

public interface ISalaryService {

    // 增加
    public void regSalary(Salarys sa);

    // 查询按content
    public Page<Salarys> querySalaryInfo(String id, int pageNumber, int pageSize);

    // 删除
    public int deleteById(int id);

    // 更新
    public int updateById(Salarys sa);

    // 图表数据2
    public List<Map<String, Object>> selecttb2();

}
