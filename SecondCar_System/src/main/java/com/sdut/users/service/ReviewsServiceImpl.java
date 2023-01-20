package com.sdut.users.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sdut.users.mapper.ReviewsMapper;
import com.sdut.users.page.Page;
import com.sdut.users.pojo.Reviews;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReviewsServiceImpl implements IReviewsService {
    @Autowired //在IOC容器中寻找类型是UsersMapper的对象
    private ReviewsMapper dao;

//    增加
    @Override
    public void regReviews(Reviews re) {
        dao.addReviews(re);
    }

//    查询按content
     @Override
     public Page<Reviews> queryReviewsInfo(String rv_content, int pageNumber, int pageSize) {

          //使用MyBatis分页插件完成分页
          PageHelper.startPage(pageNumber,pageSize);
          List<Reviews> reviews = dao.queryReviewsInfo(rv_content);

          PageInfo<Reviews> info = new PageInfo<>(reviews);
          Page<Reviews> page = new Page<>();

          page.setRows(reviews);
          page.setTotal((int)info.getTotal());
          page.setPageNumber(info.getPageNum());
          page.setPageSize(info.getPageSize());
          return page;
}

//    删除
    @Override
    public int deleteById(int id) {

        return dao.deleteById(id);
    }
//   更新
    @Override
    public int updateById (Reviews re){

        return dao.updateById(re);
    }

}
