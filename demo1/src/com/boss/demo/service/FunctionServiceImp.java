package com.boss.demo.service;

import com.boss.demo.dao.FunctionDao;
import com.boss.demo.domain.FunctionModel;
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
}
