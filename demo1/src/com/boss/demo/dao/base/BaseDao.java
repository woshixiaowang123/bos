package com.boss.demo.dao.base;

import com.boss.demo.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T> {
    //添加
    public void save(T entity);

    //修改
    public void update(T entity);

    //删除
    public void delete(T entity);

    //通过id查询
    public T findById(Serializable id);

    //查询全部
    public List<T> findAll();

    //根据条件查询
    public List<T> findByCriteria(DetachedCriteria criteria);

    /**
     * 根据命名查询语句查询
     */
    public List<T> findByNamedQuery(String queryName,Object...args);
    /**
     * 执行增删改操作的命名语句
     */
    public void executeNamedQuery(String queryName,Object ...args);


    //分页查询的通用dao类
    public  void pageFind(PageBean pageBean);



}
