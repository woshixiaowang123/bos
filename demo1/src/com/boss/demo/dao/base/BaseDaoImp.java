package com.boss.demo.dao.base;

import com.boss.demo.utils.PageBean;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.ResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * 通用dao实现类
 * Created by 隔壁老王 on 2017/6/25.
 */
public class BaseDaoImp<T> extends HibernateDaoSupport implements BaseDao<T> {

    //注入sessionfactory
    @Autowired
    public void setSf(SessionFactory sf){
        super.setSessionFactory(sf);
    }


    //定义一个属性,表示其类型
    public Class<T> clazz;
    //首先在此类中要用到泛型的具体类型,所以要获得其类型,所以就要获得所实现的类的泛型的类型,于是在创造本类的时候 是最佳时机
    public BaseDaoImp(){
        ParameterizedType parameterizedType= (ParameterizedType) this.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        //然后遍历数组,获得数组的第一个参数就是其类型
       clazz= (Class<T>) actualTypeArguments[0];

    }

    @Override
    public void save(Object entity) {
        this.getHibernateTemplate().save(entity);

    }

    @Override
    public void update(Object entity) {
        this.getHibernateTemplate().update(entity);

    }

    @Override
    public void delete(Object entity) {
        this.getHibernateTemplate().delete(entity);

    }

    @Override
    public T findById(Serializable id) {
        return this.getHibernateTemplate().get(clazz,id);

    }

    @Override
    public List<T> findAll() {
        String hql="from "+clazz.getSimpleName();
        List<T> list = this.getHibernateTemplate().find(hql);
        return list;
    }

    @Override
    public List<T> findByCriteria(DetachedCriteria criteria) {
        return this.getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<T> findByNamedQuery(String queryName, Object... args) {
        List<T> byNamedQuery = this.getHibernateTemplate().findByNamedQuery(queryName, args);
        return byNamedQuery;
    }

    //delete from Student where id = ? and age =?   1, 20
    @Override
    public void executeNamedQuery(String queryName, Object... args) {
        Session session=this.getSession();
        Query namedQuery = session.getNamedQuery(queryName);
        if (args !=null && args.length>0){
            int i=0;
            for (Object object:args
                 ) {
                namedQuery.setParameter(i++,object);
            }
        }

        namedQuery.executeUpdate();

    }

    @Override
    public void pageFind(PageBean pageBean) {
        //分页查询中pageBean中已经有三个属性已经被赋值,仅剩list与total总信息数是没有赋值的,所以查询信息只需要查询这两项内容即可
        //首先查询总的内容
        DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();//离线查询条件
        int first=(pageBean.getCurrentPage()-1)*pageBean.getPageSize();
        int pageTotal=pageBean.getPageSize();


        //然后查询总的条数
        detachedCriteria.setProjection(Projections.rowCount());
        List byCriteria = this.getHibernateTemplate().findByCriteria(detachedCriteria);//查询总的条数


        detachedCriteria.setProjection(null);
        detachedCriteria.setResultTransformer(DetachedCriteria.ROOT_ENTITY);

        List list=this.getHibernateTemplate().findByCriteria(detachedCriteria,first,pageTotal);

        Long aLong = (Long) byCriteria.get(0);

        int o=aLong.intValue();

        pageBean.setRows(list);

        pageBean.setTotal(o);
    }
}
