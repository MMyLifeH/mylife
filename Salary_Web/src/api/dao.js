/* 导入我们自己创建用来发送请求的axios实例对象request */
import request from '@/utils/request'

export default {

    queryReimburseInfo(pageNumber, pageSize, rid) {
        // 报销
        return request({
            url: `/reimburse/getAll?pageNumber=${pageNumber}&pageSize=${pageSize}&rid=${rid}`,
            method: 'get',
        })
    },
    // 工资
    querySalaryInfo(pageNumber, pageSize, id) {
        return request({
            url: `/salary/querySalaryInfo?pageNumber=${pageNumber}&pageSize=${pageSize}&id=${id}`,
            method: 'get',
        })
    },
    queryVehicles(pageNumber, pageSize, vehiclesname) {
        return request({
            url: `/vehicle/queryBy?pageNumber=${pageNumber}&pageSize=${pageSize}&vehiclesname=${vehiclesname}`,
            method: 'get',
        })
    },
    queryUsersInfo(pageNumber, pageSize, u_name) {
        return request({
            url: `/login/queryUsersInfo?pageNumber=${pageNumber}&pageSize=${pageSize}&u_name=${u_name}`,
            method: 'get',
        })
    },
    loginAdmin(userName, userPassword) {
        return request({
            url: `/login/?userName=${userName}&userPassword=${userPassword}`,
            method: `post`,
        })
    },
    
    userInfoApi(userId) {
        return request({
            url: `/login/getUser?userId=${userId}`,
            method: `post`,
        })
    },
    querySome1(pageNumber, pageSize) {
        return request({
            url: `/vehicle/querySome1?pageNumber=${pageNumber}&pageSize=${pageSize}`,
            method: 'get',
        })
    },
    querySome2(pageNumber, pageSize) {
        return request({
            url: `/vehicle/querySome2?pageNumber=${pageNumber}&pageSize=${pageSize}`,
            method: 'get',
        })
    },
    loginUser(name, password) {
        return request({
            url: `/login/?name=${name}&userPassword=${password}`,
            method: `post`,
        })
    },
}