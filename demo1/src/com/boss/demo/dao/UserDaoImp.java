package com.boss.demo.dao;

import com.boss.demo.dao.base.BaseDaoImp;
import com.boss.demo.domain.UserModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户dao实现类
 * Created by 隔壁老王 on 2017/6/25.
 */
@Repository
public class UserDaoImp extends BaseDaoImp<UserModel> implements UserDao {
    @Override
    //根据用户名查询用户的方法
    public UserModel findUserByUsername(String username) {
        String hql="from UserModel where username = ?";
        List<UserModel> list = this.getHibernateTemplate().find(hql, username);
        if (list!=null&&list.size()>0){
            return list.get(0);
        }
        return null;
    }
}
