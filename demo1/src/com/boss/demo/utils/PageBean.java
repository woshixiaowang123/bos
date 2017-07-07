package com.boss.demo.utils;

import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * 分页查询工具类
 * Created by 隔壁老王 on 2017/6/26.
 */
public class PageBean {

    private int total;//总信息数
    private List rows;//查出的信息的集合
    private int currentPage;//当前页码
    private int pageSize;//查询的每页的条数
    private DetachedCriteria detachedCriteria;//分页查询的条件

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public DetachedCriteria getDetachedCriteria() {
        return detachedCriteria;
    }

    public void setDetachedCriteria(DetachedCriteria detachedCriteria) {
        this.detachedCriteria = detachedCriteria;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "total=" + total +
                ", rows=" + rows +
                ", currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", detachedCriteria=" + detachedCriteria +
                '}';
    }
}
