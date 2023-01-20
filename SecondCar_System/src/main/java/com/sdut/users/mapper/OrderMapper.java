package com.sdut.users.mapper;


import com.sdut.users.pojo.Orders;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface OrderMapper {
    public int addOrders(Orders orders);
    //查询一个对象
    public int findByordername(String name);
//    public Orders queryOrderById(int id);
    //删除一个对象
    public int deleteorder(int id);
    //修改一个对象
    public int updateorder (Orders orders);

    public List<Orders> querySomeorder(@Param("name") String name);

    //图表数据
    public List<Map<String, Object>> selecttb();

    public List<Map<String, Object>> selecttb2();
//购买
    public int buyOrders(Orders orders);
}
