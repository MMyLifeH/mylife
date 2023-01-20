package com.sdut.users.controller;

import com.github.pagehelper.PageInfo;
import com.sdut.users.page.Page;
import com.sdut.users.pojo.Vehicles;
import com.sdut.users.service.IVehiclesService;
import com.sdut.users.vo.VehiclesVo;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.sql.Timestamp;
import java.util.*;

@CrossOrigin
@RestController //向Spring IOC容器注册一个bean， 类型就是UsersController  RestController=@Controller+@ResponseBody
@RequestMapping("/vehicle") //@RequestMapping:接收请求，所有发往 /user的请求 都交给该类处理
public class VehiclesController {
    //controller需要Service处理 添加业务，则注入一个
    //@Autowired:在IOC容器中寻找类型是IUsersService的对象 给service注入。
    @Autowired
    private IVehiclesService service;

    public void add(HttpServletRequest req, HttpServletResponse response){
        //req.get
    }

    @RequestMapping("/add")//所有发往/user/add 的请求，交给addUsers方法处理
    @ResponseBody //返回JSON数据把该方法的返回值组装成JSON返回给客户端
    public Map<String,Object> addVehicles(@RequestBody VehiclesVo vo){
//        System.out.println("UsersController的addUsers.方法。。");
        //Controller层调用service层完成 添加
        service.regVehicles(vo);
        Map<String,Object> map = new HashMap<>();
        map.put("flag","success");
        return map;
    }

    // 删除
    @RequestMapping(value = "/delete")
    public String delVehicleById(int vehicleid) {
        int result = service.delVehicleById(vehicleid);
        if (result >= 1) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }
    //  更新
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public String  updateVehicleById(@RequestBody Vehicles vehicles) {
        int result = service.updateVehicleById(vehicles);
        if (result >= 1) {
            return "修改成功";
        } else {
            return "修改失败";
        }
    }


// 查询
    @RequestMapping(value = "/queryBy")
    public Page queryBy(String vehiclesname,int pageNumber, int pageSize) {
        Page<Vehicles> data = service.queryBy(vehiclesname,pageNumber,pageSize);
        return data;
    }


    @RequestMapping("/querySome1")
    public Page querySome1(int pageNumber,int pageSize){
        Page<Vehicles> page = new Page<>();
        //1.pageNumber;
        page.setPageNumber(pageNumber);
        //2.pageSize;
        page.setPageSize(pageSize);
        //查询数据库
        List<Vehicles> data = service.querySome1(pageNumber,pageSize);
        PageInfo<Vehicles> info = new PageInfo<>(data);
        //3.total
        int total = ((Long)info.getTotal()).intValue();
        page.setTotal(total);
        //4.rows
        page.setRows(info.getList());
        return page;
    }
    @RequestMapping("/querySome2")
    public Page querySome2(int pageNumber,int pageSize){
        Page<Vehicles> page = new Page<>();
        //1.pageNumber;
        page.setPageNumber(pageNumber);
        //2.pageSize;
        page.setPageSize(pageSize);
        //查询数据库
        List<Vehicles> data = service.querySome2(pageNumber,pageSize);
        PageInfo<Vehicles> info = new PageInfo<>(data);
        //3.total
        int total = ((Long)info.getTotal()).intValue();
        page.setTotal(total);
        //4.rows
        page.setRows(info.getList());
        return page;
    }
//    图片添加
@RequestMapping(value="/addpic",method= RequestMethod.POST)
public Map addpic(MultipartFile pic){
    System.out.println(pic);
    //1.获得文件的本来的名字
    String filename = pic.getOriginalFilename();
    //2.获得文件后缀==》UUID===》改名
    String fext = FilenameUtils.getExtension(filename);
    //新名字==why: 避免命名的冲突
    String newName = UUID.randomUUID().toString().replaceAll("-","")+"."+fext;
    //3.保存到指定目录
    String path="c:/upload/";
    File target = new File(path,newName);
    try {
        pic.transferTo(target);
    }catch(Exception e){
        e.printStackTrace();
    }
    //4.把文件地址返回给前端
    Map<String,String> result = new HashMap<>();
    result.put("savepath","/pics/"+newName);
    result.put("realname",filename);
    result.put("uploadtime",new Timestamp(System.currentTimeMillis())+"");
    return result;
}

    @RequestMapping("/updateCarShoppingStatus")
    public Map<String,Object> updateCarShoppingStatus(Integer vehicleid,Integer shoppingstate){
        int i = service.updateCarShoppingStatus(vehicleid,shoppingstate);
        Map<String,Object> map = new HashMap<>();
        if(i > 0){
            if(shoppingstate == 1){
                map.put("msg","成功加入购物车");
            }else {
                map.put("msg","取消购物车");
            }
            map.put("flag","success");
        }else {
            map.put("flag","error");
        }
        return  map;
    }

    @RequestMapping("/updateCarc")
    public Map<String,Object> updateCarc(Integer vehicleid,Integer  collectstate){
        int i = service.updateCarc(vehicleid, collectstate);
        Map<String,Object> map = new HashMap<>();
        if(i > 0){
            if( collectstate == 1){
                map.put("msg","成功加入收藏夹");
            }else {
                map.put("msg","取消收藏");
            }
            map.put("flag","success");
        }else {
            map.put("flag","error");
        }
        return  map;
    }
}
