package com.sdut.users.mapper;



import com.sdut.users.pojo.Vehicles;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface VehiclesMapper {
    public void addVehicles(Vehicles v);

//    名字查询
    public List<Vehicles>  queryBy(@Param("vehiclename") String vehiclename);

    //根据id删除用户
    public int delVehicleById(int vehicleid);

    //根据id修改用户
    public int updateVehicleById (Vehicles vehicles);

    public List<Vehicles> querySome1();
    public List<Vehicles> querySome2();
    int updateCarShoppingStatus(Integer vehicleid, Integer shoppingstate);
    int updateCarc(Integer vehicleid, Integer collectstate);

}
