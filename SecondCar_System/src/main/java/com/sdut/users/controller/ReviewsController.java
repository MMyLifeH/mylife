package com.sdut.users.controller;


import com.sdut.users.page.Page;
import com.sdut.users.pojo.Reviews;
import com.sdut.users.service.IReviewsService;
import com.sdut.users.vo.ReviewsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/reviews")
public class ReviewsController {

   @Autowired
   private IReviewsService service;

   public void add(HttpServletRequest req, HttpServletResponse response){
      //req.get
   }

//  增加
   @RequestMapping("/addReviews")
   @ResponseBody
   public Map<String, Object> addReviews(@RequestBody ReviewsVo vo) {
      service.regReviews(vo);
      Map<String, Object> map = new HashMap<>();
      map.put("flag", "添加成功");
      return map;
   }

//  查询按content
   @RequestMapping(value = "/queryReviewsInfo")
   public Page queryReviewsInfo(String rv_content, int pageNumber, int pageSize) {
      Page<Reviews> data = service.queryReviewsInfo(rv_content,pageNumber,pageSize);
      return data;
   }

// 删除
   @RequestMapping(value = "/deleteReviews")
   public String deleteById(int id) {
      int result = service.deleteById(id);
      if (result >= 1) {
         return "删除成功";
      } else {
         return "删除失败";
      }
   }
//  更新
   @RequestMapping(value = "/updateReviews", method = RequestMethod.POST)
   @ResponseBody
   public String updateById(@RequestBody  Reviews re) {
      int result = service.updateById(re);
      if (result >= 1) {
         return "修改成功";
      } else {
         return "修改失败";
      }
   }

}

