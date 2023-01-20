package com.sdut.users.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sdut.users.mapper.OrderMapper;
import com.sdut.users.page.Page;
import com.sdut.users.pojo.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements IOrdersService {
    @Autowired //在IOC容器中寻找类型是UsersMapper的对象
    private OrderMapper dao;

    @Override
    public void regUsers(Orders orders){
        dao.addOrders(orders);
    }

    @Override
    public int delOrderById(int id) {
        return dao.deleteorder(id);
    }


    @Override
    public int findByordername(String name) {
        return dao.findByordername(name);
    }

    @Override
    public int updateorder(Orders orders) {
        return dao.updateorder(orders);
    }


    @Override
    public Page<Orders> querySomeorder(String name, int pageNumber, int pageSize) {
        System.out.println("service...querySomeorder......");
        //使用MyBatis分页插件完成分页
        PageHelper.startPage(pageNumber,pageSize);
        List<Orders> orders = dao.querySomeorder(name);
        PageInfo<Orders> info = new PageInfo<>(orders);
        Page<Orders> page = new Page<>();
        page.setRows(orders);
        page.setTotal((int)info.getTotal());
        page.setPageNumber(info.getPageNum());
        page.setPageSize(info.getPageSize());
        return page;
    }
    //图表数据
    @Override
    public List<Map<String, Object>> selecttb() {

        return dao.selecttb();
    }
    @Override
    public List<Map<String, Object>> selecttb2() {
        return dao.selecttb2();
    }

    @Override
    public int buyOrder(Orders orders) {
        return dao.buyOrders(orders);
    }
}
