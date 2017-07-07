package com.boss.demo.dao;

import com.boss.demo.dao.base.BaseDao;
import com.boss.demo.domain.UserModel;

public interface UserDao extends BaseDao<UserModel> {
    UserModel findUserByUsername(String username);
}
