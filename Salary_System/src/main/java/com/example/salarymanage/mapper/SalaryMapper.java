package com.example.salarymanage.mapper;

import com.example.salarymanage.pojo.Salarys;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SalaryMapper {
    public void addSalary(Salarys sa);

    // 删除
    public int deleteById(int id);

    // 修改
    public int updateById(Salarys sa);

    // 查询按content
    public List<Salarys> querySalaryInfo(@Param("id") String id);

    // 获取所有
    public List<Salarys> querySalary();

    // 图表数据

    public List<Map<String, Object>> selecttb2();
}
