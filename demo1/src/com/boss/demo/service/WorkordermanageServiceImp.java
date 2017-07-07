package com.boss.demo.service;

import com.boss.demo.dao.WorkordermanageDao;
import com.boss.demo.domain.WorkordermanageModel;
import com.boss.demo.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 隔壁老王 on 2017/7/7.
 */
@Service
@Transactional
public class WorkordermanageServiceImp implements WorkordermanageService{
    @Autowired
    private WorkordermanageDao workordermanageDao;
    @Override
    public void save(WorkordermanageModel model) {
        workordermanageDao.save(model);
    }

    @Override
    public void pageFind(PageBean pageBean) {
        workordermanageDao.pageFind(pageBean);
    }
}
