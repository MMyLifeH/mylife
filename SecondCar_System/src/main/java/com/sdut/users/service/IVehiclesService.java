package com.sdut.users.service;

import com.sdut.users.page.Page;
import com.sdut.users.pojo.Vehicles;

import java.util.List;

public interface IVehiclesService {

    public void regVehicles(Vehicles v);
    //名字查询
   public Page<Vehicles> queryBy(String vehiclename, int pageNumber, int pageSize);

    //    <!--       根据id删除用户-->
    public int delVehicleById(int vehicleid);

    //<!--      根据id修改用户-->
    public int updateVehicleById (Vehicles vehicles);

    public List<Vehicles> querySome1(int  pageNumber, int pageSize);

    public List<Vehicles> querySome2(int  pageNumber, int pageSize);
    int updateCarShoppingStatus(Integer vehicleid, Integer shoppingstate);

    int updateCarc(Integer vehicleid, Integer collectstate);

}
