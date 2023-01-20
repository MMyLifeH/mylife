package com.sdut.users.service;

import com.sdut.users.page.Page;
import com.sdut.users.pojo.Reviews;

import java.util.List;


public interface IReviewsService {

//   增加
    public void regReviews(Reviews re);
//   查询按content
    public Page<Reviews> queryReviewsInfo(String  rv_content, int pageNumber, int pageSize);
//   删除
    public int deleteById(int id);
//   更新
    public int updateById (Reviews re);
}
