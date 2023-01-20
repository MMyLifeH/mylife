package com.sdut.users.controller;


import com.sdut.users.service.IOrdersService;
import com.sdut.users.page.Page;
import com.sdut.users.pojo.Orders;
import com.sdut.users.vo.OrdersVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@CrossOrigin
@RestController //向Spring IOC容器注册一个bean， 类型就是UsersController  RestController=@Controller+@ResponseBody
@RequestMapping("/order") //@RequestMapping:接收请求，所有发往 /user的请求 都交给该类处理
public class OrdersController {
    //controller需要Service处理 添加业务，则注入一个
    //@Autowired:在IOC容器中寻找类型是IUsersService的对象 给service注入。
    @Autowired
    private IOrdersService service;

    public void add(HttpServletRequest req, HttpServletResponse response){
        //req.get
    }

    @RequestMapping("/addOrders")//所有发往/user/add 的请求，交给addUsers方法处理
    @ResponseBody //返回JSON数据把该方法的返回值组装成JSON返回给客户端
    public Map<String,Object> addOrders(@RequestBody OrdersVo vo){
        System.out.println("UsersController的addUsers.方法。。");
        //Controller层调用service层完成 添加
        service.regUsers(vo);
        Map<String,Object> map = new HashMap<>();
        map.put("flag","success");
        return map;
    }

    @RequestMapping(value = "/deleteorder")
    @ResponseBody //返回JSON数据把该方法的返回值组装成JSON返回给客户端
    public String deleteorder(int id) {
        int result = service.delOrderById(id);
        if (result >= 1) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }
    @RequestMapping(value = "/updateorder", method = RequestMethod.POST)
    @ResponseBody
    public int updateorder(@RequestBody Orders orders) {

        int result = service.updateorder(orders);

        if (result >0) {
            return 1;
        } else {
            return 0;
        }
    }

    @RequestMapping("/querySomeorder")
    public Page querySomeorder(String name,int pageNumber,int pageSize){

        //查询数据库
        Page<Orders> data = service.querySomeorder(name,pageNumber,pageSize);

        return data;
    }

    //图表数据
    @GetMapping(value = "/selecttb")
    public List<Map<String, Object>> selecttb() {
        return  service.selecttb();

    }
    //图表数据
    @GetMapping(value = "/selecttb2")
    public List<Map<String, Object>> selecttb2() {
        return  service.selecttb2();

    }

    @PostMapping("/buyOrders")
    public int buyOrder(@RequestBody Orders orders){
        return service.buyOrder(orders);
    }

}
