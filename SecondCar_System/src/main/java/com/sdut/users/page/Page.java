package com.sdut.users.page;

import lombok.Data;

import java.util.List;

//该组件存放所有和分页相关的内容
@Data
public class Page<T> {
    private int pageNumber;         //当前页
    private int pageSize;           //每页的记录数
    private int total;              //总记录数
    private List<T> rows;           //本页的内容
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    private int pageCount;         //总页数

    private int pre;                //上一页
    private int next;               //下一页
    private int start;              //每页的起始位置

    public int getPageCount() {
        return (total+pageSize-1)/pageSize;
    }
    public int getPre() {
        return pageNumber-1;
    }
    public int getNext() {
        return pageNumber+1;
    }
    public int getStart() {
        return (pageNumber-1)*pageSize;
    }
}
