package com.example.salarymanage.service;

import com.example.salarymanage.mapper.ReimBurseMapper;
import com.example.salarymanage.page.Page;
import com.example.salarymanage.pojo.ReimBurses;
import com.example.salarymanage.pojo.Salarys;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReimBurseServiceImpl implements IReimBurseService {
    @Autowired // 在IOC容器中寻找类型是Mapper的对象
    private ReimBurseMapper dao;

    @Override
    // 增加
    public void regReimBurse(ReimBurses reimBurses) {
        dao.addreimburse(reimBurses);
    }

    // 获取所有报销申请,按content
    @Override
    public Page<ReimBurses> getAll(String rid, int pageNumber, int pageSize) {
        // 使用MyBatis分页插件完成分页
        PageHelper.startPage(pageNumber, pageSize);
        List<ReimBurses> reviews = dao.getAll(rid);

        PageInfo<ReimBurses> info = new PageInfo<>(reviews);
        Page<ReimBurses> page = new Page<>();

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
    public int updateById(ReimBurses reimBurses) {

        return dao.updateById(reimBurses);
    }

    // 审核
    @Override
    public int updateState(Integer rid, Integer state_pass) {
        return dao.updateState(rid, state_pass);
    }

    // 图表数据
    @Override
    public List<Map<String, Object>> selecttb() {

        return dao.selecttb();
    }

}
