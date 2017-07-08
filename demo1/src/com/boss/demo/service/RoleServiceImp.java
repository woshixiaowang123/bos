package com.boss.demo.service;

import com.boss.demo.dao.RoleDao;
import com.boss.demo.domain.FunctionModel;
import com.boss.demo.domain.RoleModel;
import com.boss.demo.utils.PageBean;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 隔壁老王 on 2017/7/7.
 */
@Service
@Transactional
public class RoleServiceImp implements RoleService {
    @Autowired
    private RoleDao roleDao;
    @Override
    public void findPage(PageBean pageBean) {
        roleDao.pageFind(pageBean);
    }

    @Override
    public void save(RoleModel model, String functionIds) {
        roleDao.save(model);
        String[] split = functionIds.split(",");
        //遍历id的数组,然后
        for (String s : split) {
            FunctionModel functionModel=new FunctionModel();
            functionModel.setId(s);
           model.getFunctions().add(functionModel);
        }
    }
}
