package com.example.salarymanage.service;

import com.example.salarymanage.mapper.SalaryMapper;
import com.example.salarymanage.page.Page;
import com.example.salarymanage.pojo.Salarys;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SalaryServiceImpl implements ISalaryService {
    @Autowired // 在IOC容器中寻找类型是UsersMapper的对象
    private SalaryMapper dao;

    @Override
    // 增加
    public void regSalary(Salarys sa) {
        dao.addSalary(sa);
    }

    @Override
    // 查询按content
    public Page<Salarys> querySalaryInfo(String id, int pageNumber, int pageSize) {

        // 使用MyBatis分页插件完成分页
        PageHelper.startPage(pageNumber, pageSize);
        List<Salarys> reviews = dao.querySalaryInfo(id);

        PageInfo<Salarys> info = new PageInfo<>(reviews);
        Page<Salarys> page = new Page<>();

        page.setRows(reviews);
        page.setTotal((int)info.getTotal());
        page.setPageNumber(info.getPageNum());
        page.setPageSize(info.getPageSize());
        return page;
    }

    @Override
    // 删除
    public int deleteById(int id) {

        return dao.deleteById(id);
    }

    @Override
    // 更新
    public int updateById(Salarys sa) {

        return dao.updateById(sa);
    }

    // 图表数据
    @Override
    public List<Map<String, Object>> selecttb2() {
        return dao.selecttb2();
    }

}
