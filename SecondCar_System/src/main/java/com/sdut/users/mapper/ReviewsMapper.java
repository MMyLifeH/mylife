package com.sdut.users.mapper;

import com.sdut.users.pojo.Reviews;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReviewsMapper {
//    增加
      public void addReviews(Reviews re);
//    删除
      public int deleteById (int id);
//    修改
      public int updateById(Reviews re);
//    查询按content
      public List<Reviews> queryReviewsInfo(@Param("rv_content") String rv_content);
//    获取所有
      public List<Reviews> queryReviews();

}
