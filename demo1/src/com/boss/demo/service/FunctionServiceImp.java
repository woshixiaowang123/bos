package com.boss.demo.service;

import com.boss.demo.dao.FunctionDao;
import com.boss.demo.domain.FunctionModel;
import com.boss.demo.domain.UserModel;
import com.boss.demo.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 隔壁老王 on 2017/7/7.
 */
@Service
@Transactional
public class FunctionServiceImp implements FunctionService {

    @Autowired
   private FunctionDao functionDao;
    @Override
    public void pageFind(PageBean pageBean) {
        functionDao.pageFind(pageBean);
    }

    @Override
    public List<FunctionModel> findAll() {
        return functionDao.findAll();
    }

    @Override
    //通过登录用户查询其对应的所有权限
    public List<FunctionModel> finMenu(UserModel loginUser) {
        List<FunctionModel> list=null;
        if ("admin".equals(loginUser.getUsername())){
            //说明登陆的是管理员
            list=functionDao.findAllMenu();

        }else {
            //说明是普通用户
            list=functionDao.findMenu(loginUser.getId());
        }
        return list;
    }
}