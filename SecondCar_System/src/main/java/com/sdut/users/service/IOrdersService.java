package com.sdut.users.service;

import com.sdut.users.page.Page;
import com.sdut.users.pojo.Orders;

import java.util.List;
import java.util.Map;

public interface IOrdersService {
    public void regUsers(Orders orders);
    //    根据id删除用户
    public int delOrderById(int id);

    public int findByordername(String name);

    //    根据id修改用户
    public int updateorder (Orders orders);


    public Page<Orders> querySomeorder(String name, int  pageNumber, int pageSize);

    //图表数据
    public List<Map<String, Object>> selecttb();
    //图表数据2
    public List<Map<String, Object>> selecttb2();
//购买
    public int buyOrder(Orders orders);
}
