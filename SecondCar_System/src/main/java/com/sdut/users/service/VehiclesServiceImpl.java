package com.sdut.users.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sdut.users.mapper.VehiclesMapper;
import com.sdut.users.page.Page;
import com.sdut.users.pojo.Vehicles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiclesServiceImpl implements IVehiclesService {
    @Autowired //在IOC容器中寻找类型是UsersMapper的对象
    private VehiclesMapper dao;

//    增加
    @Override
    public void regVehicles(Vehicles v) {
//        System.out.println("service中的。。。addUsers...");

        dao.addVehicles(v);
    }
//    删除
@Override
    public int delVehicleById(int vehicleid){
        return dao.delVehicleById(vehicleid);
    }
//更新
@Override
    public int updateVehicleById (Vehicles vehicles){
        return dao.updateVehicleById(vehicles);
    }

//  查询
@Override
   public Page<Vehicles> queryBy(String vehiclename,int pageNumber, int pageSize){

       PageHelper.startPage(pageNumber,pageSize);
       List<Vehicles> vehicle = dao.queryBy(vehiclename);

       PageInfo<Vehicles> info = new PageInfo<>(vehicle);
       Page<Vehicles> page = new Page<>();

       page.setRows(vehicle);
       page.setTotal((int)info.getTotal());
       page.setPageNumber(info.getPageNum());
       page.setPageSize(info.getPageSize());
       return page;
}

    @Override
    public List<Vehicles> querySome1(int pageNumber, int pageSize) {
        System.out.println("service...querySome......");
        //使用MyBatis分页插件完成分页
        PageHelper.startPage(pageNumber,pageSize);
        return dao.querySome1();
    }

    @Override
    public List<Vehicles> querySome2(int pageNumber, int pageSize) {
        System.out.println("service...querySome......");
        //使用MyBatis分页插件完成分页
        PageHelper.startPage(pageNumber,pageSize);
        return dao.querySome2();
    }

    @Override
    public int updateCarShoppingStatus(Integer vehicleid, Integer shoppingstate) {
        return dao.updateCarShoppingStatus(vehicleid,shoppingstate);
    }
    @Override
    public int updateCarc(Integer vehicleid, Integer collectstate) {
        return dao.updateCarc(vehicleid,collectstate);
    }
}
