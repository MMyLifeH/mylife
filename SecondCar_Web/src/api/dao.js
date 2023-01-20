/* 导入我们自己创建用来发送请求的axios实例对象request */
import request from '@/utils/request'

export default {

    queryReviewsInfo(pageNumber, pageSize, rv_content) {
        return request({
            url: `/reviews/queryReviewsInfo?pageNumber=${pageNumber}&pageSize=${pageSize}&rv_content=${rv_content}`,
            method: 'get',
        })
    },
    queryOrders(pageNumber, pageSize, name) {
        return request({
            url: `/order/querySomeorder?pageNumber=${pageNumber}&pageSize=${pageSize}&name=${name}`,
            method: 'get',
        })
    },
    queryVehicles(pageNumber, pageSize, vehiclesname) {
        return request({
            url: `/vehicle/queryBy?pageNumber=${pageNumber}&pageSize=${pageSize}&vehiclesname=${vehiclesname}`,
            method: 'get',
        })
    },
    queryUsersInfo(pageNumber, pageSize, name) {
        return request({
            url: `/login/queryUsersInfo?pageNumber=${pageNumber}&pageSize=${pageSize}&name=${name}`,
            method: 'get',
        })
    },
    loginAdmin(userName, userPassword) {
        return request({
            url: `/login/?userName=${userName}&userPassword=${userPassword}`,
            method: `post`,
        })
    },
    tokenApi() {
        return request({
            url: `/auth/token`,
            method: `post`,
        })
    },
    tokenInfoApi(token, userId, isScan) {
        return request({
            url: `/auth/info/${token}?userId=${userId}&isScan=${isScan}`,
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