package com.boss.demo.dao;

import com.boss.demo.dao.base.BaseDaoImp;
import com.boss.demo.domain.FunctionModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * dao
 * Created by 隔壁老王 on 2017/7/7.
 */
@Repository
public class FunctionDaoImp extends BaseDaoImp<FunctionModel> implements FunctionDao {
    @Override
    //通过用户的id查询岁对应的权限,这里需要用到关联查询,即通过用户id查询所对应的角色,在通过角色查询所对应的权限
    public List<FunctionModel> findByUserId(String id) {
        /*String hql="from FunctionModel f left outer join fetch f.roles r left outer join fetch r.user u where u.id = ?";*/
        String hql = "from FunctionModel f left outer join fetch " +
                "f.roles r left outer join fetch r.users u where u.id = ?";

        return this.getHibernateTemplate().find(hql,id);
    }

    @Override
    //通过用户的id查询对应的权限
    public List<FunctionModel> findMenu(String id) {
        String hql="select  distinct f from FunctionModel f left outer join fetch " +
                "f.roles r left outer join fetch r.users u where u.id = ?" +
                " and f.generatemenu = '1' order by f.zindex desc ";
        return this.getHibernateTemplate().find(hql,id);
    }

    @Override
    //查询管理员的权限
    public List<FunctionModel> findAllMenu() {
        String hql="from FunctionModel f where  f.generatemenu = '1' order by f.zindex desc";

        return this.getHibernateTemplate().find(hql);
    }
}
